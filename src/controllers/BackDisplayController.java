/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Weepey
 */
public class BackDisplayController implements Initializable {

    @FXML
    private StackPane globalStackPane;
    @FXML
    private AnchorPane globalPane;
    @FXML
    private AnchorPane paneFeedApp;
    @FXML
    private JFXListView<?> eventList;
    @FXML
    private JFXScrollPane scrollePaneEvent;
    @FXML
    private Text textent;
    @FXML
    private ScrollPane scrolDetail;
    @FXML
    private GridPane gridDetails;
    @FXML
    private ScrollPane scrolDetailS;
    @FXML
    private GridPane gridDetailsS;
    @FXML
    private AnchorPane paneResume;
    @FXML
    private Text nbrApp;
    @FXML
    private Text nbrSig;
    @FXML
    private AnchorPane paneFeedSignal;
    @FXML
    private AnchorPane PaneStp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showStat(ActionEvent event) {
    }

    @FXML
    private void crudCatgorie(ActionEvent event) {
    }

    @FXML
    private void HideFeeds(MouseEvent event) {
    }
    
}
