/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Host;
import entities.Utilisateur;
import services.UserService;
import services.HostMail;
import utils.ConnectionBase;
import utils.DBHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static utils.DBHelper.GetResultSetFromQuerry;
import java.sql.PreparedStatement;

/**
 *
 * 
 * @author Hiro
 */
public class HostService {
    
  
    public static ArrayList<Host> GetAllHosts() throws SQLException{
        
        //Init Result Set
        ResultSet CurrentResult = GetResultSetFromQuerry("SELECT * from Host");
        
        //Init The ArrayList to return
        ArrayList<Host> HostList = new ArrayList<>();
        
        //Adding the Hosts to the List 
        while (CurrentResult.next()){
            
            //Initialize Class
            Host TempHost = new Host();
            
            //Fill the Class with DATA
            TempHost.setID(CurrentResult.getInt(1)); 
            TempHost.setOwner(CurrentResult.getString(2));
            TempHost.setDateStart(CurrentResult.getDate(3));
            TempHost.setDateEnd(CurrentResult.getDate(4));
            TempHost.setTotalPlaces(CurrentResult.getInt(5));
            TempHost.setAvailablePlaces(CurrentResult.getInt(6));
            TempHost.setLocalisation(CurrentResult.getString(7));
            TempHost.setParticipants(CurrentResult.getString(8));
            
            //Add to the List
            HostList.add(TempHost);
        }
        return HostList;
    }
    public static ArrayList<Host> GetAllHosts(String StringInTheName) throws SQLException{
        ArrayList<Host> FullList = GetAllHosts();
        ArrayList<Host> FinalList = new ArrayList<>();
        
        for (Host TempHost : FullList){
            if (TempHost.getOwner().contains(StringInTheName)){
                FinalList.add(TempHost);
            }
        }
        return FinalList;
    }
    public static Host GetMostRatedHost()throws SQLException{
        
        //Init Result Set
        String Query = "SELECT h.ID, h.OwnerID, AVG( hr.Rank ) as avg_rate\n" +
        "FROM hostrating hr join host h on hr.HostId = h.ID\n" +
        "GROUP BY ID\n" +
        "ORDER BY AVG( Rank ) DESC";
        
        ResultSet CurrentResult = GetResultSetFromQuerry(Query);
        
        
        Host TempHost = new Host();
            //Adding the Hosts to the List 
        while (CurrentResult.next()){
            //Fill the Class with DATA
            TempHost = GetHost(CurrentResult.getInt(1));
            return TempHost;
        }
        System.out.println(TempHost.getID());
        return TempHost;
 
    }
    public static Host GetHost(int HostID)throws SQLException{
        //Init Result Set

        String Query = "SELECT * FROM Host WHERE ID = ?";
        
        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, HostID);
        
        
        ResultSet CurrentResult = PrepST.executeQuery();
        
