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
 * @author arafe
 */
public class CommentairePublication {
    private int id;
    private int createdBy;
    private String description;
    private String createdByName;
    private int idPublication;
    private String publicationName;
    private String photo;
    private Image photoFile;
    private Date createdAt;
    private int nbSignalisation;
    private int likes;
    private int dislikes; 

    public CommentairePublication() {
        this.createdBy = Session.getCurrentSession();
        this.createdAt = Date.valueOf(LocalDate.now());
        this.nbSignalisation = 0;
        this.likes = 0;
        this.dislikes = 0;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getNbSignalisation() {
        return nbSignalisation;
    }

    public void setNbSignalisation(int nbrSignalisation) {
        this.nbSignalisation = nbrSignalisation;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String idPublicationName) {
        this.publicationName = idPublicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public Image getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(Image photo) {
        this.photoFile = photo;
    }

    @Override
    public String toString() {
        return "CommentairePublication{" + "id=" + id + ", createdBy=" + createdBy + ", description=" + description + ", createdByName=" + createdByName + ", idPublication=" + idPublication + ", publicationName=" + publicationName + ", createdAt=" + createdAt + ", nbrSignalisation=" + nbSignalisation + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
        try {
            BufferedImage bf = ImageIO.read(new File("C:\\wamp\\www\\ecosystemweb\\web\\uploads\\publication\\commentaire\\pieceJointe\\"+photo));
            System.out.println(bf);
            BufferedImage bf1 = Scalr.resize(bf, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    100, 100, Scalr.OP_ANTIALIAS);
            photoFile = SwingFXUtils.toFXImage(bf1, null);
        } catch (IOException ex) {
        }
    }
}
