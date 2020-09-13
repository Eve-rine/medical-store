package sample.controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class medic implements Initializable {
    public ComboBox<String> Product_Name;
    public AnchorPane anchor;

    @FXML
    private TableView<medictable> tableView;
    @FXML
    private TableColumn<medictable, String > productId;
    @FXML
    private TableColumn<medictable, String> ProductName;
    @FXML
    private TableColumn<medictable, String > CompanyName;
    @FXML
    private TableColumn<medictable, String> ProductCategory;
    @FXML
    private TableColumn<medictable, String> Quantity;
    @FXML
    private TableColumn<medictable, String> UnitPrice;

    public void initialize(URL location, ResourceBundle resources) {
        //   new SlideInUp(fine_anchor).play();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<medictable> observableList = FXCollections.observableArrayList();


        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select productId, ProductName,CompanyName,ProductCategory,Quantity,UnitPrice from medicines");

            while (resultSet.next()){
                observableList.add(new medictable(resultSet.getString("productId"),resultSet.getString("ProductName"),resultSet.getString("CompanyName")
                        ,resultSet.getString("ProductCategory"),resultSet.getString("Quantity"),resultSet.getString("UnitPrice")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        CompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        ProductCategory.setCellValueFactory(new PropertyValueFactory<>("ProductCategory"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        UnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        tableView.setItems(observableList);
    }
}
