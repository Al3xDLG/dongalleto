package mx.com.dongalleto.app.EmialService;

import mx.com.dongalleto.app.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email}")
    String email;

    public void sendEmail(String toEmail,String subject,String body){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(email);
        mail.setTo(toEmail);
        mail.setSubject(subject);
        mail.setText(body);
        javaMailSender.send(mail);
        System.out.println("Main sent successfully");
    }




}
