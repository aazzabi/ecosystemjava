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
            System.out.println("Okey ");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
}
