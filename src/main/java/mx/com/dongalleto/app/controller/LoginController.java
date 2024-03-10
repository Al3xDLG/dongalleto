package mx.com.dongalleto.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.dongalleto.app.model.user.UserRequest;
import mx.com.dongalleto.app.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://192.168.174.254:8080", "http://192.168.174.227:3000", "http://192.168.174.217:5500"})
public class LoginController {

    @Autowired
    UserService userService;
    
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserRequest userRequest) {
        if(userRequest == null) {
            return "No hay objeto";
        }
        if (userRequest.getUsername() == null || userRequest.getPassword() == null){
            return "No hay datos de autenticación";
        }
        if(userService.authUser(userRequest)){
            return "Autenticación exitosa";
        }
        else{
            return "Autenticación fallida";
        }
    }
}
