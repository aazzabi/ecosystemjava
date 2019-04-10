/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import static controllers.Annonce.AllAnnoncesController.obsl;
import static controllers.Annonce.CardsAnnonceController.i;
import entities.Annonce;
import entities.Categorie_Annonce;
import entities.Session;
import iservices.IAnnonceService;
import iservices.ICategorieAnnonceService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AnnonceService;
import services.CategorieAnnonceService;
import utils.ControlleSaisie;
import utils.copyImages;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class AjouterAnnonceController implements Initializable {

    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_prix;
    @FXML
    private ComboBox<Categorie_Annonce> cmb_cat;
    @FXML
    private ComboBox<String> cmb_Region;
    @FXML
    private Button filechoose;
    @FXML
    private Button ajouter;
    @FXML
    private TextArea descriptionn;
    @FXML
    private Label filename;
    @FXML
    private Label filepath;
    @FXML
    private ImageView img_annonce;
     @FXML
    private Text label_photo;
    private IAnnonceService annonceService;
    private ICategorieAnnonceService categorieAnnonceService;
   
    private String absolutePathPhotoAnnonce;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherCombo();
        // TODO
    }  
    private void AfficherCombo() {
        categorieAnnonceService = new CategorieAnnonceService();
        List<Categorie_Annonce> listCat = categorieAnnonceService.getall();
        cmb_cat.getItems().addAll(listCat);

        ObservableList<String> reg = FXCollections.observableArrayList("Tunis", "Ariana", "Manouba", "Ben Arous", "Bizerte", "Béja", "Jendouba", "Siliana", "Kasserine", "Sidi Bouzid", "Gafsa", "Tozeur", "Kébili", "Tataouine", "Médenine", "Gabès", "Sfax", "Kairouan", "Mahdia", "Monastir", "Sousse", "Zaghouan", "Nabeul");
        cmb_Region.getItems().addAll(reg);

    }

    @FXML
    private void filechoose(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        filechoose.setOnAction(e -> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoAnnonce = choix.getAbsolutePath();
                label_photo.setText(choix.getName());
                copyImages.deplacerVers(label_photo, absolutePathPhotoAnnonce, "C:\\ecosystemjava\\src\\res\\Annonce\\photo\\");
            copyImages.deplacerVers(label_photo, absolutePathPhotoAnnonce, "C:\\wamp64\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\");
            Image imag = new Image("file:/C:/wamp64/www/ecosystemweb/web/uploads/Annonce/photo/" + label_photo.getText());
            img_annonce.setImage(imag);
            } else {
                System.out.println("Image introuvable");
            }
        });
    }

    @FXML
    private void insertData(ActionEvent event) {
        annonceService = new AnnonceService();
        if (!(ControlleSaisie.estVide(txt_titre, "titre"))
                && !(ControlleSaisie.estVideTextArea(descriptionn, "descritpion"))
                && !(ControlleSaisie.estVide(txt_prix, "prix"))
                && !(ControlleSaisie.estVidePhoto(label_photo, "image"))
                && !(ControlleSaisie.estVideCombo(cmb_Region, "region"))
                && !(ControlleSaisie.estVideCombo(cmb_cat, "Catégorie"))) {
            Annonce a = new Annonce(
                    txt_titre.getText(),
                    descriptionn.getText(),
                    Double.parseDouble(txt_prix.getText()),
                    cmb_Region.getValue(),
                    label_photo.getText(),
                    cmb_cat.getValue().getId(),
                    Session.getCurrentSession());
           // copyImages.deplacerVers(label_photo, absolutePathPhotoAnnonce, "C:\\ecosystemjava\\src\\res\\Annonce\\photo\\");
           // copyImages.deplacerVers(label_photo, absolutePathPhotoAnnonce, "C:\\wamp64\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\");
            annonceService.add(a);
              Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        }
    }
}
