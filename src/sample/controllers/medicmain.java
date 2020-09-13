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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class medicmain implements Initializable {
    public  Button user_create;
    public AnchorPane rootpane;
    public Button Drugs;
    public Button Companies;
    public Button ViewSales;
    public Button NewSales;
    public BorderPane borderpane;
    public TextField search_field;
    public TextField ProductID;
    public TextField ProductName;
    public TextField CompanyName;
    public TextField ProductCategory;
    public TextField Quantity;
    public TextField UnitPrice;
    public AnchorPane search_anchor;
    public Button receive;

    public void medicines(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/drugs.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();
        stage_app.centerOnScreen();



    }


    public void companies(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/companies.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();
        stage_app.centerOnScreen();


    }


    public void New_Sales(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/new_sales.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.show();
        stage_app.centerOnScreen();
        stage_app.setMaximized(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            borderpane.setCenter(FXMLLoader.load(getClass().getResource("../fxml/drug_records.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void change_pass(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/change_password.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.centerOnScreen();
        stage_app.setMaximized(true);
        stage_app.show();

    }

    public void create_user(ActionEvent actionEvent) {
        loadUi("create_user");
    }
    public void loadUi(String ui) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/"+ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.getCenter();
        borderpane.setCenter(root);
    }
    public void ViewSales(ActionEvent actionEvent) {
        loadUi("sales_record");


    }

    public void drug_search(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM medicines WHERE ProductName ='" + search_field.getText() + "'";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet.next()) {

                try {

                    String productId= resultSet.getString("ProductID");
                    String productName = resultSet.getString("ProductName");
                    String companyName =resultSet.getString("CompanyName");
                    String productCategory = resultSet.getString("ProductCategory");
                    String quantity =resultSet.getString("Quantity");
                    String unitPrice = resultSet.getString("UnitPrice");

                    ProductID.setText(productId);
                    ProductName.setText(productName);
                    CompanyName.setText(companyName);
                    ProductCategory.setText(productCategory);
                    Quantity.setText(quantity);
                    UnitPrice.setText(unitPrice);
                    borderpane.setCenter(search_anchor);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                //errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Check the correct spelling of the drud");
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close(BorderPane borderPane){
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();

    }
    public void button_logout(ActionEvent actionEvent) throws Exception {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to log out?");
            alert.setTitle("Log out");
            alert.setHeaderText(null);

            Optional<ButtonType> answer =   alert.showAndWait();
            if(answer.get()==ButtonType.OK){
                Parent change = FXMLLoader.load(getClass().getResource("../fxml/login.fxml"));
                Scene change_scene = new Scene(change);
                Stage stage_app = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
                stage_app.setScene(change_scene);
                //change_scene.getStylesheets().add("ullaf.css");
                //stage_app.getIcons().add(image1);
                stage_app.show();
                close(borderpane);
            }

    }

    public void received(ActionEvent actionEvent) {
        loadUi("received_drugs");

    }
}
