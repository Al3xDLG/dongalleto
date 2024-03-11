package mx.com.dongalleto.app.Controller;
import mx.com.dongalleto.app.AppService.AESUtil;
import mx.com.dongalleto.app.AppService.LogsService;
import mx.com.dongalleto.app.EmialService.EmailService;
import mx.com.dongalleto.app.Model.User;
import mx.com.dongalleto.app.Model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.dongalleto.app.Model.UserRequest;
import mx.com.dongalleto.app.AppService.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://192.168.174.254:8080", "http://192.168.174.227:3000", "http://192.168.174.217:5500"})
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    @Autowired
    LogsService logsService;
    @Autowired
    AESUtil aesUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest usr) {
        try {
           UserResponse u = userService.login(usr.getUsername(), usr.getPassword());
           User us = new User();
           us.setUsername(usr.getUsername());
           us.setRol(u.getRole());
           logsService.log(us);
           emailService.sendEmail(usr.getUsername(),"Don Galleto","Just logged in!");

            return ResponseEntity.ok(u);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            emailService.sendEmail(usr.getUsername(), "Don Galleto", "Failed login attempt");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
