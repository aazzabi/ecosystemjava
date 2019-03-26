/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Weepey
 */
public class AddMissionController implements Initializable {

    @FXML
    private StackPane rootStackPane;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField animateur;
    @FXML
    private JFXComboBox<?> type;
    @FXML
    private JFXComboBox<?> categorie;
    @FXML
    private JFXDatePicker dateDebut;
    @FXML
    private JFXDatePicker dateFin;
    @FXML
    private JFXTimePicker heureDebut;
    @FXML
    private JFXTimePicker heureFin;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXRadioButton priveE;
    @FXML
    private JFXRadioButton publiqueE;
    @FXML
    private JFXTextArea description;
    @FXML
    private VBox ticketBox;
    @FXML
    private JFXTextField nomTicket;
    @FXML
    private JFXDatePicker dateDTicket;
    @FXML
    private JFXDatePicker dateFTicket1;
    @FXML
    private JFXTextField nbrTicket;
    @FXML
    private JFXTextField prix;
    @FXML
    private ImageView imageEvent;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton b31;
    @FXML
    private JFXButton b311;
    @FXML
    private JFXButton b312;
    @FXML
    private JFXButton b313;
    @FXML
    private JFXButton exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fixDateFin(MouseEvent event) {
    }

    @FXML
    private void priveSelected(ActionEvent event) {
    }

    @FXML
    private void publiqueSelected(ActionEvent event) {
    }

    @FXML
    private void AddEventAction(ActionEvent event) {
    }

    @FXML
    private void fixDateFinTicket(MouseEvent event) {
    }

    @FXML
    private void addImage(ActionEvent event) {
    }

    @FXML
    private void navigate(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }
    
}
