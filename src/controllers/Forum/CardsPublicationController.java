/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import entities.PublicationForum;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.PublicationForumService;

/**
 *
 * @author arafe
 */
public class CardsPublicationController implements Initializable{
    @FXML
    private HBox hbox;
    static int i;
    public int t;
    @FXML
    private Text lblTitre;
    @FXML
    private Text lblDescription;
    @FXML
    private Text lblNbrCommentaire;
    @FXML
    private Text lblNbrVues;
    private int lblId;
    private Button btnShowPublication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
         if (ForumUserController.indice==0){
            lblTitre.setText(ForumUserController.obsl.get(i).getTitre());
            lblDescription.setText(ForumUserController.obsl.get(i).getDescription().toString());
            System.out.println(String.valueOf(ForumUserController.obsl.get(i).getNbrCommentaires()));
            lblNbrCommentaire.setText(String.valueOf(ForumUserController.obsl.get(i).getNbrCommentaires()));
            lblNbrVues.setText(String.valueOf(ForumUserController.obsl.get(i).getNbrVues()));
            lblId= ForumUserController.obsl.get(i).getId();
            i++;
        }

    }

    @FXML
    private void showPublication(ActionEvent event) {
        System.out.println("j'ai clicker ici");
        FXMLLoader Loader = new FXMLLoader();
        PublicationForum pub = PublicationForumService.getPublicationById(lblId);
        Loader.setLocation(getClass().getResource("/gui/forum/showPublicationUser.fxml"));
        try {
            Loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        ShowPublicationUserController pubCtr = Loader.getController();
        pubCtr.afficherPublication(pub);
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        event.consume();
            

        System.out.println("Button clicked : id = "+ lblId);
    }
 
}
