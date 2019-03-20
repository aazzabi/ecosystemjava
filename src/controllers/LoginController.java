/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Session;
import entites.Utilisateur;
import java.io.IOException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txt_user_name;

    @FXML
    private PasswordField txt_password;

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    void connexionUtilisateur(ActionEvent event) throws SQLException, IOException, Exception {
        UserService us = new UserService();
        Utilisateur u = new Utilisateur();
        u.setUsername(txt_user_name.getText());
        u.setPassword(txt_password.getText());
        if (us.login(u) == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("user inccorrect");
            alert.setContentText("please check your informations!");
            alert.showAndWait();
        } else {
            Session.start(u.getId());
            System.out.println(Session.getCurrentSession());
            if (us.login(u) != null && us.testMotDePasse(txt_password.getText(),u.getPassword())) {
                System.out.println("5deeeem");
                if (us.login(u).getRoles().contains("ROLE_ADMIN")) {
                     Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/ReparateurMainSc.fxml")));
                    dialogStage.setScene(scene);
                    dialogStage.show();
                } else {
                   Node node = (Node)event.getSource();
                    dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/user.fxml")));
                    dialogStage.setScene(scene);
                    dialogStage.show();
                }

            }
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
