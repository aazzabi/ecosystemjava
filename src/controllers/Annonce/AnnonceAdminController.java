/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Annonce;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Annonce;
import entities.Categorie_Annonce;
import entities.Session;
import iservices.IAnnonceService;
import iservices.ICategorieAnnonceService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.List;
import java.util.function.Predicate;
import javafx.scene.Parent;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AnnonceService;
import services.CategorieAnnonceService;
import utils.ControlleSaisie;
import utils.copyImages;

/**
 * FXML Controller class
 *
 * @author anasc
 */
public class AnnonceAdminController implements Initializable {

    @FXML
    private TableView<Annonce> ListeAnnonce;
    @FXML
    private TableColumn<Annonce, String> titre;

    @FXML
    private TableColumn<Annonce, String> Description;

    @FXML
    private TableColumn<Annonce, Date> date_creation;

    @FXML
    private TableColumn<Annonce, Double> prix;

    @FXML
    private TableColumn<Annonce, String> region;

    @FXML
    private TableColumn<Annonce, String> etat;

    @FXML
    private TableColumn<Annonce, Image> photo;

    @FXML
    private TableColumn<Annonce, Integer> likes;

    @FXML
    private TableColumn<Annonce, Integer> views;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_Edit;

    @FXML
    private JFXButton btn_Delete;

    @FXML
    private JFXButton btn_Clear;

    @FXML
    private JFXTextField txt_Titre;

    @FXML
    private JFXTextField txt_discription;

    @FXML
    private JFXTextField txt_prix;

    @FXML
    private ComboBox<String> cmb_region;

    @FXML
    private Button btn_photo_img;

    @FXML
    private ImageView img_photo;

    @FXML
    private ComboBox<Categorie_Annonce> cmb_cat;

    private String absolutePathPhotoAnnonce;

    @FXML
    private Text txtAnnoncephoto;
    @FXML
    private TextField rechTF;

    private IAnnonceService annonceService;

    private ICategorieAnnonceService categorieAnnonceService;

    private Image img;
    @FXML
    private TextField rechTF1;
    @FXML
    private JFXTextField txt_lib;
    @FXML
    private JFXButton btn_add_Cat;
    @FXML
    private JFXButton btn_Delet_Cat;
    @FXML
    private JFXButton btn_Edit_Cat;
    @FXML
    private JFXButton btn_Clear_Cat;
    @FXML
    private TableView<Categorie_Annonce> ListeCategories;
    @FXML
    private TableColumn<Categorie_Annonce, Integer> id;
    @FXML
    private TableColumn<Categorie_Annonce, String> Libelle;
    @FXML
    private TableColumn<Annonce, String> Cat_lib;
    @FXML
    private TableColumn<Annonce, String> user_lib;
    @FXML
    private BarChart<?, ?> all;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private PieChart piecharCAt;

