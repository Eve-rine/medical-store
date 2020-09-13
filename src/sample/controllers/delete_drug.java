package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class delete_drug {
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

    @FXML
    private ComboBox<String> Product_Name;
    @FXML
    private void initialize(){
        Product_Name.setItems(FXCollections.observableArrayList(getData()));
    }

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

    public void DrugDelete(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete This Drug");
        alert.setTitle("Delete");
        alert.setHeaderText(null);

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            String query = "delete from medicines where ProductName='" + Product_Name.getValue() + "' ";
            PreparedStatement prepare = null;
            try {
                prepare = connection.prepareStatement(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // prepare.setString(1, lable_id.getText());
            try {
                prepare.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ObservableList<medictable> observableList = FXCollections.observableArrayList();


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
        }
    }

    public void back_button(ActionEvent actionEvent) {
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
