/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecosystemjava;

import entities.Categorie_Evts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Categorie_EvtsService;
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
        stage.show();
        }
        catch (Exception exp){
            exp.printStackTrace();
            
        }
        
      
    }
    public static void main(String[] args) {
        
       /* Categorie_Evts c=new Categorie_Evts("java","java");
        Categorie_EvtsService cs=new  Categorie_EvtsService();
        cs.addCategorie_Evts(c);*/
        launch(args);
        
    }

}
