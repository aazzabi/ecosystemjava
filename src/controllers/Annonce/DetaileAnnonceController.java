/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import entities.Annonce;
import entities.Categorie_Annonce;
import iservices.IAnnonceService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.AnnonceService;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class DetaileAnnonceController implements Initializable {

    @FXML
    private ImageView img_ann;
    @FXML
    private Label lbl_desc;
    @FXML
    private Label lbl_Region;
    @FXML
    private Label lbl_viwes;
    @FXML
    private Label lbl_titre;
    @FXML
    private Label lbl_prix;

    public static int idd;
    private Image img;
    private IAnnonceService annonceService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        annonceService = new AnnonceService();
        Annonce a = annonceService.getAnnonceById(idd);
        lbl_titre.setText(a.getTitre());
        lbl_desc.setText(a.getDescription());
        lbl_Region.setText(a.getRegion());
        lbl_viwes.setText(Integer.toString(a.getViews()));
        lbl_prix.setText("DT"+" "+Double.toString(a.getPrix()));
        img = new Image("file:/C:/wamp64/www/ecosystemweb/web/uploads/Annonce/photo/" + a.getPhoto());
        img_ann.setImage(img);
    }

}
