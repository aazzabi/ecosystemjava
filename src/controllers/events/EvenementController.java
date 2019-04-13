/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.events;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Categorie_Evts;
import entities.Evenement;
import entities.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.System.in;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
//import java.sql.LocalDateTime;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.Categorie_EvtsService;
import services.EvenementService;
import utils.copyImages;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class EvenementController implements Initializable {

    @FXML
    private TableView<Evenement> events;
    @FXML
    private TableColumn<Evenement, String> id;
    @FXML
    private TableColumn<Evenement, String> lieu;
    @FXML
    private TableColumn<Evenement, String> categorie;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> cover;
    @FXML
    private TableColumn<Evenement, String> nbvues;
     @FXML
    private TableColumn<Evenement, String> creator;
    @FXML
    private TableColumn<Evenement, Date> date;
     @FXML
     private TextField lieutext;
     @FXML
     private DatePicker datepicker;
     @FXML
     private ChoiceBox categoriebox;
     @FXML
     private ChoiceBox categoriebox_id;
     @FXML
     private TextField titretext;
     @FXML
     private TextArea descriptiontext;
     @FXML
     private Button ajouter;
     @FXML
     private Button modifier;
     @FXML
     private Button supprimer;
      @FXML
     private Button ajout;
      @FXML
     private Button valider;
      @FXML
     private Button consulter;
      @FXML
     private Button annuler;
      @FXML
     private Hyperlink myEvents;
      @FXML
     private TabPane tabpane;
      @FXML
     private JFXButton search;
      @FXML
     private JFXTextField searchbar;
      @FXML
      private Text txtPhoto;
      @FXML
      private ImageView imageEvent;
      @FXML
      private JFXTextField sonlieu;
      @FXML
      private JFXTextField sontitre;
      @FXML
      private JFXTextField sadate;
      @FXML
      private JFXTextField sacategorie;
      @FXML
      private JFXTextArea sadescription;
      
      @FXML
      private Button btnPhoto;
      private String absolutePathPhoto;
    
     
      
         
       
      
     
     
     
    /**
     * Initializes the controller class.
     */
    
     private ObservableList<Evenement> list_event = FXCollections.observableArrayList();
     private ObservableList<Evenement> list_myEvents = FXCollections.observableArrayList();
     Evenement e=new Evenement();
     EvenementService es=new EvenementService();
    Categorie_EvtsService Ev_S=new Categorie_EvtsService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            afficher();
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
        
        modifier.setVisible(false);
        supprimer.setVisible(false);
        consulter.setVisible(false); 
        
    }
    
    void afficher()
         {
              
              
           list_event = FXCollections.observableArrayList(es.getAll());
           
            
       /* id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.cellFactoryProperty();*/
        
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
     private void ajout(ActionEvent event) throws Exception
     {
           
     if (!lieutext.getText().equals("") && !titretext.getText().equals("") && !descriptiontext.getText().equals("") 
                ) { 
              
         System.out.println("hi");
         EvenementService cs =new EvenementService();
         int i = (int)categoriebox_id.getValue();
       // LocalDate d=(LocalDate) datetimepicker.getV;
      //LocalDateTime da = (java.sql.LocalDateTime.valueOf(datepicker.getSelectedDate())) ;
      LocalDate localDate = datepicker.getValue();
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date date = Date.from(instant);
System.out.println(localDate + "\n" + instant + "\n" + date);
         Evenement c=new Evenement(lieutext.getText(),i,titretext.getText(),descriptiontext.getText(),date,txtPhoto.getText());
         txtPhoto.setVisible(false);
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\ecosystemjava\\src\\res\\event\\photo");
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\evt\\cover");
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
     private void switchtab(ActionEvent event)
     {
     tabpane.getSelectionModel().select(1);
     }
     
     @FXML
     private void afficher2()
     {
         list_myEvents = FXCollections.observableArrayList(es.myEvents());
           
           
        /*id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.cellFactoryProperty();*/
        
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
       
        events.setItems(list_myEvents);
        myEvents.setVisible(false);
         
     }
      @FXML
    private void options(MouseEvent event)
    {
        consulter.setVisible(true);
      if (!myEvents.isVisible())
      { modifier.setVisible(true);
            supprimer.setVisible(true);
            } 
    }
     @FXML
     private void supprimerEvent(ActionEvent event)
     {
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
               // SendMail.sendmail("amine.mraihi@esprit.tn",
                  //   "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                afficher2();
            }
        }
     
     
     }
     
     
    @FXML
     private void consulter(ActionEvent event)
     {
         tabpane.getSelectionModel().select(2);
         sontitre.setText(events.getSelectionModel().getSelectedItem().getTitre());
         sonlieu.setText(events.getSelectionModel().getSelectedItem().getLieu());
       //datepicker.setDate();
       sadate.setText(events.getSelectionModel().getSelectedItem().getDate().toString());
   //    sacategorie.setText(events.getSelectionModel().getSelectedItem().getCategorie().getLibelle());
         sadescription.setText(events.getSelectionModel().getSelectedItem().getDescription());
         javafx.scene.image.Image im = new javafx.scene.image.Image("http://localhost/ecosystemweb/web/uploads/evt/cover/"+events.getSelectionModel().getSelectedItem().getCover());
         imageEvent.setImage(im);
         
       //  ajouter.setVisible(false);
         
     }
     
     @FXML
     private void modifierEvent(ActionEvent event)
     {
         tabpane.getSelectionModel().select(1);
         titretext.setText(events.getSelectionModel().getSelectedItem().getTitre());
         lieutext.setText(events.getSelectionModel().getSelectedItem().getLieu());
       //datepicker.setDate();
       categoriebox.getSelectionModel().select(-1);
       categoriebox_id.getSelectionModel().select(-1);
       do{
           categoriebox.getSelectionModel().selectNext();
           categoriebox_id.getSelectionModel().selectNext();
         
      }
       while((int)categoriebox_id.getValue()!=events.getSelectionModel().getSelectedItem().getCategorie().getId());
        categoriebox.getSelectionModel().selectNext();
           categoriebox_id.getSelectionModel().selectNext();
         descriptiontext.setText(events.getSelectionModel().getSelectedItem().getDescription());
         valider.setVisible(true);
         annuler.setVisible(true);
         ajouter.setVisible(false);
         
     }
     
     @FXML
     private void validerModif(ActionEvent event)
     {

      if (!events.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("modification d'un event");
            alert.setHeaderText("Etes-vous sur de vouloir le modifier ?  "
                    + events.getSelectionModel().getSelectedItem().getTitre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EvenementService es =new EvenementService();
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

           int i = (int)categoriebox_id.getValue();

       LocalDate localDate = datepicker.getValue();
       Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
       Date date = Date.from(instant);
         
         Evenement e=new Evenement(events.getSelectionModel().getSelectedItem().getId(),lieutext.getText(),i,titretext.getText(),descriptiontext.getText(),date,txtPhoto.getText());
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\ecosystemjava\\src\\res\\event\\photo\\");
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\event\\photo\\");
         es.updateEvent(e);
         lieutext.setText("");
         titretext.setText("");
         descriptiontext.setText("");   
         categoriebox_id.getSelectionModel().select(0);
         categoriebox.getSelectionModel().select(0);
         afficher2();
                 
                   annuler.setVisible(false);
                   valider.setVisible(false); 
                   ajouter.setVisible(true);
                  tabpane.getSelectionModel().select(0);
                afficher2();
            }
        }
}
     
     @FXML
     private void annulerModif(ActionEvent event)
     {
     
         lieutext.setText("");
         titretext.setText("");
         descriptiontext.setText("");   
         categoriebox_id.getSelectionModel().select(0);
         categoriebox.getSelectionModel().select(0);
         afficher2();
         tabpane.getSelectionModel().select(0);
     }
     
     @FXML
     private void photoChooser(ActionEvent event)
     {
          FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg","*.JPG","*.JPEG")
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
}
