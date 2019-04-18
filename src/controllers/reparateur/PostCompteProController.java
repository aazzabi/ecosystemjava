/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entities.reparateur.DemandeComptePro;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.DemandeCompteProService;
import utils.copyImages;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class PostCompteProController implements Initializable {

   @FXML
    private Text txtAnnoncephoto;

    @FXML
    private JFXButton btn_photo_img;
    private String absolutePathPhotoAnnonce;
    private int id;
    
        @FXML
    private JFXComboBox<String> combobox;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void initData(int  id) {
        
         ObservableList<String> status = FXCollections.observableArrayList();
        status.addAll("Standard", "Basique", "Ilimité");
        combobox.getItems().addAll(status);
        this.id=id;
        
        
        
        
    }    
    
     @FXML
    private void photoAnnonceChooser(ActionEvent event
    ) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
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
    
    public void confirmer()
    {
        
        DemandeComptePro dmd=new DemandeComptePro();
        dmd.setRepId(id);
        dmd.setStatut(combobox.getSelectionModel().getSelectedItem());
        dmd.setUrlPhoto(txtAnnoncephoto.getText());
        DemandeCompteProService.add(dmd);
        
        
        copyImages.deplacerVers(txtAnnoncephoto, absolutePathPhotoAnnonce, "C:\\wamp64\\www\\ecosystemweb\\web\\uploads\\demandecompte\\photos\\");
            Stage s = (Stage) combobox.getScene().getWindow();
        s.close();
    }
    
    public void annuler()
    {
              Stage s = (Stage) combobox.getScene().getWindow();
        s.close();
    }
    
}
