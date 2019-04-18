/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import controllers.MainAdminScreenController;
import entities.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class OffreCompteProController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private AnchorPane root1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1 = new Image("/res/Offre1.png");
        img1.setImage(image1);
        Image image2 = new Image("/res/Offre2.png");
        img2.setImage(image2);
        Image image3 = new Image("/res/Offre3.png");
        img3.setImage(image3);
    }

    public void Demander() {

        if (UserService.getTtUtilisateur().stream().filter(e -> e.getRoles().contains("ROLE_REPARATEUR")).anyMatch(e -> e.getId() == Session.getCurrentSession())) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/reparateur/PostComptePro.fxml"));
            /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                PostCompteProController controller = fxmlLoader.getController();
                controller.initData(Session.getCurrentSession());

                stage.setTitle("Demande d'un compte PROF");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReparateurMainScController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vous devez posséder un compte réparateur ");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez posséder un compte réparateur");

            alert.showAndWait();
        }

    }

    public void retour() {
        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reparateur/ReparateurMainScUser.fxml"));
            root1.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
