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

public class SalesRecord implements Initializable{

    @FXML
    private TableView<salestable> table1;
    @FXML
    private TableColumn <salestable,String>SalesID;
    @FXML
    private TableColumn <salestable,String>ProductName;
    @FXML
    private TableColumn<salestable,String> CompanyName;
    @FXML
    private TableColumn<salestable,String> DateOfSale;
    @FXML
    private TableColumn<salestable,String> Quantity;
    @FXML
    private TableColumn<salestable,String> UnitPrice;
    @FXML
    private TableColumn <salestable,String>Total;
   // ObservableList<salestable> observableList2 = FXCollections.observableArrayList();
public void sales() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<salestable> observableList2 = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select SalesID,ProductName,CompanyName,DateOfSale,Quantity,UnitPrice,Total from sales");

            while (resultSet.next()) {
                observableList2.add(new salestable(resultSet.getString("SalesID"), resultSet.getString("ProductName"), resultSet.getString("CompanyName")
                        , resultSet.getString("DateOfSale"), resultSet.getString("Quantity"), resultSet.getString("UnitPrice"), resultSet.getString("Total")));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        SalesID.setCellValueFactory(new PropertyValueFactory<>("SalesID"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        DateOfSale.setCellValueFactory(new PropertyValueFactory<>("DateOfSale"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        table1.setItems(observableList2);
    }


    public void on_print(ActionEvent actionEvent) {
        MyPrinter print = new MyPrinter();
        print.Print(table1);
    }
}
