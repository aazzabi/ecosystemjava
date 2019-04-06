/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.CategoriePub;
import entities.PublicationForum;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PublicationForumService;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class ShowPublicationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void afficherPublication(CategoriePub c) {
//        txtIdPublication.setText(String.valueOf(c.getId()));
//        txtLibelleCategorie.setText(c.getLibelle());
//        txtDescriptionCategorie.setText(c.getDescription());
//        txtDomaineCategorie.setText(c.getDomaine());
//        txtNbrPubCategorie.setText(String.valueOf(c.getNbPublication()));
//        afficherAllPublicationsCategorie(c.getId());
    }
    
    public void afficherAllCommentaires(int id ){
//        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllPublicationsByCategorie(id);
//
//        for(PublicationForum e:le)
//        {
//            obl.add(e);
//        }  
//        
//        idPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
//        datePublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
//        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
//        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
//        etatPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
//        categoriePublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
//        creeParPublication.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
//        
//        tableListePublication.setItems(obl);
//        tableListePublication.setEditable(true);
    }
}
