package PMS;

import com.sun.mail.smtp.SMTPSaslAuthenticator;

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



    public void contactUnassignedStudents(List<Student> Students) throws Exception{
        String from = this.email;
        List<String> to = new ArrayList<String>();
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        for(Student s: Students){
            if(s.getMyProject() == null){
                to.add(s.getEmail());
            }
        }

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            for(String s: to) message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));

            // Set Subject: header field
            message.setSubject("Reminder!");

            // Now set the actual message
            message.setText("Do not forget to register in a project" +"\n \n" + this.getFirstName() + " "+ this.getLastName());

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");



    }
}
