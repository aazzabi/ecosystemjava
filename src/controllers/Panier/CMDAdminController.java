/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Panier;

import javafx.scene.control.Tab;
import entities.Session;
import entities.panier.Commande;
import entities.panier.Livraison;
import entities.panier.Livreur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.panier.CommandeService;
import iservices.panier.ICommandeService;
import service.panier.SendEmailService;
import iservices.panier.ISendEmailService;
import service.panier.LivraisonService;
import iservices.panier.ILivraisonService;
import iservices.panier.ILigneCommandeService;
import iservices.panier.IPanierService;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import service.panier.LigneCommandeService;
import services.UserService;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;
import utils.ControlleSaisie;
/**
 * FXML Controller class
 *
 * @author Aziz
 */
public class CMDAdminController implements Initializable {
    
    @FXML
    private BarChart<String,Integer> bar_chart;
    
 @FXML
    private Tab cond;

    @FXML
    private TableView<Commande> table_commande;

    @FXML
    private TableColumn<Commande,Date> date;

    @FXML
    private TableColumn<Commande, String> etat;

    @FXML
    private TableColumn<Commande,Double> prix_total;

    @FXML
    private TableColumn<Commande, Integer> id_user;
    
    @FXML
    private TextField motdepasse;

    @FXML
    private TextField confirmer_mdp;
    
     @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField pseudo;
@FXML
    private TextField zone;

  @FXML
    private Button photo;
    

    @FXML
    private Button ajouter;
    
    @FXML
    private TableView<Livraison> table_livraison;

    @FXML
    private TableColumn<Livraison, Integer> id_livreur;

    @FXML
    private TableColumn<Livraison, Date> date_livraison;

    @FXML
    private TableColumn<Livraison, String> etat_livraison;

    @FXML
    private TableColumn<Livraison, String> adr_livraison;
   @FXML
    private Button annuler_cmd;
   
   @FXML
    private Button annuler_livraison;
   
   @FXML
    private TableView<Livreur> table_livreur;

    @FXML
    private TableColumn<Livreur,Integer> id_liv;

    @FXML
    private TableColumn<Livreur, String> zone_travail;

    @FXML
    private TableColumn<Livreur,String> dispo_livreur;

    @FXML
    private TableColumn<Livreur, Integer> nombre_livraison;

    @FXML
    private TableColumn<Livreur,Integer> note;
    
    @FXML
    private Button noter1;

    @FXML
    private Button noter2;

    @FXML
    private Button noter3;

    @FXML
    private Button noter4;

    @FXML
    private Button noter5;
    @FXML
    private Button meilleur_livreur;
    
     
    
    private IPanierService panierService;
    private ICommandeService commandeService;
    private ILivraisonService livraisonService;
     private ILigneCommandeService lignecommandeService;
     private ISendEmailService sendEmailService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       commandeService = new CommandeService();
        table_commande.setEditable(false);
        table_commande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
       
