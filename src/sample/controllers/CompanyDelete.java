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

public class CompanyDelete {
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

    @FXML
    private ComboBox<String> company_name;
    @FXML
    private void initialize(){
        company_name.setItems(FXCollections.observableArrayList(getData()));
    }
    private List<String>getData(){

        List<String>options=new ArrayList<>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String query= "Select CompanyName from Companies";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet set =statement.executeQuery();
            while (set.next()){
                options.add(set.getString("CompanyName"));
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
    public void Company_name(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<companytable> observableList = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select CompanyName,CompanyCountry,CompanyEmail,CompanyContact,CompanyAddress from companies where CompanyName='"+company_name.getValue()+"' ");

            while (resultSet.next()) {
                observableList.add(new companytable(resultSet.getString("CompanyName"), resultSet.getString("CompanyCountry"), resultSet.getString("CompanyEmail")
                        , resultSet.getString("CompanyContact"), resultSet.getString("CompanyAddress")));
            }

        } catch (SQLException e) {
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

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete This Company");
            alert.setTitle("Delete");
            alert.setHeaderText(null);

            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.OK) {
                String query = "delete from companies where CompanyName='"+company_name.getValue()+"' ";
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
                ObservableList<companytable> observableList = FXCollections.observableArrayList();
                try {
                    ResultSet resultSet = connection.createStatement().executeQuery("select CompanyName,CompanyCountry,CompanyEmail,CompanyContact,CompanyAddress from companies where CompanyName='"+company_name.getValue()+"' ");

                    while (resultSet.next()) {
                        observableList.add(new companytable(resultSet.getString("CompanyName"), resultSet.getString("CompanyCountry"), resultSet.getString("CompanyEmail")
                                , resultSet.getString("CompanyContact"), resultSet.getString("CompanyAddress")));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
                CompanyCountry.setCellValueFactory(new PropertyValueFactory<>("CompanyCountry"));
                CompanyEmail.setCellValueFactory(new PropertyValueFactory<>("CompanyEmail"));
                CompanyContact.setCellValueFactory(new PropertyValueFactory<>("CompanyContact"));
                Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
                tableView.setItems(observableList);


        }
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
}
