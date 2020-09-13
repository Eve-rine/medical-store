package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReceivedRecords implements Initializable {

    public TableView <receivedtable>table2;
    public TableColumn<receivedtable,String> Date;
    @FXML
    private TableColumn <receivedtable,String>ProductName;
    @FXML
    private TableColumn<receivedtable,String> CompanyName;
    @FXML
    private TableColumn<receivedtable,String> Quantity;

    public ReceivedRecords() {
    }

    @FXML

    // ObservableList<salestable> observableList2 = FXCollections.observableArrayList();
    public void sales() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<receivedtable> observableList3 = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select ProductName,CompanyName,Date,Quantity from orders");

            while (resultSet.next()) {
                observableList3.add(new receivedtable(resultSet.getString("ProductName"), resultSet.getString("CompanyName")
                        , resultSet.getString("Date"), resultSet.getString("Quantity")));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        table2.setItems(observableList3);
    }

    public void on_print(ActionEvent actionEvent) {
        MyPrinter print = new MyPrinter();
        print.Print(table2);
    }
}
