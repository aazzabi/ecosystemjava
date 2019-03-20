/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Iservices.IUserService;
import entites.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String sql = "INSERT INTO user(username, username_canonical, email, email_canonical, enabled, password, roles, nom, prenom, discr) VALUES(?,?,?,?,?,?,?,?,?,?)";
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
            pt.setString(10, "user");
            status = pt.executeUpdate();
            System.out.println("succée");
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

}
