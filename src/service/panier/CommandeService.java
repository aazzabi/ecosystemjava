/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.panier;

import entities.Annonce;
import entities.panier.AnnoncePanier;
import entities.panier.Commande;
import entities.panier.LigneCommande;
import iservices.panier.ICommandeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
public class CommandeService implements ICommandeService{
 Connection cnx= ConnectionBase.getInstance().getCnx();
Statement st;
    PreparedStatement pt;
    ResultSet rs;
    
   

    @Override
    public void AnnulerCommande(int id) {
        String req = "delete from commande where id =?";
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
    public ObservableList<Commande> getall() {
        ObservableList<Commande> cmd = FXCollections.observableArrayList();
       String req = "SELECT * FROM `commande` ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next())
            {   
               Commande a = new Commande();
               a.setId(rs.getInt(1));
               a.setDate_emission(rs.getDate("date_emission"));
               a.setEtat_commande(rs.getString("etat_commande"));
               a.setId_user(rs.getInt("id_utilisateur"));
               a.setPrixTotal(rs.getDouble("prixTotal"));
               cmd.add(a);   
            }
             System.out.println("recup cmd ok ");
            return cmd;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void AjouterCommande( Commande c) {
       String requete=" INSERT INTO commande(date_emission,etat_commande,id_utilisateur,prixTotal) VALUES(?,?,?,?)";
        try {
            pt = cnx.prepareStatement(requete);
            pt.setDate(1, c.getDate_emission());
            pt.setString(2, c.getEtat_commande());
            pt.setInt(3, c.getId_user());
            pt.setDouble(4, c.getPrixTotal());
            pt.executeUpdate();
            System.out.println("Commande Ajouté");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());  
        }
    }

    @Override
    public Commande RecupererCommande(Commande c) {
        String req = "SELECT * FROM `commande` WHERE `id_utilisateur`='"+c.getId_user()+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Commande a = new Commande();
            while(rs.next())
            {
               a.setId(rs.getInt("id"));
               a.setDate_emission(rs.getDate("date_emission"));
               a.setEtat_commande(rs.getString("etat_commande"));
               a.setId_user(rs.getInt("id_utilisateur"));
               a.setPrixTotal(rs.getInt("prixTotal"));
               
               
            }
            
            return a;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String RecupererNP_Utilisateur(int id) {
       
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
              
                  res=nom+" "+prenom;  
            }     
            return res;
           
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ObservableList<Commande> RecupererCommandeClient(int id) {
        ObservableList<Commande> cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `commande` WHERE `id_utilisateur`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            
            while(rs.next())
            {
                Commande a = new Commande();
               a.setId(rs.getInt("id"));
               a.setDate_emission(rs.getDate("date_emission"));
               a.setEtat_commande(rs.getString("etat_commande"));
               a.setId_user(rs.getInt("id_utilisateur"));
               a.setPrixTotal(rs.getInt("prixTotal"));
               
               cmd.add(a); 
            }
            return cmd;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Commande RecupererCommandeClient2(int id) {
      String req = "SELECT * FROM `commande` WHERE `id_utilisateur`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Commande a = new Commande();
            while(rs.next())
            {
               a.setId(rs.getInt("id"));
               a.setDate_emission(rs.getDate("date_emission"));
               a.setEtat_commande(rs.getString("etat_commande"));
               a.setId_user(rs.getInt("id_utilisateur"));
               a.setPrixTotal(rs.getInt("prixTotal"));
            
            }
            
            return a;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String RecupererMail(int id) {
       String req = "SELECT * FROM `user` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
           String mail="";
           
            
            while(rs.next())
            {
                 
               mail=rs.getString(5);
               
           
            }     
            return mail;
           
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void ChangerEtatCommandeToPaye(int id) {
       try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE commande SET etat_commande = ? where id = ? ");

            ps.setString(1,"Payée");
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    @Override
    public String RecupererTel_Utilisateur(int id) {
         String req = "SELECT * FROM `user` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
           String mail="";
           
            
            while(rs.next())
            {
                 
               mail=rs.getString(20);
               
           
            }     
            return mail;
           
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int CommandeJanvier() {
        int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-01-01') AND (date_emission <= '2019-01-31')";
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
    public int CommandeFevrier() {
         int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-02-01') AND (date_emission <= '2019-02-31')";
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
    public int CommandeMars() {
            int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-03-01') AND (date_emission <= '2019-03-31')";
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
    public int CommandeAvril() {
          int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-04-01') AND (date_emission <= '2019-04-31')";
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
    public int CommandeMai() {
          int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-05-01') AND (date_emission <= '2019-05-31')";
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
    public int CommandeJuin() {
          int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-06-01') AND (date_emission <= '2019-06-31')";
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
    public int CommandeJuillet() {
          int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-07-01') AND (date_emission <= '2019-07-31')";
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
    public int CommandeAout() {
           int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-08-01') AND (date_emission <= '2019-08-31')";
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
    public int CommandeSeptembre() {
         int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-09-01') AND (date_emission <= '2019-09-31')";
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
    public int CommandeOctobre() {
          int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-10-01') AND (date_emission <= '2019-10-31')";
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
    public int CommandeNovembre() {
          int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-11-01') AND (date_emission <= '2019-11-31')";
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
    public int CommandeDecembre() {
         int count=0;
     String req = "SELECT  COUNT(*) from commande where (date_emission >= '2019-12-01') AND (date_emission <= '2019-12-31')";
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
    public void ChangerEtatCommandeToLivre(int id) {
      try {

            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE commande SET etat_commande = ? where id = ? ");

            ps.setString(1,"Livrée");
            ps.setInt(2,id);
            ps.executeUpdate();
            //ps.close();
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }
    }

    
   
}
    