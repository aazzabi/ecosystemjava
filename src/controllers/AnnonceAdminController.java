/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Annonce;
import entities.Categorie_Annonce;
import entities.Session;
import iservices.IAnnonceService;
import iservices.ICategorieAnnonceService;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.AnnonceService;
import services.CategorieAnnonceService;
import utils.ControlleSaisie;
import utils.copyImages;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class AnnonceAdminController implements Initializable {

    @FXML
    private TableView<Annonce> ListeAnnonce;
    @FXML
    private TableColumn<Annonce, String> titre;

    @FXML
    private TableColumn<Annonce, String> Description;

    @FXML
    private TableColumn<Annonce, Date> date_creation;

    @FXML
    private TableColumn<Annonce, Double> prix;

    @FXML
    private TableColumn<Annonce, String> region;

    @FXML
    private TableColumn<Annonce, String> etat;

    @FXML
    private TableColumn<Annonce, Image> photo;

    @FXML
    private TableColumn<Annonce, Integer> likes;

    @FXML
    private TableColumn<Annonce, Integer> views;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_Edit;

    @FXML
    private JFXButton btn_Delete;

    @FXML
    private JFXButton btn_Clear;

    @FXML
    private JFXTextField txt_Titre;

    @FXML
    private JFXTextField txt_discription;

    @FXML
    private JFXTextField txt_prix;

    @FXML
    private ComboBox<String> cmb_region;

    @FXML
    private Button btn_photo_img;

    @FXML
    private ImageView img_photo;

    @FXML
    private ComboBox<Categorie_Annonce> cmb_cat;

    private String absolutePathPhotoAnnonce;

    @FXML
    private JFXTextField txtAnnoncephoto;
    
    IAnnonceService annonceService = new AnnonceService();
    
    /**
     * Initializes the controller class.
     */
        private void AfficherAll() {
        
            for ( int i = 0; i<ListeAnnonce.getItems().size(); i++) {
    ListeAnnonce.getItems().clear();
}
        ArrayList arrayList = (ArrayList) annonceService.getall();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        ListeAnnonce.setItems(observableList);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_creation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
        views.setCellValueFactory(new PropertyValueFactory<>("views"));
    }

    private void AfficherCombo() {
        ICategorieAnnonceService categorieAnnonceService = new CategorieAnnonceService();
        List<Categorie_Annonce> listCat = categorieAnnonceService.getall();
        cmb_cat.getItems().addAll(listCat);

        ObservableList<String> reg = FXCollections.observableArrayList("Tunis", "Ariana", "Manouba", "Ben Arous", "Bizerte", "Béja", "Jendouba", "Siliana", "Kasserine", "Sidi Bouzid", "Gafsa", "Tozeur", "Kébili", "Tataouine", "Médenine", "Gabès", "Sfax", "Kairouan", "Mahdia", "Monastir", "Sousse", "Zaghouan", "Nabeul");
        cmb_region.getItems().addAll(reg);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AfficherAll();
        AfficherCombo();

    }

    @FXML
    private void photoAnnonceChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        btn_photo_img.setOnAction(e -> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoAnnonce = choix.getAbsolutePath();
                txtAnnoncephoto.setText(choix.getName());
            } else {
                System.out.println("Image introuvable");
            }
        });
    }

    @FXML
    private void add(ActionEvent event) {

        if(!(ControlleSaisie.estVide(txt_Titre, "titre")) 
            && !(ControlleSaisie.estVide(txt_discription, "descritpion"))
            && !(ControlleSaisie.estVide(txt_prix, "prix"))
            && !(ControlleSaisie.estVide(txtAnnoncephoto, "image"))
            && !(ControlleSaisie.estVideCombo(cmb_region, "region"))
            && !(ControlleSaisie.estVideCombo(cmb_cat, "Catégorie")))
        {
            Annonce a = new Annonce(
                    txt_Titre.getText(), 
                    txt_discription.getText(), 
                    Double.parseDouble(txt_prix.getText()),
                    cmb_region.getValue(),
                    txtAnnoncephoto.getText(),
                    cmb_cat.getValue().getId(),
                    Session.getCurrentSession());
                   annonceService.add(a);
                  
            
        }
         AfficherAll();
        
    }

}
