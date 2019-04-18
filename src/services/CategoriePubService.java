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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    public static ObservableList<String> getAllCategoriesLibelle()
    {
        ObservableList<String> pList = FXCollections.observableArrayList();
        String requete = "SELECT c.libelle FROM categorie_pub c";
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        String request = "select libelle from categorie_pub";
        try {
            pt = cn.prepareStatement(request);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                pList.add(rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnounceRepService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pList;
    }

    public static CategoriePub getCategorieById(int id) {
        String requete = "SELECT c.*, (SELECT COUNT(id) AS nb_pub FROM publication_forum p WHERE p.categorie_id=c.id) as nbPublication  From categorie_pub c where c.id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        CategoriePub c = new CategoriePub();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                c.setId(rs.getInt("c.id"));
                c.setLibelle(rs.getString("c.libelle"));
                c.setDescription(rs.getString("c.description"));
                c.setDomaine(rs.getString("c.domaine"));
                c.setNbPublication(rs.getInt("nbPublication"));
                c.toString();
            }
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return c;
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

    public static void update(int id , String colonne, String newValue) {
        String requete = "UPDATE `categorie_pub` SET `description`= ?  WHERE `id`= ?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, newValue);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }

    public static void updateCategorie(CategoriePub c) {
        int id = c.getId();
        String requete = "UPDATE `categorie_pub` SET `libelle`=? WHERE `id`=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, c.getLibelle());
            pst.setInt(2, id);
            pst.executeUpdate();
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static  Integer getIdCategoriePub(String nom) {
        try {
            Connection cn = ConnectionBase.getInstance().getCnx();
            String loginQry = "SELECT id FROM categorie_pub WHERE libelle = ? ";
            PreparedStatement ste = cn.prepareStatement(loginQry);
            ste.setString(1, nom);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
            int id = rs.getInt("id");
            return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<CategoriePub> getStatPublicationPerCategorie()
    {
        List<CategoriePub> pList = new ArrayList();
        String requete = "SELECT c.id, c.libelle , (SELECT COUNT(id) AS nb_pub FROM publication_forum p WHERE p.categorie_id=c.id) as nbPublication FROM categorie_pub c";
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
