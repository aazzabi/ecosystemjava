/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.signalAnnonce;
import iservices.ISignalAnnonceService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionBase;

/**
 *
 * @author anasc
 */
public class SignalAnnonceService implements ISignalAnnonceService{

    Connection cn = ConnectionBase.getInstance().getCnx();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<signalAnnonce> signalessAs = new ArrayList<signalAnnonce>();
    @Override
    public void add(signalAnnonce e) {
          String req = "INSERT INTO signal_annonce (annonce_id, user_id, description) VALUES (?,?,?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setInt(1, e.getAnnonce_id());
            pt.setInt(2, e.getUser_id());
            pt.setString(3, e.getDescription());
            pt.executeUpdate();
            System.out.println("ajout etablie");

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<signalAnnonce> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
