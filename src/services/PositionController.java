/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Mission;
import entities.Position;
import utils.ConnectionBase;

/**
 *
 * @author imene
 */
public class PositionController {
    ConnectionBase conn = ConnectionBase.getInstance();
    Connection cn = ConnectionBase.getInstance().getCnx();
    
    /*public void ajouterPosition(Position p )
    {
      
        try {
            ResultSet res = null ;
            String requete = "insert into position (adresse,ville,codePostal,pays) values (?,?,?,?)";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setString(1,p.getAdresse());
            st.setString(2,p.getVille());
            st.setInt(3,p.getCodeP());
            st.setString(4,p.getPays());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PositionController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    public void afficherPosition(Evenement e)
    {   
         try {
             Statement st = conn.getCnx().createStatement();
             String requete = "select * from position where id = "+e.getAdr();
              
             ResultSet resultat = st.executeQuery(requete);
            while (resultat.next())
                {
    

                 Position p = new Position();
                 p.setAdresse(resultat.getString("adresse"));
                 System.err.println("--------------------------------------------");
                System.err.println(p.getAdresse());
               System.err.println("--------------------------------------------");


                  p.setVille(resultat.getString(5));                 
                  p.setCodeP(resultat.getInt(6));                 
                  p.setPays(resultat.getString(7));                
                 System.out.println(e.getAdr().getId()+" "+p.getAdresse()+" "+p.getVille());
                }
         } catch (SQLException ex) {
             Logger.getLogger(PositionController.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
    }
    public void modifierPosition(Evenement e) {
        try {
            String requete = "update position set adresse = ? ,ville= ?,codePostal= ?,pays=? where id="+e.getAdr().getId();
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            
             st.setString(1,e.getAdr().getAdresse());
            st.setString(2,e.getAdr().getVille());
            st.setInt(3,e.getAdr().getCodeP());
            st.setString(4,e.getAdr().getPays());
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

       */
        
  
}
