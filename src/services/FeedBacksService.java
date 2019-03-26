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
import entities.FeedBack;
import entities.Participant;
import entities.Position;
import java.sql.Timestamp;
import utils.ConnectionBase;

/**
 *
 * @author weepey
 */
public class FeedBacksService {

    ConnectionBase conn = ConnectionBase.getInstance();
    Connection cn = ConnectionBase.getInstance().getCnx();

    public void ajouterFeedBack(Participant p) {
        try {
            ResultSet res = null;
            String requete = "update participant set type_avis = ? ,commentaire= ? where idEvent =  "+p.getId_event()+" and idUser= "+p.getId_user();
            System.out.println("Id Event :"+p.getId_event()+" idUser= "+p.getId_user()+" Feed :"+p.getAvis()+" Comment :"+p.getCommentaire()); 
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setString(1, p.getAvis());
            st.setString(2, p.getCommentaire());
            st.executeUpdate();
            System.out.println("Le commentaire a bien été rajouté dans la table participant ! pour le User :"+p.getId_user()+" et pour l'événement :"+p.getId_event());

        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void purgerFeedBack(Participant p) {
        try {
            ResultSet res = null;
            String requete = "update participant set type_avis = ? ,commentaire= ? where idEvent =  "+p.getId_event()+" and idUser= "+"9999";
            System.out.println("Id Event :"+p.getId_event()+" idUser= "+p.getId_user()+" Feed :"+p.getAvis()+" Comment :"+p.getCommentaire()); 
            PreparedStatement st = conn.getCnx().prepareStatement(requete);
            st.setString(1, "");
            st.setString(2, "");
            st.executeUpdate();
            System.out.println("Le commentaire a bien été purgé dans la table participant ! pour le User :"+p.getId_user()+" et pour l'événement :"+p.getId_event());

        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public List<FeedBack> showFeedBack(Participant p) {
        List<FeedBack> feedbckList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select type_avis,commentaire from participant where idEvent =  "+p.getId_event()+" and idUser= "+"9999";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 FeedBack f = new FeedBack();
                 f.setType(resultat.getString(1));
                 f.setCommentaire(resultat.getString(2));
                 feedbckList.add(f);
                System.out.println("Le commentaire instruit :"+f.getCommentaire()+" dont le type est :"+f.getType());   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return feedbckList;
    }
    public List<FeedBack> showFeedBacks(Participant p) {
        List<FeedBack> feedbckList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select type_avis,commentaire from participant where  idUser= "+"9999";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 FeedBack f = new FeedBack();
                 f.setType(resultat.getString(1));
                 f.setCommentaire(resultat.getString(2));
                 feedbckList.add(f);
                System.out.println("Le commentaire instruit :"+f.getCommentaire()+" dont le type est :"+f.getType());   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return feedbckList;
    }
      public ArrayList<FeedBack> showFeedBacksEvent(int id ) {
        ArrayList<FeedBack> feedbckList = new ArrayList<>();
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select type_avis,commentaire from participant where type_avis IS NOT NULL and idEvent="+id;
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 FeedBack f = new FeedBack();
                 f.setType(resultat.getString(1));
                 f.setCommentaire(resultat.getString(2));
                 feedbckList.add(f);
                System.out.println("Le commentaire instruit :"+f.getCommentaire()+" dont le type est :"+f.getType());   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
 return feedbckList;
    }
      public List<FeedBack> FeebApp(String nom)
      {
          List<FeedBack> feedbckList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select type,commentaire from listeappreciation where idEvent  ='" + nom + "'";
            
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 FeedBack f = new FeedBack();
                 f.setType(resultat.getString("type"));
                 f.setCommentaire(resultat.getString("commentaire"));
                 feedbckList.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return feedbckList;
      }
        public List<FeedBack> FeebSignal(String nom)
      {
          List<FeedBack> feedbckList = new ArrayList<>();
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "select type,commentaire from listesfeedsignal where idEvent  ='" + nom + "'";
            
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 FeedBack f = new FeedBack();
                 f.setType(resultat.getString("type"));
                 f.setCommentaire(resultat.getString("commentaire"));
                 feedbckList.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 return feedbckList;
      }

}
