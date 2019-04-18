/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices.panier;

import entities.Annonce;
import entities.panier.AnnoncePanier;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Aziz
 */
public interface IPanierService {
    public Annonce RecupererAnnonce(int id);
    public int existAnnonce(int id);
    public void AjouterAuPanier(Annonce a);
    public void SupprimerDuPanier(int id);
    
    public int LonguerPanier();
   public ObservableList<AnnoncePanier> getall();
}