        date.setCellValueFactory(new PropertyValueFactory<>("date_emission"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         prix_total.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
         id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
         
        int id=Session.getCurrentSession();
        table_commande.setItems(commandeService.getall());
       
        livraisonService = new LivraisonService();
        table_livraison.setEditable(false);
        table_livraison.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
       
        id_livreur.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
        etat_livraison.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         date_livraison.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
         adr_livraison.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
         
        table_livraison.setItems(livraisonService.getall());
        
        table_livreur.setEditable(false);
        table_livreur.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
       
        id_liv.setCellValueFactory(new PropertyValueFactory<>("id"));
        zone_travail.setCellValueFactory(new PropertyValueFactory<>("zone"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         dispo_livreur.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
         nombre_livraison.setCellValueFactory(new PropertyValueFactory<>("nbr_livraison"));
         note.setCellValueFactory(new PropertyValueFactory<>("note"));
        table_livreur.setItems(livraisonService.getall2());
        XYChart.Series<String,Integer> series=new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Janvier",3));
        series.getData().add(new XYChart.Data<>("Février",0));
        series.getData().add(new XYChart.Data<>("Mars",3));
        series.getData().add(new XYChart.Data<>("Avril",8));
        series.getData().add(new XYChart.Data<>("Mai",3));
        series.getData().add(new XYChart.Data<>("Juin",3));
        series.getData().add(new XYChart.Data<>("Juillet",15));
        series.getData().add(new XYChart.Data<>("Août",3));
        series.getData().add(new XYChart.Data<>("Spetembre",3));
        series.getData().add(new XYChart.Data<>("Octobre",6));
        series.getData().add(new XYChart.Data<>("Nomvembre",3));
        series.getData().add(new XYChart.Data<>("Décembre",3));
                          bar_chart.getData().add(series);
       bar_chart.setTitle("Nombres de Commandes Par rapport au mois de l'année 2019 ");
    
    }    
    @FXML
    void AjouterLivreur(ActionEvent event) {
        
if ( !(ControlleSaisie.estVide(nom, "nom")) 
        && !(ControlleSaisie.estVide(prenom, "prenom")) 
        && !(ControlleSaisie.estVide(email, "email")) 
        && !(ControlleSaisie.estVide(pseudo, "pseudo")) 
        && !(ControlleSaisie.estVide(zone, "zone")) 
        && !(ControlleSaisie.estVide(motdepasse, " mot de passe ")) 
        && !(ControlleSaisie.estVide(confirmer_mdp, "confirmation mdp"))
        && !(ControlleSaisie.sontConforme( motdepasse, "mot de passe", confirmer_mdp, "confirmation de mot de passe "))
        && (ControlleSaisie.estEmailValide(email)) )
    {
        System.out.println("d5alt ");
     Livreur r= new Livreur();
        r.setNom(nom.getText());
        r.setPrenom(prenom.getText());
        r.setEmail(email.getText());
        r.setEmailCanonical(email.getText());
        r.setUsername(pseudo.getText());
        r.setPhoto("tab.png");
        r.setUsernameCanonical(pseudo.getText());
        r.setZone(zone.getText());
        r.setDisponibilite("Disponible");
        r.setNbr_livraison(0);
        r.setNote(0);
        r.setPassword(motdepasse.getText());
        r.setEnabled(true);
        r.setDiscr("livreur");
        r.setRoles("a:1:{i:0;s:15:\"ROLE_LIVREUR\";}");
        
      //  copyImages.deplacerVers(txtPhotoReparateur, absolutePathPhotoReparateur,"C:\\ecosystemjava\\src\\res\\upload\\user\\");
      //  copyImages.deplacerVers(txtPhotoReparateur, absolutePathPhotoReparateur,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\user\\photo\\");
            
        UserService.InscriptionLivreur(r);
        TrayNotification tray = new TrayNotification("succès", "Livreur ajouté", SUCCESS);
        tray.showAndWait();
     }
    }
    
    @FXML
    void Annuler_Commande(ActionEvent event) {
commandeService = new CommandeService();
lignecommandeService = new LigneCommandeService();
if (table_commande.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne  sélectionnée ");
            alert.showAndWait();
        } 
else 
{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de Suppression");
            alert.setHeaderText("Voulez vous supprimer les commandes suivantes  ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<Commande> selectedRows = table_commande.getSelectionModel().getSelectedItems();
                ArrayList<Commande> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   lignecommandeService.AnnulerLigneCommande(row.getId()); 
                    commandeService.AnnulerCommande(row.getId());
                    table_commande.getItems().remove(row);
                });

            }
            
            else {

            }

        }
    }
    
       @FXML
    void Annuler_Livraison(ActionEvent event) {
livraisonService = new LivraisonService();
if (table_livraison.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne  sélectionnée ");
            alert.showAndWait();
        } 
else 
{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de Suppression");
            alert.setHeaderText("Voulez vous supprimer les livraisons suivantes  ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<Livraison> selectedRows = table_livraison.getSelectionModel().getSelectedItems();
                ArrayList<Livraison> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   
                    livraisonService.AnnulerLivraison(row.getId());
                    table_livraison.getItems().remove(row);
                });

            }
            
            else {

            }

        }
    }
    
    @FXML
    void Noter1(ActionEvent event) {
if (table_livreur.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Livreur  sélectionné ");
            alert.showAndWait();
        } 
else 
{

                ObservableList<Livreur> selectedRows = table_livreur.getSelectionModel().getSelectedItems();
                ArrayList<Livreur> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   int note=1;
                    livraisonService.NoterLivreur(row.getId(),note);
                    
                });
                
                id_liv.setCellValueFactory(new PropertyValueFactory<>("id"));
        zone_travail.setCellValueFactory(new PropertyValueFactory<>("zone"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         dispo_livreur.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
         nombre_livraison.setCellValueFactory(new PropertyValueFactory<>("nbr_livraison"));
         note.setCellValueFactory(new PropertyValueFactory<>("note"));
        table_livreur.setItems(livraisonService.getall2());

        }
    }

    @FXML
    void Noter2(ActionEvent event) {
if (table_livreur.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Livreur  sélectionné ");
            alert.showAndWait();
        } 
else 
{

                ObservableList<Livreur> selectedRows = table_livreur.getSelectionModel().getSelectedItems();
                ArrayList<Livreur> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   int note=2;
                    livraisonService.NoterLivreur(row.getId(),note);
                    
                });
                
                id_liv.setCellValueFactory(new PropertyValueFactory<>("id"));
        zone_travail.setCellValueFactory(new PropertyValueFactory<>("zone"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         dispo_livreur.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
         nombre_livraison.setCellValueFactory(new PropertyValueFactory<>("nbr_livraison"));
         note.setCellValueFactory(new PropertyValueFactory<>("note"));
        table_livreur.setItems(livraisonService.getall2());

        }
    }

    @FXML
    void Noter3(ActionEvent event) {
if (table_livreur.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Livreur  sélectionné ");
            alert.showAndWait();
        } 
else 
{

                ObservableList<Livreur> selectedRows = table_livreur.getSelectionModel().getSelectedItems();
                ArrayList<Livreur> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   int note=3;
                    livraisonService.NoterLivreur(row.getId(),note);
                    
                });
                
                id_liv.setCellValueFactory(new PropertyValueFactory<>("id"));
        zone_travail.setCellValueFactory(new PropertyValueFactory<>("zone"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         dispo_livreur.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
         nombre_livraison.setCellValueFactory(new PropertyValueFactory<>("nbr_livraison"));
         note.setCellValueFactory(new PropertyValueFactory<>("note"));
        table_livreur.setItems(livraisonService.getall2());

        }
    }

    @FXML
    void Noter4(ActionEvent event) {
if (table_livreur.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Livreur  sélectionné ");
            alert.showAndWait();
        } 
else 
{

                ObservableList<Livreur> selectedRows = table_livreur.getSelectionModel().getSelectedItems();
                ArrayList<Livreur> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   int note=4;
                    livraisonService.NoterLivreur(row.getId(),note);
                    
                });
                
                id_liv.setCellValueFactory(new PropertyValueFactory<>("id"));
        zone_travail.setCellValueFactory(new PropertyValueFactory<>("zone"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         dispo_livreur.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
         nombre_livraison.setCellValueFactory(new PropertyValueFactory<>("nbr_livraison"));
         note.setCellValueFactory(new PropertyValueFactory<>("note"));
        table_livreur.setItems(livraisonService.getall2());

        }
    }

    @FXML
    void Noter5(ActionEvent event) {
if (table_livreur.getSelectionModel().getSelectedIndex() == -1)
{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Livreur  sélectionné ");
            alert.showAndWait();
        } 
else 
{

                ObservableList<Livreur> selectedRows = table_livreur.getSelectionModel().getSelectedItems();
                ArrayList<Livreur> rows = new ArrayList<>(selectedRows);
                rows.forEach((row) -> {
                   int note=5;
                    livraisonService.NoterLivreur(row.getId(),note);
                    
                });
                
                id_liv.setCellValueFactory(new PropertyValueFactory<>("id"));
        zone_travail.setCellValueFactory(new PropertyValueFactory<>("zone"));
        //nom_prenom_u.setCellValueFactory(new PropertyValueFactory<>("prix"));
         dispo_livreur.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
         nombre_livraison.setCellValueFactory(new PropertyValueFactory<>("nbr_livraison"));
         note.setCellValueFactory(new PropertyValueFactory<>("note"));
        table_livreur.setItems(livraisonService.getall2());

        }
    }
    
    @FXML
    void Meilleur_livreur(ActionEvent event) {
try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/panier/MeilleurLivreur.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
         
            
                stage.setTitle("Meilleur Livreur");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
    
 
   
    
}
