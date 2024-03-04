package mx.com.dongalleto.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.dongalleto.app.model.user.User;
import mx.com.dongalleto.app.model.user.UserRequest;
import mx.com.dongalleto.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // existing code
    public boolean authUser(UserRequest userRequest){
        logger.info("Buscando usuario: " + userRequest.getUsername());
        User user = userRepository.findByUsername(userRequest.getUsername());
        if(user == null){
            logger.info("Usuario no encontrado");
            return false;
        }
        logger.info("User: " + user.getUsername() + " Password: " + user.getPassword() + " UserRequest: " + userRequest.getUsername() + " " + userRequest.getPassword());
        if(user.getPassword().equals(userRequest.getPassword())){
            logger.info("Autenticación exitosa");
            return true;
        }
        
        return false;
    }
}
