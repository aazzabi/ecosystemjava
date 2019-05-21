/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Forum;

import controllers.Forum.ShowCategoriePublicationController;
import entities.PublicationForum;
import entities.CategoriePub;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.CategoriePubService;
import services.PublicationForumService;
import services.UserService;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;
import utils.ConnectionBase;
import utils.ControlleSaisie;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class ForumAdminController implements Initializable {

    @FXML
    private TableView<PublicationForum> tableListePublication;
    @FXML
    private TableColumn<PublicationForum, Integer> idPublication;
    @FXML
    private TableColumn<PublicationForum, Date> datePublication;
    @FXML
    private TableColumn<PublicationForum, String> titrePublication;
    @FXML
    private TableColumn<PublicationForum, String> descriptionPublication;
    @FXML
    private TableColumn<PublicationForum, String> etatPublication;
    @FXML
    private TableColumn<PublicationForum, String> categoriePublication;
    @FXML
    private TableColumn<PublicationForum, Integer> creeParPublication;
    
    ObservableList<PublicationForum> obl = FXCollections.observableArrayList();
    
    
    @FXML
    private TableView<CategoriePub> tableListeCategorie;
    
    @FXML
    private TableColumn<CategoriePub, Integer> idCategorie;
    
    @FXML
    private TableColumn<CategoriePub, String> libelleCategorie;
    @FXML
    private TableColumn<CategoriePub, String> descriptionCategorie;
    @FXML
    private TableColumn<CategoriePub, String> domaineCategorie;
    @FXML
    private TableColumn<CategoriePub, Integer> nbrPublicationCategorie;
    ObservableList<CategoriePub> obCateg = FXCollections.observableArrayList();
    
    @FXML
    private TextField txtLibelleCategorie;
    @FXML
    private TextField txtDescriptionCategorie;
    private TextField txtDomaineCategorie;
    @FXML
    private TextField txtRechercheCategorie;
    
    @FXML
    private Button btnDeleteCategorie;
    @FXML
    private Button btnViderFormulaireCategorie;
    @FXML
    private Button btnAddCategorie;
    @FXML
    private Button btnShowCategorie;
    @FXML
    private Button btnArchiverPublication;
    @FXML
    private Button btnShowPublication;
    @FXML
    private TextField txtRechercherPublication;
    @FXML
    private ChoiceBox<String> cbDomaine;
    
    @FXML
    private BarChart<?, ?> chartCommentPerPub;
    
    @FXML
    private BarChart<?, ?> chartPubPerCateg;
    @FXML
    private CategoryAxis categrories;
    @FXML
    private NumberAxis nbrPubPerCateg;
    @FXML
    private CategoryAxis publication;
    @FXML
    private PieChart pieChartVuesPerPublication;
    @FXML
    private NumberAxis nbrCommPerPub;
    @FXML
    private Label lblPieChartInfo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherAllPublications();
        afficherAllCategories();
        publicationsPerCategorie();
        commentsPerPublication();
        vuesPerPublication();
        btnDeleteCategorie.setDisable(true);
        btnViderFormulaireCategorie.setDisable(true);
        btnShowCategorie.setDisable(true);
        
        btnShowPublication.setDisable(true);
        btnArchiverPublication.setDisable(true);
        
        tableListeCategorie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnDeleteCategorie.setDisable(false);
                btnViderFormulaireCategorie.setDisable(false);
                btnShowCategorie.setDisable(false);        
            }
        });
        cbDomaine.getItems().addAll("Electronique","Environement","Art","Vie");
        tableListePublication.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnArchiverPublication.setDisable(false);
                btnShowPublication.setDisable(false);
            }
        });
        
        //CELLULE EDITABLE PUBLICATION
        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        titrePublication.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionPublication.setCellFactory(TextFieldTableCell.forTableColumn());
        
        tableListePublication.setEditable(true);

        
        
        //CELLULE EDITABLE CATEGORIE
        libelleCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionCategorie.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableListeCategorie.setEditable(true);
        
        libelleCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
    }  
    
    public void afficherAllPublications(){
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getAllPublications();

        for(PublicationForum e:le)
        {
            obl.add(e);
        }  
        
        idPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
        datePublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categoriePublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        creeParPublication.setCellValueFactory(new PropertyValueFactory<>("createdByName"));
        
        tableListePublication.setItems(obl);
        tableListePublication.setEditable(true);
    }
    
    public void afficherAllCategories(){
        ArrayList<CategoriePub> lc = (ArrayList<CategoriePub>) CategoriePubService.getAllCategoriePub();
        for(CategoriePub c:lc)
        {
            obCateg.add(c);
        }
        idCategorie.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionCategorie.setCellValueFactory(new PropertyValueFactory<>("description"));
        domaineCategorie.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        nbrPublicationCategorie.setCellValueFactory(new PropertyValueFactory<>("nbPublication"));

        tableListeCategorie.setItems(obCateg);
        tableListeCategorie.setEditable(true);
    }

    @FXML
    private void archiverPublication(ActionEvent event) {
        int id = tableListePublication.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        int index = tableListePublication.getSelectionModel().getSelectedIndex(); 
        PublicationForumService.archiverPublication(id);
        
        clearTable(tableListePublication);
        afficherAllPublications();
    }
    
    public void clearTable(TableView table) {
       for ( int i = 0; i< table.getItems().size(); i++) {
            table.getItems().clear();
        } 
    }

    @FXML
    private void btnViderFormulaireCategorie(ActionEvent event) {
        txtLibelleCategorie.clear();
        txtDescriptionCategorie.clear();
        txtDomaineCategorie.clear();
    }

    @FXML
    private void btnDeleteCategorie(ActionEvent event) {
        int id = tableListeCategorie.getSelectionModel().getSelectedItem().getId();
        int index = tableListeCategorie.getSelectionModel().getSelectedIndex(); 
        CategoriePubService.delete(id);
        
        clearTable(tableListeCategorie);
        afficherAllCategories();
    }

    @FXML
    private void btnAddCategorie(ActionEvent event) {
        if ( !(ControlleSaisie.estVide(txtLibelleCategorie, "nom")) 
            && !(ControlleSaisie.estVide(txtDescriptionCategorie, "description")) 
            && !(ControlleSaisie.estVideComboBox(cbDomaine, "categorie")) ){
            CategoriePub c = new CategoriePub();

            c.setLibelle(txtLibelleCategorie.getText());
            c.setDescription(txtDescriptionCategorie.getText());
//            c.setDomaine(txtDomaineCategorie.getText());
            c.setDomaine(cbDomaine.getValue().toString());
            
            CategoriePubService.add(c);
            clearTable(tableListeCategorie);
            afficherAllCategories();

            
            TrayNotification tray = new TrayNotification("succès", "Catégorie ajoutée", SUCCESS);
            tray.showAndWait();
        }
    }

    @FXML
    private void rechercherCategorie(KeyEvent event) {
        clearTable(tableListeCategorie);
        ArrayList<CategoriePub> lc = (ArrayList<CategoriePub>) CategoriePubService.rechercheKeyWord(txtRechercheCategorie.getText());
        for(CategoriePub c:lc)
        {
            obCateg.add(c);
        }
        libelleCategorie.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        descriptionCategorie.setCellValueFactory(new PropertyValueFactory<>("description"));
        domaineCategorie.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        nbrPublicationCategorie.setCellValueFactory(new PropertyValueFactory<>("nbPublication"));

        tableListeCategorie.setItems(obCateg);
        tableListeCategorie.setEditable(true);
        
    }

    @FXML
    private void btnShowCategorie(ActionEvent event) {
        try
        {
            int id = tableListeCategorie.getSelectionModel().getSelectedItem().getId();
            System.out.println(id);
            CategoriePub cat = CategoriePubService.getCategorieById(id);
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/gui/forum/showCategoriePublication.fxml"));
            try {
                Loader.load();
            } catch (IOException e) {
                System.out.println(e);
            }
            ShowCategoriePublicationController c = Loader.getController();
            c.afficherCategorie(cat);
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
    private void btnShowPublication(ActionEvent event) {
        try
        {
            int id = tableListePublication.getSelectionModel().getSelectedItem().getId();
            
            PublicationForum pub = PublicationForumService.getPublicationById(id);
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/gui/forum/showPublication.fxml"));
            try {
                Loader.load();
            } catch (IOException e) {
                System.out.println(e);
            }
            ShowPublicationController pubCtr = Loader.getController();
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
    public void onEditChangedLibelle(TableColumn.CellEditEvent<CategoriePub, String> event) {
        CategoriePub c = tableListeCategorie.getSelectionModel().getSelectedItem();
        int id = tableListeCategorie.getSelectionModel().getSelectedItem().getId();
        c.setLibelle(event.getNewValue());
        c.setId(id);
        System.out.println(c);
        
        System.out.println(event.getNewValue());
        
        CategoriePubService.updateCategorie(c);
    }
    
    @FXML
    public void onEditChangedDescription(TableColumn.CellEditEvent<CategoriePub, String> event) {
        int id = tableListeCategorie.getSelectionModel().getSelectedItem().getId();
        CategoriePub c = tableListeCategorie.getSelectionModel().getSelectedItem();
        c.setId(tableListeCategorie.getSelectionModel().getSelectedItem().getId());

        CategoriePubService.update(id, "description",event.getNewValue());
    }

    @FXML
    private void rechercherPublication(KeyEvent event) {
        clearTable(tableListePublication);
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>)
                PublicationForumService.recherchePublicationsKeyWord(txtRechercherPublication.getText());
        for(PublicationForum e:le)
        {
            obl.add(e);
        }  
        
        idPublication.setCellValueFactory(new PropertyValueFactory<>("id"));
        datePublication.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        titrePublication.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionPublication.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatPublication.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categoriePublication.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        creeParPublication.setCellValueFactory(new PropertyValueFactory<>("createdByName"));
        
        tableListePublication.setItems(obl);
        tableListePublication.setEditable(true);
    }
    
    public void publicationsPerCategorie(){
        ArrayList<CategoriePub> le = (ArrayList<CategoriePub>) CategoriePubService.getStatPublicationPerCategorie();
        XYChart.Series set1 = new XYChart.Series<>();

        for(CategoriePub e:le)
        {
            set1.getData().add(new XYChart.Data(e.getLibelle(), e.getNbPublication()));
        }

        chartPubPerCateg.getData().addAll(set1);
    }
    
    public void commentsPerPublication(){
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getStatVuesPerPublication();
        XYChart.Series set2 = new XYChart.Series<>();

        for(PublicationForum e:le)
        {
            System.out.println(" taille "+le.size());
            System.out.println("nbr comm "+e.getNbrCommentaires());
            System.out.println(e);
            System.out.println("----------------------------");
            set2.getData().add(new XYChart.Data(e.getTitre(), e.getNbrCommentaires()));
        }

        chartCommentPerPub.getData().addAll(set2);
    }

    public void vuesPerPublication(){
        ArrayList<PublicationForum> le = (ArrayList<PublicationForum>) PublicationForumService.getStatVuesPerPublication();
        for(PublicationForum e:le)
        {
            System.out.println(" taille "+le.size());
            System.out.println("nbr vues "+e.getNbrVues());
            System.out.println(e);
            System.out.println("----------------------------");
            pieChartVuesPerPublication.getData().add(new PieChart.Data(e.getTitre()+" : "+ e.getNbrVues()+" Vue(s)", e.getNbrVues()));
//            pieChartVuesPerPublication.getData().add(new PieChart.Data("Publication"+e.getTitre().toString()));
        }
    }
    
    @FXML
    public void onEditChangedTitrePub(TableColumn.CellEditEvent<PublicationForum, String> event)
    {
        
        PublicationForum p = tableListePublication.getSelectionModel().getSelectedItem();
        int id = tableListePublication.getSelectionModel().getSelectedItem().getId();
        p.setTitre(event.getNewValue());
        p.setId(id);
        System.out.println(p);
        
        System.out.println(event.getNewValue());
        
        PublicationForumService.updatePublicationTitre(p);
        

    }

    /*
    @FXML
    private void onEditChangedDescriptionPub(TableColumn.CellEditEvent<CategoriePub, String> event) {
    }
*/  
    @FXML
    public void onEditChangedDescriptionPub(TableColumn.CellEditEvent<PublicationForum, String> event) {
        
        PublicationForum p = tableListePublication.getSelectionModel().getSelectedItem();
        int id = tableListePublication.getSelectionModel().getSelectedItem().getId();
        p.setDescription(event.getNewValue());
        p.setId(id);
        System.out.println(p);
        
        System.out.println(event.getNewValue());
        
        PublicationForumService.updatePublicationDesc(p);
    }


}
