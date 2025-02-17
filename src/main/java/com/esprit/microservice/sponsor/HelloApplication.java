package com.esprit.microservice.sponsor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sponsor/sponsorship.fxml"));
        Parent root = loader.load();

        // Set the size of the window
        stage.setWidth(1200); // Set the width as needed
        stage.setHeight(800); // Set the height as needed

        // Create scene and set it on the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("sponso");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}