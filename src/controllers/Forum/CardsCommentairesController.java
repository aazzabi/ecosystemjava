/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import entities.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.CommentairePublicationService;
import services.SignalisationCommentairePubService;

/**
 *
 * @author arafe
 */
public class CardsCommentairesController implements Initializable {
    static int i;
    public int t;
    @FXML
    private Text lblCommentaire;
    @FXML
    private Text lblUser;
    @FXML
    private Text lblDate;
    @FXML
    private Button btnSignaler;
    @FXML
    private Button btnDislike;
    @FXML
    private Button btnLike;
    private int idComm;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         if (ShowPublicationUserController.indice==0){
             
            idComm = ShowPublicationUserController.obsl.get(i).getId();
            lblCommentaire.setText(ShowPublicationUserController.obsl.get(i).getDescription());
            lblUser.setText(ShowPublicationUserController.obsl.get(i).getCreatedByName());
            lblDate.setText(ShowPublicationUserController.obsl.get(i).getCreatedAt().toString());
             System.out.println(" who ? "+ShowPublicationUserController.obsl.get(i).getCreatedBy());
            if (Session.getCurrentSession() == ShowPublicationUserController.obsl.get(i).getCreatedBy()){
                btnSignaler.setVisible(false);
            }
            btnSignaler.setOnAction( e -> {
                StackPane root2 = new StackPane();
                ChoiceBox cb = new ChoiceBox();
                cb.getItems().addAll("Violence", "arnaque", "Harcelement", "Discour insitant à la haine");
                root2.getChildren().add(cb); 

                Button btnConfirme = new Button("Confirme"); // the button
                root2.getChildren().add(btnConfirme); 
                Scene secondScene = new Scene(root2, 250,80);
                
                root2.setAlignment(cb, Pos.TOP_CENTER);
                root2.setAlignment(btnConfirme, Pos.BOTTOM_CENTER);
                cb.setLayoutY(50);
                btnConfirme.setLayoutY(250);
                Stage secondStage = new Stage();
                secondStage.setScene(secondScene); // set the scene
                secondStage.setTitle("Second Form");
                secondStage.show();
                
                btnConfirme.setOnAction(a-> {
                    System.out.println("Signaler commentaire :  "+cb.getValue());
                    CommentairePublicationService.signaler(idComm);
                    SignalisationCommentairePubService.add(Session.getCurrentSession(), idComm, cb.getValue().toString());
                    secondStage.close();
                });
            });
            i++;
         }
    }

    @FXML
    private void signalerCommentaire(ActionEvent event) {
        System.out.println(idComm);
    }

    @FXML
    private void dislike(ActionEvent event) {
        System.out.println(idComm);
        CommentairePublicationService.dislike(idComm);
        btnDislike.setDisable(true);
    }

    @FXML
    private void like(ActionEvent event) {
        System.out.println(idComm);
        CommentairePublicationService.like(idComm);
        btnLike.setDisable(true);
    }
     
}
