/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import entities.Categorie_Evts;
import java.util.List;

/**
 *
 * @author Rania
 */
public interface ICategorie_EvtsService {
    
    public void addCategorie_Evts(Categorie_Evts c);
    public void deleteCategorie_Evts(int id);
    public void updateCategorie_Evts(Categorie_Evts c);
   public List<Categorie_Evts> getAll();
    
    
}
