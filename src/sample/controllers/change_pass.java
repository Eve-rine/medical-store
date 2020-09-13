package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
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
import sample.Main;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class change_pass {

    public TextField current_pass;
    public TextField new_pass;
    public TextField repeat_pass;
    public ComboBox<String> user_name;
    @FXML
    private void initialize(){
        user_name.setItems(FXCollections.observableArrayList(getData()));
    }
    private List<String> getData(){

        List<String>options=new ArrayList<>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String query= "Select username from authenticate";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet set =statement.executeQuery();
            while (set.next()){
                options.add(set.getString("username"));
            }
            statement.close();
            set.close();
            //Return the list
            return options;
        }catch (SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }
    public void change_pass(ActionEvent actionEvent) {
String current=current_pass.getText();
String pass1=new_pass.getText();
        String pass2=repeat_pass.getText();

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        //addCart();
        String sql = "SELECT pass FROM authenticate WHERE username = '" + user_name.getValue() + "';";
        try {
            Statement st = connection.createStatement();
            ResultSet query = st.executeQuery(sql);
          //  System.out.println("query");
            // System.out.println("match");
            if (query.next() && current.equals(query.getString("pass") )){


                  //  if (current_pass.getText()==(query.getString("pass"))) {

                            String sql1 = "Update authenticate set pass='" + new_pass.getText() + "' , pass2='" + repeat_pass.getText() + "'where username='" + user_name.getValue() + "'";
                            while (pass1.equals(pass2)) {
                                PreparedStatement pst = connection.prepareStatement(sql1);
                                pst.executeUpdate();

                                Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
                                //errorAlert.setHeaderText("Error");
                                updateAlert.setContentText("Password updated successfully");
                                updateAlert.showAndWait();
                            }
                    }

                    else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        //errorAlert.setHeaderText("Error");
                        errorAlert.setContentText("The Password Entered does not Match");
                        errorAlert.showAndWait();
                    }


        } catch (SQLException e){e.printStackTrace();}


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
        stage_app.setResizable(false);
        stage_app.setMaximized(true);
        stage_app.centerOnScreen();
        stage_app.show();
    }
}
