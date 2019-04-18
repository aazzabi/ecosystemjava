/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import entities.reparateur.AnnounceRep;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnectionBase;

/**
 *
 * @author actar
 */
public class AnnounceRepService {

    public static ObservableList<AnnounceRep> getAnnounceRepList() {
        ObservableList<AnnounceRep> announceList = FXCollections.observableArrayList();
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        try {
            String request = "select * from annonce_rep";
            pt = cn.prepareStatement(request);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()) {
                AnnounceRep announce = new AnnounceRep();
                announce.setId(resultSet.getInt("id"));
                announce.setTitre(resultSet.getString("titre"));
                announce.setDescription(resultSet.getString("description"));
                announce.setUrlPhoto(resultSet.getString("photo"));
                announce.setRepId(resultSet.getInt("reparateur_id"));
                announce.setUserId(resultSet.getInt("utilisateur_id"));
                announce.setEtat(resultSet.getString("etat"));
                announce.setCategorie(resultSet.getString("categorie"));
                announce.setPrix(resultSet.getFloat("lastprix"));
                announce.setDatePub(resultSet.getDate("datepublication").toLocalDate().toString());

                // get Nom réparateur + nom utilisateur
                request = "select nom from user where id ='" + resultSet.getInt("reparateur_id") + "'";
                pt = cn.prepareStatement(request);
                ResultSet resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    announce.setNomRep(resultSet1.getString("nom"));
                }
                request = "select nom from user where id ='" + resultSet.getInt("utilisateur_id") + "'";
                pt = cn.prepareStatement(request);
                resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    announce.setNomUser(resultSet1.getString("nom"));
                }
                announceList.add(announce);
            }
            // cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announceList;
    }
    
        public static AnnounceRep getAnnounceRep(int id) {
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
 AnnounceRep announce = new AnnounceRep();
        try {
            String request = "SELECT * FROM `annonce_rep` WHERE id ='" + id + "'";
            pt = cn.prepareStatement(request);
            ResultSet resultSet = pt.executeQuery();
              
            while (resultSet.next()) {
            
                announce.setId(resultSet.getInt("id"));
                announce.setTitre(resultSet.getString("titre"));
                announce.setDescription(resultSet.getString("description"));
                announce.setUrlPhoto(resultSet.getString("photo"));
                announce.setRepId(resultSet.getInt("reparateur_id"));
                announce.setUserId(resultSet.getInt("utilisateur_id"));
                announce.setEtat(resultSet.getString("etat"));
                announce.setCategorie(resultSet.getString("categorie"));
                announce.setPrix(resultSet.getFloat("lastprix"));
                announce.setDatePub(resultSet.getDate("datepublication").toLocalDate().toString());

                // get Nom réparateur + nom utilisateur
                request = "select nom from user where id ='" + resultSet.getInt("reparateur_id") + "'";
                pt = cn.prepareStatement(request);
                ResultSet resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    announce.setNomRep(resultSet1.getString("nom"));
                }
                request = "select nom from user where id ='" + resultSet.getInt("utilisateur_id") + "'";
                pt = cn.prepareStatement(request);
                resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    announce.setNomUser(resultSet1.getString("nom"));
                }
                
            }
            
            // cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announce;
    }

    public static void supprimer(int id) {

        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        String req = "delete from annonce_rep where id =?";
        try {
            pt = cn.prepareStatement(req);
            pt.setInt(1, id);
            pt.executeUpdate();
          System.out.println("Suppression terminé avec succes ");
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("not ok");
        }

    }

    public static ObservableList<String> getAllReparateur() {
        ObservableList<String> replist = FXCollections.observableArrayList();
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        String request = "select nom from user where roles ='a:1:{i:0;s:15:\"ROLE_REPARATEUR\";}'";
        try {
            pt = cn.prepareStatement(request);
            ResultSet resultSet1 = pt.executeQuery();
            while (resultSet1.next()) {
                replist.add(resultSet1.getString("nom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnounceRepService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return replist;

    }

    public static void edit(AnnounceRep ann) {

        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        try {

            PreparedStatement ps = cn.prepareStatement(
                    "UPDATE annonce_rep SET titre = ? , description=?, lastprix = ?, categorie = ?, reparateur_id = ?, etat = ? WHERE id = ?");

            ps.setString(1, ann.getTitre());
            ps.setString(2, ann.getDescription());
            ps.setFloat(3, ann.getPrix());
            ps.setString(4, ann.getCategorie());
            ps.setInt(5, ann.getRepId());
            ps.setString(6, ann.getEtat());
            ps.setInt(7, ann.getId());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }

    }
    
      public static int getNombreAnn(String cat) {
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        int rowCount=0;
        try {
            String request = "SELECT COUNT(*) FROM `annonce_rep` WHERE categorie ='" + cat + "'";
            pt = cn.prepareStatement(request);
            ResultSet resultSet = pt.executeQuery();
              
            while (resultSet.next()) {
            
                     rowCount = Integer.parseInt(resultSet.getString("count(*)"));
            System.out.println(Integer.parseInt(resultSet.getString("count(*)")));
            return rowCount;
                
            }
            
            // cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return rowCount;
    }
      
      
      
      
      public static  void add(AnnounceRep a) {
            Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        

    

        String req = "INSERT INTO annonce_rep (utilisateur_id, titre, description, datepublication, datemodification,photo, photo_updated_at, categorie, etat) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setInt(1, a.getUserId());
            pt.setString(2, a.getTitre());
            pt.setString(3, a.getDescription());
            pt.setDate(4, Date.valueOf(LocalDate.now()));
            pt.setDate(5, Date.valueOf(LocalDate.now()));
            pt.setString(6, a.getUrlPhoto());
            pt.setDate(7,Date.valueOf(LocalDate.now()) );
            pt.setString(8, a.getCategorie());
            pt.setString(9, a.getEtat());        
            pt.executeUpdate();
            System.out.println("ajout établie");

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
