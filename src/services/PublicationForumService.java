/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategoriePub;
import entities.CommentairePublication;
import entities.PublicationForum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionBase;

/**
 *
 * @author arafe
 */
public class PublicationForumService {
    
    public static List<PublicationForum> getAllPublications()
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select p.id, p.titre, p.description, p.etat, u.username, c.libelle, p.pub_created_at, p.nbrVues "
                + "from publication_forum p, user u, categorie_pub c "
                + "where c.id = p.categorie_id AND u.id=p.publication_created_by_id";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setId(rs.getInt("p.id"));
                p.setTitre(rs.getString("p.titre"));
                p.setDescription(rs.getString("p.description"));
                p.setEtat(rs.getString("p.etat"));
                p.setCategorie(rs.getString("c.libelle"));
                p.setCreatedByName(rs.getString("u.username"));
                p.setCreatedAt(rs.getDate("p.pub_created_at"));
                p.setNbrVues(rs.getInt("p.nbrVues"));
                pList.add(p);
            }
            System.out.println("Okey ");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
    
    public static void deletePublication(int p)
    {
        String requete = "DELETE FROM publication_forum WHERE id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("deleted"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static List<PublicationForum> getAllPublicationsByCategorie(int id)
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select p.titre, p.description, p.etat, u.username, c.libelle, p.pub_created_at, p.nbrVues "
                + "from publication_forum p, user u, categorie_pub c "
                + "WHERE c.id=? AND c.id = p.categorie_id AND u.id=p.publication_created_by_id";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setTitre(rs.getString("p.titre"));
                p.setDescription(rs.getString("p.description"));
                p.setEtat(rs.getString("p.etat"));
                p.setCategorie(rs.getString("c.libelle"));
                p.setCreatedByName(rs.getString("u.username"));
                p.setCreatedAt(rs.getDate("p.pub_created_at"));
                p.setNbrVues(rs.getInt("p.nbrVues"));
                pList.add(p);
            }
            System.out.println("Okey ");
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
    
    public static void archiverPublication(int p)
    {
        String requete = "UPDATE publication_forum SET etat= 'publié' WHERE id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("archivé"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static PublicationForum getPublicationById(int id) {
        String requete = "Select p.id, p.titre, p.description, p.etat, u.username, c.libelle, p.pub_created_at, p.nbrVues, "
                + "(select count(*) from commentaire_publication cp, publication_forum pub where cp.publication_id = pub.id and pub.id=? ) as nbrCommentaire "
                + "from  publication_forum p, user u, categorie_pub c "
                + "WHERE p.id=? AND c.id = p.categorie_id AND u.id=p.publication_created_by_id";
        Connection cn = ConnectionBase.getInstance().getCnx();
        PublicationForum p = new PublicationForum();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setInt(2, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                p.setId(rs.getInt("p.id"));
                p.setTitre(rs.getString("p.titre"));
                p.setDescription(rs.getString("p.description"));
                p.setEtat(rs.getString("p.etat"));
                p.setCategorie(rs.getString("c.libelle"));
                p.setCreatedByName(rs.getString("u.username"));
                p.setCreatedAt(rs.getDate("p.pub_created_at"));
                p.setNbrVues(rs.getInt("p.nbrVues"));
                p.setNbrCommentaires(rs.getInt("nbrCommentaire"));
            }
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return p;
    }
    
    public static List<CommentairePublication> getAllCommentairesByPublication(int id) {
        List<CommentairePublication> pList = new ArrayList();
        String requete = "Select  u.username , c.description, c.likes, c.dislikes, c.photo,  c.commented_at, c.nbSignalisation "
                + "from commentaire_publication c, user u, publication_forum p "
                + " where c.publication_id=? and c.publication_id=p.id AND u.id =c.commented_by_id ;";
        Connection cn = ConnectionBase.getInstance().getCnx();
        System.out.println(requete);
        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
             ResultSet rs = pst.executeQuery();
            while (rs.next()){
                CommentairePublication c = new CommentairePublication();
                
                c.setDescription(rs.getString("c.description"));
                c.setCreatedByName(rs.getString("u.username"));
                c.setCreatedAt(rs.getDate("c.commented_at"));
//                c.setPhoto(rs.getString("c.photo"));
                c.setPhoto(rs.getString("c.photo"));
                c.setNbSignalisation(rs.getInt("c.nbSignalisation"));
                System.out.println(c.getPhoto());
                c.setDislikes(rs.getInt("c.dislikes"));
                c.setLikes(rs.getInt("c.likes"));
                pList.add(c);
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
