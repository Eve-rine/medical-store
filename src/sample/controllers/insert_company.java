package sample.controllers;

import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class insert_company {
    public TextField CompanyName;
    public TextField CompanyContact;
    public TextField CompanyCountry;
    public TextField CompanyEmail;
    public TextField CompanyAddress;
    public TextField mail;
    validator validate= new validator();


    public void button_insert(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to loan ");
        alert.setTitle("Loan");
        alert.setHeaderText(null);
        String sql = "INSERT INTO companies(CompanyName,CompanyCountry,CompanyEmail,CompanyContact,CompanyAddress) VALUES('"+CompanyName.getText()+"','"+CompanyCountry.getText()+"','"+CompanyEmail.getText()+"','"+CompanyContact.getText()+"','"+CompanyAddress.getText()+"')";
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
CompanyName.clear();
        CompanyCountry.clear();
CompanyEmail.clear();
    CompanyAddress.clear();
    CompanyContact.clear();
        Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
        updateAlert.setContentText("New Company added successfully");
        updateAlert.showAndWait();
    }


    public void button_back(ActionEvent actionEvent) {
        Parent change = null;
        try {
            change = FXMLLoader.load(getClass().getResource("../fxml/companies.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene change_scene = new Scene(change);
        Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage_app.setScene(change_scene);
        stage_app.centerOnScreen();
        stage_app.show();
    }
//    public void emailCheck(KeyEvent keyEvent) {
//        String emailEntered = CompanyEmail.getText();
//        validator valid = new validator();
//        if( valid.emailValidate(emailEntered));
//        else mail.setText("Invalid email");
//    }
}
