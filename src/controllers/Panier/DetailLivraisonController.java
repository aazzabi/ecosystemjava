/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Panier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import entities.Session;
import entities.panier.Livraison;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import service.panier.LivraisonService;
import iservices.panier.ILivraisonService;
import service.panier.CommandeService;
import iservices.panier.ICommandeService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class DetailLivraisonController implements Initializable {

    @FXML
    private Label id_cmd;

    @FXML
    private Label nom_u;

    @FXML
    private Label num_tel;

    @FXML
    private Label adresse;
     private ICommandeService commandeService;
     
     private ILivraisonService livraisonService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void initData(Livraison l)
    {
         livraisonService = new LivraisonService();
       commandeService = new CommandeService();
       id_cmd.setText(Integer.toString(l.getId_commande()));
       nom_u.setText(commandeService.RecupererNP_Utilisateur(l.getId_utilisateur()));
       num_tel.setText(commandeService.RecupererTel_Utilisateur(l.getId_utilisateur()));
       adresse.setText(l.getAdresse_livraison());
    }
    
}
