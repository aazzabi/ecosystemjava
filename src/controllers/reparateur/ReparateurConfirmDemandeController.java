/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.reparateur.DemandeComptePro;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.DemandeCompteProService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class ReparateurConfirmDemandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton confirmer;

    @FXML
    private PDFViewer jpdfviewer;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField date;

    @FXML
    private JFXTextField type;

    @FXML
    private Label id;
    
    public static final String ACCOUNT_SID =
            "AC5676bb425276f7ee63955cf4079883ec";
    public static final String AUTH_TOKEN =
            "260916038da9a829cc9a8adf2e9a94e7";
    
    
    
    
    
    
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle rb) {

    }

    public void initData(DemandeComptePro demande) throws FileNotFoundException {
        nom.setText(demande.getNomRep());
        date.setText(demande.getDateDemande());
        type.setText(demande.getStatut());
        id.setText(Integer.toString(demande.getId()));
        File f = new File("C:\\wamp\\www\\ecosystemweb\\web\\uploads\\demandecompte\\photos\\" + demande.getUrlPhoto());
        InputStream in = new FileInputStream(f);

        try {

            jpdfviewer.loadPDF(in);
        } catch (PDFException ex) {
            Logger.getLogger(ReparateurConfirmDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void genererPDF() {

        String id1 = id.getText();
        String out = "D:\\Contrat" + id1 + ".pdf";
        Document document = new Document(PageSize.A4);
        StringBuilder boxText = new StringBuilder();

        try {
            
          

            PdfWriter.getInstance(document, new FileOutputStream(out));
            document.open();
            document.addTitle("Contrat de migration vers un compte PRO");
            document.addCreationDate();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            Anchor anchorTarget = new Anchor("ECO-SYSTEME");
            anchorTarget.setName("BackToTop");
              com.itextpdf.text.Image Eco = com.itextpdf.text.Image.getInstance("C:\\ecosystemjava\\src\\res\\logoeco.png");
            Eco.scaleToFit(200, 70);
            document.add(Eco);
            Paragraph p1 = new Paragraph();

            p1.setSpacingBefore(50);

            p1.add(anchorTarget);
            document.add(p1);
            Paragraph p2 = new Paragraph("Contrat", FontFactory.getFont(FontFactory.COURIER, 24, Font.BOLD, new CMYKColor(0, 255, 0, 0)));
            document.add(p2);
            Paragraph p3 = new Paragraph("Je sousigne Mr/Mme " + nom.getText() + " avoir acheter un compte PRO de type :  " + type.getText());
            document.add(p3);
            Paragraph p4 = new Paragraph("Date : Le " + now.getDayOfWeek().toString() + " " + now.getMonth() + " " + now.getYear());
            document.add(p4);
            Paragraph p5 = new Paragraph("Sigature");
            document.add(p5);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        document.close();

        System.out.println("Document '" + out + "' generated");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PDF générer");
        alert.setHeaderText(null);
        alert.setContentText("PDF générer");

        alert.showAndWait();

    }
    
    public void confirmer()
    {
        UserService.updateType(UserService.getTtReparateur().stream().filter(e->e.getId()==UserService.getIdRep(nom.getText())).map(e->e.getId()).collect(Collectors.toList()).get(0)) ;
        DemandeCompteProService.supprimer(Integer.parseInt(id.getText()));
  
    int num =UserService.getTtReparateur().stream().filter(e->e.getId()==UserService.getIdRep(nom.getText())).map(e->e.getNumerotel()).collect(Collectors.toList()).get(0);    
    
        
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber("+216"+num), // to
                        new PhoneNumber("+12015711871"), // from
                       "Mr/Mme: "+nom.getText()+ " EcoSysteme vous informe que votre demande de compte PRO a été approuvé avec succés Veuillez vous nous contacter pour signer le contrat "
                               + "Merci , EcoSysteme est toujours la pour vous servir")
                .create();

        System.out.println(message.getSid());
               Stage s = (Stage) nom.getScene().getWindow();
        s.close();
    }
    
    public void annuler()
    {
             Stage s = (Stage) nom.getScene().getWindow();
        s.close();
    }

}
