/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.PublicationForum;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import entities.Utilisateur;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PublicationForumService;
import services.UserService;
/**
 * FXML Controller class
 *
 * @author arafe
 */
public class UtilisateursController implements Initializable {

    @FXML
    private TableColumn<Utilisateur, String> colNom;
    @FXML
    private TableColumn<Utilisateur, String> colPrenom;
    @FXML
    private TableColumn<Utilisateur, String> colPseudo;
    @FXML
    private TableColumn<Utilisateur, String> colEmail;
    @FXML
    private TableColumn<Utilisateur, String> colTel;
    @FXML
    private TableColumn<Utilisateur, String> colVille;
    @FXML
    private Button btnDeleteUser;
    ObservableList<Utilisateur> obl = FXCollections.observableArrayList();
    @FXML
    private TableView<Utilisateur> tblUsers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherAllUsers();
    }    

    public void afficherAllUsers(){
        ArrayList<Utilisateur> le = (ArrayList<Utilisateur>) UserService.getAllUsers();

        for(Utilisateur e:le)
        {
            obl.add(e);
        }  
        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colPseudo.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        
        tblUsers.setItems(obl);
        tblUsers.setEditable(true);
    }
    
    
    @FXML
    private void deleteUser(ActionEvent event) {
    }
    
}
