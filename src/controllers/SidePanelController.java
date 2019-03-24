/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SidePanelController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;

    private ChangeCallback callback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCallback(ChangeCallback callback) {
        this.callback = callback;
    }

    @FXML
    // a mettre les SRC
    private void navigate(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch (btn.getText()) {
            case "Acceuil":
                callback.update("");
                break;
            case "Announce":
                callback.update("");
                break;
             case "Réparation":
                callback.update("");
                break;
             case "Evenement":
                callback.update("");
                break;
             case "Recyclage":
                callback.update("");
                break;
             case "Forum":
                callback.update("");
                break;
             case "Signalisation":
                callback.update("");
                break;
             case "Utilisateur":
                callback.update("");
                break;
            
         
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

}
