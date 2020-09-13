package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class update_medic implements Initializable {
    public ComboBox<String> Product_Name;
    public TextField price_update;
    public TextField quantity_update;

    @FXML
    private TableView<medictable> tableView;
    @FXML
    private TableColumn<medictable, String> productId;
    @FXML
    private TableColumn<medictable, String> ProductName;
    @FXML
    private TableColumn<medictable, String> CompanyName;
    @FXML
    private TableColumn<medictable, String> ProductCategory;
    @FXML
    private TableColumn<medictable, String> Quantity;
    @FXML
    private TableColumn<medictable, String> UnitPrice;
    public BorderPane borderpane;

   @FXML


    private List<String> getData() {

        List<String> options = new ArrayList<>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String query = "Select ProductName from medicines";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                options.add(set.getString("ProductName"));
            }
            statement.close();
            set.close();
            //Return the list
            return options;
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       combomenu();
       // table();

}

  private List<String> combomenu() {
            Product_Name.setItems(FXCollections.observableArrayList(getData()));

        List<String> options = new ArrayList<>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String query = "Select ProductName from medicines";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                options.add(set.getString("ProductName"));
            }
            statement.close();
            set.close();
            //Return the list
            return options;
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void Product_Name(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<medictable> observableList = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select productId, ProductName,CompanyName,ProductCategory,Quantity,UnitPrice from medicines where ProductName='"+Product_Name.getValue()+"' ");

            while (resultSet.next()) {
                observableList.add(new medictable(resultSet.getString("productId"), resultSet.getString("ProductName"), resultSet.getString("CompanyName")
                        , resultSet.getString("ProductCategory"), resultSet.getString("Quantity"), resultSet.getString("UnitPrice")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        ProductCategory.setCellValueFactory(new PropertyValueFactory<>("ProductCategory"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        tableView.setItems(observableList);
    }

    public void Update_Price(ActionEvent actionEvent) {
        Connection connection=null;
        try{

            ConnectionClass connectionClass = new ConnectionClass();
            connection = connectionClass.getConnection();
            String sql = "Update medicines set UnitPrice='"+price_update.getText()+"' where ProductName='" +Product_Name.getValue()+"'";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Data update successfully");
        } catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        ObservableList<medictable> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select productId, ProductName,CompanyName,ProductCategory,Quantity,UnitPrice from medicines where ProductName='"+Product_Name.getValue()+"' ");

            while (resultSet.next()) {
                observableList.add(new medictable(resultSet.getString("productId"), resultSet.getString("ProductName"), resultSet.getString("CompanyName")
                        , resultSet.getString("ProductCategory"), resultSet.getString("Quantity"), resultSet.getString("UnitPrice")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        ProductCategory.setCellValueFactory(new PropertyValueFactory<>("ProductCategory"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        tableView.setItems(observableList);
    }

    public void Update_Quantity(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

//               // Connection connection=null;
        try{

           // ConnectionClass connectionClass = new ConnectionClass();
            connection = connectionClass.getConnection();
            String sql1 = "Update medicines set Quantity=Quantity+'"+quantity_update.getText()+"' where ProductName='" +Product_Name.getValue()+"'";

            PreparedStatement pst = connection.prepareStatement(sql1);
            pst.executeUpdate();
            System.out.println("Data update successfully");
        } catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


       // ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        ObservableList<medictable> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select productId, ProductName,CompanyName,ProductCategory,Quantity,UnitPrice from medicines where ProductName='"+Product_Name.getValue()+"' ");

            while (resultSet.next()) {
                observableList.add(new medictable(resultSet.getString("productId"), resultSet.getString("ProductName"), resultSet.getString("CompanyName")
                        , resultSet.getString("ProductCategory"), resultSet.getString("Quantity"), resultSet.getString("UnitPrice")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        ProductCategory.setCellValueFactory(new PropertyValueFactory<>("ProductCategory"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        tableView.setItems(observableList);

        LocalDateTime timeNow = LocalDateTime.now();
        String sql = "";
        Statement statement = null;
        try {
            Statement st = connection.createStatement();
            ResultSet query1 = st.executeQuery("SELECT * FROM medicines WHERE ProductName = '" + Product_Name.getValue() + "';");

            PreparedStatement statement1 = null;

            try {
                statement = connection.prepareStatement(String.valueOf(query1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("query");
            if (query1.next()) {
                System.out.println("match");
                sql = "INSERT INTO orders (ProductName,CompanyName,Quantity,Date) VALUES ('" + query1.getString("ProductName") + "','" + query1.getString("CompanyName") + "','" + quantity_update.getText() + "', '" + timeNow + "')";
            }
        } catch (SQLException e) {
        }


        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        quantity_update.clear();
        Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
        updateAlert.setContentText("Stock added successflly");
        updateAlert.showAndWait();
    }


    public void BACK(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/drugs.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.centerOnScreen();
        stage_app.show();
    }
}