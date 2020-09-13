package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = null;
        try {
            // root = FXMLLoader.load(getClass().getResource("../fxml/authentication.fxml"));
            root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("MEDICAL STORE");
        primaryStage.setScene(new Scene(root, 600, 400));
        /* primaryStage.setResizable(false);*/
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
