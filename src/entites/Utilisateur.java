/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author anasc
 */
public class Utilisateur {
    
    private Integer id;
    
    private Integer group_id;
 
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
    
    private Date photo_updated_at;
    
    private String rue;
    
    private String ville;
    
    private String numtel;

    public Utilisateur(String username, String usernameCanonical, String email, String emailCanonical, String password, String nom, String prenom) {
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }



    public Utilisateur() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
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

}
