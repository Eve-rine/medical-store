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

public class companies implements Initializable {
    @FXML
    TableColumn<companies,String> CompanyName;
    @FXML
    private TableColumn<companies,String> CompanyCountry;
    @FXML
    private TableColumn <companies,String>CompanyEmail;
    @FXML
    private TableColumn <companies,String>CompanyContact;
    @FXML
    private TableColumn <companies,String>Address;

    @FXML
    private TableView<companytable> tableView;

    public void insert_company(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/insert_company.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();


    }


    public void update_company(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/update_company.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();

    }

    public void Button_Back(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/medicmain.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<companytable> observableList = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select CompanyName,CompanyCountry,CompanyEmail,CompanyContact,CompanyAddress from companies ");

            while (resultSet.next()) {
                observableList.add(new companytable(resultSet.getString("CompanyName"), resultSet.getString("CompanyCountry"), resultSet.getString("CompanyEmail")
                        , resultSet.getString("CompanyContact"), resultSet.getString("CompanyAddress")));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        CompanyCountry.setCellValueFactory(new PropertyValueFactory<>("CompanyCountry"));
        CompanyEmail.setCellValueFactory(new PropertyValueFactory<>("CompanyEmail"));
        CompanyContact.setCellValueFactory(new PropertyValueFactory<>("CompanyContact"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tableView.setItems(observableList);
    }

    public void CompanyDelete(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/company_delete.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();

    }
}


