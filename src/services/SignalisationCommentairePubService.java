/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CommentairePublication;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.ConnectionBase;

/**
 *
 * @author arafe
 */
public class SignalisationCommentairePubService {

    /**
     *
     * @param u
     * @param comm
     * @param libelle
     */
    public static void add(int userId , int commId, String libelle) {
        String requete = "INSERT INTO signalisationforumcomm"
                + "(`signaled_by_id`, `commentaire_id`, `libelle`) "
                + "VALUES (?,?,?)";
        Connection cn = ConnectionBase.getInstance().getCnx();
         System.out.println(requete);
        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, userId);
            pst.setInt(2, commId);
            pst.setString(3, libelle);
           
            pst.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
