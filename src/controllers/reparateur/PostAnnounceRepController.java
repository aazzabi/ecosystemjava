/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Session;
import entities.reparateur.AnnounceRep;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AnnounceRepService;
import utils.copyImages;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class PostAnnounceRepController implements Initializable {

    @FXML
    private JFXTextField a_titre;

    @FXML
    private JFXTextArea a_desc;

    @FXML
    private JFXComboBox<String> a_cat;

    @FXML
    private ImageView img_annonce;

    @FXML
    private Text txtAnnoncephoto;

    @FXML
    private JFXButton btn_photo_img;
    private String absolutePathPhotoAnnonce;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> status = FXCollections.observableArrayList();
        status.addAll("Téléphone", "Meuble", "Electroménager");
        a_cat.getItems().addAll(status);
    }

    public void confirmer() {
        AnnounceRep rep = new AnnounceRep();
        rep.setCategorie(a_cat.getSelectionModel().getSelectedItem());
        rep.setTitre(a_titre.getText());
        rep.setDescription(a_desc.getText());
        rep.setEtat("En cours");
        int idUser = Session.getCurrentSession();
        System.out.println("USER ID" + idUser);
        rep.setUserId(idUser);
        rep.setUrlPhoto(txtAnnoncephoto.getText());
        //copyImages.deplacerVers(txtAnnoncephoto, absolutePathPhotoAnnonce, "C:\\ecosystemjava\\src\\res\\Annonce\\photo\\");
        copyImages.deplacerVers(txtAnnoncephoto, absolutePathPhotoAnnonce, "C:\\wamp\\www\\ecosystemweb\\web\\uploads\\annoncerep\\photos\\");
        AnnounceRepService.add(rep);
        Stage s = (Stage) a_titre.getScene().getWindow();
        s.close();

    }

    public void annuler() {
        Stage s = (Stage) a_titre.getScene().getWindow();
        s.close();
    }

    @FXML
    private void photoAnnonceChooser(ActionEvent event
    ) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        btn_photo_img.setOnAction(e -> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoAnnonce = choix.getAbsolutePath();
                System.out.println("TEST" + absolutePathPhotoAnnonce);
                txtAnnoncephoto.setText(choix.getName());
                //Image imag = new Image(absolutePathPhotoAnnonce);
                //img_annonce.setImage(imag);
            } else {
                System.out.println("Image introuvable");
            }
        });
    }

}
