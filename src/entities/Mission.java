/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author weepey
 */

public class Mission extends RecursiveTreeObject<Mission> {

    public Mission(int i, String hiho) {
this.id_event = i ; 
this .nom=hiho;    }

   
   
    public enum Disponible {ouvert,fermé}
    private int id_event ; 
    private String nom = null; 
    private String Nomcategorie = null; 
    private String Nomtype =null ; 
    private String animateur =null ;    
    private Timestamp date_debut =null ; 
    private Timestamp date_fin =null; 
    private String disponible =null;
    private String acces =null ; 
    private String adr =null; 
    private String description = null ;
    private int idOrg ; 
    String image= null;
 public int getIdOrg() {
     return this.idOrg;
    }

    public void setIdOrg(int idOrg) {
        this.idOrg = idOrg;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getId_event_str() {
       return Integer.toString(id_event);
   }

    public String getDescription() {
        return description;
    }

    public Mission(int id_event, String nom, String categorie, String type, String animateur, Timestamp date_debut, Timestamp date_fin, String disponible, String acces, String adr,String description) {
        this.id_event = id_event;
        this.nom = nom;
        this.Nomcategorie = categorie;
        this.Nomtype = type;
        this.animateur = animateur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.disponible = disponible;
        this.acces = acces;
        this.adr = adr;
        this.description=description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
   
    public Mission() {
    }

    public Mission(int id_event,String nom, String categorie, String type, String animateur, Timestamp date_debut, Timestamp date_fin, String disponible, String acces, String adr,String description, int org,String img) {
                this.id_event = id_event;

        this.nom = nom;
        this.Nomcategorie = categorie;
        this.Nomtype = type;
        this.animateur = animateur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.disponible = disponible;
        this.acces = acces;
        this.adr = adr;
        this.description=description;
        this.idOrg=org;
        this.image=img;

    }

    public int getId_event() {
        return id_event;
    }

    public String getNom() {
        return nom;
    }

    public String getNomcategorie() {
        return Nomcategorie;
    }

    public String getNomtype() {
        return Nomtype;
    }


    public String getAnimateur() {
        return animateur;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }



    
     public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNomcategorie(String Nomcategorie) {
        this.Nomcategorie = Nomcategorie;
    }

    public void setNomtype(String Nomtype) {
        this.Nomtype = Nomtype;
    }

    

    public void setAnimateur(String animateur) {
        this.animateur = animateur;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public String getDisponible() {
        return disponible;
    }

    public String getAcces() {
        return acces;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }
    
    
   

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

   

   
    
    
    
    
}
