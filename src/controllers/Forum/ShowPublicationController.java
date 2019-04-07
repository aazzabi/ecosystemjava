/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import com.sun.javafx.scene.control.skin.TableColumnHeader;
import entities.CategoriePub;
import entities.CommentairePublication;
import entities.PublicationForum;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import services.CategoriePubService;
import services.CommentairePublicationService;
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
    private TableColumn<CommentairePublication, String> descriptionCommentaire;
    @FXML
    private TableColumn<CommentairePublication, String> userCommentaire;
    @FXML
    private TableColumn<CommentairePublication, Date> dateCommentaire;
    @FXML
    private TableColumn<CommentairePublication, String> signalisationCommentaire;
    @FXML
    private TableColumn<CommentairePublication, Integer> likesCommentaire;
    @FXML
    private TableColumn<CommentairePublication, Integer> dislikesCommentaire;
    @FXML
    private TableView<CommentairePublication> tableListeCommentaire;

    ObservableList<CommentairePublication> obl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<CommentairePublication, ImageView> photo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void afficherPublication(PublicationForum p) {
        txtTitrePublication.setText(p.getTitre());
        txtDescriptionPublication.setText(p.getDescription());
        txtDatePublication.setText(String.valueOf(p.getCreatedAt()));
        System.out.println(p.getNbrCommentaires());
        txtNbrCommentaire.setText(String.valueOf(p.getNbrCommentaires()));
        txtIdPublication.setText(String.valueOf(p.getId()));
        txtEtatPublication.setText(p.getEtat());
        txtCategoriePublication.setText(p.getCategorie());
        afficherAllCommentaires(p.getId());
    }
    
    public void afficherAllCommentaires(int id){
        ArrayList<CommentairePublication> le = (ArrayList<CommentairePublication>) PublicationForumService.getAllCommentairesByPublication(id);
        System.out.println(id);
        
        for(CommentairePublication e:le)
        {
            obl.add(e);
            System.out.println(e);
        }  

        descriptionCommentaire.setCellValueFactory(new PropertyValueFactory<>("description"));
        userCommentaire.setCellValueFactory(new PropertyValueFactory<>("createdByName"));
        dateCommentaire.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        photo.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getPhotoFile())));
        signalisationCommentaire.setCellValueFactory(new PropertyValueFactory<>("nbSignalisation"));
        likesCommentaire.setCellValueFactory(new PropertyValueFactory<>("likes"));
        dislikesCommentaire.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
        
        tableListeCommentaire.setItems(obl);
        tableListeCommentaire.setEditable(true);
    }

    @FXML
    private void deleteCommentaire(ActionEvent event) {
        int id = tableListeCommentaire.getSelectionModel().getSelectedItem().getId();
        int index = tableListeCommentaire.getSelectionModel().getSelectedIndex(); 
        System.out.println("id to del "+id);
        System.out.println("id to del "+index);
        CommentairePublicationService.delete(id);
        
        clearTable(tableListeCommentaire);
        afficherAllCommentaires(Integer.valueOf(txtIdPublication.getText()));
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        int id = tableListeCommentaire.getSelectionModel().getSelectedItem().getId();
        int index = tableListeCommentaire.getSelectionModel().getSelectedIndex(); 
        System.out.println("id to del "+id);
        System.out.println("id to del "+index);
        CommentairePublicationService.deleteCommentaireByIdUser(id);
        CommentairePublicationService.delete(id);
        
        clearTable(tableListeCommentaire);
        afficherAllCommentaires(Integer.valueOf(txtIdPublication.getText()));
    }
    
    public void clearTable(TableView table) {
       for ( int i = 0; i< table.getItems().size(); i++) {
            table.getItems().clear();
        } 
    }
}
