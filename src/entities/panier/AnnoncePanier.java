/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.panier;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author Aziz
 */
public class AnnoncePanier {
    private int id ;
    private String id_annonce;
    private String titre;
    private String description;
    Image image;
    private double prix;
    private String region ;
    private String photo;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public AnnoncePanier() {
    }

    public AnnoncePanier(int id, String id_annonce, String titre, String description, double prix, String region, String photo) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.region = region;
        this.photo = photo;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(String id_annonce) {
        this.id_annonce = id_annonce;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
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
    

}
