package com.example.fx.Cardcontroller;

import com.example.fx.object.Card;
import com.example.fx.joueurs.joueurs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javafx.application.Application;
import java.net.URL;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.example.fx.joueurs.joueurs.*;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;

import static com.example.fx.joueurs.joueurs.main;

public class CardController{
    public static void start(int j) {

        // Création de la main du joueur
        HBox mainJoueur = new HBox(10);
        mainJoueur.setStyle("-fx-background-color: White");
        mainJoueur.setPadding(new Insets(10));
        for (int i =0 ;i<joueurs.joueurs.get(j).size();i++){
            ajouterCarteDansMain(mainJoueur,joueurs.joueurs.get(j).get(i).getNum_card(),j,i);
        }
        // Création de la scène principale
        Parent root = mainJoueur;
        joueurs.mainFx.add(root);
    }

    // Fonction pour ajouter une carte à la main du joueur
    private static void ajouterCarteDansMain(HBox mainJoueur, int valeur, int j, int i) {
        Rectangle carte = new Rectangle(100,130,Color.WHITE);
        carte.setArcWidth(10);
        carte.setArcHeight(10);
        carte.setStrokeWidth(4);
        if (joueurs.joueurs.get(j).get(i).getNbTaureau() < 2) {
            carte.setStroke(Color.BLACK);
        } else if (joueurs.joueurs.get(j).get(i).getNbTaureau() < 4) {
            carte.setStroke(Color.YELLOW);
        } else if (joueurs.joueurs.get(j).get(i).getNbTaureau() < 6) {
            carte.setStroke(Color.ORANGE);
        } else {
            carte.setStroke(Color.RED);
        }

        // Adding image background to the card
        Image image = new Image("file:src/main/resources/cards/" +valeur+ ".png"); // Use your image file here
        ImagePattern imagePattern = new ImagePattern(image);
        carte.setFill(imagePattern);

        Label label = new Label(String.valueOf(valeur) + "\n\n");
        label.setStyle("-fx-font-size: 40px;");
        Label label2 = new Label("Taur : " + String.valueOf(joueurs.joueurs.get(j).get(i).getNbTaureau()));

        label.setStyle("-fx-font-size: 20px;");

        VBox carteContainer = new VBox();
        carteContainer.setAlignment(Pos.CENTER);
        StackPane card = new StackPane();
        card.setAlignment(Pos.CENTER);
        card.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! " + event.getSource() + " "+ label + " " + label2);
            };
        });
        card.getChildren().addAll(carte,carteContainer);

        mainJoueur.getChildren().add(card);
    }
    public void changecart(){}

}