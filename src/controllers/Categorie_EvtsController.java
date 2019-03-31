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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private Button supprimer;
     @FXML
    private Button annuler;
      @FXML
    private Button valider;
     @FXML
    private Button modifier;
      @FXML
    private Button ajout;
    @FXML 
    private TableView<Categorie_Evts> catsTable;
    @FXML
    private TableColumn<Categorie_Evts, String> libCol; 
    @FXML
    private TableColumn<Categorie_Evts, String> butCol;
    @FXML
    private TabPane tabpane;
    
    
    private ObservableList<Categorie_Evts> list_cats = FXCollections.observableArrayList();
    Categorie_Evts c=new Categorie_Evts();
    Categorie_EvtsService cs= new Categorie_EvtsService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ObservableList<Categorie_Evts> list_cats =catsTable.getItems();
  
      supprimer.setVisible(false);
      modifier.setVisible(false);
      annuler.setVisible(false);
      valider.setVisible(false); 
           afficher();
    
} 
    
    @FXML
    private void switchtab(ActionEvent event)
    {
    
        tabpane.getSelectionModel().select(1);
    
    }
    @FXML
     private void ajouterCat(ActionEvent event)
     {
      if (!textlibelle.getText().equals("") && !textbut.getText().equals("")
                ) {
         Categorie_EvtsService cs =new Categorie_EvtsService();
         Categorie_Evts c=new Categorie_Evts(textlibelle.getText(),textbut.getText());
         cs.addCategorie_Evts(c);
         
         textlibelle.setText("");
         textbut.setText("");
         tabpane.getSelectionModel().select(0);
         afficher();
         
         

        } else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("erreur champs vides");
         alert.setHeaderText("il ya des champs vides");
         Optional<ButtonType> result = alert.showAndWait();
        }
        
         afficher();
     }
     
     @FXML
     private void supprimerCat(ActionEvent event)
     {
         if (!catsTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'une catégorie d'event");
            alert.setHeaderText("Etes-vous sur de vouloir la supprimer ?  "
                    + catsTable.getSelectionModel().getSelectedItem().getLibelle() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Categorie_EvtsService cs =new Categorie_EvtsService();
                System.out.println(catsTable.getSelectionModel().getSelectedItem().getLibelle());
                cs.deleteCategorie_Evts(catsTable.getSelectionModel().getSelectedItem().getId());
               // SendMail.sendmail("amine.mraihi@esprit.tn",
                  //   "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                afficher();
            }
        }
     
     
     }
     
     @FXML
     private void modifierCat(ActionEvent event)
     {
         tabpane.getSelectionModel().select(1);
         textlibelle.setText(catsTable.getSelectionModel().getSelectedItem().getLibelle());
         textbut.setText(catsTable.getSelectionModel().getSelectedItem().getBut());
         valider.setVisible(true);
         annuler.setVisible(true);
         ajouter.setVisible(false);
         
     
     }
     
     @FXML
     private void validerModif(ActionEvent event)
     {
      if (!catsTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("modification d'une catégorie d'event");
            alert.setHeaderText("Etes-vous sur de vouloir la modifier ?  "
                    + catsTable.getSelectionModel().getSelectedItem().getLibelle() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Categorie_EvtsService cs =new Categorie_EvtsService();
                //System.out.println(catsTable.getSelectionModel().getSelectedItem().getLibelle());
                //cs.updateCategorie_Evts(catsTable.getSelectionModel().getSelectedItem().getId());
                Categorie_Evts c=new Categorie_Evts(catsTable.getSelectionModel().getSelectedItem().getId(),textlibelle.getText(),textbut.getText());
                cs.updateCategorie_Evts(c);
               // SendMail.sendmail("amine.mraihi@esprit.tn",
                  //   "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                  textlibelle.setText("");
                  textbut.setText("");
                   annuler.setVisible(false);
                   valider.setVisible(false); 
                   ajouter.setVisible(true);
                  tabpane.getSelectionModel().select(0);
                afficher();
            }
        }
     
         
     }
     
     @FXML
     private void annulerModif(ActionEvent event)
     {
     
         textlibelle.setText("");
         textbut.setText("");
         valider.setVisible(false);
         annuler.setVisible(false);
         tabpane.getSelectionModel().select(0);
        // ajouter.setVisible(true);
     }
     
      @FXML
    private void options(MouseEvent event)
    {
        
      supprimer.setVisible(true);
      modifier.setVisible(true);
    
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
