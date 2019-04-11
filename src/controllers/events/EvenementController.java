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
import java.util.ArrayList;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
import javafx.util.Callback;
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
     private JFXTextField lieutext;
     @FXML
     private JFXDatePicker datepicker;
     @FXML
     private ChoiceBox categoriebox;
     @FXML
     private ChoiceBox categoriebox_id;
     @FXML
     private JFXTextField titretext;
     @FXML
     private JFXTextArea descriptiontext;
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
      private ListView listView;
      @FXML
      private Button btnPhoto;
      @FXML
      private Button homebutton1;
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
        myEvents.setVisible(true);
        
        
        
        
        sontitre.setEditable(false);
        sonlieu.setEditable(false);
        sadate.setEditable(false);
        sacategorie.setEditable(false);
        sadescription.setEditable(false);
         ajout.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton1.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
        //*********************************
        
       
       
        
       
        
    }
    
    void afficher()
         {
              
              
           /*list_event = FXCollections.observableArrayList(es.getAll());
           
            
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.cellFactoryProperty();
        
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
      
        events.setItems(list_event);*/
        List<Evenement> listEvents = new ArrayList<>();
        listEvents=es.getAll(); 
        
        ObservableList<Evenement> data = FXCollections.observableArrayList(listEvents);
    
        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
                @Override   
                public ListCell<Evenement> call(ListView<Evenement> listView) {
                    return new CustomListCell();
                }
             });
       
        listView.setItems(data);
       
            
         
         }
    
     @FXML
    private void retourHome(ActionEvent event)
    {
        tabpane.getSelectionModel().select(0);
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
         //list_myEvents = FXCollections.observableArrayList(es.myEvents());
           
           
        List<Evenement> listEvents = new ArrayList<>();
        listEvents=es.myEvents(); 
        
        ObservableList<Evenement> data = FXCollections.observableArrayList(listEvents);
    
        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
                @Override   
                public ListCell<Evenement> call(ListView<Evenement> listView) {
                    return new CustomListCell();
                }
             });
       
        listView.setItems(data);
       
            
         
     }
      @FXML
    private void options(MouseEvent event)
    {
       // consulter.setVisible(true);
      if (!myEvents.isVisible())
      { modifier.setVisible(true);
            supprimer.setVisible(true);
            } 
    }
     @FXML
     private void supprimerEvent(ActionEvent event)
     {
         Evenement e=(Evenement)listView .getSelectionModel().getSelectedItem();
         if (!listView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'un event");
            alert.setHeaderText("Etes-vous sur de vouloir le supprimer ?  "
                    + e.getTitre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EvenementService es =new EvenementService(); 
                
                es.deleteEvent(e.getId());
               // SendMail.sendmail("amine.mraihi@esprit.tn",
                  //   "Annulation d evenement", "nous sommes désolés mais l evenement est annulé");
                afficher2();
            }
        }
     
     
     }
     
     
    @FXML
     private void consulter(MouseEvent event)
     {
         tabpane.getSelectionModel().select(2);
         Evenement e=(Evenement)listView.getSelectionModel().getSelectedItem();
         sontitre.setText(e.getTitre());
         sonlieu.setText(e.getLieu());
       //datepicker.setDate();
       sadate.setText(e.getDate().toString());
   //   sacategorie.setText(events.getSelectionModel().getSelectedItem().getCategorie().getLibelle());
         sadescription.setText(e.getDescription());
         javafx.scene.image.Image im = new javafx.scene.image.Image("http://localhost/ecosystemweb/web/uploads/evt/cover/"+e.getCover());
         imageEvent.setImage(im);
         modifier.setVisible(true);
            supprimer.setVisible(true);
         
       //  ajouter.setVisible(false);
         
     }
     
     @FXML
     private void modifierEvent(ActionEvent event)
     {
         tabpane.getSelectionModel().select(1);
         Evenement e=(Evenement)listView.getSelectionModel().getSelectedItem();
         titretext.setText(e.getTitre());
         lieutext.setText(e.getLieu());
       //datepicker.setDate();
       categoriebox.getSelectionModel().select(-1);
       categoriebox_id.getSelectionModel().select(-1);
       do{
           categoriebox.getSelectionModel().selectNext();
           categoriebox_id.getSelectionModel().selectNext();
         
      }
       while((int)categoriebox_id.getValue()!=e.getCategorie().getId());
        categoriebox.getSelectionModel().selectNext();
           categoriebox_id.getSelectionModel().selectNext();
         descriptiontext.setText(e.getDescription());
         valider.setVisible(true);
         annuler.setVisible(true);
         ajouter.setVisible(false);
         
     }
     
     @FXML
     private void validerModif(ActionEvent event)
     {
 Evenement e=(Evenement)listView .getSelectionModel().getSelectedItem();
      if (!listView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("modification d'un event");
            alert.setHeaderText("Etes-vous sur de vouloir le modifier ?  "
                    + e.getTitre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EvenementService es =new EvenementService();
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

           int i = (int)categoriebox_id.getValue();

       LocalDate localDate = datepicker.getValue();
       Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
       Date date = Date.from(instant);
         
         Evenement e1=new Evenement(e.getId(),lieutext.getText(),i,titretext.getText(),descriptiontext.getText(),date,txtPhoto.getText());
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\ecosystemjava\\src\\res\\event\\photo\\");
         copyImages.deplacerVers(txtPhoto, absolutePathPhoto,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\event\\photo\\");
         es.updateEvent(e1);
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
     
     @FXML
    private void retourHomeUser(ActionEvent event)
    {
        tabpane.getSelectionModel().select(0);
    }
}
