/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author Khairi
 */
public class Mailer {
       
    public void ConfirmationAccountMail(String email, String username, String token)
    {
        Properties p = new Properties();
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.starttls.enable","true");
        p.put("mail.smtp.host","smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        
        Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator(){
            
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("volcanofootball.securite@gmail.com","volcanotest123");
            }
        }); 
        
        try
        {
            MimeMessage m = new MimeMessage(s);
            m.setFrom("volcanofootball.securite@gmail.com");
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            m.setSubject("Confirmation du compte Volcano Football");
            m.setText("Hi "+username+" votre code de confirmation est: "+token);
            Transport.send(m);
            System.out.println("Email sended!");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void ResetPassword(String email, String username, String password)
    {
        Properties p = new Properties();
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.starttls.enable","true");
        p.put("mail.smtp.host","smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        
        Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator(){
            
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("volcanofootball.securite@gmail.com","volcanotest123");
            }
        }); 
        
        try
        {
            MimeMessage m = new MimeMessage(s);
            m.setFrom("volcanofootball.securite@gmail.com");
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            m.setSubject("Réinitialisation du mot de passe");
            m.setText("Bonjour "+username+" votre mot de passe est: "+password);
            Transport.send(m);
            AlertsController.get().Alert("information","Succès","Votre mot de passe a été envoyé à votre email");
            System.out.println("Email sended!");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    
    
}
