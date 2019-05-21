/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Panier;

import entities.Annonce;
import entities.panier.AnnoncePanier;
import entities.panier.Commande;
import entities.panier.LigneCommande;
import iservices.IAnnonceService;
import iservices.panier.ICommandeService;
import iservices.panier.ILigneCommandeService;
import iservices.panier.IPanierService;
import service.panier.LigneCommandeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.panier.PanierService;

/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class LigneCommandeController implements Initializable {
@FXML
    private Button fermer;
    
    @FXML
    private TableView<Annonce> detail_commande;

    @FXML
    private TableColumn<Annonce,ImageView> photo;

    @FXML
    private TableColumn<Annonce,String> titre;

    @FXML
    private TableColumn<Annonce,String> description;

    @FXML
    private TableColumn<Annonce,Double> prix;

    
      private IPanierService panierService;
    private IAnnonceService annonceService;
    private ILigneCommandeService lignecommandeService;
    
    @FXML
    void Fermer(ActionEvent event) {
Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
           lignecommandeService = new LigneCommandeService();
      
         
     
     
    }    
    public void initData(Commande c)
    {
       int idc=c.getId();
       lignecommandeService = new LigneCommandeService();
        System.out.println("id commande : ");
       System.out.println(idc);
    ObservableList<LigneCommande> l = FXCollections.observableArrayList();
    ObservableList<Annonce> all_a = FXCollections.observableArrayList();
    l=lignecommandeService.RecupererLignesCommande(idc);
    for(int i=0;i<l.size();i++)
    {
        int id8=l.get(i).getId_annonce();
        System.out.println("id ligne ");
        System.out.println(l.get(i).getId_annonce());

        Annonce a=new Annonce();
       
    a=lignecommandeService.RecupA(id8);
    System.out.println("titre a : "+a.getTitre());
    all_a.add(a);
    }
   
    /*
     for(int i=0;i<all_a.size();i++)
    {
        System.out.println(all_a.get(i).getTitre());
        System.out.println(all_a.get(i).getDescription());
        System.out.println(all_a.get(i).getPrix());
    }*/
       detail_commande.setEditable(false);
        detail_commande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         photo.setCellValueFactory(p -> new SimpleObjectProperty<>(new ImageView(p.getValue().getImage())));
        detail_commande.setItems(all_a);
      
    }
    
}
