/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reparateur;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import utils.ConnectionBase;

/**
 *
 * @author anasc
 */
public class UserService {

    public static int Inscription(Utilisateur u) {
        int workload = 13;
        int status = 0;
        PreparedStatement pt;
        String sql = "INSERT INTO user(username, username_canonical, email, email_canonical, "
                + "enabled, password, roles, nom, prenom,"
                + " photo, photo_updated_at, ville, rue, nom_propriete, numtel, discr ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        try {
            Connection cn = ConnectionBase.getInstance().getCnx();
            pt = cn.prepareStatement(sql);
            pt.setString(1, u.getUsername());
            pt.setString(2, u.getUsername());
            pt.setString(3, u.getEmail());
            pt.setString(4, u.getEmail());
            pt.setInt(5, 1);
            String mdp = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(workload));
            pt.setString(6, mdp.replaceFirst("2a", "2y"));
            pt.setString(7, "a:0:{}");
            pt.setString(8, u.getNom());
            pt.setString(9, u.getPrenom());
            pt.setString(10, u.getPhoto());
            pt.setDate(11, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pt.setString(12, u.getVille());
            pt.setString(13, u.getRue());
            pt.setString(14, u.getNomPropriete());
            pt.setString(15, u.getNumtel());
            pt.setString(16, u.getDiscr());

            status = pt.executeUpdate();
            System.out.println("succée");
            cn.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return status;
    }

    public static int InscriptionReparateur(Reparateur r) {
        int workload = 13;
        int status = 0;
        int statusRep = 0;

        PreparedStatement pt, ptRep;
        String sql = "INSERT INTO user(username, username_canonical, email, email_canonical, enabled,"
                + " password, roles, nom, prenom, photo,"
                + " photo_updated_at, ville, rue, nom_propriete, numtel, "
                + "discr ) "
                + "VALUES(?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?)";
        String sqlRep = "INSERT INTO reparateur(adresse,numerotel, numerofix, specialite, horaire, type, description) VALUES (?,?,?,?,?,?,?)";
        System.out.println(sql);
        System.out.println(sqlRep);
        try {
            Connection cn = ConnectionBase.getInstance().getCnx();
            pt = cn.prepareStatement(sql);
            pt.setString(1, r.getUsername());
            pt.setString(2, r.getUsername());
            pt.setString(3, r.getEmail());
            pt.setString(4, r.getEmail());
            pt.setInt(5, 1);
            String mdp = BCrypt.hashpw(r.getPassword(), BCrypt.gensalt(workload));
            pt.setString(6, mdp.replaceFirst("2a", "2y"));
            pt.setString(7, "a:1:{i:0;s:15:\"ROLE_REPARATEUR\";}");
            pt.setString(8, r.getNom());
            pt.setString(9, r.getPrenom());
            pt.setString(10, r.getPhoto());
            pt.setDate(11, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pt.setString(12, r.getVille());
            pt.setString(13, r.getRue());
            pt.setString(14, r.getNomPropriete());
            pt.setString(15, r.getNumtel());
            pt.setString(16, r.getDiscr());

            ptRep = cn.prepareStatement(sqlRep);
            ptRep.setString(1, r.getAdresse());
            ptRep.setString(2, r.getNumerotel());
            ptRep.setString(3, r.getNumerofix());
            ptRep.setString(4, r.getSpecialite());
            ptRep.setString(5, r.getHoraire());
            ptRep.setString(6, r.getType());
            ptRep.setString(7, r.getDescription());

            status = pt.executeUpdate();
            System.out.println("succée part 1");
            statusRep = ptRep.executeUpdate();
            System.out.println("succée part 2");
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean testMotDePasse(String motDePasseGUI, String motDePasseBD) {
        boolean password_verified = false;

        if (null == motDePasseBD) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        // en remplaçant 2y par 2a le cryptage on obtient le cryptage par defaut pour que la methode checkpw puisse comparer
        password_verified = BCrypt.checkpw(motDePasseGUI, motDePasseBD.replaceFirst("2y", "2a"));

        return (password_verified);
    }

    public static List<Utilisateur> getTtUtilisateur() {
        List<Utilisateur> list = new ArrayList<Utilisateur>();
        Connection cn = ConnectionBase.getInstance().getCnx();
        PreparedStatement pt;
        try {
            String sql = "select * from user ";
            pt = cn.prepareStatement(sql);
            ResultSet resultSet = pt.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();

                utilisateur.setUsername(resultSet.getString("username"));
                utilisateur.setPassword(resultSet.getString("password"));
                utilisateur.setRoles(resultSet.getString("roles"));
                list.add(utilisateur);
            }
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    public Utilisateur login(Utilisateur u) {

        try {
            Connection cn = ConnectionBase.getInstance().getCnx();
            String loginQry = "SELECT * FROM user WHERE username = ? ";
            PreparedStatement ste = cn.prepareStatement(loginQry);
            ste.setString(1, u.getUsername());
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                u.setRoles(rs.getString("roles"));
                u.setPassword(rs.getString("password"));
                u.setId(rs.getInt(1));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  Integer getIdRep(String nom) {

        try {
            Connection cn = ConnectionBase.getInstance().getCnx();
            String loginQry = "SELECT * FROM user WHERE nom = ? ";
            PreparedStatement ste = cn.prepareStatement(loginQry);
            ste.setString(1, nom);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
            int id = rs.getInt("id");
             return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
