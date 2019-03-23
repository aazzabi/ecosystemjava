/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private Button confirmationUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
