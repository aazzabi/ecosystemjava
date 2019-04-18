/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Panier;

import entities.panier.Livreur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.panier.LivraisonService;
import iservices.panier.ILivraisonService;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class MeilleurLivreurController implements Initializable {
   @FXML
    private Label nom_liv;

    @FXML
    private Label zone_liv;

    @FXML
    private Label nbr_liv;
    
  private ILivraisonService livraisonService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livraisonService = new LivraisonService();
        Livreur a=livraisonService.MeilleurLivreur();
        String nom=livraisonService.RecupererNP_Livreur(a.getId());
        String zone=a.getZone();
        int nbr=a.getNbr_livraison();
        nom_liv.setText(nom);
        zone_liv.setText(zone);
        nbr_liv.setText(Integer.toString(nbr));
        
    }    
    
}
