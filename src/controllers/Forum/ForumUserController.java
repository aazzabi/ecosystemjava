/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import entities.PublicationForum;
import entities.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import services.CategoriePubService;
import services.PublicationForumService;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;
import utils.ControlleSaisie;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class ForumUserController implements Initializable {

    private TableView<PublicationForum> tableListePublication;
    private TableColumn<PublicationForum, Date> datePublication;
    private TableColumn<PublicationForum, String> titrePublication;
    private TableColumn<PublicationForum, String> descriptionPublication;
    private TableColumn<PublicationForum, String> etatPublication;
    private TableColumn<PublicationForum, String> categoriePublication;
    private TableColumn<PublicationForum, String> pubCreeParPublication;
    private TableColumn<PublicationForum, Integer> idPublication;
    
    
    @FXML
    private TextField txtRechercherPublication;
    @FXML
    private TableView<PublicationForum> tableListeMyPublication;
    @FXML
    private TableColumn<PublicationForum, Date> dateMyPublication;
    @FXML
    private TableColumn<PublicationForum, String> titreMyPublication;
    @FXML
    private TableColumn<PublicationForum, String> descriptionMyPublication;
    @FXML
    private TableColumn<PublicationForum, String> etatMyPublication;
    @FXML
    private TableColumn<PublicationForum, String> categorieMyPublication;
    @FXML
    private TableColumn<PublicationForum, Integer> idMyPublication;
    @FXML
    private TextField txtRechercherMyPublication;
    @FXML
    private Button btnShowMyPublication;
    @FXML
    private Button btnArchiverPublication;
    @FXML
    private Button btnSupprimerPublication;
    @FXML
    private TextArea txtMyPublicationDescription;
    @FXML
    private ChoiceBox<String> txtMyPublicationCategorie;

    ObservableList<PublicationForum> oblAllPublication = FXCollections.observableArrayList();
    ObservableList<PublicationForum> oblAllMyPublication = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> creeParPublication;
    int idUser = Session.getCurrentSession();
    @FXML
    private TextField txtMyPublicationTitle;

    public static ObservableList<PublicationForum> obsl;
    public static int indice;
    @FXML
    private FlowPane flow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int idUser = Session.getCurrentSession();
        txtMyPublicationCategorie.getItems().addAll(CategoriePubService.getAllCategoriesLibelle());
//        afficherAllPublications();
        afficherAllPublicationsCard();
        afficherAllMyPublications(idUser);
        
        //CELLULE EDITABLE PUBLICATION
        titreMyPublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionMyPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        titreMyPublication.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionMyPublication.setCellFactory(TextFieldTableCell.forTableColumn());
        
        tableListeMyPublication.setEditable(true);
        
        
        
    }
    
    public void afficherAllPublicationsCard(){
        CardsPublicationController.i=0;
        ArrayList<PublicationForum> publications = new ArrayList<>();
        publications = (ArrayList) PublicationForumService.getAllPublications();
        obsl = FXCollections.observableArrayList(publications);
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/forum/CardPublication.fxml"));
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        

    }
    
    public void afficherAllPublications(){
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllPublications();

        for(PublicationForum e:le)
        {
            oblAllPublication.add(e);
        }  
        
        idPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
        datePublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categoriePublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        pubCreeParPublication.setCellValueFactory(new PropertyValueFactory<>("createdByName"));
        
        tableListePublication.setItems(oblAllPublication);
        tableListePublication.setEditable(true);
    }    
    
    public void afficherAllMyPublications(int idUser){
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllPublicationsByUserId(idUser);

        for(PublicationForum e:le)
        {
            oblAllMyPublication.add(e);
            e.toString();
        }  
        
        idMyPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateMyPublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        titreMyPublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionMyPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatMyPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorieMyPublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
        tableListeMyPublication.setItems(oblAllMyPublication);
        tableListeMyPublication.setEditable(true);
    }    

    @FXML
    private void rechercherPublication(KeyEvent event) {
        System.out.println("rechercherPublication();");
        flow.getChildren().clear();
        CardsPublicationController.i=0;
        ArrayList<PublicationForum> publications = new ArrayList<>();
        publications = (ArrayList) PublicationForumService.recherchePublicationsKeyWord(txtRechercherPublication.getText());
        obsl = FXCollections.observableArrayList(publications);
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/forum/CardPublication.fxml"));
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }      
    }
    
    @FXML
    private void rechercherMyPublication(KeyEvent event) {
        System.out.println("ID USEAAARRR "+idUser);
        clearTable(tableListeMyPublication);
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>)
                PublicationForumService.rechercherMyPublicationsKeyWord(idUser,txtRechercherMyPublication.getText());
        for(PublicationForum e:le)
        {
            oblAllMyPublication.add(e);
            e.toString();
        }  
        
        idMyPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateMyPublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        titreMyPublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionMyPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatMyPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorieMyPublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        
        tableListeMyPublication.setItems(oblAllMyPublication);
        tableListeMyPublication.setEditable(true);
    }


    @FXML
    private void archiverMyPublication(ActionEvent event) {
        int id = tableListeMyPublication.getSelectionModel().getSelectedItem().getId();
        PublicationForumService.archiverPublication(id);
        
        clearTable(tableListeMyPublication);
        afficherAllMyPublications(idUser);
        flow.getChildren().clear();
        afficherAllPublicationsCard();
    }

    @FXML
    private void supprimerMyPublication(ActionEvent event) {
        int id = tableListeMyPublication.getSelectionModel().getSelectedItem().getId();
        PublicationForumService.deletePublication(id);
                
        clearTable(tableListeMyPublication);
        afficherAllMyPublications(idUser);
        flow.getChildren().clear();
        afficherAllPublicationsCard();
    }
    

    public void clearTable(TableView table) {
       for ( int i = 0; i< table.getItems().size(); i++) {
            table.getItems().clear();
        } 
    }

    @FXML
    private void ajouterPublication(ActionEvent event) {
        System.out.println(txtMyPublicationCategorie.getSelectionModel().getSelectedItem());
        System.out.println(CategoriePubService.getIdCategoriePub(txtMyPublicationCategorie.getSelectionModel().getSelectedItem()));
        
        if ( !(ControlleSaisie.estVide(txtMyPublicationTitle, "titre")) 
            && !(ControlleSaisie.estVideComboBox(txtMyPublicationCategorie, "categorie"))
            && !(ControlleSaisie.estVideTextArea(txtMyPublicationDescription, "description")) ){
            PublicationForum f = new PublicationForum();

            f.setTitre(txtMyPublicationTitle.getText());
            f.setDescription(txtMyPublicationDescription.getText());
            f.setCategorieId(CategoriePubService.getIdCategoriePub(txtMyPublicationCategorie.getSelectionModel().getSelectedItem()));
            
            PublicationForumService.add(f);
            f.toString();
            clearTable(tableListeMyPublication);
            afficherAllMyPublications(idUser);
            
            TrayNotification tray = new TrayNotification("succès", "Publication ajoutée", SUCCESS);
            tray.showAndWait();
        }
    }


    @FXML
    private void btnShowPublication(ActionEvent event) {
        try
        {
            int id = tableListeMyPublication.getSelectionModel().getSelectedItem().getId();
            
            PublicationForum pub = PublicationForumService.getPublicationById(id);
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/gui/forum/showPublicationUser.fxml"));
            try {
                Loader.load();
            } catch (IOException e) {
                System.out.println(e);
            }
            ShowPublicationUserController pubCtr = Loader.getController();
            pubCtr.afficherPublication(pub);
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
            event.consume();
        }
        catch (Exception exp){
            exp.printStackTrace();
        }
    }

    @FXML
    public void onEditChangedTitrePub(TableColumn.CellEditEvent<PublicationForum, String> event) {
        PublicationForum p = tableListeMyPublication.getSelectionModel().getSelectedItem();
        int id = tableListeMyPublication.getSelectionModel().getSelectedItem().getId();
        p.setTitre(event.getNewValue());
        p.setId(id);
        System.out.println(p);
        
        System.out.println(event.getNewValue());
        
        PublicationForumService.updatePublicationTitre(p);
        
    }

    @FXML
    public void onEditChangedDescriptionPub(TableColumn.CellEditEvent<PublicationForum, String> event) {
          PublicationForum p = tableListeMyPublication.getSelectionModel().getSelectedItem();
        int id = tableListeMyPublication.getSelectionModel().getSelectedItem().getId();
        p.setDescription(event.getNewValue());
        p.setId(id);
        System.out.println(p);
        
        System.out.println(event.getNewValue());
        
        PublicationForumService.updatePublicationDesc(p);
    }
    
}
