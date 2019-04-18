/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.missions;

import static gui.missions.HostVariableManager.WINDOW_HEIGHT;
import static gui.missions.HostVariableManager.WINDOW_WIDTH;
import gui.missions.HostVariableManager;
import loaders.HostAddLoader;
import loaders.HostDetailsLoader;
import entities.Host;
import static services.HostService.GetMostRatedHost;
import static services.HostService.GetAllHosts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controllers.ChangeCallback;
import controllers.MainuserscreenController;
import controllers.SidePanelUserController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.management.relation.Role;
import loaders.HostListLoader;

/**
 * FXML Controller class
 *
 * @author Hiro
 */
public class HostListController implements Initializable {

    private String Filter = "";

    @FXML
    private GridPane HostGrid;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private JFXButton HostButton;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private JFXTextField SearchTF;
    @FXML
    private AnchorPane content;

    private ChangeCallback callback;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        InitiateGrid();
    }

    private void Refresh(ContextMenuEvent event) {
        InitiateGrid();
    }

    public void setCallback(ChangeCallback callback) {
        this.callback = callback;
    }

    private void InitiateGrid() {
        HostGrid.getChildren().clear();

        ArrayList<Host> HostsList = new ArrayList<>();
        Host MostRatedHost = new Host();
        try {

            System.out.println("Getting data from DataBase at controllers.missions.HostListController.InitiateGrid(), if error occured, check DB connection or Tables");
            MostRatedHost = GetMostRatedHost();
            System.out.println(MostRatedHost.getID() + " has been transferred to the list Controller");
            HostsList = GetAllHosts(Filter);
        } catch (SQLException ex) {
            Logger.getLogger(HostListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String BoxLayout = "-fx-border-color: black;\n"
                + "-fx-border-insets: 10;\n"
                + "-fx-border-width: 0.8;\n";
        String LabelLayout = "-fx-font-size: 30px;";
        String ButtonLayout = "-fx-font-size: 20px; -fx-background-color: #90ee90; -fx-text-fill: white;";

        int ColumnPicker = 0;
        int IndexPicker = 0;

        for (Host TempHost : HostsList) {
            //Host DATA

            //Adding the button to the Grid
            String ButtonDetails = "Nom Mission : " + TempHost.getOwner() + "\n"
                    + "Matériaux Atteints/Objectifs : " + TempHost.getAvailablePlaces() + "/" + TempHost.getTotalPlaces() + "\n"
                    + "Date de début & fin : " + TempHost.getDateStart() + " -> " + TempHost.getDateEnd();
            if (TempHost.getID() == MostRatedHost.getID()) {
                ButtonDetails = "LA MEILLEUR MISSION de notre platforme!\n \n " + ButtonDetails;
            }
            JFXButton HostButtonTemp = MakeGridButton(ButtonDetails, "#008000");
            //Setting Up the ID for later Use
            HostButtonTemp.setId(TempHost.getID() + "");

            GridPane.setConstraints(HostButtonTemp, ColumnPicker, IndexPicker);

            //Event handling 
            HostButtonTemp.onMouseReleasedProperty().set((event) -> {

                HostVariableManager.setCurrentHost(Integer.valueOf(HostButtonTemp.getId()));
//                HostVariableManager.setCurrentUserID(LoginController.getLoggedInUser().getId());  
                System.out.println(Integer.valueOf(HostButtonTemp.getId()));
               /* try {
                    FXMLLoader hostD = new FXMLLoader(getClass().getResource("/gui/missions/HostDetails.fxml"));
                    AnchorPane box = (AnchorPane) hostD.load();
                    HostDetailsController controllerHost = hostD.getController();
                    controllerHost.thisControler.insertData(Integer.valueOf(HostButtonTemp.getId()));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/mainuserscreen.fxml"));
                    MainuserscreenController controllerMainScreen = loader.getController();
                    controllerMainScreen.thisController.up(box);
                } catch (IOException ex) {
                    Logger.getLogger(HostListController.class.getName()).log(Level.SEVERE, null, ex);
                }*/
               
               ((Stage)HostButtonTemp.getScene().getWindow()).setScene(new Scene(HostDetailsLoader.GetRoot(Integer.valueOf(HostButtonTemp.getId())), WINDOW_WIDTH, WINDOW_HEIGHT));
            });

            if (ColumnPicker == 0) {
                ColumnPicker++;
            } else {
                ColumnPicker = 0;
                IndexPicker++;
            }

//            HostGrid.setVgap(50);
//            HostGrid.setHgap(50);
            HostGrid.getChildren().add(HostButtonTemp);

        }

        //BECOME A HOST BUTTON CHECK
        if (HostVariableManager.getCurrentRole() == HostVariableManager.UserRole.Other) {
            //Become a Host Button Declaration
            JFXButton BecomeHost_Button = MakeGridButton("Crée votre mission ", "#435470");

            //OnClick
            BecomeHost_Button.onMouseReleasedProperty().set((event) -> {
                ((Stage) BecomeHost_Button.getScene().getWindow()).setScene(new Scene(HostAddLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));
            });

            //Adding the button to the Grid
            GridPane.setConstraints(BecomeHost_Button, ColumnPicker, IndexPicker);
            HostGrid.getChildren().add(BecomeHost_Button);

        }

        System.out.println("There were no errors from what's previously mentioned in this function Gui.HostListController.InitiateGrid()");
    }

    public static JFXButton MakeGridButton(String Text) {
        JFXButton CurrentButton = new JFXButton();

        //Become a Host Button Style
        CurrentButton.getStyleClass().add("button-raised");
        CurrentButton.setButtonType(JFXButton.ButtonType.RAISED);
        CurrentButton.setStyle("-fx-background-color: #ccd5e8; -fx-font-size: 20px;");

        //Become a Host Button Font
        CurrentButton.setPrefWidth(350.0);
        CurrentButton.setPrefHeight(140.0);
        CurrentButton.setAlignment(Pos.CENTER);

        //Button Text
        CurrentButton.setText(Text);

        return CurrentButton;
    }

    public static JFXButton MakeGridButton(String Text, String BackgroundColor) {
        JFXButton CurrentButton = new JFXButton();

        //Become a Host Button Style
//        CurrentButton.getStyleClass().add("button-raised");
//        CurrentButton.setButtonType(JFXButton.ButtonType.RAISED);
        CurrentButton.setStyle("-fx-background-color: " + BackgroundColor + "; -fx-font-size: 13px;  -fx-border-radius: 40px;\n"
                + "    -fx-background-radius: 40px; -fx-border-color:#e2dbcc");
//        
        CurrentButton.setId("loginBtn");
        CurrentButton.setRipplerFill(getTextFillColor(1));
        CurrentButton.setTextFill(getTextFillColor(1));
//        CurrentButton.setFont(Font.font(15));

        //Become a Host Button Size
        CurrentButton.setPrefWidth(250.0);
        CurrentButton.setPrefHeight(140.0);
        CurrentButton.setMinSize(250, 140);
        CurrentButton.setAlignment(Pos.CENTER);

        //Button Text
        CurrentButton.setText(Text);

        return CurrentButton;
    }

    private static Paint getTextFillColor(int value) {
        String color;
        switch (value) {
            case -1:
                color = "#FFFF8D";
                break;
            case 1:
                color = "#e2dbcc";
                break;
            default:
                color = "black";
                break;
        }
        return Paint.valueOf(color);
    }

    private void AccessHostSection(ActionEvent event) {
//        HostVariableManager.setCurrentUserID(LoginController.getLoggedInUser().getId());       
        ((Stage) HostButton.getScene().getWindow()).setScene(new Scene(HostDetailsLoader.GetRoot(Integer.valueOf(HostButton.getId())), WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    @FXML
    private void SetFilter(KeyEvent event) {
        SetFilter();

    }

    @FXML
    private void SetFilter(InputMethodEvent event) {
//        SetFilter();
    }

    private void SetFilter() {
        Filter = SearchTF.getCharacters().toString();
        InitiateGrid();
//        Filter = SearchTF.getCharacters().toString();
        System.out.println("CurrentFilter = " + Filter);
    }

    @FXML
    private void AccessHostListSection(ActionEvent event) {
        ((Stage) SearchTF.getScene().getWindow()).setScene(new Scene(HostListLoader.GetRoot(), WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    @FXML
    private void AccessJoinSession(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopicsModuleMenu.fxml"));
        AnchorPane pane = fxmlLoader.load();
        content.getChildren().setAll(pane);
    }
}
