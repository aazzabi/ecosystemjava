/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices.panier;
import entities.Annonce;
import entities.panier.Commande;
import entities.panier.LigneCommande;
import entities.panier.AnnoncePanier;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author Aziz
 */
public interface ICommandeService {
     public Commande RecupererCommande(Commande c);
     public Commande RecupererCommandeClient2(int id);
      public ObservableList<Commande> RecupererCommandeClient(int id);
     public String RecupererNP_Utilisateur(int id);
     public String RecupererTel_Utilisateur(int id);
     public String RecupererMail(int id);
     // public String RecupererNP_Utilisateur2(int id);
    public void AjouterCommande(Commande c);
    public void AnnulerCommande(int id);
    public ObservableList<Commande> getall();
       public void ChangerEtatCommandeToPaye(int id);
}
