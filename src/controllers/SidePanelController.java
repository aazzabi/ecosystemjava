/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Session;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.UserService;

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
      
    @FXML
    private Label user;

    private ChangeCallback callback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          String nomUser=UserService.getTtUtilisateur().stream().filter(e -> e.getId() == Session.getCurrentSession()).findFirst().get().getUsername();
       
        user.setText("Utilisateur : "+ nomUser);


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
                callback.update("/gui/events/EvenementAdmin.fxml");
                break;
             case "Recyclage":
                callback.update("/gui/missions/HostList.fxml");
                break;
             case "Forum":
                callback.update("/gui/forum/forumAdmin.fxml");
                break;
                case "Commandes & Livraisons":
                callback.update("/gui/panier/CMDAdmin.fxml");
                break;
                
             case "Signalisation":
                callback.update("/gui/forum/signalisationsCommentaire.fxml");
                break;
             case "Utilisateur":
                callback.update("/gui/utilisateurs.fxml");
                break;
            
         
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
     @FXML
    // a mettre les SRC
    private void deco(ActionEvent event) {
                try
        {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/gui/Login.fxml"));
              Stage s = (Stage) b2.getScene().getWindow();
            s.close();
            

            
            try {
                Loader.load();
            } catch (IOException e) {
                System.out.println(e);
            }
            LoginController c = Loader.getController();
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
            event.consume();
            Session.setCurrentSessionToNull();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
        
    
    } 
}
