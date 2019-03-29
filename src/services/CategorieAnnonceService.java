/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Annonce;
import entities.Categorie_Annonce;
import iservices.ICategorieAnnonceService;
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
public class CategorieAnnonceService implements ICategorieAnnonceService {

    Connection cn = ConnectionBase.getInstance().getCnx();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<Categorie_Annonce> categories = new ArrayList<Categorie_Annonce>();

    @Override
    public void add(Categorie_Annonce c) {
        String req = "INSERT INTO categorie_annonce (libelle) VALUES (?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setString(1, c.getLibelle());
            pt.executeUpdate();
            System.out.println("ajout etablie");

        } catch (SQLException ex) {
            Logger.getLogger(CategorieAnnonceService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void delete(int id) {
        try {
            String req = "delete from categorie_annonce where id=?";
            pt = cn.prepareStatement(req);
            pt.setInt(1, id);
            pt.executeUpdate();
            System.out.println("Categorie  supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(Categorie_Annonce a, int id) {
        try {
            String req = "update categorie_annonce SET libelle=? WHERE id=" + id;
            pt = cn.prepareStatement(req);
            pt.setString(1, a.getLibelle());
            if (pt.executeUpdate() > 0) {
                System.out.println("update avce sucée");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Categorie_Annonce> getall() {
        String req = "select * from categorie_annonce";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {
                Categorie_Annonce a = new Categorie_Annonce();
                a.setId(rs.getInt(1));
                a.setLibelle(rs.getString(2));
                categories.add(a);
            }
            System.out.println("affichage etablie");
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Categorie_Annonce displayById(int id) {
        String req = "select libelle from categorie_annonce where id=?";
        Categorie_Annonce cA=new Categorie_Annonce();
        try {
            pt = cn.prepareStatement(req);
            pt.setInt(1, id);
            rs = pt.executeQuery();
            while(rs.next())
            {cA.setId(rs.getInt(1));
                cA.setLibelle(rs.getString(1));
            }
            return cA;
        } catch (SQLException e) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public Categorie_Annonce displayByName(int id) {
            String req = "select * from categorie_annonce where id=?";
        Categorie_Annonce cA=new Categorie_Annonce();
        try {
            pt = cn.prepareStatement(req);
            pt.setInt(1, id);
            rs = pt.executeQuery();
            while(rs.next())
            {
                cA.setId(rs.getInt(1));
                cA.setLibelle(rs.getString(2));
            }
            return cA;
        } catch (SQLException e) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
