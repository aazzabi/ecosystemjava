/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loaders;

import static gui.HostVariableManager.WINDOW_HEIGHT;
import static gui.HostVariableManager.WINDOW_WIDTH;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hiro
 */
public class MissionListLoader extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/gui/HostList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Host List");
        Scene CurrentScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        CurrentScene.getStylesheets().add("gui/HostStyle.css");
        primaryStage.setScene(CurrentScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Parent GetRoot() {
        URL url;
        Parent CurrentRoot = new Parent() {
        };
        try {
            url = new File("src/gui/HostList.fxml").toURI().toURL();
            CurrentRoot = FXMLLoader.load(url);
        } catch (Exception ex) {
            Logger.getLogger(MissionDetailsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CurrentRoot;
    }
}
