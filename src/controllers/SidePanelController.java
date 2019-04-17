/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SidePanelController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;
    
      @FXML
    private JFXButton b3111;
      
      

    private ChangeCallback callback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCallback(ChangeCallback callback) {
        this.callback = callback;
    }

    @FXML
    // a mettre les SRC
    private void navigate(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch (btn.getText()) {
            case "Acceuil":
                callback.update("");
                break;
            case "Announce":
                callback.update("/gui/Annonce/annonceAdmin.fxml");
                break;
             case "Réparation":
                callback.update("/gui/reparateur/ReparateurMainSc.fxml");
                break;
             case "Evenement":
                callback.update("/gui/Evenement.fxml");
                break;
             case "Recyclage":
                callback.update("");
                break;
             case "Forum":
                callback.update("/gui/forum/forumAdmin.fxml");
                break;
                case "Commandes & Livraisons":
                callback.update("/gui/panier/CMDAdmin.fxml");
                break;
                
             case "Signalisation":
                callback.update("");
                break;
             case "Utilisateur":
                callback.update("");
                break;
            
         
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
   
}
