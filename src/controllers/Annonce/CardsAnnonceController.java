/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import entities.Annonce;
import iservices.panier.IPanierService;
import iservices.IAnnonceService;
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
import iservices.panier.ILigneCommandeService;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.panier.PanierService;
import services.AnnonceService;

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
    private ILigneCommandeService lignecommandeService;
    static int i;
    public int t;
    public int iduser;
    public double xx;
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    @FXML
    private Label lbl_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_annonce.setVisible(false);
        // TODO
      //  lignecommandeService = new LigneCommandeService();
        if (AllAnnoncesController.indice == 0) {

                lbl_titre.setText(AllAnnoncesController.obsl.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.obsl.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.obsl.get(i).getId()));
                t = AllAnnoncesController.obsl.get(i).getId();
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.obsl.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 1) {
           
                lbl_titre.setText(AllAnnoncesController.prixasc.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.prixasc.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.prixasc.get(i).getId()));
                t = AllAnnoncesController.prixasc.get(i).getId();
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.prixasc.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 2) {
            
                lbl_titre.setText(AllAnnoncesController.prixdesc.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.prixdesc.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.prixdesc.get(i).getId()));
                t = AllAnnoncesController.prixdesc.get(i).getId();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllAnnoncesController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.prixdesc.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 3) {
           
                lbl_titre.setText(AllAnnoncesController.obsDate.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.obsDate.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.obsDate.get(i).getId()));
                t = AllAnnoncesController.obsDate.get(i).getId();
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.obsDate.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 4) {
            
                lbl_titre.setText(AllAnnoncesController.myannonces.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.myannonces.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.myannonces.get(i).getId()));
                t = AllAnnoncesController.myannonces.get(i).getId();
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.myannonces.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 5) {
            
                lbl_titre.setText(AllAnnoncesController.myannoncesCAt.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.myannoncesCAt.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.myannoncesCAt.get(i).getId()));
                t = AllAnnoncesController.myannoncesCAt.get(i).getId();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllAnnoncesController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.myannoncesCAt.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 6) {
            
                lbl_titre.setText(AllAnnoncesController.listsearch.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.listsearch.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.listsearch.get(i).getId()));
                t = AllAnnoncesController.listsearch.get(i).getId();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllAnnoncesController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.listsearch.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 7) {
           

                lbl_titre.setText(AllAnnoncesController.likedAnnonce.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.likedAnnonce.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.likedAnnonce.get(i).getId()));
                t = AllAnnoncesController.likedAnnonce.get(i).getId();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllAnnoncesController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.likedAnnonce.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        } else if (AllAnnoncesController.indice == 8) {
            
                lbl_titre.setText(AllAnnoncesController.ViwedAnnonce.get(i).getTitre());
                lbl_prix.setText("$" + AllAnnoncesController.ViwedAnnonce.get(i).getPrix().toString());
                lbl_id.setText(Integer.toString(AllAnnoncesController.ViwedAnnonce.get(i).getId()));
                t = AllAnnoncesController.ViwedAnnonce.get(i).getId();
                //pri=Integer.parseInt(prixx.getText());
                //System.out.println(AllAnnoncesController.prixasc.get(i).getPhoto());
                Image imag = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + AllAnnoncesController.ViwedAnnonce.get(i).getPhoto());
                img_annoce.setImage(imag);
                i++;
            
        }
    }

    @FXML
    private void likes(ActionEvent event) {
        annonceService = new AnnonceService();
        int index = Integer.parseInt(lbl_id.getText());
        annonceService.updateLikes(index);
        likes.setVisible(false);
    }

    @FXML
    private void ajoutersignal(ActionEvent event) {
        System.out.println("j'ai clicker ici");
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/gui/Annonce/scenesignaler.fxml"));
        try {
            Loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        ScenesignalerController display = Loader.getController();
        display.getId(Integer.parseInt(lbl_id.getText()));
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        event.consume();
        signaler.setVisible(false);

    }

    @FXML
    private void ajouterpanier(ActionEvent event) {

    }

    @FXML
    private void consulter(ActionEvent event) throws IOException {
        annonceService = new AnnonceService();
        DetaileAnnonceController.idd = Integer.parseInt(lbl_id.getText());
        annonceService.updateViwes(Integer.parseInt(lbl_id.getText()));
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/gui/Annonce/DetaileAnnonce.fxml"));
        try {
            Loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        event.consume();
    }

    @FXML
    private void ajouterAupanier(ActionEvent event) {
        int ida = Integer.parseInt(lbl_id.getText());
        System.out.println("id : " + ida);
        panierService = new PanierService();
        System.out.println("Test de fonction ::: " + panierService.existAnnonce(ida));

        if (panierService.existAnnonce(ida) == 0) {
            Annonce a = panierService.RecupererAnnonce(ida);
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
            
            
        } else {
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
