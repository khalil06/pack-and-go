/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iHoussem
 */
public class JavaMailUtil {
    public static void sendMail(String reciepent,String subject,String text) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAcountEmail="packandgosupp@gmail.com";
        String myAcountPassword="Pipr0j!Pipr0j!";
        Session session= Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAcountEmail, myAcountPassword);
            }
        });
        
        Message message =prepareMessage(session,myAcountEmail,reciepent,subject,text);
        System.out.println(message);
        
        Transport.send(message);
        System.out.println("Message send succ");
    }

    private static Message prepareMessage(Session session , String myAccountEmail,String recipient, String subject,String text) throws MessagingException {
        Message message= new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            System.out.println(message.getFrom());
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            System.out.println(message.getRecipients(Message.RecipientType.TO));
            message.setSubject(subject);
            System.out.println(message.getSubject());
            message.setText(text);
            System.out.println();
        } catch (AddressException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
}