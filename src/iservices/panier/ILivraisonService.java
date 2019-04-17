/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices.panier;

import entities.panier.Commande;
import entities.panier.Livraison;
import entities.panier.Livreur;
import java.sql.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Aziz
 */
public interface ILivraisonService {
    public void AjouterLivraison(Livraison L);
    public Livraison RecupererLivraison(int id);
     public ObservableList<Livraison> RecupererLivraisonLivreur(int id);
      public ObservableList<Livraison> RecupererLivraisonClient2(int id);
 //    public Livreur RecupererLivreur(int id);
     // public String RecupererNP_Utilisateur2(int id);
    public void AnnulerLivraison(int id);
    public ObservableList<Livraison> getall();
    public ObservableList<Livreur> getall2();
     public Livreur RecupererLivreurDispo(String zone);
     public void ChangerEtatLivreurTodispo(int id);
     public void ChangerEtatCommandeToEncours(int id);
     public void ChangerEtatCommandeToLivre(int id);
     public void ChangerEtatLivreurToindispo(int id);
     public void ChangerEtatLivraison(int id);
      public void NoterLivreur(int id,int note);
       public Livreur MeilleurLivreur();
    public String RecupererAdresseClient(int id);
    public String RecupererVilleClient(int id);
    public String RecupererNP_Livreur(int id);
    
}
