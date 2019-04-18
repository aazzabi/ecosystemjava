/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.reparateur.AnnounceRep;
import entities.reparateur.DemandeComptePro;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnectionBase;

/**
 *
 * @author actar
 */
public class DemandeCompteProService {

    public static ObservableList<DemandeComptePro> getDemandeCompteList() {

        ObservableList<DemandeComptePro> demandeList = FXCollections.observableArrayList();
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        try {
            String request = "select * from demeande_c";
            pt = cn.prepareStatement(request);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()) {
                DemandeComptePro demande = new DemandeComptePro();
                demande.setId(resultSet.getInt("id"));
                demande.setStatut(resultSet.getString("statut"));
                demande.setDateDemande(resultSet.getDate("DateDeLaDemande").toString());
                demande.setUrlPhoto(resultSet.getString("photo"));
                demande.setRepId(resultSet.getInt("reparateur_id"));

                // get Nom réparateur + nom utilisateur
                request = "select nom from user where id ='" + resultSet.getInt("reparateur_id") + "'";
                pt = cn.prepareStatement(request);
                ResultSet resultSet1 = pt.executeQuery();
                while (resultSet1.next()) {
                    demande.setNomRep(resultSet1.getString("nom"));
                }

                demandeList.add(demande);
            }
            // cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandeList;
    }
    
    
    
   public static void supprimer(int id) {

        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;

        try {
            String request = "DELETE FROM `demeande_c` WHERE id ='" + id + "'";
            pt = cn.prepareStatement(request);
            int res = pt.executeUpdate();
            System.err.println(res);

            //cn.close();
        } catch (Exception exp) {
            System.out.println(exp.getMessage());

        }

    }
   
    public static  void add(DemandeComptePro a) {
            Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        

    

        String req = "INSERT INTO `demeande_c` (reparateur_id, DateDeLaDemande, statut, photo, photo_updated_at) VALUES (?,?,?,?,?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setInt(1, a.getRepId());
            pt.setDate(2,Date.valueOf(LocalDate.now()));
            pt.setString(3, a.getStatut());
            pt.setString(4,a.getUrlPhoto());
            pt.setDate(5, Date.valueOf(LocalDate.now()));   
            pt.executeUpdate();
            System.out.println("ajout établie");

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
