/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import entities.Annonce;
import iservices.IAnnonceService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import services.AnnonceService;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class AllAnnoncesController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private FlowPane flow;
    @FXML
    private BorderPane container;

    private IAnnonceService annonceService;
    public static ObservableList<Annonce> obsl;
    public static int indice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        annonceService = new AnnonceService();
        CardsAnnonceController.i=0;
        ArrayList<Annonce> annonces = new ArrayList<>();
        annonces = (ArrayList) annonceService.getall();
        obsl = FXCollections.observableArrayList(annonces);
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/Annonce/CardsAnnonce.fxml"));
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void alphabetiqueaz(ActionEvent event) {
    }

    @FXML
    private void prixasc(ActionEvent event) {
    }

    @FXML
    private void prixdesc(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
    }

}
