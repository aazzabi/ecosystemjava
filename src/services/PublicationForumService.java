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
    
    public static void add(PublicationForum c) {
        String requete = "INSERT INTO publication_forum"
                + "(`categorie_id`, `publication_created_by_id`, `titre`, `description`, `etat`, `pub_created_at`, `nbrVues`) "
                + "VALUES (?,?,?,?,?,?,?)";
        Connection cn = ConnectionBase.getInstance().getCnx();
         System.out.println(requete);
        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, c.getCategorieId());
            pst.setInt(2, c.getCreatedBy());
            pst.setString(3, c.getTitre());
            pst.setString(4, c.getDescription());
            pst.setString(5, c.getEtat());
            pst.setDate(6, c.getCreatedAt());
            pst.setInt(7, c.getNbrVues());
           
            pst.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    public static List<PublicationForum> getAllPublications()
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select p.id, p.titre, p.description, p.etat, u.username, c.libelle, p.pub_created_at, p.nbrVues, "
                + "(select count(*) from commentaire_publication cp where cp.publication_id = p.id  ) as nbrCommentaire "
                + "from publication_forum p, user u, categorie_pub c "
                + "where c.id = p.categorie_id AND u.id=p.publication_created_by_id";
//                + "ORDER BY p.pub_created_at DESC";
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
                p.setNbrVues(rs.getInt("p.nbrVues"));
                p.setNbrCommentaires(rs.getInt("nbrCommentaire"));
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
    
    public static List<PublicationForum> getAllPublicationsByUserId(int id)
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select p.id, p.titre, p.description, p.etat, u.username, c.libelle, p.pub_created_at, p.nbrVues "
                + "from publication_forum p, user u, categorie_pub c "
                + "where u.id= ? and c.id = p.categorie_id AND u.id=p.publication_created_by_id";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setId(rs.getInt("p.id"));
                p.setTitre(rs.getString("p.titre"));
                p.setDescription(rs.getString("p.description"));
                p.setEtat(rs.getString("p.etat"));
                p.setCategorie(rs.getString("c.libelle"));
                p.setCreatedByName(rs.getString("u.username"));
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
    
    public static List<PublicationForum> recherchePublicationsKeyWord(String text)
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select p.id, p.titre, p.description, p.etat, u.username, c.libelle, p.pub_created_at, p.nbrVues "
                + "from publication_forum p, user u, categorie_pub c "
                + "where (p.titre LIKE ? or p.description LIKE ? or c.libelle LIKE ? )"
                + "and c.id = p.categorie_id AND u.id=p.publication_created_by_id";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, "%"+text+"%");
            pst.setString(2, "%"+text+"%");
            pst.setString(3, "%"+text+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setId(rs.getInt("p.id"));
                p.setTitre(rs.getString("p.titre"));
                p.setDescription(rs.getString("p.description"));
                p.setEtat(rs.getString("p.etat"));
                p.setCategorie(rs.getString("c.libelle"));
                p.setCreatedByName(rs.getString("u.username"));
                p.setNbrVues(rs.getInt("p.nbrVues"));
                pList.add(p);
            }
        } 
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        return pList;
    }
    
    public static void vu(int p)
    {
        String req = "UPDATE publication_forum SET nbrVues = nbrVues+1 where id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, p);
            pst.executeUpdate();
            pst.close();
            System.out.println("vu :"+ p);
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static List<PublicationForum> rechercherMyPublicationsKeyWord(int id, String text){
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select p.id, p.titre, p.description, p.etat, u.username, c.libelle, p.pub_created_at, p.nbrVues "
                + "from publication_forum p, user u, categorie_pub c "
                + "where u.id= ? and (p.titre LIKE ? or p.description LIKE ? or c.libelle LIKE ? )"
                + "and c.id = p.categorie_id AND u.id=p.publication_created_by_id";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setString(2, "%"+text+"%");
            pst.setString(3, "%"+text+"%");
            pst.setString(4, "%"+text+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setId(rs.getInt("p.id"));
                p.setTitre(rs.getString("p.titre"));
                p.setDescription(rs.getString("p.description"));
                p.setEtat(rs.getString("p.etat"));
                p.setCategorie(rs.getString("c.libelle"));
                p.setCreatedByName(rs.getString("u.username"));
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
        String requete = "UPDATE publication_forum SET etat= 'archivé' WHERE id=?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, p);
            pst.executeUpdate();
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
        String requete = "Select  c.id, u.id , u.username , c.description, c.likes, c.dislikes, c.photo,  c.commented_at, c.nbSignalisation "
                + "from commentaire_publication c, user u, publication_forum p "
                + " where c.publication_id=? and c.publication_id=p.id AND u.id =c.commented_by_id ;";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, id);
             ResultSet rs = pst.executeQuery();
            while (rs.next()){
                CommentairePublication c = new CommentairePublication();
                
                c.setId(rs.getInt("c.id"));
                c.setDescription(rs.getString("c.description"));
                c.setCreatedBy(rs.getInt("u.id"));
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
        
    public static List<PublicationForum> getStatVuesPerPublication()
    {
        List<PublicationForum> pList = new ArrayList();
        String requete = "Select p.id, p.titre, p.nbrVues, "
                + "(select count(*) from commentaire_publication cp where cp.publication_id = p.id  ) as nbrCommentaire "
                + "from publication_forum p ";
        Connection cn = ConnectionBase.getInstance().getCnx();

        try
        {
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PublicationForum p = new PublicationForum();
               
                p.setId(rs.getInt("p.id"));
                p.setTitre(rs.getString("p.titre"));
                p.setNbrVues(rs.getInt("p.nbrVues"));
                p.setNbrCommentaires(rs.getInt("nbrCommentaire"));
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
    
    public static void updatePublicationTitre(PublicationForum p)
    {
        String req = "UPDATE `publication_forum` SET `titre` = ?  WHERE `id` = ?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1, p.getTitre());
            pst.setInt(2, p.getId());

            pst.executeUpdate();
            
            pst.close();
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
    
    public static void updatePublicationDesc(PublicationForum p)
    {
        String req = "UPDATE `publication_forum` SET `description` = ?  WHERE `id` = ?";
        Connection cn = ConnectionBase.getInstance().getCnx();
        try 
        {
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1, p.getDescription());
            pst.setInt(2, p.getId());

            pst.executeUpdate();
            
            pst.close();
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }  
    }
}
