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
import iservices.panier.ILigneCommandeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class LigneCommandeService implements ILigneCommandeService{
    
Connection cnx= ConnectionBase.getInstance().getCnx();
Statement st;
    PreparedStatement pt;
    ResultSet rs;
    @Override
    public void AjouterLigneCommande(LigneCommande c) {
        String requete=" INSERT INTO ligne_commande(id_commande,id_annonce,id_utilisateur,prix_annonce) VALUES(?,?,?,?)";
        try {
            pt = cnx.prepareStatement(requete);
            pt.setInt(1, c.getId_commande());
            pt.setInt(2, c.getId_annonce());
            pt.setInt(3, c.getId_user());
            pt.setDouble(4, c.getPrix_annonce());
            pt.executeUpdate();
            System.out.println("Ligne Ajouté");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());  
        }
    }

    @Override
    public void AnnulerLigneCommande(int id) {
       String req = "delete from ligne_commande where id_commande =?";
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
    public ObservableList<LigneCommande> getall() {
       ObservableList<LigneCommande> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `ligne_commande`";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            LigneCommande a = new LigneCommande();
            while(rs.next())
            {
               a.setId_commande(rs.getInt(2));
               a.setId_annonce(rs.getInt(3));
               a.setId_user(rs.getInt(4));
               a.setPrix_annonce(rs.getInt(5));
                   l_cmd.add(a); 
            }
            
            return l_cmd;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ObservableList<LigneCommande> RecupererLignesCommande(int id) {
        ObservableList<LigneCommande> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `ligne_commande` WHERE `id_commande`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            LigneCommande a = new LigneCommande();
            while(rs.next())
            {
               a.setId_commande(rs.getInt(2));
               a.setId_annonce(rs.getInt(3));
               a.setId_user(rs.getInt(4));
               a.setPrix_annonce(rs.getDouble(5));
                   l_cmd.add(a); 
            }
            
            return l_cmd;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ObservableList<AnnoncePanier> RecupererLignesCommande2(int id,ObservableList<AnnoncePanier> A) {
        ObservableList<AnnoncePanier> l_cmd = FXCollections.observableArrayList();
        ObservableList<AnnoncePanier> tab2 = FXCollections.observableArrayList();
       ObservableList<AnnoncePanier> t3=A;
        String req = "SELECT * FROM `ligne_commande` WHERE `id_commande`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            AnnoncePanier a = new AnnoncePanier();
            for(int i=0;i<t3.size();i++)
            {
            while(rs.next())
            {
                if(Integer.toString(rs.getInt(3)).equals(t3.get(i).getId_annonce()))
                {
                a=t3.get(i);
                 tab2.add(a); 
                }
              
                  
            }
            }
            return tab2;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int VerifAnnonce(int id) {
       ObservableList<LigneCommande> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `ligne_commande` WHERE `id_annonce`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            LigneCommande a = new LigneCommande();
            while(rs.next())
            {
               a.setId_commande(rs.getInt(2));
               a.setId_annonce(rs.getInt(3));
               a.setId_user(rs.getInt(4));
               a.setPrix_annonce(rs.getDouble(5));
                   l_cmd.add(a); 
            }
            
            if(l_cmd.size()>0)
            {
            return 1;
            }
            else
            {
            return 0;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public Annonce RecupA(int id) {
         System.out.println("f west el fonction mta reuperation11");
        //ObservableList<Annonce> l_cmd = FXCollections.observableArrayList();
        String req = "SELECT * FROM `annonce` WHERE `id`='"+id+"' ";
        try {
           // System.out.println("Dkhal lena");
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Annonce a = new Annonce();
            while(rs.next())
            {
                 System.out.println("f west el fonction mta reuperation");
                System.out.println(rs.getString(4));
              a.setId(rs.getInt(1));
               a.setCategorie_id(rs.getInt(2));
               a.setUser_id(rs.getInt(3));
               a.setTitre(rs.getString(4));
               a.setDescription(rs.getString(5));
               a.setDate_creation(rs.getDate(6));
               a.setDate_update(rs.getDate(7));
               a.setPrix(rs.getDouble(8));
               a.setRegion(rs.getString(9));
               a.setEtat(rs.getString(10));
               a.setPhoto(rs.getString(11));
               a.setPhoto_updated_at(rs.getDate(12));
               a.setLikes(rs.getInt(13));
               a.setViews(rs.getInt(14));
            }
            
            return a;
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
   
    
}
