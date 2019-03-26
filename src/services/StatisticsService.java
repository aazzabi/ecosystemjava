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
 * @author imene
 */
public class StatisticsService {
    int nbrtot ;
    int nbrpay ;
    int nbrfree ;
    int nbrouvert ;
    int nbrferme ;
    int nbrsignal ;
    int nbrapprec ;

    ConnectionBase conn = ConnectionBase.getInstance();
    Connection cn = ConnectionBase.getInstance().getCnx();


    public int nbreTotalEvent() {
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "SELECT COUNT(*) FROM evenement";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 nbrtot=Integer.parseInt(resultat.getString(1));
                System.out.println("Le nbre d'event est :"+nbrtot);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbrtot;
    }

    public int nbreEventPayant() {
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "SELECT COUNT(*) FROM evenement where acces='Privé'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 nbrpay=Integer.parseInt(resultat.getString(1));
                System.out.println("Le nbre d'events Payants est :"+nbrpay);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbrpay;
    }

    public int nbreEventGratuit() {
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "SELECT COUNT(*) FROM evenement where acces='Publique'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 nbrfree=Integer.parseInt(resultat.getString(1));
                System.out.println("Le nbre d'events gratuit est :"+nbrfree);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbrfree;
    }

    public int nbreEventOuvert() {
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "SELECT COUNT(*) FROM evenement where disponible='ouvert'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 nbrouvert=Integer.parseInt(resultat.getString(1));
                System.out.println("Le nbre d'events ouverts est :"+nbrouvert);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbrouvert;
    }

    public int nbreEventferme() {
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "SELECT COUNT(*) FROM evenement where disponible='fermé'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 nbrferme=Integer.parseInt(resultat.getString(1));
                System.out.println("Le nbre d'event fermés est :"+nbrferme);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbrferme;
    }

    public int nbreEventApprec() {
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "SELECT COUNT(*) FROM `participant` WHERE type_avis='A'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 nbrapprec=Integer.parseInt(resultat.getString(1));
                System.out.println("Le nbre d'appréciations est :"+nbrapprec);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbrferme;
    }

    public int nbreEventSignal() {
        
        try {
            Statement st = conn.getCnx().createStatement();
            String requete = "SELECT COUNT(*) FROM `participant` WHERE type_avis='S'";
            ResultSet resultat = st.executeQuery(requete);
            while (resultat.next()) {
                 nbrsignal=Integer.parseInt(resultat.getString(1));
                System.out.println("Le nbre de signal est :"+nbrsignal);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(MissionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nbrferme;
    }



}