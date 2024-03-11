package mx.com.dongalleto.app.Controller.UserController;

import mx.com.dongalleto.app.AppService.UserService;
import mx.com.dongalleto.app.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserREST {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User u) {
        try {
            userService.saveUser(u);
            Map<String, String> jsonResponse = new HashMap<>();
            jsonResponse.put("success", "User added");
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }


    }


}
