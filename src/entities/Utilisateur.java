/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author anasc
 */
public class Utilisateur {
    
    private int id;
    
    private int group_id;
 
    private String username;
   
    private String usernameCanonical;
  
    private String email;
   
    private String emailCanonical;
   
    private boolean enabled;

    private String salt;
 
   
    private String password;
  
    private Date lastLogin;
 
    private String confirmationToken;

    private Date passwordRequestedAt;
   
    private String roles;
   
    private String nom;
   
    private String prenom;
    
    private String discr ;
    
    private String photo ;
    
    Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    private Date photo_updated_at;
    
    private String rue;
    
    private String ville;
    
    private String numtel;
    
    private String nomPropriete;
    
    private List<Evenement>eventsParticipes=new ArrayList();

    public Utilisateur(int aInt, String string) {
        this.id = aInt;
        this.username = string;
    }

    public List<Evenement> getEventsParticipes() {
        return eventsParticipes;
    }

    public Utilisateur(String username, String usernameCanonical, String email, String emailCanonical, String password, String nom, String prenom, String nomPropriete) {
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.nomPropriete = nomPropriete;
    }

    public Utilisateur(int id, String username, String nom, String prenom) {
        this.id = id;
        this.username = username;
         this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(int id, String username, String email, String nom, String prenom, String discr, String photo , String rue, String ville, String numtel) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.discr = discr;
        this.photo = photo;
        this.rue = rue;
        this.ville = ville;
        this.numtel = numtel;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDiscr() {
        return discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
        try {
            BufferedImage bf = ImageIO.read(new File("C:\\wamp\\www\\ecosystemweb\\web\\uploads\\user\\photo\\" + photo));
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

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getNomPropriete() {
        return nomPropriete;
    }

    public void setNomPropriete(String nomPropriete) {
        this.nomPropriete = nomPropriete;
    }

    @Override
    public String toString() {
        return ""+ username + "";
    }

}
