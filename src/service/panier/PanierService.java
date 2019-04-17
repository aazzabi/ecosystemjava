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
import entities.reparateur.AnnounceRep;
import iservices.panier.IPanierService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class PanierService implements IPanierService{

       Connection cnx= ConnectionBase.getInstance().getCnx();
Statement st;
    PreparedStatement pt;
    ResultSet rs;
    
    @Override
    public void AjouterAuPanier(Annonce a) {
    
String requete=" INSERT INTO annonce_panier(id_annonce,titre,description,prix,region,photo) VALUES(?,?,?,?,?,?)";
        try {
            pt = cnx.prepareStatement(requete);
            pt.setInt(1, a.getId());
            pt.setString(2, a.getTitre());
            pt.setString(3, a.getDescription());
            pt.setDouble(4, a.getPrix());
            pt.setString(5, a.getRegion());
            pt.setString(6, a.getPhoto());            
            pt.executeUpdate();
      
            System.out.println("Annonce ajouté au Panier ");
        } catch (SQLException ex) {
            
            
           System.out.println(ex.getMessage());  
        }
    }
    

    @Override
    public void SupprimerDuPanier(int id) {
        String req = "delete from annonce_panier where id =?";
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
    public Annonce RecupererAnnonce(int id) {
        
        String req = "SELECT * FROM `annonce` WHERE `id`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            Annonce a = new Annonce();
            while(rs.next())
            {
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

    @Override
    public int LonguerPanier() {
        int count=0;
     String req = "select count(*) from `annonce_panier`";
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
    public int existAnnonce(int id) {
        String req = "SELECT * FROM `annonce_panier` WHERE `id_annonce`='"+id+"' ";
        try {
            pt = cnx.prepareStatement(req);
            ResultSet res = pt.executeQuery();
           if ( res.absolute (1)) 
           { 
               
System.out.print (" L'objet ResultSet n'est pas vide "); 
return 1;
}
           else { 
System.out.print (" L'objet ResultSet est vide " ) ; 
return 0;
           } 
              
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public ObservableList<AnnoncePanier> getall() {
    ObservableList<AnnoncePanier> annoncesp = FXCollections.observableArrayList();
       String req = "SELECT * FROM `annonce_panier` ";
        try {
            pt = cnx.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next())
            {   
               AnnoncePanier a = new AnnoncePanier();
               a.setId(rs.getInt(1));
               a.setId_annonce(rs.getString(2));
               
               a.setTitre(rs.getString(3));
               a.setDescription(rs.getString(4));
               
               a.setPrix(rs.getDouble(5));
               a.setRegion(rs.getString(6));
               
               a.setPhoto(rs.getString(7));
               
               annoncesp.add(a);   
            }
             System.out.println("recup ok ");
            return annoncesp;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    

}
    