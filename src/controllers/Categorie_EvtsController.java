/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Categorie_Evts;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Categorie_EvtsService;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class Categorie_EvtsController implements Initializable {

    @FXML
    private TextField textlibelle;
    @FXML
    private TextField textbut;
    @FXML
    private Button ajouter;
    @FXML
    private Button refresh;
    @FXML 
    private TableView<Categorie_Evts> catsTable;
    @FXML
    private TableColumn<Categorie_Evts, String> libCol; 
    @FXML
    private TableColumn<Categorie_Evts, String> butCol;
    
    private ObservableList<Categorie_Evts> list_cats = FXCollections.observableArrayList();
    Categorie_Evts c=new Categorie_Evts();
    Categorie_EvtsService cs= new Categorie_EvtsService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     ajouter.setOnAction(e->{
       
     if (!textlibelle.getText().equals("") && !textbut.getText().equals("")
                ) {
         Categorie_EvtsService cs =new Categorie_EvtsService();
         Categorie_Evts c=new Categorie_Evts(textlibelle.getText(),textbut.getText());
         cs.addCategorie_Evts(c);
            
         afficher();

        } else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("erreur champs vides");
         alert.setHeaderText("il ya des champs vides");
         Optional<ButtonType> result = alert.showAndWait();
        }
         });
         afficher();
} 
     

    void afficher()
         {
             list_cats = FXCollections.observableArrayList(cs.getAll());
        libCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        libCol.cellFactoryProperty();
        
        butCol.setCellValueFactory(new PropertyValueFactory<>("but"));
        butCol.cellFactoryProperty();
        
        catsTable.setItems(list_cats);
         
         }
 
    
}
