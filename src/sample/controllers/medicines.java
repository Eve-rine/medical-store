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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class medicines implements Initializable {
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
    public Button drug_add;
    public void medicy(){
        drug_add.setDisable(true);
    }

    public void back(ActionEvent actionEvent) {
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

    public void insert_medic(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/insert_drug.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.centerOnScreen();
        stage_app.show();
    }

    public void update_medic(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/update_medic.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.centerOnScreen();
        stage_app.show();
    }

    public void Delete_drug(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/delete_drug.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.centerOnScreen();
        stage_app.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<medictable> observableList = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select productId, ProductName,CompanyName,ProductCategory,Quantity,UnitPrice from medicines ");

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
