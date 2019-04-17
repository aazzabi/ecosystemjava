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
import entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.System.in;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
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
import services.UserService;
import utils.copyImages;
import tray.notification.TrayNotification;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Label;
import tray.notification.NotificationType;
import static tray.notification.NotificationType.CUSTOM;
import static tray.notification.NotificationType.NOTICE;
import static tray.notification.NotificationType.SUCCESS;

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
     private Button best;
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
      @FXML
      private Button participer;
     @FXML
     private Button button;
      @FXML
      private Button noparticiper;
      @FXML
      private Button homebutton2;
      @FXML
      private Button homebutton;
      @FXML
      private ImageView participerstar;
      @FXML
      private ImageView noparticiperstar;
      @FXML
      Label label;
      @FXML
      private ImageView qr;
      @FXML
      private Label nbparticipants;
      @FXML
      private Label label1;
      @FXML
      private Label label2;
     @FXML
     private JFXButton trendingsif;
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
          //afficherNbparticipants();
          chercher();
          // searchbar.setVisible(false);
          //partagerRecent();
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
        
   
       
        consulter.setVisible(false); 
       // myEvents.setVisible(true);
        
        
        sontitre.setEditable(false);
        sonlieu.setEditable(false);
        sadate.setEditable(false);
        sacategorie.setEditable(false);
        sadescription.setEditable(false);
         ajout.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
       //  homebutton.setVisible(false);
        // imagehome.setVisible(false);
         homebutton1.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton2.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         participer.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         noparticiper.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         
         modifier.setVisible(false);
         supprimer.setVisible(false);
         
         valider.setVisible(false);
         annuler.setVisible(false);
          qr.setVisible(false);
      best.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
      
        //*********************************
        
        searchbar.textProperty().addListener(new ChangeListener<String>(){
      @Override
      public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue)
      {
          chercher();
      }      
      });
       
        
       
        
    }
    
    public void accueil()
    {
     
               myEvents.setVisible(true);
               modifier.setVisible(false);
               supprimer.setVisible(false);
             
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
        modifier.setVisible(false);
         supprimer.setVisible(false);
     
        }
         
        
    
    
  
    void afficher() 
         {
  
               myEvents.setVisible(true);
               modifier.setVisible(false);
               supprimer.setVisible(false);
             
        List<Evenement> listEvents = new ArrayList<>();
        listEvents=es.getRecent(); 
         ObservableList<Evenement> data = FXCollections.observableArrayList(listEvents);

        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
                @Override   
                public ListCell<Evenement> call(ListView<Evenement> listView) {
                    return new CustomListCell();
                }
             });
       
        listView.setItems(data);
        modifier.setVisible(false);
         supprimer.setVisible(false);
          lieutext.setText("");
         titretext.setText("");
         descriptiontext.setText("");   
         categoriebox_id.getSelectionModel().select(0);
         categoriebox.getSelectionModel().select(0);
         txtPhoto.setVisible(false);
         
         }
    
     @FXML
    private void retourHome(ActionEvent event)
    {
        tabpane.getSelectionModel().select(0);
        afficher();
    }
    
     @FXML
     private void ajout(ActionEvent event) throws Exception
     {
         
         
          LocalDate localDate = datepicker.getValue();
          try{
          Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
          Date date = Date.from(instant);
       System.out.println(localDate + "\n" + instant + "\n" + date);
       
           
     if (!lieutext.getText().equals("") && !titretext.getText().equals("") && !descriptiontext.getText().equals("") 
           &&  !date.toString().equals("") ) { 
              
         System.out.println("hi");
         EvenementService cs =new EvenementService();
         int i = (int)categoriebox_id.getValue();
       // LocalDate d=(LocalDate) datetimepicker.getV;
      //LocalDateTime da = (java.sql.LocalDateTime.valueOf(datepicker.getSelectedDate())) ;
      long dateevent=0;
      long datetawa=0;
      long DayInMillisecond=24*60*60*1000;
      
     dateevent= date.getTime()/DayInMillisecond;
     datetawa=java.sql.Date.valueOf(java.time.LocalDate.now()).getTime()/DayInMillisecond;
     String nbJour=Long.toString(dateevent-datetawa);
     if (Integer.parseInt(nbJour)>=2 ) 
      {  
          Evenement c=new Evenement(lieutext.getText(),i,titretext.getText(),descriptiontext.getText(),date,txtPhoto.getText());
               
          System.out.println(" le nombre de jour est"+Integer.parseInt(nbJour));
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
        
         accueil();} 
      
      else {
       
          TrayNotification tray = new TrayNotification("Date invalide!", "Vous ne pouvez pas planifier un évènement en moins de 48H!", NotificationType.ERROR);
            tray.showAndWait();
      }}
         else {
        TrayNotification tray = new TrayNotification("Champ(s) manquant(s)!", "Veuillez remplir tous les champs", NotificationType.ERROR);
            tray.showAndWait();
        }
        tabpane.getSelectionModel().select(0);
         accueil();
     
     }catch(Exception ex){
      TrayNotification tray = new TrayNotification("Champ  manquant!", "Veuillez remplir tous les champs", NotificationType.ERROR);
            tray.showAndWait();}
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
           
           myEvents.setVisible(false);
        List<Evenement> listEvents = new ArrayList<>();
        listEvents=es.myEvents(); 
        
        ObservableList<Evenement> data = FXCollections.observableArrayList(listEvents);
    
        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
                @Override   
                public ListCell<Evenement> call(ListView<Evenement> listView) {
                    return new CustomListCell();
                }
                
             });
       participer.setVisible(false);
       modifier.setVisible(true);
       supprimer.setVisible(true);
        listView.setItems(data);    
     }
      @FXML
    private void options(MouseEvent event)
    {
       // consulter.setVisible(true);
        /*if (!myEvents.isVisible())
        { modifier.setVisible(true);
        supprimer.setVisible(true);
        } */
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
        modifier.setVisible(false);
         supprimer.setVisible(false);
       
         tabpane.getSelectionModel().select(2);
       //   myEvents.setVisible(false); 
       //  homebutton.setVisible(true);
        // imagehome.setVisible(true);
      
         Evenement e=(Evenement)listView.getSelectionModel().getSelectedItem();
         sontitre.setText(e.getTitre());
         sonlieu.setText(e.getLieu());
       //datepicker.setDate();
       sadate.setText(e.getDate().toString());
   //   sacategorie.setText(events.getSelectionModel().getSelectedItem().getCategorie().getLibelle());
         sadescription.setText(e.getDescription());
         javafx.scene.image.Image im = new javafx.scene.image.Image("http://localhost/ecosystemweb/web/uploads/evt/cover/"+e.getCover());
         imageEvent.setImage(im);
         
       //  ajouter.setVisible(false);
    
        if(!es.verifierUser(e) && !myEvents.isVisible())
         {
             modifier.setVisible(true);
             supprimer.setVisible(true);
           
        // es.verifierParticipation(e);
             
         }
        
        if(es.verifierParticipation(e))
            {
               
                participer.setVisible(true);
                noparticiper.setVisible(false);
                participerstar.setVisible(true);
                noparticiperstar.setVisible(false);
               /* LikeLabel.setVisible(true);
                DislikeLabel.setVisible(false);*/
            }
            else
            {
                participer.setVisible(false); 
                noparticiper.setVisible(true);
                 participerstar.setVisible(false);
                noparticiperstar.setVisible(true);
              /*  ThumbsUp.setVisible(false);
                ThumbsDown.setVisible(true);
                LikeLabel.setVisible(false);
                DislikeLabel.setVisible(true);*/
            }
        if(es.verifierParticipation(e) && !es.verifierUser(e) )
                {
                  participer.setVisible(false);
                   noparticiper.setVisible(false);
                    participerstar.setVisible(false);
                noparticiperstar.setVisible(false);
                }
                    
         afficherNbparticipants();
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
                   lieutext.setText("");
      
                   
         titretext.setText("");
         descriptiontext.setText("");   
         categoriebox_id.getSelectionModel().select(0);
         categoriebox.getSelectionModel().select(0);
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
       
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhoto = choix.getAbsolutePath();
                txtPhoto.setText(choix.getName());
             } else {
                System.out.println("Image introuvable");
            }
        
     }
     
     @FXML
    private void retourHomeUser(ActionEvent event)
    {
        tabpane.getSelectionModel().select(0);
         modifier.setVisible(false);
         supprimer.setVisible(false);
         valider.setVisible(false);
         annuler.setVisible(false);
         ajouter.setVisible(true);
    }
    @FXML
    private void participer(ActionEvent event) throws SQLException
    {
        Evenement e=(Evenement)listView.getSelectionModel().getSelectedItem();
        int id=Session.getCurrentSession();
        Utilisateur u=new Utilisateur();
        UserService us=new UserService();
   
        es.participer(e,us.findById(id));
        participer.setVisible(false);
        noparticiper.setVisible(true);
        
        participerstar.setVisible(false);
        noparticiperstar.setVisible(true);
        
         /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Nouvelle Participation!");
         alert.setHeaderText("Vous venez de participer a l'évènement"+e.getTitre());
         Optional<ButtonType> result = alert.showAndWait();*/
         
          TrayNotification tray = new TrayNotification("Nouvelle Participation!", "Vous venez de participer a l'évènement"+e.getTitre(), SUCCESS);
            tray.showAndWait();
        
        
       
        
        
    }
    
    @FXML
    private void removeParticiper(ActionEvent event) throws SQLException
    {
         Evenement e=(Evenement)listView.getSelectionModel().getSelectedItem();
         
         Utilisateur u=new Utilisateur();
        int id=Session.getCurrentSession();
       
        UserService us=new UserService();
       
        es.annulerParticipation(e,us.findById(id));
        
        participer.setVisible(true);
        noparticiper.setVisible(false);
        
        participerstar.setVisible(true);
        noparticiperstar.setVisible(false);
        
          TrayNotification tray = new TrayNotification("Annulation participation", "Vous ne participez plus a cet évènement",NOTICE);
            tray.showAndWait();
       
      //  participer.setVisible(true);
  
    }
    
    @FXML
    private void trending(ActionEvent event)
    {
           ObservableList<Evenement> listEvents = FXCollections.observableArrayList();
        listEvents=es.TrendingEvents(); 
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
    private void handleButtonAction(ActionEvent event) {
         try {
             Evenement e=(Evenement)listView.getSelectionModel().getSelectedItem();
            String qrCodeData = ""+e.getTitre()+e.getDescription();
            String filePath = "src\\res\\event\\photo\\chillyfacts.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            File imageFile = new File("src/res/event/photo/chillyfacts.png");
String fileLocation = imageFile.toURI().toString();
Image fxImage = new Image(fileLocation);   
           qr.setVisible(true);
           
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    void afficherNbparticipants()

    {
        
         Evenement ev=(Evenement)listView.getSelectionModel().getSelectedItem();
        int x=es.getNbParticipants(ev);
        
        if(x>0)
        {  nbparticipants.setText(""+x);
        nbparticipants.setVisible(true);
          label1.setVisible(true);
          label2.setVisible(true);}
        else{
            nbparticipants.setText("");
           nbparticipants.setVisible(false);
          label1.setVisible(false);
          label2.setVisible(false);
        }
    }
    
    @FXML
    private void bestEvents(ActionEvent event)
    {
         List<Evenement> listEvents = new ArrayList<>();
        listEvents=es.BestEvents(); 
         ObservableList<Evenement> data = FXCollections.observableArrayList(listEvents);

        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
                @Override   
                public ListCell<Evenement> call(ListView<Evenement> listView) {
                    return new CustomListCell();
                }
             });
       
        listView.setItems(data);
        modifier.setVisible(false);
         supprimer.setVisible(false);
          lieutext.setText("");
         titretext.setText("");
         descriptiontext.setText("");   
         categoriebox_id.getSelectionModel().select(0);
         categoriebox.getSelectionModel().select(0);
         txtPhoto.setVisible(false);
    }
    
    void chercher()
    {
        if(searchbar.getText().equals(""))
         {
             afficher(); 
         }
        else
        {
             List<Evenement> listEvents = new ArrayList<>();
        listEvents=es.rechercherEvent(searchbar.getText()); 
         ObservableList<Evenement> data = FXCollections.observableArrayList(listEvents);

        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
                @Override   
                public ListCell<Evenement> call(ListView<Evenement> listView) {
                    return new CustomListCell();
                }
             });
       
        listView.setItems(data);
        }
    /*      List<Evenement> listEvents = new ArrayList<>();
        listEvents=es.myEvents(); 
        
        ObservableList<Evenement> data = FXCollections.observableArrayList(listEvents);
    
        listView.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {
                @Override   
                public ListCell<Evenement> call(ListView<Evenement> listView) {
                    return new CustomListCell();
                }
                
             });
       participer.setVisible(false);
       modifier.setVisible(true);
       supprimer.setVisible(true);
        listView.setItems(data);    */
    }
}
