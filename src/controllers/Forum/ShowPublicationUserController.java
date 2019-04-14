/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import static controllers.Forum.CardsPublicationController.i;
import static controllers.Forum.ForumUserController.indice;
import static controllers.Forum.ForumUserController.obsl;
import entities.CommentairePublication;
import entities.PublicationForum;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javax.sound.sampled.AudioInputStream;
import services.CommentairePublicationService;
import services.PublicationForumService;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;
import utils.ControlleSaisie;
import utils.SpeechApi;

/**
 * @author arafe
 */
public class ShowPublicationUserController implements Initializable {
    
    static int i;
    public static ObservableList<CommentairePublication> obsl;
    @FXML
    private FlowPane flowCom;
    public static int indice;
    @FXML
    private Label txtTitrePublication;
    @FXML
    private Label txtDescriptionPublication;
    @FXML
    private Label txtDatePublication;
    @FXML
    private Label txtNbrCommentaire;
    @FXML
    private Label txtEtatPublication;
    @FXML
    private Label txtCategoriePublication;
    @FXML
    private TextField txtCommentaire;
    private int idPub;
    @FXML
    private Button btnCommenter;
    @FXML
    private ImageView son;
    @Override 
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public void afficherPublication(PublicationForum p) {
        txtTitrePublication.setText(p.getTitre());
        txtDescriptionPublication.setText(p.getDescription());
        txtDatePublication.setText(String.valueOf(p.getCreatedAt()));
        System.out.println(p.getNbrCommentaires());
        txtNbrCommentaire.setText(String.valueOf(p.getNbrCommentaires()));
        idPub = p.getId();
//        txtIdPublication.setText(String.valueOf(p.getId()));
        txtEtatPublication.setText(p.getEtat());
        txtCategoriePublication.setText(p.getCategorie());
        
        if (txtEtatPublication.getText() == "archivé") {
           txtCommentaire.setEditable(false); 
           btnCommenter.setDisable(true); 
        }
        afficherAllCommentairesCard(p.getId());
    }
    
    public void afficherAllCommentairesCard(int id){
        CardsCommentairesController.i=0;
        ArrayList<CommentairePublication> commentaires = new ArrayList<>();
        commentaires = (ArrayList) PublicationForumService.getAllCommentairesByPublication(id);
        obsl = FXCollections.observableArrayList(commentaires);
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/forum/CardsCommentaires.fxml"));
                flowCom.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
    }

    @FXML
    private void commenter(ActionEvent event) {
        if ( !(ControlleSaisie.estVide(txtCommentaire, "nom")) ) {
           CommentairePublication c = new CommentairePublication();
           c.setDescription(txtCommentaire.getText());
           c.setIdPublication(idPub);
           CommentairePublicationService.add(c);
           
           flowCom.getChildren().clear();
           txtCommentaire.clear();
           
           afficherAllCommentairesCard(idPub);
           txtNbrCommentaire.setText(String.valueOf(Integer.parseInt(txtNbrCommentaire.getText())+1 ) );
           
           
           TrayNotification tray = new TrayNotification("succès", "Commentaire ajouté", SUCCESS);
           tray.showAndWait();
        }
    }

    @FXML
    private void readDescription(MouseEvent event) {
        SpeechApi.speechApi(txtDescriptionPublication.getText());

    }
    @FXML
    private void readTitre(MouseEvent event) {
        SpeechApi.speechApi(txtTitrePublication.getText());

    }

}
