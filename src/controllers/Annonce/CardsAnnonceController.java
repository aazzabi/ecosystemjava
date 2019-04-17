/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import controllers.Panier.PanierController;
import entities.Annonce;
import iservices.IAnnonceService;
import iservices.panier.IPanierService;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.panier.PanierService;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class CardsAnnonceController implements Initializable {

    @FXML
    private ImageView img_annoce;
    @FXML
    private Label lbl_titre;
    @FXML
    private Label id_annonce;
    @FXML

    private Button consulter;
    @FXML
    private Label lbl_prix;
    @FXML
    private Button likes;
    @FXML
    private Button signaler;
    @FXML
    private Button panier;
    @FXML
    private VBox vbox;
    private IAnnonceService annonceService;
    private IPanierService panierService;
    static int i;
    public int t;
    public int iduser;
    public double  xx;
     java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if (AllAnnoncesController.indice==0){
            lbl_titre.setText(AllAnnoncesController.obsl.get(i).getTitre());
            id_annonce.setText(Integer.toString(AllAnnoncesController.obsl.get(i).getId()));
            
        lbl_prix.setText("$"+AllAnnoncesController.obsl.get(i).getPrix().toString());
xx=AllAnnoncesController.obsl.get(i).getPrix();
        t=AllAnnoncesController.obsl.get(i).getId();
        //pri=Integer.parseInt(prixx.getText());
           System.out.println(AllAnnoncesController.obsl.get(i).getPhoto());
         Image imag = new Image("file:/C:/wamp64/www/ecosystemweb/web/uploads/Annonce/photo/"+AllAnnoncesController.obsl.get(i).getPhoto());
         img_annoce.setImage(imag);
        i++;
        }
    }  
   

    @FXML
    private void ajouterAupanier(ActionEvent event) {
         int ida=Integer.parseInt(id_annonce.getText());
         System.out.println("id : "+ida);
         panierService = new PanierService();
         System.out.println("Test de fonction ::: "+panierService.existAnnonce(ida));
         
         if(panierService.existAnnonce(ida)==0)
         {
         Annonce a=panierService.RecupererAnnonce(ida);
        System.out.println(a.toString());
        panierService.AjouterAuPanier(a);
         System.out.println("Ajouté au Panier");

       try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/CheckedPanier.fxml"));
          
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              
                stage.setTitle("Panier");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
         }
         else
         {
         System.out.println("Erreur Ajout");
          try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/ErreurPanier.fxml"));
          
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
              
                stage.setTitle("Panier");
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
