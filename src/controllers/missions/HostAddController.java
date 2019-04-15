/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.missions;


import static gui.missions.HostVariableManager.WINDOW_WIDTH;
import static gui.missions.HostVariableManager.WINDOW_HEIGHT;
import  gui.missions.HostVariableManager;
import loaders.HostListLoader;
import entities.Host;
import services.HostGuiService;
import services.HostService;
import utils.MapClass;
import static utils.MapClass.LatLngToString;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.teamdev.jxmaps.Marker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hiro
 */
public class HostAddController implements Initializable {
    
    private boolean MapOn = false;
    private MapClass Map;
    private Marker Mark;
    private String Localisation = "";
    @FXML
    private JFXTextField OwnerTF;
    @FXML
    private DatePicker DateStart;
    @FXML
    private DatePicker DateEnd;
    @FXML
    private JFXTextField TotalPlacesTF;
    @FXML
    private JFXButton AddButton;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private JFXButton HostButton;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private JFXButton OpenMap_Button;
    @FXML
    private JFXButton TakePosition_Button;
    @FXML
    private AnchorPane content;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        HostVariableManager.setCurrentUserID(LoginController.getLoggedInUser().getId());  

    }    

    @FXML
    private void AddHostAction(ActionEvent event) {
        
//        OwnerTF.getCharacters().toString() == "" ||
        if( !ValidName() || !ValidDateStart() || !ValidDateEnd())
        {
            System.out.println("Error in typing");

        }
        else{
            Host HostToAdd = new Host();
            HostToAdd.setOwner(OwnerTF.getCharacters().toString());
            HostToAdd.setDateStart(java.sql.Date.valueOf(DateStart.getValue()));
            HostToAdd.setDateStart(java.sql.Date.valueOf(DateEnd.getValue()));
            HostToAdd.setTotalPlaces(Integer.parseInt(TotalPlacesTF.getCharacters().toString()));
            HostToAdd.setAvailablePlaces(Integer.parseInt(TotalPlacesTF.getCharacters().toString()));
            HostToAdd.setLocalisation(Localisation);
            HostToAdd.setOwnerID(HostVariableManager.getCurrentUserID());

            try {
                HostService.AddHost(HostToAdd);
            } catch (SQLException ex) {
                Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
            }

            OwnerTF.clear();
            TotalPlacesTF.clear();
            Map.ShutDownMap();

            ((Stage)AddButton.getScene().getWindow()).setScene(new Scene(HostListLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));   
            }
    }

    @FXML
    private void Return_Button(ActionEvent event) {
        ((Stage)AddButton.getScene().getWindow()).setScene(new Scene(HostListLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    @FXML
    private void AccessHostSection(ActionEvent event) {
        ((Stage)AddButton.getScene().getWindow()).setScene(new Scene(HostListLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    @FXML
    private void OpenMap_Action(ActionEvent event) {
        
        if (!MapOn){
            MapOn = !MapOn;
            Map = MapClass.LaunchMap("Map");
            TakePosition_Button.setDisable(false);
        }
        else if (MapOn){
            MapOn = !MapOn;
            Map.ShutDownMap();
            TakePosition_Button.setDisable(true);
        }
    }

    @FXML
    private void TakePosition_Action(ActionEvent event) {
        Localisation = LatLngToString(Map.GetLocationFromMap());
    }
    
    
    
    private boolean ValidName(){
        if (OwnerTF.getCharacters().toString() =="")
        {   OwnerTF.setStyle("-fx-border-color : Red; ");
            return false;
        }
        else return true;
    }
    private boolean ValidDateStart(){
        if (DateStart.getValue() == null)
        {
            DateStart.setStyle( "-fx-background-color : Red;" + DateStart.getStyle());
            return false;
        }
        else return true;
    }
    private boolean ValidDateEnd(){
        if (DateEnd.getValue() == null)
        {
            
            DateEnd.setStyle("-fx-background-color : Red;" + DateEnd.getStyle());
            return false;
        }
        else return true;
    }
    private boolean ValidTotalPlaces(){
        if (Integer.bitCount(Integer.valueOf(TotalPlacesTF.getCharacters().toString())) != 0){
            TotalPlacesTF.setStyle("-fx-background-color : Red;" + TotalPlacesTF.getStyle());
            return false;
        }
        else return true;
    }

    @FXML
    private void AccessSujetSection(ActionEvent event) throws IOException {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopicsModuleMenu.fxml"));
               AnchorPane pane = fxmlLoader.load();
               content.getChildren().setAll(pane);
    }
}