        Host TempHost = new Host();
                //Adding the Hosts to the List 
        while (CurrentResult.next()){
            //Fill the Class with DATA
            TempHost.setID(CurrentResult.getInt(1)); 
            TempHost.setOwner(CurrentResult.getString(2));
            TempHost.setDateStart(CurrentResult.getDate(3));
            TempHost.setDateEnd(CurrentResult.getDate(4));
            TempHost.setTotalPlaces(CurrentResult.getInt(5));
            TempHost.setAvailablePlaces(CurrentResult.getInt(6));
            TempHost.setLocalisation(CurrentResult.getString(7));
            TempHost.setParticipants(CurrentResult.getString(8));
            TempHost.setOwnerID(CurrentResult.getInt(9));
        }
        return TempHost;
    }
    public static void AddHostUsingHelper(Host HostToAdd) throws SQLException{
        
        //Initiate an Array
        Object[] HostValues = new Object[50];
        
        //Add the values to the Array in the correct database order
        HostValues[0] = HostToAdd.getOwner();
        HostValues[1] = HostToAdd.getDateStart();
        HostValues[2] = HostToAdd.getDateEnd();
        HostValues[3] = HostToAdd.getTotalPlaces();
        HostValues[4] = HostToAdd.getAvailablePlaces();
        HostValues[5] = HostToAdd.getLocalisation();
        
        //Execute the Query with the Array values
        DBHelper.ExecuteUpdateQuery("INSERT INTO Host (Owner, DateStart, DateEnd, TotalPlaces, AvailablePlaces, Localisation) VALUES (?, ?, ?, ?, ?, ?)", HostValues);
    }
    public static void AddHost(Host HostToAdd) throws SQLException{
        
        String Query = "INSERT INTO Host (Owner, DateStart, DateEnd, TotalPlaces, AvailablePlaces, Localisation, Participants, OwnerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setString(1, HostToAdd.getOwner());
        PrepST.setDate(2, HostToAdd.getDateStart());
        PrepST.setDate(3, HostToAdd.getDateEnd());
        PrepST.setInt(4, HostToAdd.getTotalPlaces());
        PrepST.setInt(5, HostToAdd.getAvailablePlaces());
        PrepST.setString(6, HostToAdd.getLocalisation());
        PrepST.setString(7, HostToAdd.getParticipants());
        PrepST.setInt(8, HostToAdd.getOwnerID());
        
        PrepST.executeUpdate();
    }
    public static void ModifyHostUsingHelper(int HostID, String Modification, String NewValue) throws SQLException{
        
        
        //Initiate an Array
        Object[] BindingValues = null;
        
        //Add the value to the array
        BindingValues[0] = Modification;
        BindingValues[1] = NewValue;
        BindingValues[2] = HostID;
        
        //Execute the Query with the Array values
        DBHelper.ExecuteUpdateQuery("UPDATE Host SET ? = ? WHERE( ID = ? )", BindingValues);
    }
    public static void ModifyHost(int HostID, Host NewHost) throws SQLException{
        
        String Query = "UPDATE Host SET Owner = ?, DateStart = ?, DateEnd = ?, TotalPlaces = ?, AvailablePlaces = ?, Localisation = ?, Participants = ? where ID = ?";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setString(1, NewHost.getOwner());
        PrepST.setDate(2, NewHost.getDateStart());
        PrepST.setDate(3, NewHost.getDateEnd());
        PrepST.setInt(4, NewHost.getTotalPlaces());
        PrepST.setInt(5, NewHost.getAvailablePlaces());
        PrepST.setString(6, NewHost.getLocalisation());
        PrepST.setString(7, NewHost.getParticipants());
        PrepST.setInt(8, HostID);
        
        PrepST.executeUpdate();
    }
    public static void JoinHost(int HostID, int OwnerID) throws SQLException{
        Host CurrentHost= GetHost(HostID);
        int NewAvailablePlaces =  CurrentHost.getAvailablePlaces() - 1;
        String Query = "UPDATE Host SET AvailablePlaces = ? WHERE ID = ?";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, NewAvailablePlaces);
        PrepST.setInt(2, HostID);
        
        PrepST.executeUpdate();
        
        //SendMail
        UserService TempUserService = new UserService();
        String EmailReceiver = TempUserService.findById(OwnerID).getEmail();
        
        
        	    System.out.println("email  = " + EmailReceiver);

        HostMail.SendMail(EmailReceiver, "Inscription", "Vous êtes inscrit à " +  GetHost(HostID).getOwner());
        
    }
    public static void LeaveHost(int HostID, int OwnerID) throws SQLException{
        Host CurrentHost= GetHost(HostID);
        int NewAvailablePlaces =  CurrentHost.getAvailablePlaces() + 1;
        String Query = "UPDATE Host SET AvailablePlaces = ? WHERE ID = ?";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, NewAvailablePlaces);
        PrepST.setInt(2, HostID);
        
        PrepST.executeUpdate();
        
        
                //SendMail
        UserService TempUserService = new UserService();
        String EmailReceiver = TempUserService.findById(OwnerID).getEmail();
        HostMail.SendMail(EmailReceiver, "Inscription", "Vous n'êtes plus inscrit à " +  GetHost(HostID).getOwner());
    }
    public static void DeleteHost(int HostID) throws SQLException{
        String Query = "DELETE FROM Host where ID = ?";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, HostID);
        
        PrepST.executeUpdate(); 
    }
}
