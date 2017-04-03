package PMS;

import com.sun.mail.smtp.SMTPSaslAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



/**
 * Created by yashpatel on 3/8/2017.
 */
@Entity
public class Coordinator extends User{



    public Coordinator(){super();}


    public Coordinator(String firstName, String lastName, String email, String password){
        super(firstName, lastName, email, password);

    }



    public void contactUnassignedStudents(List<Student> Students, final String password) throws Exception{
        ArrayList<String> to = new ArrayList<String>();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", 587);
       // props.put("mail.smtp.socketFactory.port", 25);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;
        final String username = "coordinator" + this.getFirstName()+ this.getLastName();

        mailSession = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        // Create a default MimeMessage object.


        for(Student s: Students){
            if(s.getMyProject() == null){
                to.add(s.getEmail());
            }
        }

        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
            // Set To: header field of the header.
            for(String s: to) message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));

            // Set Subject: header field
            message.setSubject("Reminder!");

            // Now set the actual message
            message.setText("Do not forget to register in a project" +"\n \n" + this.getFirstName() + " "+ this.getLastName());

            // Send message
        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
            System.out.println("Sent message successfully....");



    }
}
