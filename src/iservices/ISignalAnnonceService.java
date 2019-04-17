/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservices;

import entities.signalAnnonce;
import java.util.List;

/**
 *
 * @author anasc
 */
public interface ISignalAnnonceService {
    
    public void add(signalAnnonce e);
    public List<signalAnnonce> getAll();
    public void nbSignalParAnnonce();
  
}
