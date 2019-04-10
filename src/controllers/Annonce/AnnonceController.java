/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class AnnonceController implements Initializable {

    @FXML
    private FlowPane flow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int size = 0;
        if (AllAnnoncesController.indice == 0) {
            size = AllAnnoncesController.obsl.size();
        } else if (AllAnnoncesController.indice == 1) {
            size = AllAnnoncesController.prixasc.size();
        } else if (AllAnnoncesController.indice == 2) {
            size = AllAnnoncesController.prixdesc.size();
        } else if (AllAnnoncesController.indice == 3) {
            size = AllAnnoncesController.obsDate.size();
        }else if (AllAnnoncesController.indice == 4) {
            size = AllAnnoncesController.myannonces.size();
        }else if (AllAnnoncesController.indice == 5) {
            size = AllAnnoncesController.myannoncesCAt.size();
        }else if (AllAnnoncesController.indice == 6) {
            size = AllAnnoncesController.listsearch.size();
        }

  
            Node[] nodes = new Node[size];

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
    }
