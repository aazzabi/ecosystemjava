/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategoriePub;
import entities.PublicationForum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionBase;

/**
 *
 * @author arafe
 */
public class CategoriePubService {
    
    public static List<CategoriePub> getAllCategoriePub()
    {
        List<CategoriePub> pList = new ArrayList();
        String requete = "SELECT c.* , (SELECT COUNT(id) AS nb_pub FROM publication_forum p WHERE p.categorie_id=c.id) as nbPublication FROM categorie_pub c";
        Connection cn = ConnectionBase.getInstance().getCnx();
        
        Statement st;
        int nb=0;
        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                CategoriePub c = new CategoriePub();
                
                c.setId(rs.getInt("c.id"));
                c.setLibelle(rs.getString("c.libelle"));
                c.setDescription(rs.getString("c.description"));
                c.setDomaine(rs.getString("c.domaine"));
                c.setNbPublication(rs.getInt("nbPublication"));
                c.toString();
                pList.add(c);
            }
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }

    public static void add(CategoriePub c) {
        String requete = "INSERT INTO categorie_pub(`libelle`, `description`, `domaine`) VALUES (?, ?, ?)";
        Connection cn = ConnectionBase.getInstance().getCnx();
        
        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, c.getLibelle());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getDomaine());
           
            pst.executeUpdate();
            
            pst.close();
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    public static void delete(int id) {
        String requete = "DELETE FROM categorie_pub WHERE id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }

    public static List<CategoriePub> rechercheKeyWord(String text) {
        List<CategoriePub> pList = new ArrayList();
        String requete = "SELECT c.*, (SELECT COUNT(id) AS nb_pub FROM publication_forum p WHERE p.categorie_id=c.id) as nbPublication  FROM categorie_pub c where c.libelle LIKE ? OR c.description LIKE ? OR c.domaine LIKE ? ";
        Connection cn = ConnectionBase.getInstance().getCnx();
        
        Statement st;
        int nb=0;
        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, "%"+text+"%");
            pst.setString(2, "%"+text+"%");
            pst.setString(3, "%"+text+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                CategoriePub c = new CategoriePub();
                
                c.setId(rs.getInt("c.id"));
                c.setLibelle(rs.getString("c.libelle"));
                c.setDescription(rs.getString("c.description"));
                c.setDomaine(rs.getString("c.domaine"));
                c.setNbPublication(rs.getInt("nbPublication"));
                c.toString();
                pList.add(c);
            }
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
}
