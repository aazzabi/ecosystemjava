/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controllers.MainAdminScreenController;
import entities.Session;
import entities.reparateur.AnnounceRep;
import javafx.geometry.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import services.AnnounceRepService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class AllAnnonceRepController implements Initializable {

    @FXML
    private VBox content_product;

    @FXML
    private AnchorPane root1;

    @FXML
    private JFXComboBox<String> combobox;

    @FXML
    private JFXTextField rech;

    ObservableList<AnnounceRep> Allannonces = AnnounceRepService.getAnnounceRepList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<AnnounceRep> annonces = AnnounceRepService.getAnnounceRepList();
        afficherAll(annonces);

        ObservableList<String> status = FXCollections.observableArrayList();
        status.addAll("Tout", "Téléphone", "Meuble", "Electroménager");
        combobox.getItems().addAll(status);
    }

    private void afficherAll(ObservableList<AnnounceRep> annonces) {
        HBox row;
        int index = 0;

        Label detail = new Label("Détail d'une annonce de réparation");

        row = new HBox();
        row.setPadding(new Insets(10, 10, 10, 10));
        row.setSpacing(10);
        //row.getStyleClass().add("content-item");
        content_product.getChildren().add(row);

        Label prprix = new Label("proposez un prix");
        prprix.setStyle("-fx-font-size:12");

        for (AnnounceRep ann : annonces) {
            VBox vBoxX = new VBox();

            Label titre = new Label(ann.getTitre());
            Label Description = new Label(ann.getDescription());
            Label cat = new Label("Catégorie :" + ann.getCategorie());
            Label user = new Label("Ajoutée par  :" + ann.getNomUser());
            Label offre = new Label("Meilleur offre :" + Float.toString(ann.getPrix()) + "DT");
            String nomRep = ann.getNomRep();
            Label rep = null;
            if (nomRep != null) {
                rep = new Label("Proposé par :" + ann.getNomRep());
            } else {
                rep = new Label("Aucune propostion");
            }
            vBoxX.getChildren().add(detail);
            vBoxX.getChildren().add(titre);
            vBoxX.getChildren().add(Description);
            vBoxX.getChildren().add(user);
            vBoxX.getChildren().add(offre);
            vBoxX.getChildren().add(rep);

            PopOver popOver = new PopOver(vBoxX);

            System.out.println(ann.getTitre());
            if (index % 5 == 0) {
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
                image = new Image(new FileInputStream("C:\\wamp64\\www\\ecosystemweb\\web\\uploads\\annoncerep\\photos\\" + ann.getUrlPhoto()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AllAnnonceRepController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ImageView imageView = new ImageView(image);

            titre.setStyle("-fx-font-weight: bold");
            Label prix = new Label(String.valueOf("Meilleure offre :" + ann.getPrix()) + " TND");
            imageView.setFitHeight(255);
            imageView.setFitWidth(246);

            content.getChildren().addAll(imageView, titre, Description, prix);
            Button item = new Button("", content);
            content.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2) {
                    if (ann.getUserId()!= Session.getCurrentSession()) {
                        if (UserService.getTtUtilisateur().stream().filter(e -> e.getRoles().contains("ROLE_REPARATEUR")).anyMatch(e -> e.getId() == Session.getCurrentSession())) {

                            TextInputDialog dialog = new TextInputDialog();

                            dialog.setTitle("Offre");
                            dialog.setHeaderText("");
                            dialog.setContentText("Votre offre");

                            Optional<String> result = dialog.showAndWait();

                            result.ifPresent(name -> {
                                try {
                                    ann.setPrix(Integer.parseInt(name));
                                } catch (NumberFormatException exp) {
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Vous devez entrer un nombre");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous devez entrer un nombre");

                                    alert.showAndWait();
                                }
                                ann.setRepId(Session.getCurrentSession());
                                AnnounceRepService.edit(ann);
                            });
                        } else {
                            //ERREUR
                        }
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Vous ne pouvez pas proposer une offre a votre propre announce ");
                        alert.setHeaderText(null);
                        alert.setContentText("Vous ne pouvez pas proposer une offre a votre propre announce ");

                        alert.showAndWait();
                    }
                } else {
                    popOver.show(content);
                }

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

    public void ajouter() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/reparateur/PostAnnounceRep.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            PostAnnounceRepController controller = fxmlLoader.getController();

            stage.setTitle("Ajout d'une annonce de réparation");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReparateurMainScController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void filtrer(ActionEvent event) {
        String selected = combobox.getSelectionModel().getSelectedItem();
        ObservableList<AnnounceRep> annonces = AnnounceRepService.getAnnounceRepList();
        annonces.stream().filter(e -> e.getCategorie().equals(selected));
        if (selected.equals(("Téléphone"))) {
            content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(annonces.stream().filter(e -> e.getCategorie().equals(selected)).collect(Collectors.toCollection(FXCollections::observableArrayList)));

        } else if (selected.equals(("Meuble"))) {
            content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(annonces.stream().filter(e -> e.getCategorie().equals(selected)).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } else if (selected.equals(("Electroménager"))) {
            content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(annonces.stream().filter(e -> e.getCategorie().equals(selected)).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } else {
            content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(Allannonces);
        }

    }

    @FXML
    void Rechercher(KeyEvent event) {
        if (!rech.getText().equals("")) {
            content_product.getChildren().remove(0, content_product.getChildren().size());
            afficherAll(Allannonces.stream().filter(e -> e.getTitre().equals(rech.getText())).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } else {
            afficherAll(Allannonces);
        }

    }

}
