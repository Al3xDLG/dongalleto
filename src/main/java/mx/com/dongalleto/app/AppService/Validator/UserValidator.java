package mx.com.dongalleto.app.AppService.Validator;

import mx.com.dongalleto.app.Model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserValidator {

    public void validateUser(User u) throws Exception {
        List<String> emptyFields = new ArrayList<>();

        if (u.getPassword() == null || u.getPassword().isEmpty()) {
            emptyFields.add("Contrasenia");
        }
        if (u.getUsername() == null || u.getUsername().isEmpty()) {
            emptyFields.add("Usuario");
        }
        if (u.getRol() == null || u.getRol().isEmpty()) {
            emptyFields.add("Rol");
        }

        if (!emptyFields.isEmpty()) {
            String message = "Estos campos no pueden ir vacíos: " + String.join(",", emptyFields);
            throw new Exception(message);
        }

        if (u.getUsername().equals(u.getPassword())) {
            throw new Exception("El usuario y la contraseña no pueden ser iguales");
        }

        if (u.getPassword().length() <= 7) {
            throw new Exception("La longitud mínima de la contraseña debe ser de 8 caracteres ");
        }

        if (!u.getPassword().matches(".*[!@#$%&].*")) {
            throw new Exception("La contraseña debe contener al menos un carácter especial (!@#$%&)");
        }

        if (u.getPassword().matches(".*\\d{4,}.*")) {
            throw new Exception("La contraseña no puede contener más de tres números consecutivos.");
        }

        if (isPasswordCommon(u.getPassword())) {
            throw new Exception("La contraseña es común y no es segura.");
        }
    }

    private boolean isPasswordCommon(String password) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("static/common_passwords.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
