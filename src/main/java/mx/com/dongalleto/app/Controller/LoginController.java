package mx.com.dongalleto.app.Controller;
import mx.com.dongalleto.app.AppService.AESUtil;
import mx.com.dongalleto.app.AppService.LogsService;
import mx.com.dongalleto.app.AppService.OneTimePasswordService;
import mx.com.dongalleto.app.EmialService.EmailService;
import mx.com.dongalleto.app.Model.EncryptRequest;
import mx.com.dongalleto.app.Model.User;
import mx.com.dongalleto.app.Model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.dongalleto.app.Model.UserRequest;
import mx.com.dongalleto.app.AppService.UserService;

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

    @Autowired
    OneTimePasswordService oneTimePasswordService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EncryptRequest encrypt) {
        try {

            UserRequest usr= aesUtil.decryptToObject(encrypt.getEncrypt(), UserRequest.class);
            try {

                UserResponse u = userService.login(usr.getUsername(), usr.getPassword());
                User us = new User();
                us.setUsername(usr.getUsername());
                us.setRol(u.getRole());
                logsService.log(us);
                emailService.sendEmail(usr.getUsername(),"Don Galleto","Just logged in!");
                return ResponseEntity.ok(aesUtil.encryptObject(us));
            } catch (Exception e) {
                String errorMessage = e.getMessage();
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", errorMessage);
                emailService.sendEmail(usr.getUsername(), "Don Galleto", "Failed login attempt");
                return ResponseEntity.badRequest().body(errorResponse);
            }
        }
        catch(Exception e){
            String errorMessage = e.getMessage();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            return ResponseEntity.internalServerError().body(errorResponse);
        }

    }

    @GetMapping("/create")
    @ResponseBody
    private Object getOneTimePassword() {
        try {
            return ResponseEntity.ok(oneTimePasswordService.returnOneTimePassword());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

}