    /**
     * Initializes the controller class.
     */
    private void AfficherAll() {

        annonceService = new AnnonceService();
        ArrayList arrayList = (ArrayList) annonceService.getall();

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        ListeAnnonce.setItems(observableList);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_creation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
        views.setCellValueFactory(new PropertyValueFactory<>("views"));
        Cat_lib.setCellValueFactory(new PropertyValueFactory<>("lib"));
        user_lib.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));

    }

    private void afficherCat() {
        categorieAnnonceService = new CategorieAnnonceService();
        ArrayList array = (ArrayList) categorieAnnonceService.getall();
        ObservableList observableList = FXCollections.observableArrayList(array);
        ListeCategories.setItems(observableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    }

    private void AfficherCombo() {
        categorieAnnonceService = new CategorieAnnonceService();
        List<Categorie_Annonce> listCat = categorieAnnonceService.getall();
        cmb_cat.getItems().addAll(listCat);

        ObservableList<String> reg = FXCollections.observableArrayList("Tunis", "Ariana", "Manouba", "Ben Arous", "Bizerte", "Béja", "Jendouba", "Siliana", "Kasserine", "Sidi Bouzid", "Gafsa", "Tozeur", "Kébili", "Tataouine", "Médenine", "Gabès", "Sfax", "Kairouan", "Mahdia", "Monastir", "Sousse", "Zaghouan", "Nabeul");
        cmb_region.getItems().addAll(reg);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AfficherAll();
        AfficherCombo();
        afficherCat();
        ListeAnnonce.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails();
        });
        ListeCategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showCat();
        });
        FilterRech();
        FilterRechCat();
        ImageClicked();
        AfficherStat();
        getSataCat();
    }

    private void FilterRech() {
        annonceService = new AnnonceService();
        ArrayList annonces = (ArrayList) annonceService.getall();
        ObservableList Oannonces = FXCollections.observableArrayList(annonces);
        FilteredList<Annonce> filtred_an = new FilteredList<>(Oannonces, e -> true);
        rechTF.setOnKeyReleased(e -> {
            rechTF.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtred_an.setPredicate((Predicate<? super Annonce>) ann -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String toLowerCaseNewValue = newValue.toLowerCase();
                    if ((ann.getTitre().toLowerCase().contains(toLowerCaseNewValue)) || (ann.getDescription().toLowerCase().contains(toLowerCaseNewValue)) || (ann.getRegion().toLowerCase().contains(toLowerCaseNewValue))) {
                        return true;

                    }

                    return false;
                });
            });
        });
        ListeAnnonce.setItems(filtred_an);
    }

    private void FilterRechCat() {
        categorieAnnonceService = new CategorieAnnonceService();
        ArrayList categories = (ArrayList) categorieAnnonceService.getall();
        ObservableList Ocategories = FXCollections.observableArrayList(categories);
        FilteredList<Categorie_Annonce> filtred_c = new FilteredList<>(Ocategories, e -> true);
        rechTF1.setOnKeyReleased(e -> {
            rechTF1.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtred_c.setPredicate((Predicate<? super Categorie_Annonce>) cat -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String toLowerCaseNewValue = newValue.toLowerCase();
                    if ((cat.getLibelle().toLowerCase().contains(toLowerCaseNewValue))) {
                        return true;

                    }

                    return false;
                });
            });
        });
        ListeCategories.setItems(filtred_c);
    }

    @FXML
    private void photoAnnonceChooser(ActionEvent event
    ) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        btn_photo_img.setOnAction(e -> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoAnnonce = choix.getAbsolutePath();
                txtAnnoncephoto.setText(choix.getName());
            } else {
                System.out.println("Image introuvable");
            }
        });
    }

    @FXML
    private void ajouterAction(ActionEvent event
    ) {
        annonceService = new AnnonceService();
        if (!(ControlleSaisie.estVide(txt_Titre, "titre"))
                && !(ControlleSaisie.estVide(txt_discription, "descritpion"))
                && !(ControlleSaisie.estVide(txt_prix, "prix"))
                && !(ControlleSaisie.estVidePhoto(txtAnnoncephoto, "image"))
                && !(ControlleSaisie.estVideCombo(cmb_region, "region"))
                && !(ControlleSaisie.estVideCombo(cmb_cat, "Catégorie"))) {
            Annonce a = new Annonce(
                    txt_Titre.getText(),
                    txt_discription.getText(),
                    Double.parseDouble(txt_prix.getText()),
                    cmb_region.getValue(),
                    txtAnnoncephoto.getText(),
                    cmb_cat.getValue().getId(),
                    Session.getCurrentSession());
            copyImages.deplacerVers(txtAnnoncephoto, absolutePathPhotoAnnonce, "C:\\ecosystemjava\\src\\res\\Annonce\\photo\\");
            copyImages.deplacerVers(txtAnnoncephoto, absolutePathPhotoAnnonce, "C:\\wamp\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\");
            annonceService.add(a);

        }
        AfficherAll();

    }

    @FXML
    public void AjouterCatAvtion(ActionEvent ecent) {

        if (!(ControlleSaisie.estVide(txt_lib, "Libelle"))) {
            Categorie_Annonce cat = new Categorie_Annonce(txt_lib.getText());
            categorieAnnonceService.add(cat);

        }
        afficherCat();
    }

    @FXML
    public void supprimerAction(ActionEvent event
    ) {
        annonceService = new AnnonceService();
        int index = ListeAnnonce.getSelectionModel().getSelectedItem().getId();
        //System.out.println(index);
        annonceService.delete(index);
        AfficherAll();
    }

    @FXML
    public void supprimerCAtAction(ActionEvent event
    ) {
        categorieAnnonceService = new CategorieAnnonceService();
        int index = ListeCategories.getSelectionModel().getSelectedItem().getId();
        //System.out.println(index);
        categorieAnnonceService.delete(index);
        afficherCat();
    }

    @FXML
    void modifierAction(ActionEvent event) {

        annonceService = new AnnonceService();
        int index = ListeAnnonce.getSelectionModel().getSelectedItem().getId();
        System.out.println(index);
        String a_Titre = txt_Titre.getText();
        String a_Description = txt_discription.getText();
        String a_Prix = txt_prix.getText();
        String a_Photo = txtAnnoncephoto.getText();
        String a_Reg = cmb_region.getValue();
        int a_Cate = cmb_cat.getValue().getId();
        Annonce a = new Annonce(a_Titre, a_Description, Double.parseDouble(a_Prix), a_Reg, a_Photo, a_Cate);
        annonceService.update(a, index);
        AfficherAll();

    }

    @FXML
    void modifierCatAction(ActionEvent event) {

        categorieAnnonceService = new CategorieAnnonceService();
        int index = ListeCategories.getSelectionModel().getSelectedItem().getId();
        String c_Libb = txt_lib.getText();
        Categorie_Annonce c = new Categorie_Annonce(c_Libb);
        categorieAnnonceService.update(c, index);
        afficherCat();

    }

    @FXML
    void annulerAction(ActionEvent event) {
        txt_Titre.setText("");
        txt_discription.setText("");
        txt_prix.setText("");
        txtAnnoncephoto.setText("");
        cmb_cat.setValue(null);
        cmb_region.setValue(null);
        txt_lib.setText("");
    }

    private void showDetails() {
        txt_Titre.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getTitre());
        txt_discription.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getDescription());
        txt_prix.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getPrix().toString());
        txtAnnoncephoto.setText(ListeAnnonce.getSelectionModel().getSelectedItem().getPhoto());
        cmb_region.setValue(ListeAnnonce.getSelectionModel().getSelectedItem().getRegion());
        Categorie_Annonce a = categorieAnnonceService.displayByName(ListeAnnonce.getSelectionModel().getSelectedItem().getCategorie_id());
        cmb_cat.setValue(a);
        img = new Image("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + txtAnnoncephoto.getText());
        img_photo.setImage(img);
    }

    private void showCat() {
        txt_lib.setText(ListeCategories.getSelectionModel().getSelectedItem().getLibelle());
    }

    private void ImageClicked() {
        img_photo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("j'ai clicker ici");
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/gui/ImageshowAnn.fxml"));
                try {
                    Loader.load();
                } catch (IOException e) {
                    System.out.println(e);
                }
                ImageshowAnnController display = Loader.getController();
                display.getImage(img);
                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
                event.consume();
            }

        });
    }

    private void AfficherStat() {
        annonceService = new AnnonceService();
        ArrayList<Integer> Stat = new ArrayList<Integer>();
        Stat = (ArrayList<Integer>) annonceService.Stat();
        ArrayList<String> months = new ArrayList<>();
        months.add("janvier");
        months.add("février");
        months.add("mars");
        months.add("avril");
        months.add("mai");
        months.add("juin");
        months.add("juillet");
        months.add("janvier");
        months.add("août");
        months.add("septembre");
        months.add("octobre");
        months.add("décembre");
        XYChart.Series set1 = new XYChart.Series<>();

        for (int i = 0; i < Stat.size(); i++) {
            set1.getData().add(new XYChart.Data(months.get(i), Stat.get(i)));

        }

        all.getData().addAll(set1);
    }

    private void getSataCat() {
        List<Annonce> la = new ArrayList<>();
        annonceService = new AnnonceService();
        la = annonceService.StatByCat();
        ObservableList<PieChart.Data> piecharts = FXCollections.observableArrayList();
        for (Annonce l : la) {
            piecharts.add(new PieChart.Data(l.getNomCat(), l.getNb_cat()));
        }

        piecharCAt.setData(piecharts);
    }

}
