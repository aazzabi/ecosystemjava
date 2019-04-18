/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import entities.Annonce;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anasc
 */
public interface IAnnonceService {

    public void add(Annonce a);

    public void delete(int id);

    public boolean update(Annonce a, int id);

    public boolean updateLikes(int id);

    public boolean updateViwes(int id);

    public List<Annonce> getall();

    public Annonce getAnnonceById(int id);

    public List<Annonce> trierParDate();

    public List<Annonce> trierParPrixASC();

    public List<Annonce> trierParPrixDESC();

    public List<Annonce> GetByUser();

    public List<Annonce> GetByCategorie(int id);

    public List<Annonce> GetMostLikes();

    public List<Annonce> GetMostViwed();

    public List<Integer> Stat();

    public List<Annonce> StatByCat();

    public List<Annonce> StatByMyAnnonces();
    
    
    
}
