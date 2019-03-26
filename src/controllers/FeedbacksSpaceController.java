/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Weepey
 */
public class FeedbacksSpaceController implements Initializable {

    @FXML
    private ImageView img_apprec;
    @FXML
    private ImageView img_signal;
    @FXML
    private Button valid_btn;
    @FXML
    private TextArea feedArea;
    @FXML
    private RadioButton signal_box;
    @FXML
    private RadioButton apprec_box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitFeed(ActionEvent event) {
    }
    
}
