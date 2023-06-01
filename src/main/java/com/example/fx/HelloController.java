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




public class HelloController implements Initializable {
    @FXML
    private TextField joueur;
    @FXML
    private Button suivant;
    @FXML
    private Label alertjoueur;
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

    }
    public void switchScenejeu(ActionEvent event) throws IOException{
        primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        switchSceneall("jeu.fxml");


    }
    public void switchSceneall(String fxml) throws IOException{
        FXMLLoader sce = new FXMLLoader(getClass().getResource(fxml));
        sce.setLocation(HelloApplication.class.getResource(fxml));
        root = sce.load();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}