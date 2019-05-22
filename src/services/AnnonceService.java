/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Annonce;
import entities.Session;
import iservices.IAnnonceService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import utils.ConnectionBase;
import service.panier.LigneCommandeService;
import iservices.panier.ILigneCommandeService;

/**
 *
 * @author anasc
 */
public class AnnonceService implements IAnnonceService {
private ILigneCommandeService lignecommandeService;
    Connection cn = ConnectionBase.getInstance().getCnx();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    ArrayList<Annonce> retour = new ArrayList<Annonce>();
    ArrayList<Integer> Stat = new ArrayList<Integer>();

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
            if (pt.executeUpdate() > 0) {
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
         lignecommandeService = new LigneCommandeService();
        String req = "SELECT a.* , c.libelle , Concat(u.nom,\" \",u.prenom) from annonce a, categorie_annonce c, user u WHERE a.categorie_id = c.id AND a.user_id= u.id";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {
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
                if(lignecommandeService.VerifAnnonce(a.getId())==0)
                {
                 annonces.add(a);
                }
               
            }
            System.out.println("affichage etablie");
            return annonces;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Annonce getAnnonceById(int id) {

        retour = (ArrayList<Annonce>) getall();

        return retour.stream().filter(e -> e.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Annonce> trierParDate() {
        retour.removeAll(retour);
        retour = (ArrayList<Annonce>) getall();
        return retour.stream().sorted((a, b) -> b.getDate_creation().compareTo(a.getDate_creation())).collect(Collectors.toList());
    }

    @Override
    public List<Annonce> trierParPrixASC() {
        retour.removeAll(retour);
        retour = (ArrayList<Annonce>) getall();
        return retour.stream().sorted((a, b) -> a.getPrix().compareTo(b.getPrix())).collect(Collectors.toList());
    }

    @Override
    public List<Annonce> trierParPrixDESC() {
        retour.removeAll(retour);
        retour = (ArrayList<Annonce>) getall();
        return retour.stream().sorted((a, b) -> b.getPrix().compareTo(a.getPrix())).collect(Collectors.toList());

    }

    @Override
    public List<Annonce> GetByUser() {
        retour.removeAll(retour);
        retour = (ArrayList<Annonce>) getall();
        return retour.stream().filter(e -> e.getUser_id() == Session.getCurrentSession()).collect(Collectors.toList());
    }

    @Override
    public List<Annonce> GetByCategorie(int id) {
        retour.removeAll(retour);
        retour = (ArrayList<Annonce>) getall();
        return retour.stream().filter(e -> e.getCategorie_id() == id).collect(Collectors.toList());
    }

    @Override
    public boolean updateLikes(int id) {
        try {
            String requete = "UPDATE annonce SET likes= likes+1 WHERE id=" + id;

            pt = cn.prepareStatement(requete);
            if (pt.executeUpdate() > 0) {
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
    public boolean updateViwes(int id) {
        try {
            String requete = "UPDATE annonce SET views= views+1 WHERE id=" + id;

            pt = cn.prepareStatement(requete);
            if (pt.executeUpdate() > 0) {
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
    public List<Annonce> GetMostLikes() {
        retour.removeAll(retour);
        retour = (ArrayList<Annonce>) getall();
        return retour.stream().filter(e -> e.getLikes() >= 5).collect(Collectors.toList());
    }

    @Override
    public List<Annonce> GetMostViwed() {
        retour.removeAll(retour);
        retour = (ArrayList<Annonce>) getall();
        return retour.stream().filter(e -> e.getViews() >= 5).collect(Collectors.toList());
    }

    @Override
    public List<Integer> Stat() {
        String req = "Select year(date_creation),month(date_creation),count(*) from annonce group by year(date_creation),month(date_creation)";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {

                Stat.add(rs.getInt(3));
            }

            return Stat;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public List<Annonce> StatByCat() {

        String req = "SELECT c.libelle,COUNT(p.id) FROM annonce p, categorie_annonce c WHERE p.categorie_id = c.id GROUP BY categorie_id";
        try {
            ArrayList<Annonce> aa = new ArrayList<Annonce>();
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {

                Annonce a = new Annonce();
                a.setNomCat(rs.getString(1));
                a.setNb_cat(rs.getInt(2));
                aa.add(a);
            }

            return aa;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Annonce> StatByMyAnnonces() {

        String req = "SELECT c.libelle,count(a.id) from annonce a , categorie_annonce c  where c.id =a.categorie_id AND likes >=5 AND views >=5 group by categorie_id";
        try {
            ArrayList<Annonce> aa = new ArrayList<Annonce>();
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {

                Annonce a = new Annonce();
                a.setNomCat(rs.getString(1));
                a.setNb_cat(rs.getInt(2));
                aa.add(a);
            }

            return aa;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   
}
