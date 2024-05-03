package com.example.lab08_1a_210041109;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 948, 657);
        stage.setTitle("PokeDex!");
        Image icon = new Image(getClass().getResourceAsStream("/imageFolder/splash.png"));


        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        HelloController helloController = fxmlLoader.getController();

        // Call the startProcessing() method
        helloController.startProcess();
    }

    public static void main(String[] args) {
        launch();
    }
}