/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reparateur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import entities.reparateur.AnnounceRep;
import entities.reparateur.Reparation;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.AnnounceRepService;
import services.ReparationService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author actar
 */
public class ReparateurEditReparationController implements Initializable {

    @FXML
    private JFXTextArea rep_comment;

    @FXML
    private JFXComboBox<String> rep_rep;

    @FXML
    private JFXComboBox<String> rep_statut;

    @FXML
    private JFXComboBox<String> rep_user;

    @FXML
    private Pane pane2;

    @FXML
    private JFXButton editbtn;

    @FXML
    private Pane pane1;

    @FXML
    private JFXButton annuler;

    @FXML
    private JFXButton confirmer;

    @FXML
    private Label rep_id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Reparation rep) {
        rep_id.setText(Integer.toString(rep.getId()));
        rep_comment.setText(rep.getCommentaire());
        ObservableList<String> status = FXCollections.observableArrayList();
        status.addAll("En cours", "Terminer", "Annuler");
        rep_statut.getItems().addAll(status);
        rep_statut.setValue(rep.getStatut());
        rep_rep.getItems().addAll(AnnounceRepService.getAllReparateur());
        rep_rep.setValue(rep.getNomRep());
        System.out.println(UserService.getTtUtilisateur().stream().map(e -> e.getUsername()).collect(Collectors.toList()).toString());
        rep_user.getItems().addAll(UserService.getTtUtilisateur().stream().map(e -> e.getUsername()).collect(Collectors.toList()));
        rep_user.setValue(rep.getNomUser());

    }

    public void confirmer() {

        int idrep = Integer.parseInt(rep_id.getText());
        Reparation rep = new Reparation();
        rep = ReparationService.getReparation(idrep);
        rep.setCommentaire(rep_comment.getText());
        rep.setStatut(rep_statut.getSelectionModel().getSelectedItem());
        rep.setNomRep(rep_rep.getSelectionModel().getSelectedItem());
        rep.setNomUser(rep_user.getSelectionModel().getSelectedItem());
        ReparationService.edit(rep);
        Stage s = (Stage) editbtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void cancel1(ActionEvent event) {
        Stage s = (Stage) editbtn.getScene().getWindow();
        s.close();
    }

    @FXML
    void editer(ActionEvent event) {

        editbtn.setVisible(false);
        pane1.setVisible(true);
        rep_comment.setEditable(true);
        rep_rep.setDisable(false);
        rep_statut.setDisable(false);
        rep_user.setDisable(false);
        pane2.setVisible(false);

    }

    @FXML
    void canceledit(ActionEvent event) {

        editbtn.setVisible(true);
        pane1.setVisible(false);
        rep_comment.setEditable(false);
        rep_rep.setDisable(true);
        rep_statut.setDisable(true);
        rep_user.setDisable(true);
        pane1.setVisible(false);
        pane2.setVisible(true);

    }
    

}
