/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.PublicationForum;
import entities.CategoriePub;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import services.CategoriePubService;
import services.PublicationForumService;
import services.UserService;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;
import utils.ControlleSaisie;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class ForumAdminController implements Initializable {

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
    
    
    @FXML
    private TableView<CategoriePub> tableListeCategorie;
    @FXML
    private TableColumn<CategoriePub, Integer> idCategorie;
    @FXML
    private TableColumn<CategoriePub, String> libelleCategorie;
    @FXML
    private TableColumn<CategoriePub, String> descriptionCategorie;
    @FXML
    private TableColumn<CategoriePub, String> domaineCategorie;
    @FXML
    private TableColumn<CategoriePub, Integer> nbrPublicationCategorie;
    ObservableList<CategoriePub> obCateg = FXCollections.observableArrayList();
    
    @FXML
    private TextField txtLibelleCategorie;
    @FXML
    private TextField txtDescriptionCategorie;
    @FXML
    private TextField txtDomaineCategorie;
    @FXML
    private TextField txtRechercheCategorie;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherAllPublications();
        afficherAllCategories();
    }  
    
    public void afficherAllPublications(){
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllPublications();

        for(PublicationForum e:le)
        {
            obl.add(e);
        }  
        
        idPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
        datePublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categoriePublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        creeParPublication.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        
        tableListePublication.setItems(obl);
        tableListePublication.setEditable(true);
    }
    
    
    public void afficherAllCategories(){
        ArrayList<CategoriePub> lc = (ArrayList<CategoriePub>) CategoriePubService.getAllCategoriePub();
        for(CategoriePub c:lc)
        {
            obCateg.add(c);
        }
        idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionCategorie.setCellValueFactory(new PropertyValueFactory<>("description"));
        domaineCategorie.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        nbrPublicationCategorie.setCellValueFactory(new PropertyValueFactory<>("nbPublication"));

        tableListeCategorie.setItems(obCateg);
        tableListeCategorie.setEditable(true);
    }
    
    
    @FXML
    private void supprimerPublication(ActionEvent event) {
        
        int id = tableListePublication.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        int index = tableListePublication.getSelectionModel().getSelectedIndex(); 
        PublicationForumService.deletePublication(id);
        
        tableListePublication.getItems().remove(index);
    }
    
    @FXML
    private void archiverPublication(ActionEvent event) {
        int id = tableListePublication.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        int index = tableListePublication.getSelectionModel().getSelectedIndex(); 
        PublicationForumService.archiverPublication(id);
        
        clearTable(tableListePublication);
        afficherAllPublications();
    }
    
    public void clearTable(TableView table) {
       for ( int i = 0; i< table.getItems().size(); i++) {
            table.getItems().clear();
        } 
    }

//    public void onEditChanged(TableColumn.CellEditEvent<Evenement, String> event) {
//        PublicationForum p = tableListePublication.getSelectionModel().getSelectedItem();
//        p.setTitre(event.getNewValue());
//        p.setId(PublicationForum.getSelectionModel().getSelectedItem().getId());
//        EvenementDAO.updateEvenement(e);
//        System.out.println(event.getNewValue());
//    }


//    <TableColumn fx:id="col_adresse" onEditCommit="#onEditChanged3" prefWidth="70.0" text="adresse" />
//    public void onEditChanged3(TableColumn.CellEditEvent<PublicationForum, String> event) {
//        PublicationForum p = tableListePublication.getSelectionModel().getSelectedItem();
//        e.setAdresse(event.getNewValue());
//        e.setId(PublicationForum.getSelectionModel().getSelectedItem().getId());
//
//        EvenementDAO.updateEvenement(e);
//        System.out.println(event.getNewValue());
//    }

    @FXML
    private void btnViderFormulaireCategorie(ActionEvent event) {
        txtLibelleCategorie.clear();
        txtDescriptionCategorie.clear();
        txtDomaineCategorie.clear();
    }

    @FXML
    private void btnDeleteCategorie(ActionEvent event) {
        int id = tableListeCategorie.getSelectionModel().getSelectedItem().getId();
        int index = tableListeCategorie.getSelectionModel().getSelectedIndex(); 
        CategoriePubService.delete(id);
        
        clearTable(tableListeCategorie);
        afficherAllCategories();
    }

    @FXML
    private void btnAddCategorie(ActionEvent event) {
        if ( !(ControlleSaisie.estVide(txtLibelleCategorie, "nom")) 
            && !(ControlleSaisie.estVide(txtDescriptionCategorie, "prenom")) 
            && !(ControlleSaisie.estVide(txtDomaineCategorie, "prenom")) ){
            CategoriePub c = new CategoriePub();

            c.setLibelle(txtLibelleCategorie.getText());
            c.setDescription(txtDescriptionCategorie.getText());
            c.setDomaine(txtDomaineCategorie.getText());
            
            CategoriePubService.add(c);
            clearTable(tableListeCategorie);
            afficherAllCategories();

            
            TrayNotification tray = new TrayNotification("succès", "Catégorie ajoutée", SUCCESS);
            tray.showAndWait();
        }
    }

    @FXML
    private void rechercherCategorie(KeyEvent event) {
        clearTable(tableListeCategorie);
        ArrayList<CategoriePub> lc = (ArrayList<CategoriePub>) CategoriePubService.rechercheKeyWord(txtRechercheCategorie.getText());
        for(CategoriePub c:lc)
        {
            obCateg.add(c);
        }
        idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionCategorie.setCellValueFactory(new PropertyValueFactory<>("description"));
        domaineCategorie.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        nbrPublicationCategorie.setCellValueFactory(new PropertyValueFactory<>("nbPublication"));

        tableListeCategorie.setItems(obCateg);
        tableListeCategorie.setEditable(true);
        
    }
    
}
