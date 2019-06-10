/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.panier;

import entities.panier.Commande;
import entities.panier.Livraison;
import entities.panier.Livreur;
import iservices.panier.ILivraisonService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.AnnonceService;
import utils.ConnectionBase;

/**
 *
 * @author Aziz
 */
public class LivraisonService implements ILivraisonService{
 Connection cnx= ConnectionBase.getInstance().getCnx();
Statement st;
    PreparedStatement pt;
    ResultSet rs;
    
    @Override
    public void AjouterLivraison(Livraison L) {
        String requete=" INSERT INTO livraison(id_commande,id_utilisateur,id_livreur,date_livraison,etat_livraison,adresse_livraison,ville) VALUES(?,?,?,?,?,?,?)";
        try {
            pt = cnx.prepareStatement(requete);
            pt.setInt(1, L.getId_commande());
            pt.setInt(2, L.getId_utilisateur());
            pt.setInt(3, L.getId_livreur());
            pt.setDate(4,L.getDate_livraison() );
            pt.setString(5,L.getEtat_livraison());
            pt.setString(6,L.getAdresse_livraison());
             pt.setString(7,L.getVille());
            pt.executeUpdate();
            System.out.println("Livraison Ajouté");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());  
        }
    }

    @Override
    public Livraison RecupererLivraison(int id) {
      String req = "SELECT * FROM `livraison` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Livraison a = new Livraison();
            while(rs.next())
            {
                 
               a.setId(rs.getInt(1));
               a.setId_commande(rs.getInt(2));
               a.setId_utilisateur(rs.getInt(3));
               a.setId_livreur(rs.getInt(4));
               a.setDate_livraison(rs.getDate(5));
               a.setEtat_livraison(rs.getString(6));
               a.setAdresse_livraison(rs.getString(7));
               a.setVille(rs.getString(8));
          
               
                
            }
            return a;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ObservableList<Livraison> RecupererLivraisonLivreur(int id) {
         ObservableList<Livraison> cmd = FXCollections.observableArrayList();
       String req = "SELECT * FROM `livraison` WHERE `id_livreur`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
         
            while(rs.next())
            {
                    Livraison a = new Livraison();
               a.setId(rs.getInt(1));
               a.setId_commande(rs.getInt(2));
               a.setId_utilisateur(rs.getInt(3));
               a.setId_livreur(rs.getInt(4));
               a.setDate_livraison(rs.getDate(5));
               a.setEtat_livraison(rs.getString(6));
               a.setAdresse_livraison(rs.getString(7));
               a.setVille(rs.getString(8));
          
               
                cmd.add(a);   
            }
            return cmd;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ObservableList<Livraison> RecupererLivraisonClient2(int id) {
        ObservableList<Livraison> cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `livraison` WHERE `id_utilisateur`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            
            while(rs.next())
            {
                 Livraison a = new Livraison();
               a.setId(rs.getInt(1));
               a.setId_commande(rs.getInt(2));
               a.setId_utilisateur(rs.getInt(3));
               a.setId_livreur(rs.getInt(4));
               a.setDate_livraison(rs.getDate(5));
               a.setEtat_livraison(rs.getString(6));
               a.setAdresse_livraison(rs.getString(7));
               a.setVille(rs.getString(8));
          
               
               cmd.add(a);   
            }
            return cmd;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void AnnulerLivraison(int id) {
       String req = "delete from livraison where id =?";
        try {
            pt = cnx.prepareStatement(req);
            pt.setInt(1, id);
            pt.executeUpdate();
          System.out.println("Suppression terminé avec succes ");
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("not ok");
        }
    }

    @Override
    public ObservableList<Livraison> getall() {
       ObservableList<Livraison> cmd = FXCollections.observableArrayList();
       String req = "SELECT * FROM `livraison` ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next())
            {   
               Livraison a = new Livraison();
               a.setId(rs.getInt("id"));
               a.setId_commande(rs.getInt("id_commande"));
               a.setId_utilisateur(rs.getInt("id_utilisateur"));
               a.setId_livreur(rs.getInt("id_livreur"));
               a.setDate_livraison(rs.getDate("date_livraison"));
               a.setEtat_livraison(rs.getString("etat_livraison"));
               a.setAdresse_livraison(rs.getString("adresse_livraison"));
               a.setVille(rs.getString("ville"));
               cmd.add(a);   
            }
             System.out.println("recup liv ok ");
            return cmd;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Livreur RecupererLivreurDispo(String zone) {
        System.out.println("dkhal l recup liv ");
        String dispo="Disponbile";
       String req = "SELECT * FROM livreur WHERE zone=? and disponibilite='Disponible' ";
        try {
            
            pt = cnx.prepareStatement(req);
            pt.setString(1, zone);
             // pt.setString(2, dispo);
            rs = pt.executeQuery();
            Livreur a = new Livreur();
            while(rs.next())
            {
                System.out.println("Id Liv : ");
                System.out.println(rs.getInt(1));
                
               a.setId(rs.getInt(1));
               a.setZone(rs.getString(2));
               a.setDisponibilite(rs.getString(3));
               a.setNbr_livraison(rs.getInt(4));
               a.setNote(rs.getInt(5));
               
            }
            
            return a;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   
    @Override
    public String RecupererAdresseClient(int id) {
        String req = "SELECT * FROM `user` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
           String rue;
           String ville;
           String apart;
           String res="";
            
            while(rs.next())
            {
                 
               apart=rs.getString(19);
               
               rue=rs.getString(21);
                ville=rs.getString(22);
                  res=apart+" ,"+rue+" ,"+ville;  
            }     
            return res;
           
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String RecupererVilleClient(int id) {
      String req = "SELECT * FROM `user` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
           
           String ville="";
           
            
            while(rs.next())
            {
                 
               
                ville=rs.getString(22);
                   
            }     
            return ville;
           
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void ChangerEtatLivraison(int id) {
         try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE livraison SET etat_livraison = ? where id = ? ");

            ps.setString(1,"Effectué");
            ps.setInt(2,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    @Override
    public void ChangerEtatLivreurTodispo(int id) {
          try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE livreur SET disponibilite = ? where id = ? ");

            ps.setString(1,"Disponible");
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    @Override
    public void ChangerEtatLivreurToindispo(int id) {
         try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE livreur SET disponibilite = ? where id = ? ");

            ps.setString(1,"Indisponible");
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    @Override
    public void ChangerEtatCommandeToEncours(int id) {
      try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE commande SET etat_commande = ? where id = ? ");

            ps.setString(1,"En cours de livraison");
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    @Override
    public void ChangerEtatCommandeToLivre(int id) {
        try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE commande SET etat_commande = ? where id = ? ");

            ps.setString(1,"Confirmé et livré");
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    @Override
    public String RecupererNP_Livreur(int id) {
      String req = "SELECT * FROM `user` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
           String nom;
           String prenom;
           
           String res="";
            
            while(rs.next())
            {
               nom=rs.getString(14);
               
               prenom=rs.getString(17);
              //  ville=rs.getString(22);
                  res=nom+" "+prenom;  
            }     
            return res;
           
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ObservableList<Livreur> getall2() {
        ObservableList<Livreur> cmd = FXCollections.observableArrayList();
       String req = "SELECT * FROM `livreur` ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next())
            {   
               Livreur a = new Livreur();
               a.setId(rs.getInt(1));
               a.setZone(rs.getString(2));
               a.setDisponibilite(rs.getString(3));
               a.setNbr_livraison(rs.getInt(4));
               a.setNote(rs.getInt(5));
              
               cmd.add(a);   
            }
             System.out.println("recup livreru ok ");
            return cmd;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void NoterLivreur(int id, int note) {
        System.out.println("dkhal inoti");
        try {
            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE livreur SET note = ? where id = ? ");

            ps.setInt(1,note);
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    @Override
    public Livreur MeilleurLivreur() {
      String req = "SELECT * FROM livreur HAVING MAX(nbr_livraison)";
        try {
            
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Livreur a = new Livreur();
            while(rs.next())
            {
                System.out.println(rs.getInt(1));
               a.setId(rs.getInt(1));
               a.setZone(rs.getString(2));
               a.setDisponibilite(rs.getString(3));
               a.setNbr_livraison(rs.getInt(4));
               a.setNote(rs.getInt(5));
               
            }
            
            return a;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int RoleLivreur(int id) {
        int x=0;
       String req = "SELECT * FROM `livreur` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
           String zone;
            
            while(rs.next())
            {
                 
               zone=rs.getString(2);
               if(zone.length()>0)
               {
               return x=3;
               }
               else
               {
               return x=0;
               }
            }     
            
           return x;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return x;
        }
    
    }

    @Override
    public int StatLiv(String zone) {
         int count=0;
         
     String req = "SELECT COUNT(*) FROM `livraison` WHERE `ville`='"+zone+"'";
        try {
            PreparedStatement pstm = cnx.prepareStatement(req);
 ResultSet res = pstm.executeQuery();
 while (res.next()) {
            count = res.getInt(1);
        }
            return count;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int NbrLivreurDispo() {
          int count=0;
     String req = "select count(*) from `livreur` where `disponibilite`='Disponible'";
        try {
            PreparedStatement pstm = cnx.prepareStatement(req);
 ResultSet res = pstm.executeQuery();
 while (res.next()) {
            count = res.getInt(1);
        }
            return count;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    
    
    }

    
    

