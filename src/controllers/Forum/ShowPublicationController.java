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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PublicationForumService;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class ShowPublicationController implements Initializable {

    @FXML
    private Label txtTitrePublication;
    @FXML
    private Label txtDescriptionPublication;
    @FXML
    private Label txtDatePublication;
    @FXML
    private Label txtNbrCommentaire;
    @FXML
    private Label txtIdPublication;
    @FXML
    private Label txtEtatPublication;
    @FXML
    private Label txtCategoriePublication;
    @FXML
    private TableColumn<?, ?> descriptionCommentaire;
    @FXML
    private TableColumn<?, ?> userCommentaire;
    @FXML
    private TableColumn<?, ?> dateCommentaire;
    @FXML
    private TableColumn<?, ?> signalisationCommentaire;
    @FXML
    private TableColumn<?, ?> likesCommentaire;
    @FXML
    private TableColumn<?, ?> dislikesCommentaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void afficherPublication(PublicationForum p) {
        txtTitrePublication.setText(p.getTitre());
        txtDescriptionPublication.setText(p.getDescription());
        txtDatePublication.setText(String.valueOf(p.getCreatedAt()));
        txtNbrCommentaire.setText(String.valueOf(p.getNbrCommentaires()));
        txtIdPublication.setText(String.valueOf(p.getId()));
        txtEtatPublication.setText(p.getEtat());
        txtCategoriePublication.setText(p.getCategorie());
    }
    
    public void afficherAllCommentaires(int id ){
//        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllCommentairesByPublication(id);
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
