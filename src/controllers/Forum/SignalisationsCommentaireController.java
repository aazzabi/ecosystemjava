/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import entities.PublicationForum;
import entities.SignalisationCommentaire;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CommentairePublicationService;
import services.PublicationForumService;
import services.SignalisationCommentairePubService;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class SignalisationsCommentaireController implements Initializable {

    @FXML
    private TableColumn<SignalisationCommentaire, Integer> colId;
    @FXML
    private TableColumn<SignalisationCommentaire, String> colPublication;
    @FXML
    private TableColumn<SignalisationCommentaire, String> colCommentaire;
    @FXML
    private TableColumn<SignalisationCommentaire, String> colEcritePar;
    @FXML
    private TableColumn<SignalisationCommentaire, String> colCause;
    @FXML
    private TableColumn<SignalisationCommentaire, String> colSignalerPar;
    @FXML
    private Button btnDeleteComm;
    @FXML
    private Button btnDeleteUser;
    ObservableList<SignalisationCommentaire> obl = FXCollections.observableArrayList();
    @FXML
    private TableView<SignalisationCommentaire> tableSignalisation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherAllSignalisation();
    } 
    
    public void afficherAllSignalisation(){
        ArrayList<SignalisationCommentaire> le = (ArrayList<SignalisationCommentaire>) SignalisationCommentairePubService.getAllSignalisation();

        for(SignalisationCommentaire e:le)
        {
            obl.add(e);
        }  
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPublication.setCellValueFactory(new PropertyValueFactory<>("publicationLibelle"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<>("commentaireLibelle"));
        colEcritePar.setCellValueFactory(new PropertyValueFactory<>("commEcritPar"));
        colSignalerPar.setCellValueFactory(new PropertyValueFactory<>("commSignaleePar"));
        colCause.setCellValueFactory(new PropertyValueFactory<>("cause"));
        
        tableSignalisation.setItems(obl);
        tableSignalisation.setEditable(true);
    }

    @FXML
    private void deleteCommentaire(ActionEvent event) {
        int id = tableSignalisation.getSelectionModel().getSelectedItem().getId();
        int index = tableSignalisation.getSelectionModel().getSelectedIndex(); 
        SignalisationCommentairePubService.deleteCommentaireBySingalisationId(id);
        
        clearTable(tableSignalisation);
        afficherAllSignalisation();
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        int id = tableSignalisation.getSelectionModel().getSelectedItem().getId();
        int index = tableSignalisation.getSelectionModel().getSelectedIndex(); 
        SignalisationCommentairePubService.deleteUserBySingalisationId(id);
        SignalisationCommentairePubService.deleteCommentaireBySingalisationId(id);
        
        clearTable(tableSignalisation);
        afficherAllSignalisation();
    }
    
    
    public void clearTable(TableView table) {
       for ( int i = 0; i< table.getItems().size(); i++) {
            table.getItems().clear();
        } 
    }
}
