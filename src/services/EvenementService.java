/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie_Evts;
import entities.Evenement;
import entities.Session;
import iservices.IEvenementService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionBase;

/**
 *
 * @author Rania
 */
public class EvenementService implements IEvenementService{

     Connection cn = ConnectionBase.getInstance().getCnx();
    Statement st; //execute la req
    PreparedStatement pst;
    private ResultSet rs;
    
    
    @Override
    public void addEvent(Evenement e) {
        String request = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
       //Date d=new Date();
         try {
             request = "INSERT INTO evenement ( created_by_id,lieu,date,categorie,titre, description) VALUES ( '"+Session.getCurrentSession()+"','"+e.getLieu()+"','"+dateFormat.format(e.getDate()) +"','"+e.getId_categorie()+"' ,'"+e.getTitre()+"', '"+e.getDescription()+"');";
             //  req = "insert into evenement (created_by_id,lieu,categorie,titre,description) values (2,'"+e.getLieu()+"',"+e.getId_categorie()+",'"+ e.getTitre()+"','"+ e.getDescription()+"');";
         } catch (Exception ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
      
        try  
       { st= cn.createStatement(); //nesnaa statement
       st.executeUpdate(request);
       System.out.print("event ajouté");}
       
       catch (SQLException s){
           
       System.out.print("erreur d'ajout ");   
       
        } 
    }

    @Override
    public void deleteEvent(int id) {
         String req = "delete from evenement where id =?";
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
    public void updateEvent(Evenement e) {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
        String req = "update evenement set lieu='" +e.getLieu()+"' ,date='"+dateFormat.format(e.getDate()) +"' ,categorie='"+e.getId_categorie()+"' ,titre='"+e.getTitre()+"' ,description='"+e.getDescription()+"' where id = '" +e.getId()+"';  ";
       try{
     
           st=cn.createStatement();
           st.executeUpdate(req);
           
       }catch(SQLException ex)
       {
           System.out.println("erreur");
       }
    }

    @Override
    public List<Evenement> getAll() {
          List<Evenement> cats = new ArrayList<>();
        String req = "select * from evenement ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Evenement e= new Evenement(resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),resultSet.getString(3),resultSet.getDate(4), new Categorie_EvtsService().findById(resultSet.getInt(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(10));
                cats.add(e); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats;
    }

    @Override
    public List<Evenement> myEvents() {
              List<Evenement> cats = new ArrayList<>();
        String req = null;
         try {
             req = "select * from evenement where created_by_id='2'; ";
         } catch (Exception ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Evenement e= new Evenement(resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),resultSet.getString(3),resultSet.getDate(4), new Categorie_EvtsService().findById(resultSet.getInt(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(10));
                cats.add(e); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats;
    }
    
    
    
    
}
