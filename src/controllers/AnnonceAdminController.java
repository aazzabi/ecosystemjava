/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Annonce;
import iservices.IAnnonceService;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.AnnonceService;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IAnnonceService annonceService = new AnnonceService();
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

}
