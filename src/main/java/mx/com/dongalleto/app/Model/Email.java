package mx.com.dongalleto.app.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "emial")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_email")
    private long idEmial;
    @Column(name ="to_email")
    String toEmail;
    @Column(name = "subject")
    String subject;
    @Column(name ="body")
    String body;

    public long getIdEmial() {
        return idEmial;
    }

    public void setIdEmial(long idEmial) {
        this.idEmial = idEmial;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Email() {
    }

    public Email(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    public Email(long idEmial, String toEmail, String subject, String body) {
        this.idEmial = idEmial;
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

}
