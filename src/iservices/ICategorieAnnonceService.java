/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import entities.Categorie_Annonce;
import java.util.List;

/**
 *
 * @author anasc
 */
public interface ICategorieAnnonceService {
    public void add(Categorie_Annonce c);
    public void delete(int id);
    public boolean update(Categorie_Annonce a, int id);
    public List<Categorie_Annonce> getall();
}
