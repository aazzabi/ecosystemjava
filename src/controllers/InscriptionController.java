/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Reparateur;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.UserService;
import utils.Notification;
import tray.notification.TrayNotification;
import static tray.notification.NotificationType.ERROR;
import static tray.notification.NotificationType.SUCCESS;
import utils.ControlleSaisie;
import utils.copyImages;

/**
 * FXML Controller class
 *
 * @author arafe
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nomUser;
    @FXML
    private TextField prenomUser;
    @FXML
    private TextField emailUser;
    @FXML
    private TextField pseudoUser;
    @FXML
    private TextField nomProprieteUser;
    @FXML
    private TextField rueUser;
    @FXML
    private TextField villeUser;
    @FXML
    private TextField telephoneUser;
    @FXML
    private PasswordField mdpUser;
    @FXML
    private PasswordField confirmationMdpUser;
    @FXML
    private Button btnAddUser;
    
    
    @FXML
    private TextField nomReparateur;
    @FXML
    private TextField prenomReparateur;
    @FXML
    private TextField emailReparateur;
    private TextField photoReparateur;
    @FXML
    private TextField pseudoReparateur;
    @FXML
    private TextField adresseReparateur;
    @FXML
    private TextField numTelReparateur;
    @FXML
    private TextField numFixeReparateur;
    @FXML
    private TextField specialiteReparateur;
    @FXML
    private PasswordField mdpReparateur;
    @FXML
    private PasswordField confirmationMdpReparateur;
    @FXML
    private TextField descriptionReparateur;
    @FXML
    private TextField horaireTravailReparateur;
    @FXML
    private Button btnAddReparateur;
        
    
    Stage dialogStage = new Stage();
    Scene scene;
            
    private static Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    @FXML
    private Button btnPhotoUser;
    @FXML
    private Text txtPhotoUser;
    
    private String absolutePathPhotoUser;
    @FXML
    private Button btnPhotoReparateur;
    @FXML
    private Text txtPhotoReparateur;
    private String absolutePathPhotoReparateur;

    
    @FXML
    void goToLogin(ActionEvent event) throws SQLException, IOException, Exception {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/Login.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();   
    }
    
    @FXML
    private void photoUserChooser(ActionEvent event){
    FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        btnPhotoUser.setOnAction(e-> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoUser = choix.getAbsolutePath();
                txtPhotoUser.setText(choix.getName());
             } else {
                System.out.println("Image introuvable");
            }
        });
    }
    // hedhi fonction mta3 l boutton , fiha l recuperation mta3 l fichier , w ena 3amel appart champ txtPhotoUser, 3amel un autre champ esmou absolutePathPhotoUser bech n recuperii le champ kemel ( nest7a9ou fel copie )
    // donc fel base de donées n sob l txtPhotoUser fhamtni ? l absolutepath winou fel mehouch ma7tout fel FXML , ena zedtou l fou9 , amma ma3andouch position, string 3adi  , parametre simple ;) behi w l dossier upload win
    @FXML
    private void photoReparateurChooser(ActionEvent event){
    FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
         );
        btnPhotoReparateur.setOnAction(e-> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoReparateur = choix.getAbsolutePath();
                txtPhotoReparateur.setText(choix.getName());
             } else {
                System.out.println("Image introuvable");
            }
        });
    }
    
    @FXML 
    void addUser(ActionEvent event) throws SQLException, IOException, Exception {
    if ( !(ControlleSaisie.estVide(nomUser, "nom")) 
            && !(ControlleSaisie.estVide(prenomUser, "prenom")) 
            && !(ControlleSaisie.estVide(emailUser, "email"))
            && !(ControlleSaisie.estVide(pseudoUser, "pseudo"))  
            && !(ControlleSaisie.estVide(telephoneUser, " téléphone ")) 
            && !(txtPhotoUser.getText().equals(""))
            && !(ControlleSaisie.estVide(mdpUser, "mot de passe")) 
            && !(ControlleSaisie.estVide(confirmationMdpUser, "confirmation mdp")) 
            && !(ControlleSaisie.sontConforme( mdpUser, "mot de passe", confirmationMdpUser, "confirmation de mot de passe "))
            && (ControlleSaisie.estEmailValide(emailUser)))
        {
            Utilisateur u = new Utilisateur();

            u.setNom(nomUser.getText());
            u.setPrenom(prenomUser.getText());
            u.setEmail(emailUser.getText());
            u.setEmailCanonical(emailUser.getText());
            u.setUsername(pseudoUser.getText());
            u.setUsernameCanonical(pseudoUser.getText());
            u.setPhoto(txtPhotoUser.getText());
            u.setRue(rueUser.getText());
            u.setVille(villeUser.getText());
            u.setNumtel(telephoneUser.getText());
            u.setNomPropriete(nomProprieteUser.getText());
            u.setPassword(mdpUser.getText());
            u.setEnabled(true);
            u.setDiscr("user");
            u.setRoles("a:0:{}");
            
            //ena na5dem 3la dossier www/wamp/ enoutou peut etre wamp64 donc chouf badel selon l dossier ey ama famech dossier upload houni yetkayed fihN, yesn3ou ki yabda mafamech mech mochkol tkt :p okeythnxx  :* pas de quoi , nkhalik tjareb hedhi ba3ed ken famma haja on rreprend okiii
            copyImages.deplacerVers(txtPhotoUser, absolutePathPhotoUser,"C:\\ecosystemjava\\src\\res\\upload\\user\\");
            copyImages.deplacerVers(txtPhotoUser, absolutePathPhotoUser,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\user\\photo\\");
            
            UserService.Inscription(u);

            TrayNotification tray = new TrayNotification("succès", "Réparateur ajouté", SUCCESS);
            tray.showAndWait();
        }
    }
    
    @FXML
    void addReparateur(ActionEvent event) throws SQLException, IOException, Exception {
    if ( !(ControlleSaisie.estVide(nomReparateur, "nom")) 
        && !(ControlleSaisie.estVide(prenomReparateur, "prenom")) 
        && !(ControlleSaisie.estVide(emailReparateur, "email")) 
        && !(ControlleSaisie.estVide(pseudoReparateur, "pseudo")) 
        && !(ControlleSaisie.estVide(numTelReparateur, "téléphone")) 
        && !(ControlleSaisie.estVide(numFixeReparateur, "téléphone fixe")) 
        && !(txtPhotoReparateur.getText().equals(""))
        && !(ControlleSaisie.estVide(specialiteReparateur, " specialité ")) 
        && !(ControlleSaisie.estVide(mdpReparateur, "mot de passe")) 
        && !(ControlleSaisie.estVide(confirmationMdpReparateur, "confirmation mdp"))
        && !(ControlleSaisie.sontConforme( mdpReparateur, "mot de passe", confirmationMdpReparateur, "confirmation de mot de passe "))
        && (ControlleSaisie.estEmailValide(emailReparateur)) )
    {
        System.out.println("d5alt ");
        Reparateur r = new Reparateur();
        r.setNom(nomReparateur.getText());
        r.setPrenom(prenomReparateur.getText());
        r.setEmail(emailReparateur.getText());
        r.setEmailCanonical(emailReparateur.getText());
        r.setUsername(pseudoReparateur.getText());
        r.setPhoto(txtPhotoReparateur.getText());
        r.setUsernameCanonical(pseudoReparateur.getText());
        r.setNumerotel(Integer.parseInt(numTelReparateur.getText()));
        r.setNumerofix(Integer.parseInt(numFixeReparateur.getText()));
        r.setAdresse(adresseReparateur.getText());
        r.setSpecialite(specialiteReparateur.getText());
        r.setDescription(descriptionReparateur.getText());
        r.setHoraire(horaireTravailReparateur.getText());
        r.setPassword(mdpReparateur.getText());
        r.setEnabled(true);
        r.setDiscr("reparateur");
        r.setRoles("a:1:{i:0;s:15:\"ROLE_REPARATEUR\";}");
        
        copyImages.deplacerVers(txtPhotoReparateur, absolutePathPhotoReparateur,"C:\\ecosystemjava\\src\\res\\upload\\user\\");
        copyImages.deplacerVers(txtPhotoReparateur, absolutePathPhotoReparateur,"C:\\wamp\\www\\ecosystemweb\\web\\uploads\\user\\photo\\");
            
        UserService.InscriptionReparateur(r);

        TrayNotification tray = new TrayNotification("succès", "Réparateur ajouté", SUCCESS);
        tray.showAndWait();
     }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        telephoneUser.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
        numTelReparateur.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
        numFixeReparateur.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
    }  
    
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();                
                if (txt_TextField.getText().length() >= max_Lengh) {                    
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){ 
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume(); 
                    }
                }else{
                    e.consume();
                }
            }
        };
    }   

    public static boolean valideEmail(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
