/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import entities.Session;
import entities.signalAnnonce;
import iservices.ISignalAnnonceService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import services.SignalAnnonceService;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class ScenesignalerController implements Initializable {

    @FXML
    private Button btn_sig;
    @FXML
    private RadioButton rd_vi;
    @FXML
    private RadioButton rd_arnaq;
    @FXML
    private RadioButton rd_har;
    @FXML
    private RadioButton rd_dis;
     Stage dialogStage = new Stage();
    private String lbl;
    private int id;
     private ISignalAnnonceService signalservice;
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signalservice = new SignalAnnonceService();
        
        // TODO
    }    
        public int getId(int x)
        {
            return id=x;
        }
    @FXML
    private void signaler(ActionEvent event) {
        
        
        if (rd_vi.isSelected()) {
            lbl = "violance";
        }
        if (rd_arnaq.isSelected()) {
            lbl = "arnaque";
        }
        if (rd_har.isSelected()) {
            lbl ="Harcelement";
        }
        if (rd_dis.isSelected()) {
            lbl = "Discour insitant à la haine";
        }
        signalAnnonce e = new signalAnnonce(id, Session.getCurrentSession(), lbl);
        signalservice.add(e);
        signalservice.nbSignalParAnnonce();
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        event.consume();
        
        
    }

   
    
}
