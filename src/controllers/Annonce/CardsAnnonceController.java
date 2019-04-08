/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import controllers.Annonce.AllAnnoncesController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class CardsAnnonceController implements Initializable {

    @FXML
    private ImageView img_annoce;
    @FXML
    private Label lbl_titre;
    @FXML
    private Button consulter;
    @FXML
    private Label lbl_prix;
    @FXML
    private Button likes;
    @FXML
    private Button signaler;
    @FXML
    private Button pannier;
    @FXML
    private VBox vbox;
    static int i;
    public int t;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if (AllAnnoncesController.indice==0){
            lbl_titre.setText(AllAnnoncesController.obsl.get(i).getTitre());
        lbl_prix.setText("$"+AllAnnoncesController.obsl.get(i).getPrix().toString());

        t=AllAnnoncesController.obsl.get(i).getId();
        //pri=Integer.parseInt(prixx.getText());
           System.out.println(AllAnnoncesController.obsl.get(i).getPhoto());
         Image imag = new Image("file:/C:/wamp64/www/ecosystemweb/web/uploads/Annonce/photo/"+AllAnnoncesController.obsl.get(i).getPhoto());
         img_annoce.setImage(imag);
        i++;
        }
    }    
    
}
