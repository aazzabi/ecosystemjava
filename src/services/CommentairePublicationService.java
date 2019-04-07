/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.PublicationForum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConnectionBase;

/**
 *
 * @author arafe
 */
public class CommentairePublicationService {
    
    public static void delete(int p)
    {
        String req = "delete from commentaire_publication where id =?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("deleted"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static void deleteCommentaireByIdUser(int p)
    {
        String req = "DELETE FROM user WHERE id IN (SELECT c.commented_by_id from commentaire_publication c WHERE c.id=?)";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("deleted user :"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
}
