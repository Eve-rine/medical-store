package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class update_company {
    public TextField contact;
    public TextField email_update;
    @FXML
 TableColumn <companies,String> CompanyName;
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
    private ComboBox <String>company_name;
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
    public void update_contact(ActionEvent actionEvent) {
        Connection connection=null;
        try{

            ConnectionClass connectionClass = new ConnectionClass();
            connection = connectionClass.getConnection();
            String sql = "Update companies set CompanyContact='"+contact.getText()+"' where CompanyName='" +company_name.getValue()+"'";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Data update successfully");
        } catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
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
        contact.clear();
    }

    public void Update_Email(ActionEvent actionEvent) {
        Connection connection=null;
        try{

            ConnectionClass connectionClass = new ConnectionClass();
            connection = connectionClass.getConnection();
            String sql = "Update companies set CompanyEmail='"+email_update.getText()+"' where CompanyName='" +company_name.getValue()+"'";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Data update successfully");
        } catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
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
        email_update.clear();
    }
}

