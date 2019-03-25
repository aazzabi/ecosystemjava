/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static controllers.InscriptionController.valideEmail;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import static tray.notification.NotificationType.ERROR;
import tray.notification.TrayNotification;

/**
 *
 * @author arafe
 */
public class ControlleSaisie {
    
    public static boolean estVide(TextField txtField, String nomField){
        if(txtField.getText().equals("")){
            txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Précisez votre "+ nomField , ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return true; 
        } else {
            txtField.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
            return false; 
        }
    }
    
    public static boolean estEmailValide(TextField txtField){
        if( !(valideEmail(txtField.getText())) ){
            txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Veuillez introduire un email valide", ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return false; 
        } else {
            txtField.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
            return true; 
        }
    }
    
    public static boolean estDeLongueur(TextField txtField, int a){
        if(txtField.getText().length()!= a ){
            txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Le taille doit etre de "+ a, ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return false; 
        } else {
            txtField.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
            return true; 
        }
    }
    
    public static boolean sontConforme(TextField txtField1 , String nomField1, TextField txtField2, String nomField2) {
        if ( !(txtField1.getText().equals(txtField2.getText())) ) {
            txtField1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            txtField2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Les champs "+ nomField1+" et "+ nomField2  +" doivent etre confromes", ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return true; 
        } else { return false; }
    }
}
