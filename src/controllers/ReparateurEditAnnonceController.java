/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.AnnounceRep;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import services.AnnounceRepService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class ReparateurEditAnnonceController implements Initializable {

    @FXML
    private ImageView a_image;

    @FXML
    private JFXTextField a_desc;

    @FXML
    private JFXTextField a_titre;

    @FXML
    private JFXComboBox<String> a_cat;

    @FXML
    private JFXComboBox<String> a_rep;

    @FXML
    private Label a_id;

    @FXML
    private Label a_datepub;

    @FXML
    private Label a_user;
    
    @FXML
    private JFXTextField a_prix;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(AnnounceRep ann) {
        a_image.fitHeightProperty();
        a_image.fitWidthProperty();
        a_image.setImage(ann.getImage());
        
        a_titre.setText(ann.getTitre());
        a_desc.setText(ann.getDescription());
        a_id.setText(Integer.toString(ann.getId()));
        a_datepub.setText("Date Publication "+ann.getDatePub());
        a_user.setText("Crée par :"+ann.getNomUser());
        System.out.println(ann.getTitre());
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll("Electroménager","Téléphone","Meuble");
        a_cat.getItems().addAll(categories);
        a_cat.setValue(ann.getCategorie());
        a_rep.getItems().addAll(AnnounceRepService.getAllReparateur());
        a_rep.setValue(ann.getNomRep());

    }
    
    public void confirmer ()
    {
        
        
        
        int idAnn=Integer.parseInt(a_id.getText());
        AnnounceRep ann=new AnnounceRep();
        ann=AnnounceRepService.getAnnounceRep(idAnn);
        ann.setCategorie(a_cat.getSelectionModel().getSelectedItem());
        ann.setTitre(a_titre.getText());
        ann.setDescription(a_desc.getText());
        ann.setNomRep(a_rep.getSelectionModel().getSelectedItem());
        ann.setRepId(UserService.getIdRep(a_rep.getSelectionModel().getSelectedItem()));
        ann.setPrix(Integer.parseInt(a_prix.getText()));
        AnnounceRepService.edit(ann);
        System.exit(0);
        
        
       
        
        
        
        
        
    }

}
