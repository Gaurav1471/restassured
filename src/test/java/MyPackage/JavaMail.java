package MyPackage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class JavaMail {


   public static void main(String [] args) {     
      // Recipient's email ID needs to be mentioned.
      String to = "tanvimer1031@gmail.com";

      // Sender's email ID needs to be mentioned
      final String from = "ayurvedicburnol@gmail.com";

      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.put("mail.smtp.auth", "true");
      properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.starttls.required", "true");
      properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
//*** BEGIN CHANGE
      properties.put("mail.smtp.user", from);
      // Get the default Session object.
      Session session = Session.getInstance(properties,new Authenticator() {
    	  @Override
    	  protected PasswordAuthentication getPasswordAuthentication() {
    		  return new PasswordAuthentication(from,"gauravsinghmer");
    	  }
      });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Create the message part 
       //  BodyPart messageBodyPart = new MimeBodyPart();

         // Fill the message
         message.setText("This is message body");
         
         // Create a multipart message
//         Multipart multipart = new MimeMultipart();
//
//         // Set text message part
//         multipart.addBodyPart(messageBodyPart);
//
//         // Part two is attachment
//         messageBodyPart = new MimeBodyPart();
//         String filename = "C:\\Users\\91700\\seleniumTrain\\APITest\\test-output\\emailable-report.html";
//         DataSource source = new FileDataSource(filename);
//         messageBodyPart.setDataHandler(new DataHandler(source));
//         messageBodyPart.setFileName(filename);
//         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
    //     message.setContent(multipart );

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}


