/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import entities.Categorie_Evts;
import entities.Evenement;
import java.util.List;

/**
 *
 * @author Rania
 */
public interface IEvenementService {
    
     public void addEvent(Evenement e);
    public void deleteEvent(int id);
    public void updateEvent(Evenement e);
    public List<Evenement> getAll(); 
    public List<Evenement> myEvents();
    
    
}
