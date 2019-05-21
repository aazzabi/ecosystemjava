/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Panier;

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
import service.panier.SendEmailService;
import iservices.panier.ISendEmailService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class EspaceLivreurController implements Initializable {
@FXML
    private TableView<Livraison> table_livraison;

    @FXML
    private TableColumn<Livraison,Date> date_livraison;
    @FXML
    private TableColumn<Livraison,String> etat;

    @FXML
    private TableColumn<Livraison,String> adresse;

    @FXML
    private TableColumn<Livraison,String> ville;
        @FXML
    private Label nom_livreur;
        
        
    @FXML
    private Button valider_livraison;
     @FXML
    private Button detail_liv;
    private ICommandeService commandeService;
     
     private ILivraisonService livraisonService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       livraisonService = new LivraisonService();
       commandeService = new CommandeService();
        table_livraison.setEditable(false);
        table_livraison.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
       
        date_livraison.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
           ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        int id=Session.getCurrentSession();
        String n=commandeService.RecupererNP_Utilisateur(id);
       // String n2=commandeService.RecupererNP_Utilisateur(id2);
        System.out.println("name :"+n);
        nom_livreur.setText(n);
        table_livraison.setItems(livraisonService.RecupererLivraisonLivreur(id));
    }
    
    @FXML
    void Valider_Livraison(ActionEvent event) {

      int idliv = table_livraison.getSelectionModel().getSelectedItem().getId();
livraisonService = new LivraisonService();
commandeService = new CommandeService();
livraisonService.ChangerEtatLivraison(idliv);
      int id=Session.getCurrentSession();
livraisonService.ChangerEtatLivreurTodispo(id);
commandeService.ChangerEtatCommandeToLivre(table_livraison.getSelectionModel().getSelectedItem().getId_commande());

Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Confirmation de livraison effectué Avec success");
            alert.showAndWait();
 table_livraison.setEditable(false);
        table_livraison.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
        date_livraison.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
           ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
           table_livraison.setItems(livraisonService.RecupererLivraisonLivreur(id));
    }
     @FXML
    void Detail_Liv(ActionEvent event) {
        
        if (table_livraison.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionnée ! ");

            alert.showAndWait();
        } else {
 try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/DetailLivraison.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              DetailLivraisonController controller = fxmlLoader.getController();
                controller.initData(table_livraison.getSelectionModel().getSelectedItem());
            
                stage.setTitle("Détail Livraison");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        }
    }
}
