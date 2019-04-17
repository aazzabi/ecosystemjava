/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Annonce;
import entities.Utilisateur;
import entities.signalAnnonce;
import iservices.IAnnonceService;
import iservices.ISignalAnnonceService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import utils.ConnectionBase;

/**
 *
 * @author anasc
 */
public class SignalAnnonceService implements ISignalAnnonceService{

    Connection cn = ConnectionBase.getInstance().getCnx();
    PreparedStatement pt;
    ResultSet rs;
    private IAnnonceService as = new AnnonceService();
    private UserService us = new UserService();
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

    @Override
    public void nbSignalParAnnonce() {

        String  req="SELECT COUNT(*),annonce_id FROM signal_annonce GROUP BY annonce_id";
        try{
          pt = cn.prepareStatement(req);
         rs=pt.executeQuery();
         String txt = "anas.bahri@esprit.tn";
         while (rs.next()){
             if (rs.getInt(1)>10) {
                 //Annonce p =as.getAnnonceById(rs.getInt(2));
                 mailsendaccept(txt, rs.getInt(2));
                 as.delete(rs.getInt(2));
                 
             }
         }

         } catch (SQLException ex) {
            Logger.getLogger(SignalAnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void mailsendaccept(String mail,int id) throws SQLException{
        Annonce a = as.getAnnonceById(id);
        Utilisateur u = us.findById(a.getUser_id());
           final String username = "anasbahri1928@gmail.com";
                final String password = "BAHRIanas1995";
                Properties props = new Properties();
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                
                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            @Override
                              protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                                return new javax.mail.PasswordAuthentication(username, password);
                            }
                        });
                try {
                    
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("anasbahri1928@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
                    message.setSubject("suppression du l'annonce signaler");
                    String txt = "\n"+"l annonce  que l utilisateur : "+u.getNom()+""+u.getPrenom()+" est signler plusieur fois et supprimer";
                    message.setText(txt);
                    Transport.send(message);
                   // JOptionPane.showMessageDialog(null, "code sent successfully!");
                } catch (MessagingException ex) {
                    JOptionPane.showMessageDialog(null, "Oops something went wrong" + "\n" + ex.getMessage());
                }
                
                
                
        
           
       }
    
}
