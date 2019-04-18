/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controllers.MainAdminScreenController;
import entities.reparateur.AnnounceRep;
import entities.reparateur.Reparateur;
import entities.reparateur.Reparation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.AnnounceRepService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class AllreparateursController implements Initializable {
    
    @FXML
    private VBox content_product;
    
    
    
    @FXML
    private AnchorPane root1;

    @FXML
    private JFXTextField rech;

    @FXML
    private JFXComboBox<String> combobox;
    
    ObservableList<Reparateur> Allreps = UserService.getTtReparateur();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        afficherAll(Allreps);
        
        ObservableList<String> status = FXCollections.observableArrayList();
        status.addAll("Tout", "Téléphone", "Meuble", "Electroménager");
        combobox.getItems().addAll(status);
        // TODO
    }    
    
    private void afficherAll(ObservableList<Reparateur> listRep) {
        HBox row;
        int index = 0;
        
        row = new HBox();
        row.setPadding(new Insets(10, 10, 10, 10));
        row.setSpacing(10);
        
        content_product.getChildren().add(row);
        
        for (Reparateur rep : listRep) {
            
            Label nomRep = new Label(rep.getUsername());
            nomRep.setStyle("-fx-font-size: 11pt");
            nomRep.setStyle("-fx-font-family: 'Segoe UI Semibold'");
            
            Label description = new Label("Description: " + rep.getDescription());
            Label specialite = new Label("Specialité: " + rep.getSpecialite());
            Label adresse = new Label("Adresse: " + rep.getAdresse());
            Label numTel = new Label("Numéro Téléphone: " + rep.getNumtel());
            Label numLocal = new Label("Numéro Local: " + Integer.toString(rep.getNumerofix()));
            Label type = new Label("Type :" + rep.getType());
            type.setStyle("-fx-text-fill: red");
            
            if (index % 4 == 0) {
                row = new HBox();
                row.setPadding(new Insets(3, 3, 3, 3));
                row.setSpacing(10);
                //row.getStyleClass().add("content-item");
                content_product.getChildren().add(row);
            }
            
            VBox content = new VBox();
            content.setPadding(new Insets(5, 5, 5, 5));
            
            content.setSpacing(10);
            content.getStyleClass().add("-fx-background-radius: 10 10 10 10; -fx-background-color: #FFFAFA; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            
            Image image = null;
            try {
                image = new Image(new FileInputStream("C:\\wamp64\\www\\ecosystemweb\\web\\uploads\\user\\photo\\" + rep.getPhoto()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AllAnnonceRepController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(255);
            imageView.setFitWidth(246);
            
            content.getChildren().addAll(imageView, nomRep, type, description, specialite, adresse, numTel, numLocal);
            Button item = new Button("", content);
            content.setOnMouseClicked(mouseEvent -> {
                
            });
            row.getChildren().add(item);
            
            index++;
        }
        
    }
    
     public void retour() {
        try {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reparateur/ReparateurMainScUser.fxml"));
            root1.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
      @FXML
    void filtrer(ActionEvent event) {
        String selected = combobox.getSelectionModel().getSelectedItem();
        
        Allreps.stream().filter(e -> e.getSpecialite().equals(selected));
        if (selected.equals(("Téléphone"))) {
            content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(Allreps.stream().filter(e -> e.getSpecialite().equals(selected)).collect(Collectors.toCollection(FXCollections::observableArrayList)));

        } else if (selected.equals(("Meuble"))) {
      content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(Allreps.stream().filter(e -> e.getSpecialite().equals(selected)).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } else if (selected.equals(("Electroménager"))) {
        content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(Allreps.stream().filter(e -> e.getSpecialite().equals(selected)).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } else {
          content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(Allreps);
        }

    }

    @FXML
    void Rechercher(KeyEvent event) {
        if (!rech.getText().equals("")) {
            content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(Allreps.stream().filter(e -> e.getUsername().equals(rech.getText())).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } else {
            afficherAll(Allreps);
        }

    }
    
}
