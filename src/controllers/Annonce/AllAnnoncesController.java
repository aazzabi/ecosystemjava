/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import controllers.Panier.PanierController;
import ecosystemjava.Launcher;
import entities.Annonce;
import iservices.IAnnonceService;
import iservices.panier.IPanierService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import services.AnnonceService;
import service.panier.PanierService;
/**
 * FXML Controller class
 *
 * @author anasc
 */
public class AllAnnoncesController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private FlowPane flow;
    @FXML
    private BorderPane container;
    @FXML
    private Button livraison;

     @FXML
    private Button espace_livreur;
     
    private IAnnonceService annonceService;
    private IPanierService panierService;
    public static ObservableList<Annonce> obsl;
    public static int indice;
    public static int x=0;
    public int nbProduit;
    
    @FXML
    private ImageView panier;
    
    @FXML
    private Button mes_commandes;

    @FXML
     private Label nombre_article;


   
    /**
     * Initializes the controller class.
     */
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
       
        // TODO
        annonceService = new AnnonceService();
        CardsAnnonceController.i=0;
        ArrayList<Annonce> annonces = new ArrayList<>();
        annonces = (ArrayList) annonceService.getall();
        obsl = FXCollections.observableArrayList(annonces);
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/Annonce/CardsAnnonce.fxml"));
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     /* panierService = new PanierService();
        int nbr=panierService.LonguerPanier();
        nombre_article.setText("("+nbr+")");*/
        
    }
    
    @FXML
    private void alphabetiqueaz(ActionEvent event) {
    }

    @FXML
    private void prixasc(ActionEvent event) {
    }

    @FXML
    private void prixdesc(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
    }
    @FXML
    void Afficher_Livraison(ActionEvent event) {
 try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/Livraison.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
            
                stage.setTitle("Livraison");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }

    @FXML
    private void AffichagePanier(MouseEvent event) {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/Panier.fxml"));
                /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              
                stage.setTitle("Affichage du Panier ");
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
    
    public static void update_nb_annonce()
    {
        
    }
    
    @FXML
    void mes_commandes(ActionEvent event) {
try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/Commande.fxml"));
                /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              
                stage.setTitle("Affichage des Commandes ");
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
        @FXML
    void Espace_livreur(ActionEvent event) {
try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/EspaceLivreur.fxml"));
                /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              
                stage.setTitle("Espace Livreur ");
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
 
    
    
    
}
