/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.missions;

import static gui.missions.HostVariableManager.WINDOW_WIDTH;
import static gui.missions.HostVariableManager.WINDOW_HEIGHT;
import loaders.HostDetailsLoader;
import loaders.HostListLoader;
import entities.Host;
import entities.HostParticipation;
import entities.HostRating;
import services.HostParticipationService;
import services.HostRatingService;
import services.HostService;
import static services.HostService.GetHost;
import utils.GlobalLibrary;
import utils.MapClass;
import static utils.MapClass.StringToLatLng;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.teamdev.jxmaps.LatLng;
import entities.Utilisateur;
import gui.missions.HostVariableManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import services.UserService;


/**
 * FXML Controller class
 *
 * @author Hiro
 */
public class HostDetailsController implements Initializable {

    
    
    private boolean EditMode = false;
    private Host CurrentHost = new Host();
    private int RatingValue = 0;    
    
    
    
    @FXML
    private Label OwnerName_Label;
    @FXML
    private Label TotalPlaces_Label;
    @FXML
    private Label AvailablePlaces_Label;
    @FXML
    private WebView Web_3D;
    @FXML
    private Label DateStart;
    @FXML
    private Label DateEnd;
    @FXML
    private JFXButton ReturnButton;
    @FXML
    private VBox AdminBox;
    @FXML
    private JFXButton Modify_Button;
    @FXML
    private JFXButton Delete_Button;
    @FXML
    private Label Role_Label;
    @FXML
    private JFXTextField NewOwnerName_TF;
    @FXML
    private JFXTextField NewTP_FT;
    @FXML
    private JFXTextField New_AP_TF;
    @FXML
    private DatePicker NewStartDate;
    @FXML
    private DatePicker NewEndDate;
    @FXML
    private JFXButton Join_Button;
    @FXML
    private JFXButton ValidateModification_Button;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private JFXButton HostButton;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private GridPane CommentGrid;
    @FXML
    private JFXButton Localisation_Button;
    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        HostVariableManager.setCurrentUserID(LoginController.getLoggedInUser().getId());  
        InitializeData(HostVariableManager.getCurrentHost());
        try {
            InitializeCommentGrid();
        } catch (SQLException ex) {
            Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    public void InitializeData(int HostID){
        //Init Editmode 
        SetEditMode(EditMode);
        

        try {
            CurrentHost = HostService.GetHost(HostID);
        } catch (SQLException ex) {
            Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //SetUp the values in main data grid
        OwnerName_Label.setText(CurrentHost.getOwner());
        TotalPlaces_Label.setText(CurrentHost.getTotalPlaces()+"");
        AvailablePlaces_Label.setText(CurrentHost.getAvailablePlaces()+"");
        DateStart.setText(GlobalLibrary.DateToString(CurrentHost.getDateStart()));
        DateEnd.setText(GlobalLibrary.DateToString(CurrentHost.getDateEnd()));
        Localisation_Button.onMouseReleasedProperty().set((event) -> {
            LatLng CurrentLatLng = StringToLatLng(CurrentHost.getLocalisation());
            MapClass Map = MapClass.LaunchMap("Map");
            Map.SetLocationInMap(CurrentLatLng, 16, true);
        });
        Web_3D.getEngine().load("https://google.com/");
        
        ReturnButton.onMouseReleasedProperty().set((event) -> {
            ((Stage)ReturnButton.getScene().getWindow()).setScene(new Scene(HostListLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));
        });
        
        //SetUp Administrator management buttons
        SetUpAdminSection();
        
    }

    @FXML
    private void ModifyHost(ActionEvent event) {
        SetEditMode(!EditMode);
        
    }
    @FXML
    private void DeleteHost(ActionEvent event) {
        try {
            HostService.DeleteHost(HostVariableManager.getCurrentHost());
        } catch (SQLException ex) {
            Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AccessHostSection();

    }
    @FXML
    private void JoinHost(ActionEvent event) {

    }
    @FXML
    private void ValidateModification(ActionEvent event) {
        
        Host EditedHost = new Host();
        
        try {
            
            EditedHost = GetHost(HostVariableManager.getCurrentHost());
            EditedHost.setOwner(NewOwnerName_TF.getCharacters().toString());
            EditedHost.setDateStart(java.sql.Date.valueOf(NewStartDate.getValue()));
            EditedHost.setDateStart(java.sql.Date.valueOf(NewEndDate.getValue()));
            EditedHost.setTotalPlaces(Integer.parseInt(NewTP_FT.getCharacters().toString()));
            EditedHost.setAvailablePlaces(Integer.parseInt(New_AP_TF.getCharacters().toString()));
            
            
            HostService.ModifyHost (HostVariableManager.getCurrentHost(), EditedHost);
            ReloadThisPage();
        } catch (SQLException ex) {
            Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SetEditMode(false);
    }
    
    
    /**
     *ADMINISTRATOR SECTION MANAGEMENT
     */
    public void SetUpAdminSection(){
        
        ShowAllAdminSection();
        
        
        if(HostVariableManager.getCurrentRole() == HostVariableManager.UserRole.Admin){
            Role_Label.setText("Connecté en tant qu'administrateur");
            HideButton(Modify_Button);
            HideButton(Join_Button);
            ShowButton(Delete_Button);
        }
        else if (CurrentHost.getOwnerID() == HostVariableManager.getCurrentUserID()){
            Role_Label.setText("Connecté en tant que réparateur");
            ShowButton(Modify_Button);
            HideButton(Join_Button);
            ShowButton(Delete_Button);
            
        }
        else if(HostVariableManager.getCurrentRole() == HostVariableManager.UserRole.Other){
            Role_Label.setText("Connecté en tant que utilisateur");
            HideButton(Delete_Button);
            HideButton(Modify_Button);
            ShowButton(Join_Button);
            
            
            
            
            //check if I am already signed in here 
            HostParticipation TempParticipation = new HostParticipation();
            try {
                TempParticipation = HostParticipationService.GetActiveHostParticipation(HostVariableManager.getCurrentUserID(), HostVariableManager.getCurrentHost());
            } catch (SQLException ex) {
                Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //The user is subscribed
            if (TempParticipation.getActive() == 1){
                Join_Button.setText("Annuler la participation");
                Join_Button.onMouseReleasedProperty().set((event) -> {
                    try {
                        HostParticipationService.SetHostParticipationActiveState(HostVariableManager.getCurrentUserID(), HostVariableManager.getCurrentHost(), 0);
                        HostService.LeaveHost(HostVariableManager.getCurrentHost(), HostVariableManager.getCurrentUserID());
                    } catch (SQLException ex) {
                        Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ReloadThisPage();
                });
                
            }
            
            //There are no places
            else if (CurrentHost.getAvailablePlaces() == 0){
                Join_Button.setText("Materiaux Objectifs atteint ! ");
                Join_Button.setDisable(true);
            }
            
            
            //The User isn't subscribed
            else if(TempParticipation.getActive() == 0){
                Join_Button.setText("S'inscrire");
                Join_Button.onMouseReleasedProperty().set((event) -> {
                    try {
                        HostParticipationService.AddHostParticipation(new HostParticipation(HostVariableManager.getCurrentUserID(), HostVariableManager.getCurrentHost(), Date.valueOf(LocalDate.now()), 1));
                        HostService.JoinHost(HostVariableManager.getCurrentHost(), HostVariableManager.getCurrentUserID());
                    } catch (SQLException ex) {
                        Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ReloadThisPage();
                });
                
            }
            

            

        }

    }
    public void HideAllAdminSection(){
        AdminBox.setVisible(false);
        AdminBox.setDisable(true);
    }
    public void ShowAllAdminSection(){
        AdminBox.setVisible(true);
        AdminBox.setDisable(false);
    }
    public void HideButton(JFXButton ButtonToHide){
        ButtonToHide.setVisible(false);
        ButtonToHide.setDisable(true);
    }
    public void ShowButton(JFXButton ButtonToShow){
        ButtonToShow.setVisible(true);
        ButtonToShow.setDisable(false);
    }

    /** EDIT MODE */
    public void SetEditMode(boolean Active){
            EditMode = Active;

            
            NewEndDate.setVisible(Active);
            NewStartDate.setVisible(Active);
            NewOwnerName_TF.setVisible(Active);
            NewTP_FT.setVisible(Active);
            New_AP_TF.setVisible(Active);
            ValidateModification_Button.setVisible(Active);
            
            Active = !Active;
            NewEndDate.setDisable(Active);
            NewStartDate.setDisable(Active);
            NewOwnerName_TF.setDisable(Active);
            NewTP_FT.setDisable(Active);
            New_AP_TF.setDisable(Active);
            ValidateModification_Button.setDisable(Active);
            
    }
    
    
    private void ReloadThisPage(){
        ((Stage)ReturnButton.getScene().getWindow()).setScene(new Scene(HostDetailsLoader.GetRoot(HostVariableManager.getCurrentHost()), WINDOW_WIDTH, WINDOW_HEIGHT));
    }
    @FXML
    private void AccessHostSection(){
        ((Stage)ReturnButton.getScene().getWindow()).setScene(new Scene(HostListLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));
    }
    private void AccessHostSection(ActionEvent event) {
        ((Stage)ReturnButton.getScene().getWindow()).setScene(new Scene(HostListLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));
    }
    
    /**
     * COMMENT SECTION MANAGEMENT
     */
    public void InitializeCommentGrid() throws SQLException{
        
        //Clear everything
        CommentGrid.getChildren().clear();
        
        
        
        //SetUp List
        ArrayList<HostRating> Ratings = new ArrayList<>();
        
        //Fill the list
        try {
            Ratings = HostRatingService.GetAllRatings();
        } catch (SQLException ex) {
            Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);}
        
                
        //Grid Index
        int IndexPicker = 0;        
        
        //Go through all the Ratings
        for(HostRating RatingTemp : Ratings){
            
            if (RatingTemp.getHostID() == HostVariableManager.getCurrentHost()){
                
                //Get the data from the user 
                UserService TempUserService = new UserService();
                Utilisateur TempUser = TempUserService.findById(RatingTemp.getOwnerID());
                //Fill the Labels                
                Label UserID_Date_Label = new Label("User : " +  TempUser.getNom() + "\n" + "Date : "+GlobalLibrary.DateToString(RatingTemp.getRatingDate()));
                
                Label Comment_Label = new Label();
                if (RatingTemp.getRank()!=0)
                    Comment_Label.setText("Rank : " + RatingTemp.getRank() + "\nCommentaire : " + RatingTemp.getComment());
                else Comment_Label.setText("Commentaire : " + RatingTemp.getComment());
                
                //LabelStyle and size
                String LabelLayout = "-fx-font-size: 13px; -fx-font-color: White;";
                UserID_Date_Label.setStyle(LabelLayout);
                UserID_Date_Label.setTextFill(Paint.valueOf("White"));
                Comment_Label.setStyle(LabelLayout);
                Comment_Label.setTextFill(Paint.valueOf("White"));
                Comment_Label.setWrapText(true);
                UserID_Date_Label.setMinSize(50, 50);
                Comment_Label.setMinSize(200, 150);

                //DeleteButton
                if (HostVariableManager.getCurrentRole()==HostVariableManager.UserRole.Admin || (HostVariableManager.getCurrentRole()==HostVariableManager.UserRole.Other && RatingTemp.getOwnerID() == HostVariableManager.getCurrentUserID())){
                    JFXButton DeleteComment_Button = new JFXButton("Supprimer");
                    DeleteComment_Button.setId(""+ RatingTemp.getID());
                    DeleteComment_Button.setOnAction((event) -> {
                        try {
                            HostRatingService.DeleteRating(Integer.valueOf(DeleteComment_Button.getId()));
                            System.out.println(DeleteComment_Button.getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            InitializeCommentGrid();
                        } catch (SQLException ex) {
                            Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    //DeleteButton Style
                    DeleteComment_Button.setStyle("-fx-background-color: White;");
                    //DeleteButton inside the Grid
                    GridPane.setConstraints(DeleteComment_Button, 2, IndexPicker);
                    CommentGrid.getChildren().add(DeleteComment_Button);
                }
                
                //Set Contrainsts
                GridPane.setConstraints(UserID_Date_Label, 0, IndexPicker);
                GridPane.setConstraints(Comment_Label, 1, IndexPicker);
                IndexPicker++;

                
                //Add to grid
                CommentGrid.getChildren().add(UserID_Date_Label);
                CommentGrid.getChildren().add((Comment_Label));
            }
        }
        
        
        //Adding the Add button at the end
        JFXTextArea Comment_TF = new JFXTextArea();
//        Comment_TF.setFocusColor(Paint.valueOf("white"));
        Comment_TF.setUnFocusColor(Paint.valueOf("#FFFF8D"));
        Comment_TF.setFocusColor(Paint.valueOf("#FFFF8D"));
        Comment_TF.setStyle("-fx-text-inner-color : White; ");

        Comment_TF.setOnKeyPressed((event) -> {
            if (Comment_TF.getText().length()>0 && event.getCode()==KeyCode.ENTER){
                
                //ADDING A COMMEENT
                try {
                    HostRating CurrentRating = new HostRating();
                        
                    //Adding data to the comment
                    CurrentRating.setComment(Comment_TF.getText());
                    CurrentRating.setRank(RatingValue);
                    CurrentRating.setRatingDate(Date.valueOf(LocalDate.now()));
                    CurrentRating.setHostID(HostVariableManager.getCurrentHost());
                    CurrentRating.setOwnerID(HostVariableManager.getCurrentUserID());
                        
                    HostRatingService.AddRating(CurrentRating,HostVariableManager.getCurrentHost());
                } catch (SQLException ex) {
                    Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                RatingValue = 0;
                try {
                    InitializeCommentGrid();
                } catch (SQLException ex) {
                    Logger.getLogger(HostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
        //Rating Buttons : 
        VBox RatingBox = new VBox();
        
        
        String RatingButtonStyle = "-fx-border-color : White; -fx-border-radius : 15px; -fx-font-color : White;";
        JFXButton RatingButton1 = new JFXButton("1");
        JFXButton RatingButton2 = new JFXButton("2");
        JFXButton RatingButton3 = new JFXButton("3");
        RatingButton1.setStyle(RatingButtonStyle);
        RatingButton2.setStyle(RatingButtonStyle);
        RatingButton3.setStyle(RatingButtonStyle);
        RatingButton1.textFillProperty().set(Paint.valueOf("White"));
        RatingButton2.textFillProperty().set(Paint.valueOf("White"));
        RatingButton3.textFillProperty().set(Paint.valueOf("White"));
        
        
        RatingBox.getChildren().addAll(RatingButton1, RatingButton2, RatingButton3);
        
        RatingButton1.onMouseReleasedProperty().set((event) -> {
            RatingValue = 1;
            RatingButton1.setStyle(RatingButtonStyle + "-fx-background-color : #535354; -fx-background-radius : 15px ; ");
            RatingButton2.setStyle(RatingButtonStyle);
            RatingButton3.setStyle(RatingButtonStyle);
        });
        RatingButton2.onMouseReleasedProperty().set((event) -> {
            RatingValue = 2;
            RatingButton1.setStyle(RatingButtonStyle);
            RatingButton2.setStyle(RatingButtonStyle + "-fx-background-color : #535354; -fx-background-radius : 15px ; ");
            RatingButton3.setStyle(RatingButtonStyle);
        });
        RatingButton3.onMouseReleasedProperty().set((event) -> {
            RatingValue = 3;
            RatingButton1.setStyle(RatingButtonStyle);
            RatingButton2.setStyle(RatingButtonStyle);
            RatingButton3.setStyle(RatingButtonStyle + "-fx-background-color : #535354; -fx-background-radius : 15px ; ");
        });
        
        
        //Set Constraints
        GridPane.setConstraints(RatingBox, 0, IndexPicker);
        GridPane.setConstraints(Comment_TF, 1, IndexPicker);
        IndexPicker++;
        
        
        
        
        //Add to Grid
        CommentGrid.getChildren().add(RatingBox);
        CommentGrid.getChildren().add(Comment_TF);
        
    }

    @FXML
    private void AccessSujetSession(ActionEvent event) throws IOException {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopicsModuleMenu.fxml"));
               AnchorPane pane = fxmlLoader.load();
               content.getChildren().setAll(pane);
    }
}