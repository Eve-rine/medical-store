package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static impl.org.controlsfx.ImplUtils.getChildren;
import static sample.controllers.medicmain.*;

public class login  {


    public PasswordField pass;
    public TextField username;
    public TextField isConnected;
    public ComboBox<String> user_type;
//    String user =user_type.getValue();
//   String user1="Manager";
//   String user2="Pharmacist";

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    @FXML
    private void initialize(){
        user_type.setItems(FXCollections.observableArrayList(getData()));
    }
    @FXML
    private List<String> getData() {
        String data[]={"Manager","Pharmacist"};

        ObservableList<String>options= FXCollections.observableArrayList(data);

       // List<String> options = new ArrayList<>();
        user_type.setItems(options);
        user_type.getItems().addAll();
        return options;
    }

    public void sign_in(ActionEvent actionEvent) {
//        check();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT username,pass,user_type FROM authenticate WHERE username ='" + username.getText() + "' AND user_type='"+user_type.getValue()+"' AND pass ='" + pass.getText() + "';";
           // String sql1= "SELECT user_type FROM authenticate WHERE username ='" + username.getText() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
           // ResultSet rs = statement.executeQuery(sql1);

            if (resultSet.next() ) {
                String user=user_type.getValue();
                String user1="Manager";
                String user2="Pharmacist";

                FXMLLoader change = new  FXMLLoader(getClass().getResource("../fxml/medicmain.fxml"));
                Parent root = null;
                try {
                    root = change.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (user.equals(user2)) {
                    medicmain c = change.getController();
                    c.user_create.setDisable(true);
                    c.Drugs.setDisable(true);
                    c.Companies.setDisable(true);
                    c.ViewSales.setDisable(true);
                    c.receive.setDisable(true);
                }
                Scene change_scene = new Scene(root);
                Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage_app.setScene(change_scene);
                stage_app.setResizable(false);
                stage_app.setMaximized(true);
                stage_app.show();
                stage_app.setOnCloseRequest(event1 -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.getDialogPane().getScene().getWindow();

                    alert.setTitle("Close DrugStore");
                    alert.setContentText("Do you want to exit");
                    alert.showAndWait().filter(e->e!= ButtonType.OK).ifPresent(e->event1.consume());});



            }

            else {
//                isConnected.setText("Wrong Username or password");
//                isConnected.setStyle("-fx-text-fill:red;");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                //errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Wrong Log In credentials");
                errorAlert.showAndWait();

        }



    } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}