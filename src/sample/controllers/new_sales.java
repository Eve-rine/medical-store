package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class new_sales implements Initializable {
    public ComboBox <String>Product_Name;
    public TextField Quantity1;
    public Button back1;
    public Button add_cart;
    @FXML
    private TableView<billtable> table;
    @FXML
    private TableColumn<billtable,String> bill;
    @FXML
    private TableColumn <billtable,String>Sale_quantity;
    @FXML
    private TableColumn <billtable,String>Unit_Price;
    @FXML
    public TableColumn <billtable,String>total;
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

    int tot = 0;
    ObservableList<billtable> observableList1 = FXCollections.observableArrayList();
    //observableList1.addAll(new billtable("Total", "", "", "123s"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combomenu();
    }

    public void Add_to_Cart(ActionEvent actionEvent) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<medictable> observableList = FXCollections.observableArrayList();
        int quanty = Integer.parseInt(Quantity1.getText());

        String query = "Select Quantity from medicines where ProductName = '" + Product_Name.getValue() + "'";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet set = null;
        try {
            set = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (set.next()) {
                int quo = set.getInt("Quantity");
                if (quanty > (quo)) {


                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    //errorAlert.setHeaderText("Error");
                    errorAlert.setContentText("Cannot sell beyond stock");
                    errorAlert.showAndWait();
                }
               // Quantity1.clear();
             //   add_cart.setDisable(true);

            } else {
                try {

                    connectionClass = new ConnectionClass();
                    connection = connectionClass.getConnection();
                    String sql = "update medicines set Quantity=Quantity-'" + Quantity1.getText() + "' where ProductName = '" + Product_Name.getValue() + "'";

                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.executeUpdate();
                    System.out.println("Data update successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select productId, ProductName,CompanyName,ProductCategory,Quantity,UnitPrice from medicines where ProductName='" + Product_Name.getValue() + "' ");

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
        LocalDate dateToday = LocalDate.now();
        LocalDateTime timeNow = LocalDateTime.now();


        String sql = "";
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
                sql = "INSERT INTO sales (ProductName,CompanyName,UnitPrice,DateOfSale,Quantity,Total) VALUES ('" + query1.getString("ProductName") + "','" + query1.getString("CompanyName") + "','" + query1.getString("UnitPrice") + "','" + timeNow + "','" + Quantity1.getText() + "','" + (query1.getInt("UnitPrice")) * (Integer.parseInt(Quantity1.getText())) + "' )";
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

        int i = observableList1.size();
        if (i > 1) {
            observableList1.remove(i - 1);
        }
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select ProductName,Quantity,UnitPrice,Total from sales where DateOfSale='" + timeNow + "' ");

            while (resultSet.next()) {
                observableList1.addAll(new billtable(resultSet.getString("ProductName"), resultSet.getString("Quantity"), resultSet.getString("UnitPrice"), resultSet.getString("Total")));
                tot = tot + Integer.parseInt(resultSet.getString("Total"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //observableList1.addAll(new billtable("Total", "", "", tot+""));

        bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
        Sale_quantity.setCellValueFactory(new PropertyValueFactory<>("Sale_quantity"));
        Unit_Price.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        //observableList1.lastIndexOf();
        table.setItems(observableList1);
        observableList1.addAll(new billtable("Total", "", "", tot+""));
        Quantity1.clear();

    }

    public void ProductName(ActionEvent actionEvent) {
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



    
    public void Print(ActionEvent actionEvent) {
        MyPrinter print = new MyPrinter();
        print.Print(table);


    }

    public void Button_back(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/medicmain.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.setResizable(false);
        stage_app.setMaximized(true);
        stage_app.centerOnScreen();
        stage_app.show();
    }

}


