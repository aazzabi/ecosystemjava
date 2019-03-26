/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.InvitationMission;
import entities.Participant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionBase;

/**
 *
 * @author imene
 */
public class InvitationMissionService {
    
    
       ConnectionBase conn = ConnectionBase.getInstance();
     Connection cn = ConnectionBase.getInstance().getCnx();
    
      public void InviterAmis(InvitationMission inv )
    {
        try {
            ResultSet res = null ;
            String requete = "insert into invitationevent (idInviteur,idEvent,idInvite,etat) values (?,?,?,?)";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setInt(1,inv.getIdIviteur());
            st.setInt(2,inv.getIdEvent());
            st.setInt(3,inv.getIdIvite());
            st.setInt(4,inv.getEtat());
          
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
       
    }
      
      public List<InvitationMission> ConsulterMesInvitations(int id )
      {
           List<InvitationMission> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from invitationevent where idInvite ="+id;
            
            ResultSet resultat = st.executeQuery(requete);
            
            while (resultat.next()) {
                InvitationMission inv = new InvitationMission();
                
               inv.setIdIviteur(resultat.getInt(1));
               inv.setIdEvent(resultat.getInt(2));
               inv.setIdIvite(resultat.getInt(3));

                inv.setEtat(resultat.getInt(4));

                System.out.println("je suiis entréé dans la liste");
             
                myList.add(inv);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return myList;
         
      }
        public void annulerInv(int idInvit,String idEvent) {
        try {
           
            String requete1 = "delete from invitationevent where idEvent=? and idInvite=?";
            PreparedStatement st1 = conn.getCnx().prepareStatement(requete1);
            
            st1.setString(1, idEvent);
            st1.setInt(2, idInvit);
             st1.executeUpdate();

           
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          public void SupprimerInv(int idEvent,int idInvit) {
        try {
           
            String requete1 = "delete from invitationevent where idEvent=? and idInvite=?";
            PreparedStatement st1 = conn.getCnx().prepareStatement(requete1);
            
            st1.setInt(1, idEvent);
            st1.setInt(2, idInvit);
             st1.executeUpdate();

           
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
