/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import entities.Categorie_Evts;
import entities.Evenement;
import entities.Utilisateur;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Rania
 */
public interface IEvenementService {
    
     public void addEvent(Evenement e);
    public void deleteEvent(int id);
    public void updateEvent(Evenement e);
    public List<Evenement> getAll(); 
    public List<Evenement> getRecent(); 
    public List<Evenement> myEvents();
    public void participer(Evenement e,Utilisateur u);
    public void annulerParticipation(Evenement e,Utilisateur u);
    public boolean verifierParticipation(Evenement e);
    public boolean verifierUser(Evenement e);
    public ObservableList<Evenement> rechercherEvent(String x);
    public ObservableList<Evenement> TrendingEvents();
    
    
}
