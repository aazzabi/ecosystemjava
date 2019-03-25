/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie_Evts;
import iservices.ICategorie_EvtsService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionBase;

/**
 *
 * @author Rania
 */

  
public class Categorie_EvtsService implements ICategorie_EvtsService {
    Connection cn = ConnectionBase.getInstance().getCnx();
    Statement st; //execute la req
    PreparedStatement pst;
    
    @Override
    public void addCategorie_Evts(Categorie_Evts c) {
      // String req="INSERT INTO categorie_evts(libelle,but) VALUES ('"+c.getLibelle())+"','"+c.getBut()+"');"; //nhotou l values 
         String req="INSERT INTO categorie_evts(libelle,but) VALUES ('"+c.getLibelle()+"','"+c.getBut()+"');"; //nhotou l values 
       try  
       { st= cn.createStatement(); //nesnaa statement
       st.executeUpdate(req);
       System.out.print("categorie ajoutée");}
       
       catch (SQLException e){
       System.out.print("erreur d'ajout categorie");   
       }
    }

    @Override
    public void deleteCategorie_Evts(int id) {
       String req = "delete from categorie_evts where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCategorie_Evts(Categorie_Evts c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie_Evts> getAll() {
        
         List<Categorie_Evts> cats = new ArrayList<>();
        String req = "select * from categorie_evts ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Categorie_Evts c = new Categorie_Evts(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3));
                cats.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats;
    }
    
    
}
