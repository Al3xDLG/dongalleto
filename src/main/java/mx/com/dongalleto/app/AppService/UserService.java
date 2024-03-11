package mx.com.dongalleto.app.AppService;

import mx.com.dongalleto.app.AppService.Validator.UserValidator;
import mx.com.dongalleto.app.Model.User;
import mx.com.dongalleto.app.Model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.com.dongalleto.app.Repository.UserRepository;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserValidator userValidator;


    public UserResponse login(String username, String password) {
        User u = userRepository.search(username, password);
        if (u == null) {
            throw new RuntimeException("Nombre de usuario o contraseña incorrectos");
        }
        UserResponse ur = new UserResponse();
        ur.setRole(u.getRol());
        return ur;
    }
    public void saveUser(User u) throws Exception{
        userValidator.validateUser(u);
        userRepository.save(u);
    }





}
