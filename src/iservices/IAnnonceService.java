/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import entities.Annonce;
import java.util.List;

/**
 *
 * @author anasc
 */
public interface IAnnonceService {
    
    public void add(Annonce a);
    public void delete(int id);
    public boolean update(Annonce a, int id);
    public List<Annonce> getall();
    
    
}
