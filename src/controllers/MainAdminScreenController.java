/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ecosystemjava.Launcher;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class MainAdminScreenController implements Initializable, ChangeCallback {

    @FXML
    private AnchorPane root;
    
        @FXML
    private AnchorPane menu;
    
    @FXML
    private AnchorPane root1;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private  JFXDrawer drawer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println("Screen ADMIN");
        if (!Launcher.isSplashLoaded) {
            loadSplashScreen();
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SidePanel.fxml"));
            VBox box = loader.load();
            SidePanelController controller = loader.getController();
            controller.setCallback((ChangeCallback) this);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
           
            
            
            
            
            if (drawer.visibleProperty().getValue() == false) {
                drawer.setVisible(true);
            } else {
                drawer.setVisible(false);
            }

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    private void loadSplashScreen() {
        try {
            System.err.println("SPLASH admin");
            Launcher.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("/gui/Splash.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2.5), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2.5), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                   AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/gui/mainadminscreen.fxml")));
                   update ("/gui/Annonce/annonceAdmin.fxml");
                   root.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(String src) {
        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource(src));
            root1.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
