/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import entities.Annonce;
import entities.Categorie_Annonce;
import iservices.IAnnonceService;
import iservices.ICategorieAnnonceService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import services.AnnonceService;
import services.CategorieAnnonceService;

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

    private IAnnonceService annonceService;
    private ICategorieAnnonceService categorieAnnonceService;
    public static ObservableList<Annonce> obsl;
    public static ObservableList<Annonce> obsDate;
    public static ObservableList<Annonce> prixasc;
    public static ObservableList<Annonce> prixdesc;
    public static ObservableList<Annonce> myannonces;
    public static ObservableList<Annonce> myannoncesCAt;
    public static List<Annonce> listsearch;
    public static int indice;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_my;
    @FXML
    private Button btnall;
    @FXML
    private ComboBox<Categorie_Annonce> cmb_cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherCards();
        AfficherCombo();
    }
    private void AfficherCombo() {
        categorieAnnonceService = new CategorieAnnonceService();
        List<Categorie_Annonce> listCat = categorieAnnonceService.getall();
        cmb_cat.getItems().addAll(listCat);
    }
    
    @FXML
    private void prixasc(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new AnnonceService();
        prixasc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixASC());
        indice = 1;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);

    }

    @FXML
    private void prixdesc(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new AnnonceService();
        prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 2;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);
    }

    @FXML
    private void search(ActionEvent event) throws IOException {
        listsearch = obsl.stream().filter(e->e.getTitre().contains(search.getText())).collect(Collectors.toList());
        indice=6;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);
    }

    @FXML
    private void AjouterAnnonce(ActionEvent event) {
        System.out.println("j'ai clicker ici");
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/gui/Annonce/ajouterAnnonce.fxml"));
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
    private void MesAnnonces(ActionEvent event)throws IOException  {
        container.setCenter(null);
        annonceService = new AnnonceService();
        myannonces = FXCollections.observableArrayList((ArrayList) annonceService.GetByUser());
        indice = 4;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);
    }

    @FXML
    private void plusrécente(ActionEvent event) throws IOException {
        container.setCenter(null);
        annonceService = new AnnonceService();
        obsDate = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate());
        indice = 3;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);
    }

    public void AfficherCards() {
        
        annonceService = new AnnonceService();
        CardsAnnonceController.i = 0;
        ArrayList<Annonce> annonces = new ArrayList<>();
        annonces = (ArrayList) annonceService.getall();
        obsl = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
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
    }

    @FXML
    private void gettall(ActionEvent event) throws IOException {
      container.setCenter(null);
        annonceService = new AnnonceService();
        obsl = FXCollections.observableArrayList((ArrayList) annonceService.getall());
        indice = 0;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);
    }

    @FXML
    private void showsCardsCat(ActionEvent event) throws IOException {
       
          container.setCenter(null);
        annonceService = new AnnonceService();
        myannoncesCAt = FXCollections.observableArrayList((ArrayList) annonceService.GetByCategorie( cmb_cat.getValue().getId()));
        indice = 5;
        CardsAnnonceController.i = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Annonce/Annonce.fxml"));
        container.setCenter(root);
    }
}
