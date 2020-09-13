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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class insert_medic implements Initializable {
    public TextField product_name;
    public TextField product_quantity;
    public TextField product_price;
    //public ComboBox company_name;
   public ComboBox<String> product_category;
   // public ComboBox<String>company_name;
   @FXML
   public ComboBox <String>company_name;




    @Override
public void initialize(URL url, ResourceBundle rb) {
company_name.setItems(FXCollections.observableArrayList(getData()));

//
        product_category.getItems().clear();
        product_category.getItems().addAll(
                "syrup", "tablet","powder","spray"
        );}

    private List<String>getData() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        List<String> options = new ArrayList<>();
        try {
            String query = "select CompanyName from Companies";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet set = pst.executeQuery();
            while (set.next()) {
                options.add(set.getString("CompanyName"));
            }
            pst.close();
            set.close();
            return options;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }
    public void button_insert(ActionEvent actionEvent) {


        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            String query = "select ProductName from medicines where ProductName='" + product_name.getText()+"'";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
                Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
                updateAlert.setContentText("Drug already exists");
                updateAlert.showAndWait();
                pst.close();
                set.close();
                product_name.clear();
                product_price.clear();
                product_quantity.clear();
            }

            else{
                String sql = "INSERT INTO medicines(ProductName,CompanyName,ProductCategory,Quantity,UnitPrice) VALUES('" + product_name.getText() + "','" + company_name.getValue() + "','" + product_category.getValue() + "','" + product_quantity.getText() + "','" + product_price.getText() + "')";

                Statement statement = null;
                try {
                    statement = connection.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    statement.executeUpdate(sql);
                } catch (SQLException e) {

                }

                product_name.clear();
                product_price.clear();
                product_quantity.clear();
                Alert updateAlert = new Alert(Alert.AlertType.INFORMATION);
                updateAlert.setContentText("New drug added successflly");
                updateAlert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void Button_Back(ActionEvent actionEvent) {
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
