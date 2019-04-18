/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.jfoenix.controls.JFXTextField;
import entities.reparateur.AnnounceRep;
import entities.reparateur.DemandeComptePro;
import entities.reparateur.Reparateur;
import entities.reparateur.Reparation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.AnnounceRepService;
import services.DemandeCompteProService;
import services.ReparationService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class ReparateurMainScController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<AnnounceRep> tableviewan;

    @FXML
    private TableColumn<AnnounceRep, ImageView> a_col_img;

    @FXML
    private TableColumn<AnnounceRep, Integer> a_col_id;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_titre;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_desc;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_cat;

    @FXML
    private TableColumn<AnnounceRep, String> a_col_date;
    @FXML
    private TableColumn<AnnounceRep, Integer> a_col_user;

    @FXML
    private TableColumn<AnnounceRep, Integer> a_col_rep;

    @FXML
    private TableColumn<AnnounceRep, Float> a_col_prix;

    @FXML
    private JFXTextField search;

    @FXML
    private JFXTextField search1;

    @FXML
    private PieChart piechart1;

    @FXML
    private PieChart piechart12;

    @FXML
    private TableView<Reparation> tableviewrep;

    @FXML
    private TableColumn<Reparation, Integer> rep_col_id;

    @FXML
    private TableColumn<Reparation, String> rep_col_com;

    @FXML
    private TableColumn<Reparation, String> rep_col_rep;

    @FXML
    private TableColumn<Reparation, String> rep_col_user;

    @FXML
    private TableColumn<Reparation, String> rep_col_statut;

    @FXML
    private TableColumn<Reparation, String> rep_col_date;

    @FXML
    private Label nban;

    @FXML
    private Label nboffre;

    @FXML
    private Label nbaj;

    @FXML
    private Label nbreps;

    @FXML
    private Label nbreppro;

    @FXML
    private Label nbdemande;
    
    @FXML
    private Label nbtotal;

    @FXML
    private Label gain;

    @FXML
    private TableView<DemandeComptePro> tableviewdemande;

    @FXML
    private TableColumn<DemandeComptePro, Integer> demande_col_id;

    @FXML
    private TableColumn<DemandeComptePro, String> demande_col_rep;

    @FXML
    private TableColumn<DemandeComptePro, String> demande_col_demande;

    @FXML
    private TableColumn<DemandeComptePro, String> demande_col_type;

    @FXML
    private TableColumn<DemandeComptePro, ?> demande_col_action;

    @FXML
    private JFXTextField search2;

    @FXML
    private PieChart piechart121;
    ObservableList<AnnounceRep> tableDataAnnonce;
    ObservableList<Reparation> tableDataReparation;
    ObservableList<DemandeComptePro> tableDataDemande;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        tableviewan.setEditable(false);
        tableviewan.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        a_col_img.setCellValueFactory(c -> new SimpleObjectProperty<>(new ImageView(c.getValue().getImage())));
        a_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        a_col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        a_col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        a_col_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        a_col_date.setCellValueFactory(new PropertyValueFactory<>("datePub"));
        a_col_user.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        a_col_rep.setCellValueFactory(new PropertyValueFactory<>("nomRep"));
        a_col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableDataAnnonce = AnnounceRepService.getAnnounceRepList();
        tableviewan.setItems(tableDataAnnonce);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
        tableviewrep.setEditable(false);
        tableviewrep.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        rep_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rep_col_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        rep_col_rep.setCellValueFactory(new PropertyValueFactory<>("nomRep"));
        rep_col_user.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        rep_col_statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        rep_col_date.setCellValueFactory(new PropertyValueFactory<>("dateP"));
        tableDataReparation = ReparationService.getReparationList();
        tableviewrep.setItems(tableDataReparation);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tableviewdemande.setEditable(false);
        tableviewdemande.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        demande_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        demande_col_demande.setCellValueFactory(new PropertyValueFactory<>("dateDemande"));
        demande_col_rep.setCellValueFactory(new PropertyValueFactory<>("nomRep"));
        demande_col_type.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tableDataDemande = DemandeCompteProService.getDemandeCompteList();
        tableviewdemande.setItems(tableDataDemande);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FilteredList<AnnounceRep> filteredData = new FilteredList<>(tableDataAnnonce, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(AnnounceRep -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (AnnounceRep.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (AnnounceRep.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<AnnounceRep> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableviewan.comparatorProperty());
        tableviewan.setItems(sortedData);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        FilteredList<Reparation> filteredData2 = new FilteredList<>(tableDataReparation, p -> true);
        search1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(Reparation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (Reparation.getNomRep().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Reparation.getNomUser().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Reparation> sortedData2 = new SortedList<>(filteredData2);
        sortedData2.comparatorProperty().bind(tableviewrep.comparatorProperty());
        tableviewrep.setItems(sortedData2);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FilteredList<DemandeComptePro> filteredData3 = new FilteredList<>(tableDataDemande, p -> true);
        search2.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData3.setPredicate(Reparation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (Reparation.getNomRep().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<DemandeComptePro> sortedData3 = new SortedList<>(filteredData3);
        sortedData3.comparatorProperty().bind(tableviewdemande.comparatorProperty());
        tableviewdemande.setItems(sortedData3);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int sizett = AnnounceRepService.getAnnounceRepList().size();
        if (sizett != 0) {
            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Téléphone", (AnnounceRepService.getNombreAnn("Téléphone") / sizett) * 100),
                            new PieChart.Data("Electroménager", (AnnounceRepService.getNombreAnn("Electroménager") / sizett) * 100),
                            new PieChart.Data("Meuble", (AnnounceRepService.getNombreAnn("Meuble") / sizett) * 100));
            piechart1.setData(pieChartData);
            piechart1.setTitle("Pourcentage des catégories");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        sizett = ReparationService.getReparationList().size();
        if (sizett != 0) {

            long l1 = ReparationService.getReparationList().stream().filter(e -> e.getStatut().equals("En cours")).count();
            long l2 = ReparationService.getReparationList().stream().filter(e -> e.getStatut().equals("Terminer")).count();
            long l3 = ReparationService.getReparationList().stream().filter(e -> e.getStatut().equals("Ilimite")).count();

            ObservableList<PieChart.Data> pieChartData2
                    = FXCollections.observableArrayList(
                            new PieChart.Data("En cours", ((double) l1 / sizett) * 100),
                            new PieChart.Data("Terminer", ((double) l2 / sizett) * 100),
                            new PieChart.Data("Annuler", ((double) l3 / sizett) * 100));
            piechart12.setData(pieChartData2);
            piechart12.setTitle("Statut des réparations effectué");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        sizett = DemandeCompteProService.getDemandeCompteList().size();
        Long somme=0L ;
        if (sizett != 0) {
            long l1 = DemandeCompteProService.getDemandeCompteList().stream().filter(e -> e.getStatut().equals("Standard")).count();
            long l2 = DemandeCompteProService.getDemandeCompteList().stream().filter(e -> e.getStatut().equals("Basique")).count();
            long l3 = DemandeCompteProService.getDemandeCompteList().stream().filter(e -> e.getStatut().equals("Ilimite")).count();
            
             somme=l1*80+l2*40+l3*150;
            ObservableList<PieChart.Data> pieChartData3
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Standard", ((double) l1 / sizett) * 100),
                            new PieChart.Data("Basique", ((double) l2 / sizett) * 100),
                            new PieChart.Data("Ilimite", ((double) l3 / sizett) * 100));
            piechart121.setData(pieChartData3);
            piechart121.setTitle("Type de compte professionnel");
        }
       
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        ObservableList<AnnounceRep> list = AnnounceRepService.getAnnounceRepList();
        Long nboffres = list.stream().filter(e -> e.getPrix() != 0).count();
        Long ajs = list.stream().filter(e -> e.getDatePub().equals(dtf.format(now))).count();
        Long reps = ReparationService.getReparationList().stream().filter(e -> e.getStatut().equals("En cours")).count();
        int nbann = list.size();
        nbaj.setText(Long.toString(ajs));
        nban.setText(Integer.toString(nbann));
        nboffre.setText(Long.toString(nboffres));
        nbreps.setText(Long.toString(reps));
        ObservableList<Reparateur>ListRep=UserService.getTtReparateur();
          System.out.println(ListRep.size());
        Long nbreppros =ListRep.stream().filter(e->e.getType().equals("Professionel")).count();
        
        
        nbtotal.setText(Integer.toString(ListRep.size()));
        nbreppro.setText(Long.toString(nbreppros));
        nbdemande.setText(Integer.toString(DemandeCompteProService.getDemandeCompteList().size()));
        gain.setText(Long.toString(somme));
        
       
    
        
    }

    public void supprimer() {
        if (tableviewan.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionner vous devez en sélectioner une ");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText("Voulez vous supprimer les lignes suivants ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<AnnounceRep> selectedRows = tableviewan.getSelectionModel().getSelectedItems();
                ArrayList<AnnounceRep> rows = new ArrayList<>(selectedRows);
                rows.forEach((AnnounceRep row) -> {
                    AnnounceRepService.supprimer(row.getId());
                    tableDataAnnonce.remove(row);

                });

            } else {

            }

        }

    }

//    public void edit() {
//        if (tableviewan.getSelectionModel().getSelectedIndex() == -1) {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Attention !");
//            alert.setHeaderText(null);
//            alert.setContentText("Aucune ligne n'est sélectionner vous devez en sélectioner une ");
//
//            alert.showAndWait();
//        } else {
//            try {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/gui/reparateur/ReparateurEditAnnonce.fxml"));
//                /* 
//         * if "fx:controller" is not set in fxml
//         * fxmlLoader.setController(NewWindowController);
//                 */
//                Scene scene = new Scene(fxmlLoader.load());
//                Stage stage = new Stage();
//                ReparateurEditAnnonceController controller = fxmlLoader.getController();
//                controller.initData(tableviewan.getSelectionModel().getSelectedItem());
//
//                stage.setTitle("Edition d'une announce de réparation");
//                stage.setScene(scene);
//                stage.setResizable(false);
//
//                stage.show();
//            } catch (IOException e) {
//                Logger logger = Logger.getLogger(getClass().getName());
//                logger.log(Level.SEVERE, "Failed to create new Window.", e);
//            }
//        }
//
//    }

    public void clickItem(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/reparateur/ReparateurEditAnnonce.fxml"));
            /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                ReparateurEditAnnonceController controller = fxmlLoader.getController();
                controller.initData(tableviewan.getSelectionModel().getSelectedItem());

                stage.setTitle("Edition d'une announce de réparation");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReparateurMainScController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void clickItem2(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/reparateur/ReparateurEditReparation.fxml"));
            /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                ReparateurEditReparationController controller = fxmlLoader.getController();
                controller.initData(tableviewrep.getSelectionModel().getSelectedItem());

                stage.setTitle("Edition d'une réparation  en cours");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReparateurMainScController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
      public void clickItem3(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/reparateur/ReparateurConfirmDemande.fxml"));
            /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                ReparateurConfirmDemandeController controller = fxmlLoader.getController();
                controller.initData(tableviewdemande.getSelectionModel().getSelectedItem());

                stage.setTitle("Confirmation d'une demande de compte pro");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReparateurMainScController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }


    public void supprimer3() {

        if (tableviewdemande.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionner vous devez en sélectioner une ");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText("Voulez vous supprimer les lignes suivants ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<DemandeComptePro> selectedRows2 = tableviewdemande.getSelectionModel().getSelectedItems();
                ArrayList<DemandeComptePro> rows2 = new ArrayList<>(selectedRows2);
                rows2.forEach((DemandeComptePro row2) -> {

                    DemandeCompteProService.supprimer(row2.getId());
                    tableDataDemande.remove(row2);

                });

            } else {

            }

        }

    }

    public void supprimer2() {

        if (tableviewrep.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionner vous devez en sélectioner une ");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText("Voulez vous supprimer les lignes suivants ? ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ObservableList<Reparation> selectedRows2 = tableviewrep.getSelectionModel().getSelectedItems();
                ArrayList<Reparation> rows2 = new ArrayList<>(selectedRows2);
                rows2.forEach((Reparation row2) -> {

                    ReparationService.supprimer(row2.getId());
                    tableDataReparation.remove(row2);

                });

            } else {

            }

        }

    }
    
    
   public void refresh1()
    {
          tableDataAnnonce = AnnounceRepService.getAnnounceRepList();
        tableviewan.setItems(tableDataAnnonce);
        System.out.println("LOL");
        
    }
    
    public   void refresh2()
    {
      tableDataReparation = ReparationService.getReparationList();
        tableviewrep.setItems(tableDataReparation);
           System.out.println("LOL");
        
    }
       
     public     void refresh3()
    {
         tableDataDemande = DemandeCompteProService.getDemandeCompteList();
        tableviewdemande.setItems(tableDataDemande);
           System.out.println("LOL");
        
        
    }
}
