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
import java.sql.Timestamp;
import java.time.LocalDate;
import utils.ConnectionBase;

/**
 *
 * @author weepey
 */
public class MissionService {

    ConnectionBase conn = ConnectionBase.getInstance();
    Connection cn = ConnectionBase.getInstance().getCnx();

    public void ajouterEvenement(Mission e) {
        try {
            ResultSet res = null;
            String requete = "insert into mission (id_event,nom,Nomcategorie,Nomtype,animateur,date_debut,date_fin,disponible,acces,adresse,description,id_org,image) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setInt(1, e.getId_event());
            st.setString(2, e.getNom());
            st.setString(3, e.getNomcategorie());
            st.setString(4,e.getNomtype());
            st.setString(5,e.getAnimateur());
           
                st.setTimestamp(6, e.getDate_debut());
                st.setTimestamp(7, e.getDate_fin());
          

           st.setString(8,e.getDisponible().toString());
           st.setString(9,e.getAcces().toString());


            st.setString(10, e.getAdr());
            st.setString(11, e.getDescription());
            st.setInt(12, e.getIdOrg());
            st.setString(13, e.getImage());
       

            st.executeUpdate();
            System.err.println("ajoutttttttttttttttttt");
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Mission> afficherEvenement() {
        List<Mission> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from mission ";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                Mission e = new Mission();
              e.setId_event(resultat.getInt(1));

                e.setNom(resultat.getString(2));
                e.setNomcategorie(resultat.getString(3));
                e.setNomtype(resultat.getString(4));
                e.setAnimateur(resultat.getString(5));

                e.setDate_debut(resultat.getTimestamp(6));
                e.setDate_fin(resultat.getTimestamp(7));
                e.setDisponible(resultat.getString(8));
                e.setAcces(resultat.getString(9));
                e.setAdr(resultat.getString(10));
                e.setDescription(resultat.getString(11));
                e.setIdOrg(resultat.getInt(12));
                e.setImage(resultat.getString(13));
           
        

                myList.add(e);
                System.out.println(e.getNom()+" "+e.getAdr()+" "+e.getAnimateur());   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return myList;
    }
      public Mission GetEvenement(int id) {
                   Mission e = new Mission();

        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from evenement where id_event = "+id;
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
              e.setId_event(resultat.getInt(1));

                e.setNom(resultat.getString(2));
                e.setNomcategorie(resultat.getString(3));
                e.setNomtype(resultat.getString(4));
                e.setAnimateur(resultat.getString(5));

                e.setDate_debut(resultat.getTimestamp(6));
                e.setDate_fin(resultat.getTimestamp(7));
                e.setDisponible(resultat.getString(8));
                e.setAcces(resultat.getString(9));
                e.setAdr(resultat.getString(10));
                e.setDescription(resultat.getString(11));
        
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return e ;

    }


    public boolean supprimerEvenemnt(int id) {
        try {
        
             System.out.println("STEP  1 ");
              String requete= "delete from participant where idEvent=?";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
             st.setInt(1, id);
              st.executeUpdate();
              
                String requete4= "delete from invitationevent where idEvent=?";
            PreparedStatement st4 = conn.getCnx().prepareStatement(requete4);
             st4.setInt(1, id);
              st4.executeUpdate();
              
              
                         System.out.println("STEP  2 ");
                             String requete2 = "delete from Ticket where idEvent=?";
            PreparedStatement st2 = conn.getCnx().prepareStatement(requete2);
             st2.setInt(1, id);
             st2.executeUpdate();

            String requete1 = "delete from evenement where id_event=?";
            PreparedStatement st1 = conn.getCnx().prepareStatement(requete1);
            
            st1.setInt(1, id);
             st1.executeUpdate();
 System.out.println("STEP  3 ");
           
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void modifierEvenemnt(Mission e) {
        try {
            String requete = "update evenement set nom = ? ,NomCategorie = ? , Nomtype = ? , animateur = ? , date_debut = ? , date_fin = ? ,disponible = ?  ,adresse = ?,description = ? where id_event=? ";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setString(1, e.getNom());
            st.setString(2, e.getNomcategorie());
            st.setString(3,e.getNomtype());
            st.setString(4,e.getAnimateur());
           
                st.setTimestamp(5, e.getDate_debut());
                st.setTimestamp(6, e.getDate_fin());
          

           st.setString(7,e.getDisponible().toString());


            st.setString(8, e.getAdr());
            st.setString(9, e.getDescription());
       
       st.setInt(10,e.getId_event());
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      public List<Mission> afficherMesEvenement(int id) {
        List<Mission> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from evenement where id_org ="+id;
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                Mission e = new Mission();
              e.setId_event(resultat.getInt(1));

                e.setNom(resultat.getString(2));
                e.setNomcategorie(resultat.getString(3));
                e.setNomtype(resultat.getString(4));
                e.setAnimateur(resultat.getString(5));

                e.setDate_debut(resultat.getTimestamp(6));
                e.setDate_fin(resultat.getTimestamp(7));
                e.setDisponible(resultat.getString(8));
                e.setAcces(resultat.getString(9));
                e.setAdr(resultat.getString(10));
                e.setDescription(resultat.getString(11));
                  e.setImage(resultat.getString(13));
        

                myList.add(e);
                System.out.println(e.getNom()+" "+e.getAdr()+" "+e.getAnimateur());   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return myList;
    }

      
      public List<Mission> afficherMesEvenementCategorie(String cat) {
        List<Mission> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from evenement where NomCategorie ='" + cat + "'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                Mission e = new Mission();
              e.setId_event(resultat.getInt(1));

                e.setNom(resultat.getString(2));
                e.setNomcategorie(resultat.getString(3));
                e.setNomtype(resultat.getString(4));
                e.setAnimateur(resultat.getString(5));

                e.setDate_debut(resultat.getTimestamp(6));
                e.setDate_fin(resultat.getTimestamp(7));
                e.setDisponible(resultat.getString(8));
                e.setAcces(resultat.getString(9));
                e.setAdr(resultat.getString(10));
                e.setDescription(resultat.getString(11));
                  e.setImage(resultat.getString(13));
        

                myList.add(e);
                System.out.println(e.getNom()+" "+e.getNomcategorie());   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 return myList;
    }
       public List<Mission> afficherFreeEvent() {
           String pub = "Publique";
        List<Mission> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from evenement where acces ='" +pub + "'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                Mission e = new Mission();
              e.setId_event(resultat.getInt(1));

                e.setNom(resultat.getString(2));
                e.setNomcategorie(resultat.getString(3));
                e.setNomtype(resultat.getString(4));
                e.setAnimateur(resultat.getString(5));

                e.setDate_debut(resultat.getTimestamp(6));
                e.setDate_fin(resultat.getTimestamp(7));
                e.setDisponible(resultat.getString(8));
                e.setAcces(resultat.getString(9));
                e.setAdr(resultat.getString(10));
                e.setDescription(resultat.getString(11));
                  e.setImage(resultat.getString(13));
        

                myList.add(e);
                System.out.println("---------------------------------------------");   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 return myList;
    }
       public List<Mission> afficherMesEvenementDate(LocalDate date) {
           //select * from evenement where MONTH( date_debut ) ='" + date.getMonth() + "' and  DAY( date_debut) ='" + date.getDayOfMonth() + "' 14
           System.out.println("mois -----"+date.getMonth().getValue());
           System.out.println("jour------"+date.getDayOfMonth());
        List<Mission> myList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from evenement where MONTH( date_debut ) ='"+date.getMonth().getValue()+"' and  DAY( date_debut) ='"+date.getDayOfMonth()+"'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                Mission e = new Mission();
              e.setId_event(resultat.getInt(1));

                e.setNom(resultat.getString(2));
                e.setNomcategorie(resultat.getString(3));
                e.setNomtype(resultat.getString(4));
                e.setAnimateur(resultat.getString(5));

                e.setDate_debut(resultat.getTimestamp(6));
                e.setDate_fin(resultat.getTimestamp(7));
                e.setDisponible(resultat.getString(8));
                e.setAcces(resultat.getString(9));
                e.setAdr(resultat.getString(10));
                e.setDescription(resultat.getString(11));
                                  e.setImage(resultat.getString(13));

        

                myList.add(e);
                System.out.println("---------------------------------------------");   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 return myList;
    }
         public Mission findEventByID(String idU) {
        Mission e = new Mission();// Recheck position of this stmt
        
        try {
            // Will be deleted later 
            String q = "SELECT * FROM EVENEMENT";
             Statement stmt = conn.getCnx().createStatement();
           
            ResultSet rsltList = stmt.executeQuery(q);
            rsltList.last();
            int id = rsltList.getInt("id_event");
            e.setId_event(id);
            
            String query = "SELECT * FROM evenement WHERE id_event = " + id;
            //User user = new User();
            
            try {
              stmt = conn.getCnx().createStatement();
                /*ResultSet*/ rsltList = stmt.executeQuery(query); // not executeUpdate | Careful use same statement

                while(rsltList.next()) {
                    //user.setId(rsltList.getInt("id_user"));
                     e.setId_event(rsltList.getInt(1));

                e.setNom(rsltList.getString(2));
                e.setNomcategorie(rsltList.getString(3));
                e.setNomtype(rsltList.getString(4));
                e.setAnimateur(rsltList.getString(5));

                e.setDate_debut(rsltList.getTimestamp(6));
                e.setDate_fin(rsltList.getTimestamp(7));
                e.setDisponible(rsltList.getString(8));
                e.setAcces(rsltList.getString(9));
                e.setAdr(rsltList.getString(10));
                e.setDescription(rsltList.getString(11));
                }
                
            } catch (SQLException ex) {
                //Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return e;
    }
         
          public List<Mission> afficherEvenemntDispo() {
              Timestamp timestamp = new Timestamp(System.currentTimeMillis());
              System.out.println(" Date : "+timestamp);

            
        List<Mission> myList = new ArrayList<>();
        List<Mission> EventDispo = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select * from evenement ";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                Mission e = new Mission();
              e.setId_event(resultat.getInt(1));

                e.setNom(resultat.getString(2));
                e.setNomcategorie(resultat.getString(3));
                e.setNomtype(resultat.getString(4));
                e.setAnimateur(resultat.getString(5));

                e.setDate_debut(resultat.getTimestamp(6));
                e.setDate_fin(resultat.getTimestamp(7));
                e.setDisponible(resultat.getString(8));
                e.setAcces(resultat.getString(9));
                e.setAdr(resultat.getString(10));
                e.setDescription(resultat.getString(11));
                 e.setImage(resultat.getString(13));

        

                myList.add(e);
                System.out.println("---------------------------------------------");   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Mission  e1 : myList )
        {
            if(e1.getDate_fin().after(timestamp))
            {
                EventDispo.add(e1);
               System.out.println(" Date event : "+e1.getDate_fin());

            }
        }
        
 return EventDispo;
    }
          
           public void RefrechEvenemntDispo() {
           

            String dispo = "ouvert";
            String ferm = "fermé";
        try {
            
            String requete = "update  evenement set disponible ='"+ferm+"'where date_fin < CURRENT_DATE() ";
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
           st.executeUpdate();
      
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        

    }
    
    
}
