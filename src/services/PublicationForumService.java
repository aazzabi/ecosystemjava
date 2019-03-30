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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionBase;

/**
 *
 * @author arafe
 */
public class PublicationForumService {
    
    public static List<PublicationForum> getAllPublications()
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select * from publication_forum";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setEtat(rs.getString("etat"));
                p.setCategorie(rs.getInt("categorie_id"));
                p.setCreatedBy(rs.getInt("publication_created_by_id"));
                p.setCreatedAt(rs.getDate("pub_created_at"));
                p.setNbrVues(rs.getInt("nbrVues"));
                pList.add(p);
            }
            System.out.println("Okey ");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
    
    public static void deletePublication(int p)
    {
        String requete = "DELETE FROM publication_forum WHERE id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
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
    
    public static List<PublicationForum> getAllPublicationsByCategorie(int id)
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select * from publication_forum where categorie_id =?";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setEtat(rs.getString("etat"));
                p.setCategorie(rs.getInt("categorie_id"));
                p.setCreatedBy(rs.getInt("publication_created_by_id"));
                p.setCreatedAt(rs.getDate("pub_created_at"));
                p.setNbrVues(rs.getInt("nbrVues"));
                pList.add(p);
            }
            System.out.println("Okey ");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
    
    public static void archiverPublication(int p)
    {
        String requete = "UPDATE publication_forum SET etat= 'publié' WHERE id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("archivé"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
}
