/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Reparateur;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nomUser;
    @FXML
    private TextField prenomUser;
    @FXML
    private TextField emailUser;
    @FXML
    private TextField photoUser;
    @FXML
    private TextField pseudoUser;
    @FXML
    private TextField nomProprieteUser;
    @FXML
    private TextField rueUser;
    @FXML
    private TextField villeUser;
    @FXML
    private TextField telephoneUser;
    @FXML
    private PasswordField mdpUser;
    @FXML
    private PasswordField confirmationMdpUser;
    @FXML
    private Button btnAddUser;
    
    
    @FXML
    private TextField nomReparateur;
    @FXML
    private TextField prenomReparateur;
    @FXML
    private TextField emailReparateur;
    @FXML
    private TextField photoReparateur;
    @FXML
    private TextField pseudoReparateur;
    @FXML
    private TextField adresseReparateur;
    @FXML
    private TextField numTelReparateur;
    @FXML
    private TextField numFixeReparateur;
    @FXML
    private TextField specialiteReparateur;
    @FXML
    private PasswordField mdpReparateur;
    @FXML
    private PasswordField confirmationMdpReparateur;
    @FXML
    private TextField descriptionReparateur;
    @FXML
    private TextField horaireTravailReparateur;
    @FXML
    private Button btnAddReparateur;
        
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    @FXML
    void goToLogin(ActionEvent event) throws SQLException, IOException, Exception {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/Login.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();   
    }
    
    @FXML 
    void addUser(ActionEvent event) throws SQLException, IOException, Exception {
        Utilisateur u = new Utilisateur();
        
        u.setNom(nomReparateur.getText());
        u.setPrenom(prenomReparateur.getText());
        u.setEmail(emailReparateur.getText());
        u.setEmailCanonical(emailReparateur.getText());
        u.setUsername(pseudoReparateur.getText());
        u.setUsernameCanonical(pseudoReparateur.getText());
        u.setPhoto(photoReparateur.getText());
        u.setNomPropriete(nomProprieteUser.getText());
        u.setPassword(mdpUser.getText());
        u.setEnabled(true);
        u.setDiscr("user");
        u.setRoles("a:0:{}");

        UserService.Inscription(u);
        Notification.confirmationBox("Votre compte a été crée avec succés", "User added");
    }
    
    @FXML
    void addReparateur(ActionEvent event) throws SQLException, IOException, Exception {
        Reparateur r = new Reparateur();
        r.setNom(nomUser.getText());
        r.setPrenom(prenomUser.getText());
        r.setEmail(emailUser.getText());
        r.setEmailCanonical(emailUser.getText());
        r.setNumerotel(numTelReparateur.getText());
        r.setNumerofix(numFixeReparateur.getText());
        r.setUsername(pseudoUser.getText());
        r.setUsernameCanonical(pseudoUser.getText());
        r.setAdresse(adresseReparateur.getText());
        r.setPhoto(photoUser.getText());
        r.setPassword(mdpReparateur.getText());
        r.setSpecialite(specialiteReparateur.getText());
        r.setDescription(descriptionReparateur.getText());
        r.setHoraire(horaireTravailReparateur.getText());
        r.setEnabled(true);
        r.setDiscr("reparateur");
        r.setRoles("a:1:{i:0;s:15:\"ROLE_REPARATEUR\";}");
        
        UserService.InscriptionReparateur(r);
        Notification.confirmationBox("Votre compte a été crée avec succés", "User added");

    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
