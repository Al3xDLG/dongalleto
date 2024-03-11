package mx.com.dongalleto.app.Controller.EmailController;


import mx.com.dongalleto.app.EmialService.EmailService;
import mx.com.dongalleto.app.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mail")
@CrossOrigin("*")
public class EmailRESTController {
    @Autowired
    private EmailService emailService;
    private Email email;

    @PostMapping("/sendEmail")
    public void SendEmail(@RequestBody Email email) {
        emailService.sendEmail(email.getToEmail(), email.getSubject(), email.getBody());
    }

}
