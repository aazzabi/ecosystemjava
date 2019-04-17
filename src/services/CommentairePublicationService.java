/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CommentairePublication;
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
    
    public static void deleteUserByIdCommentaire(int p)
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
    

    public static int add(CommentairePublication c){
        int status = 0;
        PreparedStatement pt;
        
        String req = "Insert into commentaire_publication (`commented_by_id`, `publication_id`, `description`, "
                + "`commented_at`, `likes`, `nbSignalisation`, `dislikes`)"
                + "values(?,?,?,?,?,?,?)";
        try {
            Connection cn = ConnectionBase.getInstance().getCnx();
            pt = cn.prepareStatement(req);
            pt.setInt(1, c.getCreatedBy());
            pt.setInt(2, c.getIdPublication());
            pt.setString(3, c.getDescription());
            pt.setDate(4, c.getCreatedAt());
            pt.setInt(5, c.getLikes());
            pt.setInt(6, c.getNbSignalisation());
            pt.setInt(7, c.getDislikes());
      
            status = pt.executeUpdate();
            System.out.println("succée");
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return status;
    }
    
        public static void like(int p)
    {
        String req = "UPDATE commentaire_publication SET likes=likes+1 where id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("liked comment :"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static void dislike(int p)
    {
        String req = "UPDATE commentaire_publication SET dislikes = dislikes+1 where id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("liked comment :"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static void signaler(int p)
    {
        String req = "UPDATE commentaire_publication SET nbSignalisation = nbSignalisation+1 where id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("liked comment :"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }

}
