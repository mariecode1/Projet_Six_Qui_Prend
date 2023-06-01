package com.example.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.fx.object.Card;
import com.example.fx.joueurs.joueurs;
import java.io.IOException;


public class HelloApplication extends Application {

    public Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("6 qui prend");
        Init();

}
public void Init(){
        try{
            FXMLLoader start = new FXMLLoader();
            start.setLocation(HelloApplication.class.getResource("start.fxml"));
            Parent root = start.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}

    public static void main(String[] args) {
        launch();
    }
}
