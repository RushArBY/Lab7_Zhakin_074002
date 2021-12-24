package com.lab7zhakin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import java.awt.*;
import java.io.IOException;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Market.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        fxmlLoader.setController(new MainController());
        stage.setTitle("Магазин одежды");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
