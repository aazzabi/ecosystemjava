/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Annonce;
import iservices.IAnnonceService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionBase;

/**
 *
 * @author anasc
 */
public class AnnonceService implements IAnnonceService {

    Connection cn = ConnectionBase.getInstance().getCnx();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<Annonce> annonces = new ArrayList<Annonce>();

    @Override
    public void add(Annonce a) {
        String req = "INSERT INTO annonce (categorie_id, user_id, titre, description, date_creation, date_update, prix, region, etat, photo, photo_updated_at, likes, views) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setInt(1, a.getCategorie_id());
            pt.setInt(2, a.getUser_id());
            pt.setString(3, a.getTitre());
            pt.setString(4, a.getDescription());
            pt.setDate(5, a.getDate_creation());
            pt.setDate(6, a.getDate_update());
            pt.setDouble(7, a.getPrix());
            pt.setString(8, a.getRegion());
            pt.setString(9, a.getEtat());
            pt.setString(10, a.getPhoto());
            pt.setDate(11, a.getPhoto_updated_at());
            pt.setInt(12, a.getLikes());
            pt.setInt(13, a.getViews());
            pt.executeUpdate();
            System.out.println("ajout etablie");

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void delete(int id) {
        try {
            String req = "delete from annonce where id=?";
            pt = cn.prepareStatement(req);
            pt.setInt(1, id);
            pt.executeUpdate();
            System.out.println("Categorie  supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(Annonce a, int id) {
        try {
            String req = "update annonce SET categorie_id=?,titre=?, description =?,date_update =?,prix=?, region=?, photo=?,photo_updated_at=? WHERE id=" + id;
            pt = cn.prepareStatement(req);
            pt.setInt(1, a.getCategorie_id());
            pt.setString(2, a.getTitre());
            pt.setString(3, a.getDescription());
            pt.setDate(4, Date.valueOf(LocalDate.now()));
            pt.setDouble(5, a.getPrix());
            pt.setString(6, a.getRegion());
            pt.setString(7, a.getPhoto());
            pt.setDate(8, Date.valueOf(LocalDate.now()));
           if(pt.executeUpdate()>0)
           {
               System.out.println("update avce sucée");
               return true;
           }
            
            System.out.println("Categorie  modifiée !!");
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
        public List<Annonce> getall() {
           String req = "SELECT a.* , c.libelle , Concat(u.nom,\" \",u.prenom) from annonce a, categorie_annonce c, user u WHERE a.categorie_id = c.id AND a.user_id= u.id";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next())
            {   
               Annonce a = new Annonce();
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
               a.setLib(rs.getString(15));
               a.setNomPrenom(rs.getString(16));
               annonces.add(a);   
            }
             System.out.println("affichage etablie");
            return annonces;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
           
    }

   
}
