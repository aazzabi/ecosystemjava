/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.panier;

import java.sql.Date;

/**
 *
 * @author Aziz
 */
public class Livraison {
    
  private int id;
  private int id_commande;
  private int id_utilisateur;
 private int id_livreur;
 private Date date_livraison;
 private String etat_livraison;
 private String adresse_livraison;
 private String ville;
 private String code;

    public Livraison() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Livraison(int id_commande, int id_utilisateur, int id_livreur, Date date_livraison, String etat_livraison, String adresse_livraison, String ville,String code) {
        this.id_commande = id_commande;
        this.id_utilisateur = id_utilisateur;
        this.id_livreur = id_livreur;
        this.date_livraison = date_livraison;
        this.etat_livraison = etat_livraison;
        this.adresse_livraison = adresse_livraison;
        this.ville = ville;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
 
 
 
}
