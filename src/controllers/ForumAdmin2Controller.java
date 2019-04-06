/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class ForumAdmin2Controller implements Initializable {

    @FXML
    private TableView<?> ListeAnnonce;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private TableColumn<?, ?> date_creation;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private TableColumn<?, ?> etat;
    @FXML
    private TableColumn<?, ?> photo;
    @FXML
    private TableColumn<?, ?> likes;
    @FXML
    private TableColumn<?, ?> views;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_Edit;
    @FXML
    private JFXButton btn_Delete;
    @FXML
    private JFXButton btn_Clear;
    @FXML
    private Button btn_photo_img;
    @FXML
    private JFXTextField txt_Titre;
    @FXML
    private JFXTextField txt_discription;
    @FXML
    private JFXTextField txt_prix;
    @FXML
    private ComboBox<?> cmb_region;
    @FXML
    private JFXTextField txtAnnoncephoto;
    @FXML
    private ComboBox<?> cmb_cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
    }

    @FXML
    private void photoAnnonceChooser(ActionEvent event) {
    }
    
}
