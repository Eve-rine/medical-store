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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class create_user {
    public TextField user;
    public TextField pass1;
    public TextField pass2;
    public TextField email;
    public TextField contact;
    public TextField address;
    public ComboBox <String>user_type;
    validator emailvalidate= new validator();
    @FXML
    private void initialize(){
        user_type.setItems(FXCollections.observableArrayList(getData()));
    }
    @FXML
    private List<String> getData() {
        String data[]={"Manager","Pharmacist"};

        ObservableList<String> options= FXCollections.observableArrayList(data);

        // List<String> options = new ArrayList<>();
        user_type.setItems(options);
        user_type.getItems().addAll();
        return options;
    }

    public void Create_account(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
String password=pass1.getText();
String password1=pass2.getText();
        String sql = "INSERT INTO authenticate(username,pass,pass2,user_type,email,contact,address) VALUES('" + user.getText() + "','" + pass1.getText() + "','" + pass2.getText() + "','" + user_type.getValue() + "','" + email.getText() + "','" + contact.getText() + "','" + address.getText() + "')";

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(password.equals(password1) ){
          //  (emailvalidate.validate(email.getText())){
                statement.executeUpdate(sql);
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                //errorAlert.setHeaderText("Error");
                errorAlert.setContentText("New staff added successfully");
                errorAlert.showAndWait();
                user.clear();
                user_type.setPlaceholder(null);
                pass1.clear();
                pass2.clear();
                email.clear();
                contact.clear();
                address.clear();

            }else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                //errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Password does not match");
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void back_button(ActionEvent actionEvent) {
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
}
