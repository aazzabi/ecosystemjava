/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.PublicationForum;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import services.PublicationForumService;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllPublications();
        for(PublicationForum e:le)
        {
            obl.add(e);
        }
                
        idPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
        datePublication.setCellValueFactory(new PropertyValueFactory<>("pub_created_at"));
        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categoriePublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        creeParPublication.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        
        tableListePublication.setItems(obl);
        tableListePublication.setEditable(true);

    }    
    
    
//    @FXML
//    private void supprimerevenement(ActionEvent event) {
//        
//        int id = table.getSelectionModel().getSelectedItem().getId();
//        int index = table.getSelectionModel().getSelectedIndex(); 
//        Evenement e = new Evenement();
//        e.setId(id);
//        service.serviceDeleteEvenement(e);
//        
//        table.getItems().remove(index);
//    }


//    public void onEditChanged(TableColumn.CellEditEvent<Evenement, String> event) {
//        PublicationForum p = tableListePublication.getSelectionModel().getSelectedItem();
//        p.setTitre(event.getNewValue());
//        p.setId(PublicationForum.getSelectionModel().getSelectedItem().getId());
//        EvenementDAO.updateEvenement(e);
//        System.out.println(event.getNewValue());
//    }


   
//    public void onEditChanged3(TableColumn.CellEditEvent<PublicationForum, String> event) {
//        PublicationForum p = tableListePublication.getSelectionModel().getSelectedItem();
//        e.setAdresse(event.getNewValue());
//        e.setId(PublicationForum.getSelectionModel().getSelectedItem().getId());
//
//        EvenementDAO.updateEvenement(e);
//        System.out.println(event.getNewValue());
//    }
    
}
