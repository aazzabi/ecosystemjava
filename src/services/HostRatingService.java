/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.HostRating;
import utils.ConnectionBase;
import static utils.DBHelper.GetResultSetFromQuerry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hiro
 */
public class HostRatingService {
    
    public static ArrayList<HostRating> GetAllRatings() throws SQLException{
        //Init Result Set
        ResultSet CurrentResult = GetResultSetFromQuerry("SELECT * from hostrating");
        
        //Init The ArrayList to return
        ArrayList<HostRating> RatingsList = new ArrayList<>();
        
        //Adding the Hosts to the List 
        while (CurrentResult.next()){
            
            //Initialize Class
            HostRating TempRating = new HostRating();
            
            //Fill the Class with DATA
            TempRating.setID(CurrentResult.getInt(1)); 
            TempRating.setHostID(CurrentResult.getInt(2));
            TempRating.setOwnerID(CurrentResult.getInt(3));
            TempRating.setComment(CurrentResult.getString(4));
            TempRating.setRank(CurrentResult.getInt(5));
            TempRating.setRatingDate(CurrentResult.getDate(6));

            
            //Add to the List
            RatingsList.add(TempRating);
        }
        return RatingsList;
    }
    public static void  AddRating(HostRating Rating, int HostId) throws SQLException{
        
        String Query = "INSERT INTO HostRating (HostID, OwnerID, Comment, Rank, Date) VALUES (?, ?, ?, ?, ?)";


        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, Rating.getHostID());
        PrepST.setInt(2, Rating.getOwnerID());
        PrepST.setString(3, Rating.getComment());
        PrepST.setInt(4, Rating.getRank());
        PrepST.setDate(5, Rating.getRatingDate());
        
        PrepST.executeUpdate();
    }
    public static void ModifyRating(int RatingID, HostRating NewRating ) throws SQLException{
        String Query = "UPDATE HostRating SET Comment = ?, Rank = ? where ID = ?";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setString(1, NewRating.getComment());
        PrepST.setInt(2, NewRating.getRank());
        PrepST.setInt(3, RatingID);
        
        PrepST.executeUpdate();
    }
    public static void DeleteRating(HostRating Rating) throws SQLException{
        String Query = "DELETE FROM Host where ID = ?";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, Rating.getID());
        
        PrepST.executeUpdate(); 
    }
    public static void DeleteRating(int RatingID ) throws SQLException{
        String Query = "DELETE FROM HostRating where ID = ?";

        PreparedStatement PrepST = ConnectionBase.getInstance().getCnx().prepareStatement(Query);
        PrepST.setInt(1, RatingID);
        
        PrepST.executeUpdate(); 
    }

}
