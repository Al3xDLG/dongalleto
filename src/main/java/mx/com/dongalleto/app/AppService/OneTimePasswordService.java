package mx.com.dongalleto.app.AppService;

import mx.com.dongalleto.app.Model.OneTimePassword;
import mx.com.dongalleto.app.Repository.OneTimePasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OneTimePasswordService {
    private final Long expiryInterval = 5L * 60 * 1000;

    @Autowired
    OneTimePasswordRepository oneTimePasswordRepository;

    @Autowired
    OneTimePasswordHelpService oneTimePasswordHelpService;


    public OneTimePassword returnOneTimePassword() {
        OneTimePassword oneTimePassword = new OneTimePassword();

        oneTimePassword.setOneTimePasswordCode(oneTimePasswordHelpService.createRandomOneTimePassword().get());
        oneTimePassword.setExpires(new Date(System.currentTimeMillis() + expiryInterval));

        oneTimePasswordRepository.save(oneTimePassword);

        return oneTimePassword;
    }
}
