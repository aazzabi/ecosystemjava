/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.AnnounceRep;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.AnnounceRepService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class ReparateurMainScController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<AnnounceRep> tableviewan;

    @FXML
    private TableColumn<AnnounceRep, ImageView> a_col_img;

    @FXML
    private TableColumn<AnnounceRep, Integer> a_col_id;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_titre;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_desc;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_cat;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_date;
    @FXML
    private TableColumn<AnnounceRep, Integer> a_col_user;

    @FXML
    private TableColumn<AnnounceRep, Integer> a_col_rep;

    @FXML
    private TableColumn<AnnounceRep, Float> a_col_prix;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        tableviewan.setFixedCellSize(25);
//        tableviewan.prefHeightProperty().bind(Bindings.size(tableviewan.getItems()).multiply(tableviewan.getFixedCellSize()).add(30));
        tableviewan.setEditable(false);
        tableviewan.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
        a_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        a_col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        a_col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        a_col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        a_col_date.setCellValueFactory(new PropertyValueFactory<>("datePub"));
        a_col_user.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        a_col_rep.setCellValueFactory(new PropertyValueFactory<>("nomRep"));
        a_col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableviewan.setItems(AnnounceRepService.getAnnounceRepList());

    }

    public void supprimer() {

        if (tableviewan.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionner vous devez en sélectioner une ");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText("Voulez vous supprimer les lignes suivants ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<AnnounceRep> selectedRows = tableviewan.getSelectionModel().getSelectedItems();
                ArrayList<AnnounceRep> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                    AnnounceRepService.supprimer(row.getId());
                    tableviewan.getItems().remove(row);
                });

            } else {
               
            }

        }

    }
    
    
    public void edit ()
    {
        
        try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/ReparateurEditAnnonce.fxml"));
        /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        ReparateurEditAnnonceController controller= fxmlLoader.getController();
        controller.initData(tableviewan.getSelectionModel().getSelectedItem());
                
        stage.setTitle("Edition d'une announce de réparation");
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.show();
    } catch (IOException e) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }
        
    }

}
