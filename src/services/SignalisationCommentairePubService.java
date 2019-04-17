/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CommentairePublication;
import entities.PublicationForum;
import entities.SignalisationCommentaire;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public static void deleteCommentaireBySingalisationId(int p)
    {
        String req = "DELETE FROM commentaire_publication WHERE id IN (SELECT sig.commentaire_id from signalisationforumcomm sig WHERE sig.id=?)";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("deleted comm :"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static void deleteUserBySingalisationId(int p)
    {
        String req = "DELETE FROM user WHERE id IN (SELECT c.commented_by_id from commentaire_publication c, signalisationforumcomm sig WHERE sig.id=? and sig.commentaire_id = c.id)";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("deleted comm :"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static List<SignalisationCommentaire> getAllSignalisation()
    {
        List<SignalisationCommentaire> pList = new ArrayList();
        String requete = "SELECT sign.id , u.nom , c.nom , comm.description, pub.titre , sign.libelle " +
            "FROM user c, commentaire_publication comm, publication_forum pub, signalisationforumcomm sign, user u " +
            "WHERE u.id = sign.signaled_by_id " +
            "and c.id = comm.commented_by_id " +
            "and pub.id = comm.publication_id " +
            "and sign.commentaire_id = comm.id ";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                SignalisationCommentaire s = new SignalisationCommentaire();
                  
                s.setId(rs.getInt("sign.id"));
                s.setCommEcritPar(rs.getString("c.nom"));
                s.setCommSignaleePar(rs.getString("u.nom"));
                s.setCommentaireLibelle(rs.getString("comm.description"));
                s.setPublicationLibelle(rs.getString("pub.titre"));
                s.setCause(rs.getString("sign.libelle"));
                s.toString();
                pList.add(s);
            }
            System.out.println("Okey ");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
}
