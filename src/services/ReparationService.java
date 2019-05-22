/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.reparateur.AnnounceRep;
import entities.reparateur.Reparation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnectionBase;

/**
 *
 * @author actar
 */
public class ReparationService {

    public static ObservableList<Reparation> getReparationList() {
        ObservableList<Reparation> reparationList = FXCollections.observableArrayList();
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        try {
            String request = "select * from reparation";
            pt = cn.prepareStatement(request);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()) {
                Reparation rep = new Reparation();
                rep.setId(resultSet.getInt("id"));
                rep.setCommentaire(resultSet.getString("Commentaire"));
                rep.setStatut(resultSet.getString("statut"));
                rep.setRepId(resultSet.getInt("reparateur_id"));
                rep.setUserId(resultSet.getInt("utilisateur_id"));
                rep.setDateP(resultSet.getDate("DatePrisEnCharge").toLocalDate().toString());

                // get Nom réparateur + nom utilisateur
                request = "select nom from user where id ='" + resultSet.getInt("reparateur_id") + "'";
                pt = cn.prepareStatement(request);
                ResultSet resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    rep.setNomRep(resultSet1.getString("nom"));
                }
                request = "select nom from user where id ='" + resultSet.getInt("utilisateur_id") + "'";
                pt = cn.prepareStatement(request);
                resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    rep.setNomUser(resultSet1.getString("nom"));
                }
                reparationList.add(rep);
            }
            // cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reparationList;

    }

    public static Reparation getReparation(int id) {
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        Reparation rep = new Reparation();
        try {
            String request = "SELECT * FROM `reparation` WHERE id ='" + id + "'";
            pt = cn.prepareStatement(request);
            ResultSet resultSet = pt.executeQuery();

            while (resultSet.next()) {

                rep.setId(resultSet.getInt("id"));
                rep.setCommentaire(resultSet.getString("Commentaire"));
                rep.setStatut(resultSet.getString("statut"));
                rep.setRepId(resultSet.getInt("reparateur_id"));
                rep.setUserId(resultSet.getInt("utilisateur_id"));
                rep.setDateP(resultSet.getDate("DatePrisEnCharge").toLocalDate().toString());

                // get Nom réparateur + nom utilisateur
                request = "select nom from user where id ='" + resultSet.getInt("reparateur_id") + "'";
                pt = cn.prepareStatement(request);
                ResultSet resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    rep.setNomRep(resultSet1.getString("nom"));
                }
                request = "select nom from user where id ='" + resultSet.getInt("utilisateur_id") + "'";
                pt = cn.prepareStatement(request);
                resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    rep.setNomUser(resultSet1.getString("nom"));
                }

            }

            // cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rep;
    }
    
    
    public static void edit(Reparation rep) {

        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        try {

            PreparedStatement ps = cn.prepareStatement(
                    "UPDATE reparation SET statut = ? , Commentaire=?, utilisateur_id = ?, reparateur_id = ? WHERE id = ?");

            ps.setString(1, rep.getStatut());
            ps.setString(2, rep.getCommentaire());   
            ps.setInt(3, rep.getUserId());
            ps.setInt(4, rep.getRepId());
            ps.setInt(5, rep.getId());
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException se) {
           System.out.println(se.getMessage());

        }

    }

    public static void supprimer(int id) {

        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        try {
            String request = "DELETE FROM `reparation` WHERE id ='" + id + "'";
            pt = cn.prepareStatement(request);
            int res = pt.executeUpdate();
            System.err.println(res);

            //cn.close();
        } catch (Exception exp) {
            System.out.println(exp.getMessage());

        }

    }
    
    public static  void add(Reparation a) {
            Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        

    

        String req = "INSERT INTO `reparation` (DatePrisEnCharge, Commentaire, utilisateur_id, reparateur_id, statut) VALUES (?,?,?,?,?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setDate(1, Date.valueOf(LocalDate.now()));
            pt.setString(2, a.getCommentaire());
            pt.setInt(3, a.getUserId());
            pt.setInt(4, a.getRepId());
            pt.setString(5, a.getStatut());    
            pt.executeUpdate();
            System.out.println("ajout établie");

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
