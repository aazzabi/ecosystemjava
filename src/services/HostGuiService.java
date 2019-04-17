/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import gui.missions.HostVariableManager;
import entities.Host;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/*
**
 *
 * @author Hiro
 */
public class HostGuiService {

    
    public static Scene HostMainButton(){
        
        Button HostButton = new Button("Hosts");
        HostButton.setOnAction((ActionEvent event) -> {
            
        });
        StackPane root = new StackPane();
        root.getChildren().add(HostButton);
        
        return new Scene(root, 300, 250);

    }

    public static void DisplayHostWindow(Stage CurrentStage){
 
        Scene scene = new Scene(new Group());
        CurrentStage.setTitle(" HOSTS GUI ");
        CurrentStage.setWidth(1000);
        CurrentStage.setHeight(800);

        
        Label HostLabel = new Label("Hébérgement");
        HostLabel.setFont(new Font("Arial", 20));

        
        
        //Name
        Label HostOwnerLabel = new Label("Name");
        TextField HostOwnerTF = new TextField();
        HostOwnerTF.setPromptText("Name");
        


        //Hosts TableView 
        TableView HostsTableView= HostsTableView();
        //Adding Button
        Button AddButton = AddHostButton(HostOwnerTF, HostsTableView);
        //Removing Button
        Button RemoveButton = RemoveHostButton(HostsTableView);
        
        
        HBox AddBox = new HBox();
        AddBox.getChildren().addAll(HostOwnerLabel, HostOwnerTF, AddButton);
        AddBox.setSpacing(3);

        
        VBox myVBox = new VBox();
        myVBox.setSpacing(5);
        myVBox.setPadding(new Insets(10, 0, 0, 10));
        myVBox.getChildren().addAll(HostLabel, AddBox, HostsTableView , RemoveButton);

        ((Group) scene.getRoot()).getChildren().addAll(myVBox);

        CurrentStage.setScene(scene);
        CurrentStage.show();
    }
    
    
    public static TableView HostsTableView(){
        
        //Init Table
        TableView HostTableView = new TableView();
        HostTableView.setEditable(false);
 
        //SetUpColumns
        TableColumn IDColumn = new TableColumn("ID");
        IDColumn.setMinWidth(20);
        IDColumn.setCellValueFactory(
                new PropertyValueFactory<>("ID"));
        TableColumn OwnerColumn = new TableColumn("Owner");
        OwnerColumn.setMinWidth(100);
        OwnerColumn.setCellValueFactory(
                new PropertyValueFactory<>("Owner"));
        TableColumn DSColumn = new TableColumn("Date Start");
        DSColumn.setMinWidth(100);
        DSColumn.setCellValueFactory(
                new PropertyValueFactory<>("DateStart"));
        TableColumn DEColumn = new TableColumn("Date End");
        DEColumn.setMinWidth(100);
        DEColumn.setCellValueFactory(
                new PropertyValueFactory<>("DateEnd"));
        TableColumn TPColumn = new TableColumn("Total Places");
        TPColumn.setMinWidth(100);
        TPColumn.setCellValueFactory(
                new PropertyValueFactory<>("TotalPlaces"));
        TableColumn APColumn = new TableColumn("Available Places");
        APColumn.setMinWidth(100);
        APColumn.setCellValueFactory(
                new PropertyValueFactory<>("AvailablePlaces"));
        TableColumn LocColumn = new TableColumn("Localisation");
        LocColumn.setMinWidth(100);
        LocColumn.setCellValueFactory(
                new PropertyValueFactory<>("Localisation"));
        TableColumn PartColumn = new TableColumn("Participants");
        PartColumn.setMinWidth(100);
        PartColumn.setCellValueFactory(
                new PropertyValueFactory<>("Participants"));
        
        //Add Columns to TableView
        HostTableView.getColumns().addAll(IDColumn, OwnerColumn, DSColumn, DEColumn, TPColumn, APColumn, LocColumn, PartColumn);
        

        //Init the ObservableList and add it to the TableView
        try {
            ObservableList<Host> HostsOList;
            HostsOList = FXCollections.observableArrayList(HostService.GetAllHosts()); 
            HostTableView.setItems(HostsOList);
        } catch (SQLException ex) {
            Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return HostTableView;
    }
    public static void AddHost(TextField HostOwnerTF, DatePicker DateStart, DatePicker DateEnd, TextField TotalPlacesTF, TextField LocalisationTF){
        
            Host HostToAdd = new Host();
            HostToAdd.setOwner(HostOwnerTF.getCharacters().toString());
            HostToAdd.setDateStart(java.sql.Date.valueOf(DateStart.getValue()));
            HostToAdd.setDateStart(java.sql.Date.valueOf(DateEnd.getValue()));
            HostToAdd.setTotalPlaces(Integer.parseInt(TotalPlacesTF.getCharacters().toString()));
            HostToAdd.setAvailablePlaces(Integer.parseInt(TotalPlacesTF.getCharacters().toString()));
            HostToAdd.setLocalisation(LocalisationTF.getCharacters().toString());
            HostToAdd.setOwnerID(HostVariableManager.getCurrentUserID());
            try {
                HostService.AddHost(HostToAdd);
            } catch (SQLException ex) {
                Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
            }
//            HostOwnerTF.clear();
//            HostOwnerTF.clear();
            
    }
    public static Button AddHostButton(TextField HostOwnerTF){
        
        Button CurrentButton = new Button("Add");
        CurrentButton.setOnAction((ActionEvent e) -> {
            Host HostToAdd = new Host();
            HostToAdd.setOwner(HostOwnerTF.getCharacters().toString());
            try {
                HostService.AddHost(HostToAdd);
            } catch (SQLException ex) {
                Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
            }
            HostOwnerTF.clear();
            
        });
        return CurrentButton;
    }
    public static Button AddHostButton(TextField HostOwnerTF, TableView HostTableViewToClear){
        
        Button CurrentButton = new Button("Add");
        CurrentButton.setOnAction((ActionEvent e) -> {
            Host HostToAdd = new Host();
            HostToAdd.setOwner(HostOwnerTF.getCharacters().toString());
            try {
                HostService.AddHost(HostToAdd);
            } catch (SQLException ex) {
                Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
            }
            HostOwnerTF.clear();
            RefreshHostTableView(HostTableViewToClear);
        });
        
        return CurrentButton;
    }
    public static Button RemoveHostButton(int HostID){
        Button CurrentButton = new Button("Remove");
        CurrentButton.setOnAction((ActionEvent e) -> {
            Host HostToAdd = new Host();
            try {
                HostService.DeleteHost(HostID);
            } catch (SQLException ex) {
                Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return CurrentButton;
    }
    public static Button RemoveHostButton(int HostID, TableView HostTableViewToClear){
        Button CurrentButton = new Button("Remove");
        CurrentButton.setOnAction((ActionEvent e) -> {
            Host HostToAdd = new Host();
            try {
                HostService.DeleteHost(HostID);
            } catch (SQLException ex) {
                Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
            }
            RefreshHostTableView(HostTableViewToClear);
        });
        return CurrentButton;
    }
    public static Button RemoveHostButton(TableView SelectedHostTable){
        

        Button CurrentButton = new Button("Remove");
        CurrentButton.setOnAction((ActionEvent e) -> {
            Host HostToAdd = new Host();
            ObservableList<Host> HostOSelectedList = SelectedHostTable.getSelectionModel().getSelectedItems();
            try {
                HostService.DeleteHost(HostOSelectedList.get(0).getID());
            } catch (SQLException ex) {
                Logger.getLogger(HostGuiService.class.getName()).log(Level.SEVERE, null, ex);
            }
            RefreshHostTableView(SelectedHostTable);
        });
        return CurrentButton;
    }
    public static void RefreshHostTableView(TableView HostTV){
        HostTV = HostsTableView();
        
    }
    
}

