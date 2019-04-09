/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.events;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entities.Categorie_Evts;
import entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.Categorie_EvtsService;
import services.EvenementService;
import utils.copyImages;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class EvenementAdminController implements Initializable {

    @FXML
    private TabPane tabpane;
    @FXML
    private TableView<Evenement> events;
    @FXML
    private TableColumn<Evenement, String> categorie;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> lieu;
    @FXML
    private TableColumn<Evenement, String> cover;
    @FXML
    private TableColumn<Evenement, String> creator;
    @FXML
    private TableColumn<Evenement, String> nbvues;
    @FXML
    private JFXTextField searchbar;
    @FXML
    private JFXButton search;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXButton supprimer;
    @FXML
    private TextField lieutext;
    @FXML
    private ChoiceBox categoriebox;
    @FXML
    private TextField titretext;
    @FXML
    private TextArea descriptiontext;
    @FXML
    private Button homebutton;
    @FXML
    private Button homebutton1;
    @FXML
    private Button homebutton2;
    @FXML
    private Button ajouter;
    @FXML
    private ChoiceBox categoriebox_id;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button btnPhoto;
    @FXML
    private Button ajoutcategorie;
    @FXML
    private Text txtPhoto;
    private String absolutePathPhoto;
    
     @FXML
    private TabPane tabpane1;
     @FXML
    private TextField textlibelle;
    @FXML
    private TextField textbut;
    @FXML
    private Button ajouter1;
    @FXML
    private Button supprimer1;
     @FXML
    private Button annulerCat;
      @FXML
    private Button validerCat;
     @FXML
    private Button modifier;
      @FXML
    private Button ajout1;
    @FXML 
    private JFXTreeTableView<Categorie_Evts> catsTable;
   /*  @FXML
    private TreeTableColumn<Categorie_Evts, String> libCol;
    
    @FXML
    private TreeTableColumn<Categorie_Evts, String> butCol;*/
     @FXML
     private ChoiceBox combobut;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Evenement> list_event = FXCollections.observableArrayList();
    Evenement e=new Evenement();
     EvenementService es=new EvenementService();
    Categorie_EvtsService Ev_S=new Categorie_EvtsService();
    
    
      
    Categorie_Evts c=new Categorie_Evts();
    Categorie_EvtsService cs= new Categorie_EvtsService();
     JFXTreeTableColumn<Categorie_Evts, String> libCol=new JFXTreeTableColumn<>("libelle") ; 
      JFXTreeTableColumn<Categorie_Evts, String> butCol=new JFXTreeTableColumn<>("but") ; 
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      
       libCol.setPrefWidth(150);
         butCol.setPrefWidth(150);
         try {
            // TODO
            afficher();
            afficherCat();
            List<Categorie_Evts> list = Ev_S.getAll();
             categoriebox_id.setVisible(false);
             for (int i=0; i< list.size();i++)
                     {
                         categoriebox.getItems().add(list.get(i));
                         categoriebox_id.getItems().add(list.get(i).getId());
                         
                     }
               categoriebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
        categoriebox_id.getSelectionModel().select( categoriebox.getSelectionModel().getSelectedIndex());
          
      }
    });
             
        } catch (Exception ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
         supprimer.setVisible(false);  
         homebutton.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton1.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton2.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         ajoutcategorie.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         
         //categorie
          combobut.setItems(FXCollections.observableArrayList("Lucratif","Non Lucratif"));
      supprimer1.setVisible(false);
      modifier.setVisible(false);
     // ajouter1.setVisible(false);
      //annulerCat.setVisible(false);
      //validerCat.setVisible(false); 
    }
    

      void afficher()
         {
              
              
           list_event = FXCollections.observableArrayList(es.getAll());
           
          
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        lieu.cellFactoryProperty();
        
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        categorie.cellFactoryProperty();
        
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        titre.cellFactoryProperty();
        
         
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.cellFactoryProperty();
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.cellFactoryProperty();
        
        cover.setCellValueFactory(new PropertyValueFactory<>("cover"));
        cover.cellFactoryProperty();
        
        nbvues.setCellValueFactory(new PropertyValueFactory<>("nbvues"));
        nbvues.cellFactoryProperty();
        
        creator.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        creator.cellFactoryProperty();
        
        events.setItems(list_event);
         
         }

    @FXML
    private void options(MouseEvent event) {
        supprimer.setVisible(true);
    }

    @FXML
    private void switchtab(ActionEvent event) {
        tabpane.getSelectionModel().select(1);
    }


    @FXML
    private void supprimerEvent(ActionEvent event) {
        
        if (!events.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'un event");
            alert.setHeaderText("Etes-vous sur de vouloir le supprimer ?  "
                    + events.getSelectionModel().getSelectedItem().getTitre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EvenementService es =new EvenementService(); 
                System.out.println(events.getSelectionModel().getSelectedItem().getId());
                es.deleteEvent(events.getSelectionModel().getSelectedItem().getId());
                System.out.println("emchii");
               // SendMail.sendmail("amine.mraihi@esprit.tn",
                  //   "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                afficher(); 
            }
        }
    }

    @FXML
    private void ajout(ActionEvent event) {
         if (!lieutext.getText().equals("") && !titretext.getText().equals("") && !descriptiontext.getText().equals("") 
                ) { 
              
         System.out.println("hi");
         EvenementService cs =new EvenementService();
         int i = (int)categoriebox_id.getValue();
      
      LocalDate localDate = datepicker.getValue();
      Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
      Date date = Date.from(instant);
      System.out.println(localDate + "\n" + instant + "\n" + date);
         Evenement c=new Evenement(lieutext.getText(),i,titretext.getText(),descriptiontext.getText(),date,txtPhoto.getText());
         txtPhoto.setVisible(false);
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\ecosystemjava\\src\\res\\upload\\event\\");
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\event\\photo\\");
         cs.addEvent(c);
         
         lieutext.setText("");
         titretext.setText("");
         descriptiontext.setText("");   
         categoriebox_id.getSelectionModel().select(0);
         categoriebox.getSelectionModel().select(0);
         txtPhoto.setVisible(false);
        
         afficher();
         
         

        } else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("erreur champs vides");
         alert.setHeaderText("il ya des champs vides");
         Optional<ButtonType> result = alert.showAndWait();
        }
        tabpane.getSelectionModel().select(0);
         afficher();
    }

    @FXML
    private void photoChooser(ActionEvent event) {
        
         FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        btnPhoto.setOnAction(e-> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhoto = choix.getAbsolutePath();
                txtPhoto.setText(choix.getName());
             } else {
                System.out.println("Image introuvable");
            }
        });
    }
    
    @FXML
    private void addCategorie(ActionEvent event) 
    {
        /*Stage dialogStage = new Stage();
    Scene scene;  
    Node node = (Node)event.getSource();
         dialogStage = (Stage) node.getScene().getWindow();
                    dialogStage.close();
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/events/Categorie_Evts.fxml")));
                    dialogStage.setScene(scene);
                    dialogStage.show();*/
        tabpane.getSelectionModel().select(2);
        
    }
    
    @FXML
    private void retourHome(ActionEvent event)
    {
        tabpane.getSelectionModel().select(0);
    }
    
    
    
    //**************CATEGORIE***********
     @FXML
    private void switchtabCat(ActionEvent event)
    {
    
        tabpane1.getSelectionModel().select(1);
    
    }
    
     @FXML
     private void ajouterCat(ActionEvent event)
     {
      if (!textlibelle.getText().equals("") 
                ) {
         Categorie_EvtsService cs =new Categorie_EvtsService();
         String i = (String) combobut.getValue();
         Categorie_Evts c=new Categorie_Evts(textlibelle.getText(),i);
         cs.addCategorie_Evts(c);
         
         textlibelle.setText("");
        // textbut.setText("");
        combobut.getSelectionModel().select(0);
        
         afficher();
         
         

        } else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("erreur champs vides");
         alert.setHeaderText("il ya des champs vides");
         Optional<ButtonType> result = alert.showAndWait();
        }
        tabpane1.getSelectionModel().select(0);
         afficherCat();
     }
     
      @FXML
     private void supprimerCat(ActionEvent event)
     {
         if (!catsTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'une catégorie d'event");
            alert.setHeaderText("Etes-vous sur de vouloir la supprimer ?  "
                    + catsTable.getSelectionModel().getSelectedItem().getValue().libelleProperty() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Categorie_EvtsService cs =new Categorie_EvtsService();
                System.out.println(catsTable.getSelectionModel().getSelectedItem().getValue().libelleProperty());
                cs.deleteCategorie_Evts(catsTable.getSelectionModel().getSelectedItem().getValue().getId());
               // SendMail.sendmail("amine.mraihi@esprit.tn",
                  //   "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                afficherCat();
            }
        }
     
     
     }
     
       @FXML
     private void modifierCat(ActionEvent event)
     {
         tabpane1.getSelectionModel().select(1);
        textlibelle.setText(catsTable.getSelectionModel().getSelectedItem().getValue().getLibelle());
        textbut.setText(catsTable.getSelectionModel().getSelectedItem().getValue().getBut());
         combobut.getSelectionModel().select(-1);
       
       do{
           combobut.getSelectionModel().selectNext();
           
        
      }
       while((int)combobut.getValue()!=catsTable.getSelectionModel().getSelectedItem().getValue().getId());
       // combobut.getSelectionModel().selectNext();
           
         validerCat.setVisible(true);
         annulerCat.setVisible(true);
         ajouter1.setVisible(false); //zxinj.jar pour qr code
        
         
     
     }
     
     @FXML
     private void validerModif(ActionEvent event)
     {
      if (!catsTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("modification d'une catégorie d'event");
            alert.setHeaderText("Etes-vous sur de vouloir la modifier ?  "
                    + catsTable.getSelectionModel().getSelectedItem().getValue().getLibelle() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Categorie_EvtsService cs =new Categorie_EvtsService();
                 String i = (String) combobut.getValue();
                //System.out.println(catsTable.getSelectionModel().getSelectedItem().getLibelle());
                //cs.updateCategorie_Evts(catsTable.getSelectionModel().getSelectedItem().getId());
                Categorie_Evts c=new Categorie_Evts(catsTable.getSelectionModel().getSelectedItem().getValue().getId(),textlibelle.getText(),i);
                cs.updateCategorie_Evts(c);
               // SendMail.sendmail("amine.mraihi@esprit.tn",
                  //   "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                  textlibelle.setText("");
                  combobut.getSelectionModel().select(0);
                    afficherCat();
                  //textbut.setText("");
                   annulerCat.setVisible(false);
                   validerCat.setVisible(false); 
                   ajouter1.setVisible(true);
                   
                  tabpane1.getSelectionModel().select(0);
               afficherCat();
            }
        }
     
         
     }
     
     @FXML
     private void annulerModif(ActionEvent event)
     {
     
         textlibelle.setText("");
         textbut.setText("");
         //validerCat.setVisible(false);
         //annulerCat.setVisible(false);
         tabpane1.getSelectionModel().select(0);
           afficherCat();
         
       
        // ajouter.setVisible(true);
     }
       @FXML
    private void optionsCat(MouseEvent event)
    {
        
      supprimer1.setVisible(true);
      modifier.setVisible(true);
    
    }

    void afficherCat()
         {
         /*  list_cats = FXCollections.observableArrayList(cs.getAll());
           JFXTreeTableColumn<Categorie_Evts, String> libCol=new JFXTreeTableColumn<>("libelle") ;   
        libCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Categorie_Evts , String>,ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Categorie_Evts, String> param) {
                 return param.getValue().getValue().getLibelle();
               }
           });
        libCol.cellFactoryProperty();
        
        butCol.setCellValueFactory(new PropertyValueFactory<>("but"));
        butCol.cellFactoryProperty();
        
        catsTable.setItems(list_cats);*/
             
              libCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Categorie_Evts , String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Categorie_Evts, String> param) {
                return param.getValue().getValue().libelleProperty();
           }
       });
              
               butCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Categorie_Evts , String>,ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Categorie_Evts, String> param) {
                return param.getValue().getValue().butProperty();
           }
       });
                 ObservableList<Categorie_Evts> list_cats = FXCollections.observableArrayList(cs.getAll());
       
               final TreeItem<Categorie_Evts> root=new RecursiveTreeItem<Categorie_Evts>(list_cats,RecursiveTreeObject::getChildren);
       catsTable.getColumns().setAll(libCol,butCol);
        catsTable.setRoot(root);
       catsTable.setShowRoot(false);
        
    
        
         }
    
}

