package com.example.fx;

import com.example.fx.mechanic.Method;
import com.example.fx.mechanic.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.fx.Cardcontroller.CardController;
import com.example.fx.joueurs.joueurs;
import com.example.fx.object.Card;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class HelloController implements Initializable {
    @FXML
    private TextField joueur;
    @FXML
    private Button suivant;
    @FXML
    private Label alertjoueur;
    @FXML
    private CheckBox ia;
    @FXML
    private Pane pane;
    @FXML
    private HBox main;
    @FXML
    private FlowPane cardpane;
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    public void switchScene(ActionEvent event) throws IOException{
        primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        switchSceneall("regle.fxml");
    }
    public void sceneback(ActionEvent event) throws IOException {
        primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        switchSceneall("start.fxml");
    }
    public void switchSceneStart(ActionEvent event) throws IOException{
        primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        switchSceneall("joueur.fxml");
    }
    public void valider(ActionEvent event) throws IOException{
        int text = 0;
        do{
            try {
                text = Integer.parseInt(joueur.getText());
                joueur.clear();
            }
            catch (NumberFormatException e){
                joueur.clear();
                alertjoueur.setText("Entrer un nombre !!");
            }
            finally {
                joueur.clear();
            }
            joueur.clear();
        }while (text<1 || text>10);
        Method.nbr_joueur = text;
        System.out.println(Method.nbr_joueur);
        suivant.setDisable(false);
        Card.cart(event, primaryStage);

    }
    public void switchScenejeu(ActionEvent event) throws IOException{
        primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        switchSceneall("jeu.fxml");
        addCard();


    }
    public void switchSceneall(String fxml) throws IOException{
        FXMLLoader sce = new FXMLLoader(getClass().getResource(fxml));
        sce.setLocation(HelloApplication.class.getResource(fxml));
        root = sce.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void checkIA(ActionEvent event) {
        if (ia.isSelected()) {
            Start.ia = 1;
            System.out.println(Start.ia);
        }
        else {
            Start.ia=0;
            System.out.println(Start.ia);
        }
    }
    private void addCard() throws IOException {
        Start.start();
        for (int i= 0 ; i<Method.nbr_joueur;i++){
            CardController.start(i);
        }
        System.out.println(joueurs.mainFx.size());
    }
    public void car(ActionEvent event) throws IOException{
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(0));
    }
    public void car1(ActionEvent event) throws IOException{
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(1));
    }
    public void car2(ActionEvent event) throws IOException{
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(2));
    }
    public void car3(ActionEvent event) throws IOException{
        cardpane.getChildren().clear();
        cardpane.getChildren().addAll(joueurs.mainFx.get(3));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}