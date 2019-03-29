/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecosystemjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.AnnonceService;
import utils.ConnectionBase;


/**
 *
 * @author FirasM
 */
public class Launcher extends Application {

    public static Boolean isSplashLoaded = false;
    
    @Override
    public void start(Stage stage)  {
        try
        {
         ConnectionBase cnx = ConnectionBase.getInstance();
         
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        }
        catch (Exception exp){
            exp.printStackTrace();
            
        }
        
      
    }
    public static void main(String[] args) {
        launch(args);
    }

}
