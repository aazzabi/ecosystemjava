/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import controllers.MainAdminScreenController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class ReparateurMainScUserController implements Initializable {

    @FXML
    private AnchorPane root1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    
    
    
    
    public void NewFenAnnonceRep()
    {
         try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reparateur/AllAnnonceRep.fxml"));
            root1.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void NewFenReparateur()
    {
         try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reparateur/Allreparateurs.fxml"));
            root1.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void NewFendemande()
    {
         try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reparateur/OffreComptePro.fxml"));
            root1.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
