/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices.panier;

import entities.Annonce;
import entities.panier.AnnoncePanier;
import entities.panier.Commande;
import entities.panier.LigneCommande;
import javafx.collections.ObservableList;

/**
 *
 * @author Aziz
 */
public interface ILigneCommandeService {
    public ObservableList<AnnoncePanier> RecupererLignesCommande2(int id,ObservableList<AnnoncePanier> A);
    public Annonce RecupA(int id);
    public ObservableList<LigneCommande> RecupererLignesCommande(int id);
    public int VerifAnnonce(int id);
    //public void AddToHistrique(AnnoncePanier a);
   // public ObservableList<AnnoncePanier> getHistorique();
    public void AjouterLigneCommande(LigneCommande c);
    public void AnnulerLigneCommande(int id);
    public ObservableList<LigneCommande> getall();
    
}
