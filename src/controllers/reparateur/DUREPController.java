/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static controllers.reparateur.ReparateurConfirmDemandeController.ACCOUNT_SID;
import static controllers.reparateur.ReparateurConfirmDemandeController.AUTH_TOKEN;
import entities.reparateur.Reparation;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ReparationService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class DUREPController implements Initializable {

    @FXML
    private TableView<Reparation> tableviewrep;

    @FXML
    private TableColumn<Reparation, String> rep_col_user;

    @FXML
    private TableColumn<Reparation, String> rep_col_statut;

    @FXML
    private TableColumn<Reparation, String> rep_col_date;

    @FXML
    private TableColumn<Reparation, String> rep_col_com;

    public static final String ACCOUNT_SID
            = "AC5676bb425276f7ee63955cf4079883ec";
    public static final String AUTH_TOKEN
            = "260916038da9a829cc9a8adf2e9a94e7";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(ObservableList<Reparation> reps) {
        tableviewrep.setEditable(false);
        rep_col_com.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        rep_col_user.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        rep_col_statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        rep_col_date.setCellValueFactory(new PropertyValueFactory<>("dateP"));
        tableviewrep.setItems(reps);

    }

    @FXML
    void annuler(ActionEvent event) {
        Stage s = (Stage) tableviewrep.getScene().getWindow();
        s.close();
    }

    @FXML
    void confirmer(ActionEvent event) {
        if (tableviewrep.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Aucune ligne n'est sélectionner vous devez en sélectioner une ");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la réparation");
            alert.setHeaderText("Confirmer la réparation ");
            alert.setContentText("Etes vous certain ? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                Reparation selected = tableviewrep.getSelectionModel().getSelectedItem();
                selected.setCommentaire("Réparation effecuté ");
                selected.setStatut("Terminer");
                ReparationService.edit(selected);
                 String num =UserService.getTtUtilisateur().stream().filter(e->e.getId()==selected.getUserId()).map(e->e.getNumtel()).collect(Collectors.toList()).get(0);
                 num="51775201";
                 System.out.println(num);
                
                
                
                
                

                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message = Message
                        .creator(new PhoneNumber("+216" + num), // to
                                new PhoneNumber("+12015711871"), // from
                                "Mr/Mme: " + selected.getNomUser() + " EcoSysteme vous informe que votre réparation  a été effectué"
                                + "Merci , EcoSysteme est toujours la pour vous servir")
                        .create();

                System.out.println(message.getSid());
                Stage s = (Stage) tableviewrep.getScene().getWindow();
                s.close();

            } else {

            }

        }

    }

}
