/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.panier;

/**
 *
 * @author Aziz
 */
public class LigneCommande {


  private int id_commande;
    private int id_annonce;
    private int id_user;
    private double prix_annonce;

    public LigneCommande() {
    }

    public LigneCommande(int id_commande, int id_annonce, int id_user, double prix_annonce) {
        this.id_commande = id_commande;
        this.id_annonce = id_annonce;
        this.id_user = id_user;
        this.prix_annonce = prix_annonce;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getPrix_annonce() {
        return prix_annonce;
    }

    public void setPrix_annonce(double prix_annonce) {
        this.prix_annonce = prix_annonce;
    }

    public void ajouterLigne(int id, int id_panier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
