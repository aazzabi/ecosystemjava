/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
import entities.Participant;
import entities.Utilisateur;
import java.util.HashMap;
import java.util.Map;
import utils.ConnectionBase;

/**
 *
 * @author imene
 */
public class ParticipantService {
    
    ConnectionBase conn = ConnectionBase.getInstance();
     Connection cn = ConnectionBase.getInstance().getCnx();
    
      public void ajouterParticipant(Participant p )
    {
        try {
            ResultSet res = null ;
            String requete = "insert into participant (idEvent,idUser,statutP,type_avis,commentaire,idTicket) values (?,?,?,?,?,?)";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setInt(1,p.getId_event());
            st.setInt(2,p.getId_user());
            st.setString(3,p.getStatut_p());
            st.setString(4,p.getAvis());
            st.setString(5,p.getCommentaire());
            st.setInt(6,p.getId_ticket());
                
           
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
       
    }
      public int verifParticipation(int ide,int idp)
      {
          
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select idEvent from evenement_participe where idEvent ='" + ide + "' and idParticipant = '"+idp+"'";
            ResultSet resultat = st.executeQuery(requete);
           while (resultat.next())
           {
            ide=resultat.getInt("idEvent");

           }
        
            
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return ide;
      }
      public boolean verifParticipationBoolean(int ide,int idp)
      {
          int idEvn=0 ;
          
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select idEvent from evenement_participe where idEvent ='" + ide + "' and idParticipant = '"+idp+"'";
            ResultSet resultat = st.executeQuery(requete);
           while (resultat.next())
           {
               
            idEvn=resultat.getInt("idEvent");

           }
        
            
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(idEvn==ide)
            return true;
        
          return false;
      }
      public void countParticipant (int id )
      {
          
      }
        public ArrayList<Participant> afficheParticipantDeEvenement(int id) {
        ArrayList<Participant> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from participant where idEvent ="+id;
            
            ResultSet resultat = st.executeQuery(requete);
            
            while (resultat.next()) {
                Participant p = new Participant();
               p.setId_event(resultat.getInt(1));
               p.setId_user(resultat.getInt(2));
               p.setStatut_p(resultat.getString(3));

                p.setAvis(resultat.getString(4));

                p.setCommentaire(resultat.getString(5));
                p.setId_ticket(resultat.getInt(6));
             
                myList.add(p);
               
              //  System.out.println(p.getId_event() + " " + p.getId_user()+ " " + p.getStatut_p() + " " + p.getAvis() + " " + p.getCommentaire());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
return  myList;
    }
         public Map<Mission,String> afficheEvenementParticipe(int id ) {
        //List<Participant> myList = new ArrayList<>();
             Map<Mission,String> myMap = new HashMap<Mission,String>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from evenement_participe where idParticipant ="+id;
            
            ResultSet resultat = st.executeQuery(requete);
            
            while (resultat.next()) {
                
               Mission e = new Mission();
               e.setId_event(resultat.getInt(2));
                e.setNom(resultat.getString("NomEvent"));
                e.setNomcategorie(resultat.getString("Nomcategorie"));
                e.setNomtype(resultat.getString("Nomtype"));
                e.setAnimateur(resultat.getString("animateur"));
                e.setDate_debut(resultat.getTimestamp("dateD"));
                e.setDate_fin(resultat.getTimestamp("dateF"));
                e.setDisponible(resultat.getString("Disponible"));
                e.setAdr(resultat.getString("adresse"));
                e.setAdr(resultat.getString("description"));
               String avis = resultat.getString("monAvis");
        

             
                myMap.put(e,avis);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myMap;

    }
            public boolean supprimerParticipant(int idP,int idE ) {
        try {
            String requete = "delete from participant where idEvent=? and idUser=?";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setInt(1, idE);
            st.setInt(2, idP);
            st.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
            
              public void modifierParticipant(Participant p ) {
        try {
            String requete = "update participant set commentaire= ? where idEvent=? and idUser=?";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setString(1, p.getCommentaire());
         

           st.setInt(2,p.getId_event());
            st.setInt(3,p.getId_user());
           
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

       public ArrayList<Utilisateur> affichelisteParticipant(int id) {
        ArrayList<Utilisateur> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from listep join user on id_user = idUser  where idEvent ="+id;
            
            ResultSet resultat = st.executeQuery(requete);
            
            while (resultat.next()) {
                Utilisateur p = new Utilisateur();
               
               p.setId(resultat.getInt(1));
               p.setNom(resultat.getString(3));
               p.setPrenom(resultat.getString(4));
               p.setEmail(resultat.getString("email"));
              

               
             
                myList.add(p);
               
              //  System.out.println(p.getId_event() + " " + p.getId_user()+ " " + p.getStatut_p() + " " + p.getAvis() + " " + p.getCommentaire());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
return  myList;
    }
    
}
