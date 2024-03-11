package mx.com.dongalleto.app.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "login_logs")
@Data
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion = new Date();


    @Column(name = "username")
    private String username;

    @Column(name = "rol")
    private String rol;

}
