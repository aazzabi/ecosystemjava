/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import entities.CategoriePub;
import entities.PublicationForum;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PublicationForumService;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class ShowCategoriePublicationController implements Initializable {

    @FXML
    private Label txtIdCategorie;
    @FXML
    private Label txtLibelleCategorie;
    @FXML
    private Label txtDescriptionCategorie;
    @FXML
    private Label txtDomaineCategorie;
    @FXML
    private Label txtNbrPubCategorie;
    @FXML
    private TableView<PublicationForum> tableListePublication;
    @FXML
    private TableColumn<PublicationForum, Integer> idPublication;
    @FXML
    private TableColumn<PublicationForum, Date> datePublication;
    @FXML
    private TableColumn<PublicationForum, String> titrePublication;
    @FXML
    private TableColumn<PublicationForum, String> descriptionPublication;
    @FXML
    private TableColumn<PublicationForum, String> etatPublication;
    @FXML
    private TableColumn<PublicationForum, String> categoriePublication;
    @FXML
    private TableColumn<PublicationForum, Integer> creeParPublication;
    
    ObservableList<PublicationForum> obl = FXCollections.observableArrayList();

//    private String id, lib, desc, dom, nbr;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    public void afficherCategorie(CategoriePub c) {
        txtIdCategorie.setText(String.valueOf(c.getId()));
        txtLibelleCategorie.setText(c.getLibelle());
        txtDescriptionCategorie.setText(c.getDescription());
        txtDomaineCategorie.setText(c.getDomaine());
        txtNbrPubCategorie.setText(String.valueOf(c.getNbPublication()));
        afficherAllPublicationsCategorie(c.getId());
    }
    
    public void afficherAllPublicationsCategorie(int id ){
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllPublicationsByCategorie(id);

        for(PublicationForum e:le)
        {
            obl.add(e);
        }  
        
        datePublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categoriePublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        creeParPublication.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        
        tableListePublication.setItems(obl);
        tableListePublication.setEditable(true);
    }
}
