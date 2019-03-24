/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Annonce;
import iservices.IAnnonceService;
import java.sql.Connection;
import java.util.List;
import utils.ConnectionBase;

/**
 *
 * @author anasc
 */
public class AnnonceService implements IAnnonceService{
    
Connection cn = ConnectionBase.getInstance().getCnx();
    @Override
    public void add(Annonce a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Annonce a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Annonce a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Annonce> getall(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
