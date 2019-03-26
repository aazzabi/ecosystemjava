/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author imene
 */
public class MissionMail {
    
    
    String host ="smtp.gmail.com" ;
    String from = "pidevmailer2019@gmail.com";
    String pass = "Azerty123";
    boolean sessionDebug = false;

    public MissionMail() {
    }
    
    
    
    public void email(int idUser,String emailUser, String subject, String msge){
        
        try{
            String userEmail = from;
            String to = emailUser;
            String sub = subject;
            
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            //Message msg = new MimeMessage(mailSession);
//            Message messageText = new MimeMessage(mailSession);
//            messageText.setText(msge + "<form action=\"http://stackoverflow.com\">\n" +
//                    "                       <input type=\"submit\" value=\"Activer Compte\">\n" +
//                    "                   </form>"
//            );
            Message messageText = new MimeMessage(mailSession);
            messageText.setContent(msge + "\n<html>\n" +
                    "<body>\n" +
                    "\n" +
                     
                    "<a href=\"http://localhost:8000/confirmationPage.php?id=" + idUser + "\">\n" +
                    " Clique ici pour voir plus de détails!\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>", "text/html");
            messageText.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            messageText.setRecipients(Message.RecipientType.TO, address);
            messageText.setSubject(subject); messageText.setSentDate(new Date());
            //messageText.setText(messageText);

           Transport transport = mailSession.getTransport("smtp");
           transport.connect(host, userEmail, pass);
           transport.sendMessage(messageText, messageText.getAllRecipients());
           transport.close();
           System.out.println("message sent successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }

    
}
