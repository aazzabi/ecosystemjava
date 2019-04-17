/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie_Evts;
import entities.Evenement;
import entities.Session;
import entities.Utilisateur;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnectionBase;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
             request = "INSERT INTO evenement ( created_by_id,lieu,date,categorie,titre, description,cover,cover_updated_at) VALUES ( '"+Session.getCurrentSession()+"','"+e.getLieu()+"','"+dateFormat.format(e.getDate()) +"','"+e.getId_categorie()+"' ,'"+e.getTitre()+"', '"+e.getDescription()+"' ,'"+e.getCover()+"','"+java.sql.Date.valueOf(java.time.LocalDate.now())+"');";
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
        String req = "update evenement set lieu='" +e.getLieu()+"' ,date='"+dateFormat.format(e.getDate()) +"' ,categorie='"+e.getId_categorie()+"' ,titre='"+e.getTitre()+"' ,description='"+e.getDescription()+"' ,cover='"+e.getCover()+"' ,cover_updated_at='"+java.sql.Date.valueOf(java.time.LocalDate.now())+"' where id = '" +e.getId()+"';  ";
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
             req = "select * from evenement where created_by_id='"+Session.getCurrentSession()+"'; ";
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
    
    // donner son experience a la cloture de l'event/ QR code si event lucratif../etat sur categorie d'event/mail au participants si un event modifié 
    //lieu avec maps +controle saisie date

    
 /*   public void addParticipant(Utilisateur u)
    {
        
    }*/
    @Override
    public void participer(Evenement e,Utilisateur u) {
        
        
       String req="INSERT INTO evenement_user(evenement_id,user_id) VALUES ('"+e.getId()+"','"+Session.getCurrentSession()+"');"; //nhotou l values 
       try  
       { st= cn.createStatement(); //nesnaa statement
       st.executeUpdate(req);
      e.getParticipants().add(u);
      u.getEventsParticipes().add(e);
       System.out.print("participation effectuée");}
       
       catch (SQLException ex){
       System.out.print("participation ghalta");   
       }
       
        
    }

    @Override
    public void annulerParticipation(Evenement e, Utilisateur u) {
        String req=null;
       try{   req="DELETE FROM evenement_user where evenement_id='"+e.getId()+"' and user_id='"+u.getId()+"';"; //nhotou l values
       }
       catch (Exception ex)
       {
            ex.printStackTrace();    }
       try  
       { st= cn.createStatement(); //nesnaa statement
       st.executeUpdate(req);
      e.getParticipants().remove(u);
      u.getEventsParticipes().remove(e);
       System.out.print("participation enlevée");}
       
       catch (SQLException ex){
       System.out.print("participation ma tnahattech");   
       }
      
    }

    @Override
    public boolean verifierParticipation(Evenement e) {
         String req=null;
          try {
              req = "select * from evenement_user where user_id='"+Session.getCurrentSession()+"' and evenement_id='"+e.getId()+"';";
          } catch (Exception ex) {
              Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
          }
        PreparedStatement ps ;
        try{
            ps=cn.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            if(!result.isBeforeFirst())
            {
                return true;
            }
            else return false;
                
            
        }catch (SQLException ex)
        { ex.printStackTrace();}
        
    
        
        return false;
    }

    @Override
    public boolean verifierUser(Evenement e) {
         String req=null;
          try {
              req = "select * from evenement where created_by_id='"+Session.getCurrentSession()+"' and id='"+e.getId()+"';";
          } catch (Exception ex) {
              Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
          }
        PreparedStatement ps ;
        try{
            ps=cn.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            if(!result.isBeforeFirst())
            {
                return true;
            }
            else return false;
                
            
        }catch (SQLException ex)
        { ex.printStackTrace();}
        
    
        
        return false;
    }

    @Override
    public ObservableList<Evenement> rechercherEvent(String x) {
         ObservableList<Evenement>listEvents = FXCollections.observableArrayList();
        String req= "select * from evenement e ,categorie_evts c where e.titre like '"+x+"%' or e.lieu like '"+x+"%' or c.libelle like '"+x+"%'";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement=cn.prepareStatement(req);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
           Evenement event = new Evenement(resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),resultSet.getString(3),resultSet.getDate(4), new Categorie_EvtsService().findById(resultSet.getInt(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(10));
              listEvents.add(event); 
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return listEvents;
    }

    @Override
    public List<Evenement> getRecent() {
          List<Evenement> cats = new ArrayList<>();
        String req = "select * from evenement where DATEDIFF(date, CURRENT_DATE()) >0  ";
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
    public ObservableList<Evenement> TrendingEvents() {
         ObservableList<Evenement>listEvents = FXCollections.observableArrayList();
        String req= "select * from `evenement` where MONTH(date) BETWEEN 5 AND 8 and id IN (select evenement_id from `evenement_user`)";
        //PreparedStatement preparedStatement;
         
        
        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();

            while(resultSet.next()){
                
           Evenement ev= new Evenement(resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),resultSet.getString(3),resultSet.getDate(4), new Categorie_EvtsService().findById(resultSet.getInt(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(10));
             
            listEvents.add(ev); 
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return listEvents;
    }
    
    public int EventsLucratifs()
    {  int val=0 ;
        String req= "select COUNT(*) from `evenement` where id IN(select evenement_id from `evenement_user` ) AND categorie IN (select id from `categorie_evts` where but='Lucratif')";

        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();

              while(resultSet.next()){
         
            val =  ((Number) resultSet.getObject(1)).intValue();
                  System.out.println("valeuuuur"+val);
              
              }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return val;

        
    }
        public int EventsNonLucratifs()
    {  int val=0 ;
        String req= "select COUNT(*) from `evenement` where id IN(select evenement_id from `evenement_user` ) AND categorie IN (select id from `categorie_evts` where but='Non Lucratif')";

        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();

              while(resultSet.next()){
         
            val =  ((Number) resultSet.getObject(1)).intValue();
                  System.out.println("valeuuuur"+val);
              
              }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return val;

        
    }
    
    public  int[]  StatEventsLucratif(){
        int[] monthCounter = new int[12];
       int val=0; 
        String req= "SELECT month(date) ,count(*) from evenement where id IN(select evenement_id from `evenement_user` ) AND"
                + " categorie IN (select id from `categorie_evts` where but='Lucratif')"
                + " group by month(date)";

        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();
     while(resultSet.next()){
         for(int i = 0; i < 12; i++)
{
        if(((Number) resultSet.getObject(1)).intValue()==i)
        {monthCounter[i] =  ((Number) resultSet.getObject(2)).intValue();
             
        }
              }}
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return monthCounter;
      }
    
      public  int[]  StatEventsNonLucratif(){
        int[] monthCounter = new int[12];
       int val=0; 
        String req= "SELECT month(date) ,count(*) from evenement where id IN(select evenement_id from `evenement_user` ) AND"
                + " categorie IN (select id from `categorie_evts` where but='Non Lucratif')"
                + " group by month(date)";

        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();
     while(resultSet.next()){
         for(int i = 0; i < 12; i++)
{
        if(((Number) resultSet.getObject(1)).intValue()==i)
        {monthCounter[i] =  ((Number) resultSet.getObject(2)).intValue();
             
        }
              }}
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return monthCounter;
      }
    
   /* public List<Integer> ()
    { // int val=0 ;
    List<Integer> cats = new ArrayList<>();
        String req= "select COUNT(*) from `evenement` where MONTH(date) BETWEEN 3 AND 5 and id IN(select evenement_id from `evenement_user` ) AND categorie IN (select id from `categorie_evts` where but='Lucratif')";

        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();

            
       

            while (resultSet.next()) {
                //Evenement e= new Evenement(resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),resultSet.getString(3),resultSet.getDate(4), new Categorie_EvtsService().findById(resultSet.getInt(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(10));
                cats.add(((Number) resultSet.getObject(1)).intValue()); 
         
              }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return cats;
  
    }*/
    
     public int getNbParticipants(Evenement e)
    {  int val=0 ;
        String req= " SELECT count(*) FROM evenement_user where evenement_id='"+e.getId()+"';";

        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();

              while(resultSet.next()){
         
            val =  ((Number) resultSet.getObject(1)).intValue();
                  System.out.println("valeuuuur"+val);
              
              }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return val;

        
    }
     
    public ObservableList<Evenement> BestEvents() {
         ObservableList<Evenement>listEvents = FXCollections.observableArrayList();
        String req= "SELECT * FROM `evenement` where id IN (select evenement_id from `evenement_user`) AND"
                + " (select count(*) from `evenement_user` where evenement_id IN (select id from `evenement_user`)) >0 AND "
                + "DATEDIFF(date, CURRENT_DATE()) >0";
     
        try {
            pst=cn.prepareStatement(req);
            ResultSet resultSet=pst.executeQuery();

            while(resultSet.next()){
                
           Evenement ev= new Evenement(resultSet.getInt(1),new UserService().findById(resultSet.getInt(2)),resultSet.getString(3),resultSet.getDate(4), new Categorie_EvtsService().findById(resultSet.getInt(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getInt(10));
             
            listEvents.add(ev); 
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
        return listEvents;
    }
    
   
    
  
    
    
    
    
    
    
    

    
    
    
}
