/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Session;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import service.panier.LivraisonService;
import iservices.panier.ILivraisonService;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.UserService;

public class SidePanelUserController implements Initializable {
    private ILivraisonService livraisonService;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton dec;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;

    @FXML
    private JFXButton b78;
        
    @FXML
    private JFXButton b99;
    @FXML
    private JFXButton b781;
    @FXML
    private Label user;

    private ChangeCallback callback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        livraisonService= new LivraisonService();
        
        String nomUser=UserService.getTtUtilisateur().stream().filter(e -> e.getId() == Session.getCurrentSession()).findFirst().get().getUsername();
       
        user.setText("Utilisateur : "+ nomUser);

        int id_u=Session.getCurrentSession();
        System.out.println("id : "+id_u);
        if(livraisonService.RoleLivreur(id_u)==0)
        {
         b99.setDisable(false);
         
            //b99.setVisible(false);
        }else
        {
        b99.setDisable(true);
        
//             b99.setVisible(true);
        }
        
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
                callback.update("/gui/Annonce/AllAnnonces.fxml");
                break;
             case "Réparation":
                callback.update("/gui/reparateur/ReparateurMainScUser.fxml");
                break;
            case "Evenement":
                callback.update("/gui/events/Evenement.fxml");
                break;
            case "Recyclage":
                callback.update("/gui/missions/HostList.fxml");
                break;
             case "Forum":
                callback.update("/gui/forum/forumUser.fxml");
                break;
                case "Commandes":
                callback.update("/gui/panier/Commande.fxml");
                break;
                case "Livraisons":
                callback.update("/gui/panier/Livraison.fxml");
                break;
                
                case "Espace Livreur":
                    
                callback.update("/gui/panier/EspaceLivreur.fxml");
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
              Stage s = (Stage) b99.getScene().getWindow();
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
