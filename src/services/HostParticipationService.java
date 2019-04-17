/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Host;
import entities.HostParticipation;
import utils.ConnectionBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hiro
 */
public class HostParticipationService {
    public static void AddHostParticipation(HostParticipation HostParticipationToAdd) throws SQLException{
        
        String Query = "INSERT INTO HostParticipation (UserID, HostID, ParticipationDate, Active) VALUES (?, ?, ?, ?)";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, HostParticipationToAdd.getUserID());
        PrepST.setInt(2, HostParticipationToAdd.getHostID());
        PrepST.setDate(3, HostParticipationToAdd.getParticipationDate());
        PrepST.setInt(4, HostParticipationToAdd.getActive());
        
        
        PrepST.executeUpdate();
    }
    public static HostParticipation GetActiveHostParticipation(int UserID, int HostID)throws SQLException{
        //Init Result Set

        String Query = "SELECT * FROM HostParticipation WHERE UserID = ? AND HostID = ? AND Active = 1";
        
        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, UserID);
        PrepST.setInt(2, HostID);
        
        
        ResultSet CurrentResult = PrepST.executeQuery();
        
        HostParticipation TempHostParticipation = new HostParticipation();
                //Adding the Hosts to the List 
        while (CurrentResult.next()){
            //Fill the Class with DATA
            TempHostParticipation.setUserID(CurrentResult.getInt(1)); 
            TempHostParticipation.setHostID(CurrentResult.getInt(2));
            TempHostParticipation.setParticipationDate(CurrentResult.getDate(3));
            TempHostParticipation.setActive(CurrentResult.getInt(4));
        }
        return TempHostParticipation;
    }
    public static void SetHostParticipationActiveState(int UserID, int HostID, int NewActiveStateToSet) throws SQLException{
        
        String Query = "UPDATE HostParticipation SET Active = ? WHERE UserID = ? AND HostID = ? ";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, NewActiveStateToSet);
        PrepST.setInt(2, UserID);
        PrepST.setInt(3, HostID);
        
        
        PrepST.executeUpdate();
    }
}
