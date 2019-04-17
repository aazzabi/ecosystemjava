/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


/**
 *
 * @author Hiro
 */
public class GlobalLibrary {
    
    
    
    // Function to find the index of an element in a primitive array in Java
    public static int find(int[] a, int target)
    {
	return IntStream.range(0, a.length)
					.filter(i -> target == a[i])
					.findFirst()
					.orElse(-1);	// return -1 if target is not found
    }
    
    
    
    
    // Generic function to find the index of an element in an object array in Java
    public static<T> int find(T[] a, T target)
    {
	return IntStream.range(0, a.length)
					.filter(i -> target.equals(a[i]))
					.findFirst()
					.orElse(-1);	// return -1 if target is not found
    }
    
    // Load new Scene
    public static void transition(String fxmlFile, StackPane stackPane) {
        try {
            URL url = new File(fxmlFile).toURI().toURL();
            StackPane pane = FXMLLoader.load(url);
            stackPane.getChildren().setAll(pane);
            FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
            ft.setNode(pane);
            ft.setFromValue(0.10);//dispartion
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(true);
            ft.play();
        } catch (MalformedURLException ex) {
            Logger.getLogger(GlobalLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GlobalLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String DateToString(Date DateToConvert){
        DateFormat CurrentDateFormat = new SimpleDateFormat("dd / MM / yyyy");
        return CurrentDateFormat.format(DateToConvert);
    }
}
