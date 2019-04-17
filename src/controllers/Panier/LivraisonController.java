/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Panier;

import entities.Session;
import entities.panier.Commande;
import entities.panier.Livraison;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.panier.CommandeService;
import iservices.panier.ICommandeService;
import service.panier.LivraisonService;
import iservices.panier.ILivraisonService;
import service.panier.LigneCommandeService;
import iservices.panier.ILigneCommandeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class LivraisonController implements Initializable {
@FXML
    private Label nom_prenom_u;

    @FXML
    private TableView<Livraison> table_livraison;

    @FXML
    private TableColumn<Livraison, Date> date;

    @FXML
    private TableColumn<Livraison,String> etat;

    @FXML
    private TableColumn<Livraison,String> adresse;

        @FXML
    private Label np_livreur;

    @FXML
    private Button annuler_livraison;
    
    @FXML
    private Button np_liv;
    
    private ICommandeService commandeService;
     private ILigneCommandeService lignecommandeService;
     private ILivraisonService livraisonService;

    @FXML
    void Annuler_livraison(ActionEvent event) {
 livraisonService = new LivraisonService();
if (table_livraison.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne  sélectionnée ");
            alert.showAndWait();
        } 
else 
{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de Suppression");
            alert.setHeaderText("Voulez vous supprimer les livraisons suivantes  ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<Livraison> selectedRows = table_livraison.getSelectionModel().getSelectedItems();
                ArrayList<Livraison> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   
                    livraisonService.AnnulerLivraison(row.getId());
                    table_livraison.getItems().remove(row);
                });

            }
            
            else {

            }

        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Affichage Livraison");
       livraisonService = new LivraisonService();
       commandeService = new CommandeService();
        table_livraison.setEditable(false);
        table_livraison.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
       
        date.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
         
        int id=Session.getCurrentSession();
        table_livraison.setItems(livraisonService.RecupererLivraisonClient2(id));
        String n=commandeService.RecupererNP_Utilisateur(id);
        nom_prenom_u.setText(n);
       /*  Livraison l=table_livraison.getSelectionModel().getSelectedItem();
        int id_liv=l.getId_livreur();
        String n2=livraisonService.RecupererNP_Livreur(id_liv);
        np_livreur.setText(n2);*/
       np_livreur.setVisible(false);
    }    
    
    @FXML
    void LivreurName(ActionEvent event) {
         if (table_livraison.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionnée ! ");

            alert.showAndWait();
        } else {
             
  Livraison l=table_livraison.getSelectionModel().getSelectedItem();
        int id_liv=l.getId_livreur();
        String n2=livraisonService.RecupererNP_Livreur(id_liv);
        np_livreur.setText(n2);
       np_livreur.setVisible(true);
        }
       
    }
    
}
