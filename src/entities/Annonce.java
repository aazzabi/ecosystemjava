/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;


/**
 *
 * @author anasc
 */
public class Annonce  {
    
    private int id;
    
    private String titre;
    
    private String description;
    
    private Date date_creation;
    
    private Date date_update;;
    
    private Double prix;
    
    private String region;
    
    private String etat ;
    
    private String photo;
    Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    
    private Date photo_updated_at;
    
    private int likes;
    
    private int  views;
    
    private int categorie_id;
    
    private int user_id;
    
    private String lib ;
    
    private String nomPrenom;
    private String nomCat;
    private int nb_cat;
    public Annonce() {
    }
    
    public Annonce(int id, String titre, String description, Date date_creation, Date date_update, Double prix, String region, String etat, String photo, Date photo_updated_at, int likes, int views, int categorie_id, int user_id) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_creation = date_creation;
        this.date_update = date_update;
        this.prix = prix;
        this.region = region;
        this.etat = etat;
        this.photo = photo;
        this.photo_updated_at = photo_updated_at;
        this.likes = likes;
        this.views = views;
        this.categorie_id = categorie_id;
        this.user_id = user_id;
    }

   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Annonce other = (Annonce) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Annonce(String titre, String description,Double prix, String region, String photo, int categorie_id, int user_id) {
        this.titre = titre;
        this.description = description;
        this.date_creation = Date.valueOf(LocalDate.now());
        this.date_update = Date.valueOf(LocalDate.now());
        this.prix = prix;
        this.region = region;
        this.etat = "Disponible";
        this.photo = photo;
        this.photo_updated_at =Date.valueOf(LocalDate.now());
        this.likes = 0;
        this.views = 0;
        this.categorie_id = categorie_id;
        this.user_id = user_id;
    }
    

    public Annonce(String titre, String description, Double prix, String region, String photo, int categorie_id) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.region = region;
        this.photo = photo;
        this.categorie_id = categorie_id;
    }

    public Annonce(String nomCat, int nb_cat) {
        this.nomCat = nomCat;
        this.nb_cat = nb_cat;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public int getNb_cat() {
        return nb_cat;
    }

    public void setNb_cat(int nb_cat) {
        this.nb_cat = nb_cat;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
        this.photo = photo;
         try {
            BufferedImage bf = ImageIO.read(new File("C:\\wamp\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\" + photo));
            BufferedImage bf1 = Scalr.resize(bf, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    250, 100, Scalr.OP_ANTIALIAS);
            image = SwingFXUtils.toFXImage(bf1, null);
        } catch (IOException ex) {
            // NO PHOTO A AJOUTER
            //System.out.println("entities.AnnounceRep.setUrlPhoto()");;
        }
    }

    public Date getPhoto_updated_at() {
        return photo_updated_at;
    }

    public void setPhoto_updated_at(Date photo_updated_at) {
        this.photo_updated_at = photo_updated_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", date_creation=" + date_creation + ", date_update=" + date_update + ", prix=" + prix + ", region=" + region + ", etat=" + etat + ", photo=" + photo + ", photo_updated_at=" + photo_updated_at + ", likes=" + likes + ", views=" + views + ", categorie_id=" + categorie_id + ", user_id=" + user_id + ", lib=" + lib + ", nomPrenom=" + nomPrenom + '}';
    }


    

  
    
    
}
