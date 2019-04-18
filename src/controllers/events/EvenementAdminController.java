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
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

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
    /*@FXML
    private TextField textbut;*/
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
      private Button share;
         @FXML
    private CategoryAxis x;
    @FXML
    private Button homebutton11;
    @FXML
    private NumberAxis y;
      @FXML
    private BarChart<?, ?> bar;
    @FXML 
    private JFXTreeTableView<Categorie_Evts> catsTable;
    @FXML
    private ImageView tweet;
   /*  @FXML
    private TreeTableColumn<Categorie_Evts, String> libCol;
    
    @FXML
    private TreeTableColumn<Categorie_Evts, String> butCol;*/
     @FXML
     private ChoiceBox combobut;
     @FXML
     private PieChart pie;
     @FXML 
     Button gotochart;
  
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
              rechercher();
              afficherChart();
             // afficherBar();
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
         supprimer.setVisible(false);  
         homebutton.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton1.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton2.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         ajoutcategorie.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         homebutton11.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
         //categorie
          combobut.setItems(FXCollections.observableArrayList("Lucratif","Non Lucratif"));
      supprimer1.setVisible(false);
      modifier.setVisible(false);
      gotochart.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
      share.setStyle("-fx-background-color: rgba(255, 255, 255,0);");
      tweet.setVisible(false);
    searchbar.textProperty().addListener(new ChangeListener<String>(){
      @Override
      public void changed(ObservableValue<? extends String> observable,
              String oldValue, String newValue)
      {
          rechercher();
      }      
      });
     // ajouter1.setVisible(false);
      annulerCat.setVisible(false);
      validerCat.setVisible(false); 
      
      LoadBarChartData();
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
         tweet.setVisible(true);
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
    private void ajout(ActionEvent event)
    {
         if (!lieutext.getText().equals("") && !titretext.getText().equals("") && !descriptiontext.getText().equals("")) { 
              
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
         categoriebox.getSelectionModel().select(-1);
         datepicker.setValue(null);
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
        
        tabpane.getSelectionModel().select(2);
        tabpane1.getSelectionModel().select(0);
        
    }
    
    @FXML
    private void retourHome(ActionEvent event)
    {
        tabpane.getSelectionModel().select(0);
        // tabpane1.getSelectionModel().select(0);
    }
    @FXML
    private void retourHomeUser(ActionEvent event)
    {
        tabpane.getSelectionModel().select(0);
         lieutext.setText("");
         titretext.setText("");
         descriptiontext.setText("");   
         categoriebox_id.getSelectionModel().select(0);
         categoriebox.getSelectionModel().select(-1);
         datepicker.setValue(null);
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
          validerCat.setVisible(false);
         annulerCat.setVisible(false);
         ajouter1.setVisible(true); 
      if (!textlibelle.getText().equals("")) 
      {
         Categorie_EvtsService cs =new Categorie_EvtsService();
         String i = (String) combobut.getValue();
         Categorie_Evts c=new Categorie_Evts(textlibelle.getText(),i);
         cs.addCategorie_Evts(c);
         
         textlibelle.setText("");
        // textbut.setText("");
        combobut.getSelectionModel().select(2);
        
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
          validerCat.setVisible(true);
         annulerCat.setVisible(true);
         ajouter1.setVisible(false); 
        textlibelle.setText(catsTable.getSelectionModel().getSelectedItem().getValue().getLibelle());
       // textbut.setText(catsTable.getSelectionModel().getSelectedItem().getValue().getBut());
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
      //   textbut.setText("");
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
   
    void rechercher()
    {
        if(searchbar.getText().equals(""))
         {
             afficher(); 
         }
        else{
        list_event = FXCollections.observableArrayList(es.rechercherEvent(searchbar.getText()));
        
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
    
}
    
    @FXML
    private void goToChart(ActionEvent event)
    {
        tabpane.getSelectionModel().select(3);
        afficherChart();
        LoadBarChartData();
        
    }
    
    void afficherChart()
    {
        ObservableList<PieChart.Data>pieChartData=FXCollections.observableArrayList
                (new PieChart.Data("lucratifs",es.EventsLucratifs()),
                new PieChart.Data("non lucratifs", es.EventsNonLucratifs())
                );
        
        pie.setData(pieChartData);
    }
    
    void afficherBar()
    {
       /*  final String été = "été";
         final  String printemps="Spring";
          final  String hiver="winter";
           final  String automne="automne";
  
        xAxis.setLabel("Country");       
        yAxis.setLabel("Value");
 xAxis.setCategories(FXCollections.<String>
      observableArrayList(Arrays.asList(été,printemps,hiver)));
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Lucratifs");       
        series1.getData().add(new XYChart.Data(été, 1));
        series1.getData().add(new XYChart.Data(printemps, 1));
        series1.getData().add(new XYChart.Data(hiver, 0));
        series1.getData().add(new XYChart.Data(automne, 0.15));
        
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Non Lucratifs");
        series2.getData().add(new XYChart.Data(été, 2));
        series2.getData().add(new XYChart.Data(printemps, 5.9));
        series2.getData().add(new XYChart.Data(hiver, 3.37));
        series2.getData().add(new XYChart.Data(automne, 0.16));
        
        bc.getData().addAll(series1,series2);   */
        /////////*************anas 
        
    /*    ArrayList<Integer> Stat = new ArrayList<Integer>();
        Stat = (ArrayList<Integer>) es.EventsLucratifParEte();
        XYChart.Series set1 = new XYChart.Series<>();
        
        for (int i = 0; i < Stat.size(); i++) {
        set1.getData().add(new XYChart.Data(Integer.toString(i), Stat.get(i)));
        
        }
        bc.getData().addAll(set1);*/
        
        XYChart.Series set1= new XYChart.Series<>();    
        set1.getData().add(new XYChart.Data("Rania",60));
        set1.getData().add(new XYChart.Data("midou",60));
        set1.getData().add(new XYChart.Data("alé",50));
      
    //    bar.setData(bc);
       
    }
    
    @FXML
    private void partagerTwitter(ActionEvent event) {
        
        try {
            Evenement e = events.getSelectionModel().getSelectedItem();
           
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("H65Qxak8p1uLkQG0TvlM7Sp00")
                    .setOAuthConsumerSecret("BXCBFoBxGHJaIEt3e39EkqcfFHBbvvR9xuTBZSIAS39XmaVtdO")
                    .setOAuthAccessToken("1117941053896654848-IfzZkjMoKUpb12oVsPXaltNnGOuzBe")
                    .setOAuthAccessTokenSecret("4qp3000ywhK73Wh4EvE24GmDQDXHEXfKLrt3xChNiYm23");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            
            
             String ch = "Soyez nombreux à assister à cet événement Eco_friendly avec des opportunités a ne pas rater!";     
            
            twitter.updateStatus("Salut à tous! EcoZone a le plaisir"
                    + " de vous convier à son événement "+e.getTitre()+" un(e) "+e.getCategorie().toString()+" qui se déroulera le "+e.getDate().toString()+"  "+ch);
         
            
            /* Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Partage sur Twitter");
            dialog.setHeaderText("An information dialog with header");
            dialog.setContentText("Evénement publié avec succès! ");
            dialog.showAndWait();*/
               TrayNotification tray = new TrayNotification("Partage sur Twitter", "Evénement publié avec succès!", SUCCESS);
            tray.showAndWait();
            
        } catch (TwitterException ex) {
            System.out.println(ex.getMessage());
        }
            
    }
    
        
  void LoadBarChartData(){
              XYChart.Series set1= new XYChart.Series<>();   
               XYChart.Series set2= new XYChart.Series<>();   
       ObservableList<String> monthNames = FXCollections.observableArrayList();
       String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
       set1.setName("Nombre d'evenements lucratifs participés par mois");
       bar.getData().clear();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        x.setCategories(monthNames);
          int[] monthValue =es.StatEventsLucratif();
          
        for (int i = 1; i <12; i++) {
            set1.getData().add(new XYChart.Data<>(monthNames.get(i-1), monthValue[i]));
        }
        
         set2.setName("Nombre d'evenements non lucratifs participés par mois");
       bar.getData().clear();
        // Convert it to a list and add it to our ObservableList of months.
        //monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
      //  x.setCategories(monthNames);
         int[] monthValue2 =es.StatEventsNonLucratif();
          
        for (int i = 1; i <12; i++) {
            set2.getData().add(new XYChart.Data<>(monthNames.get(i-1), monthValue2[i]));
        }
        
         bar.getData().addAll(set1,set2);
      
       }
    
}

