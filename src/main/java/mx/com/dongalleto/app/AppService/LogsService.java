package mx.com.dongalleto.app.AppService;


import mx.com.dongalleto.app.Model.Logs;
import mx.com.dongalleto.app.Model.User;
import mx.com.dongalleto.app.Repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsService {
    @Autowired
    LogsRepository logsRepository;

    public void log(User u){
        Logs l = new Logs();
        l.setUsername(u.getUsername());
        l.setRol(u.getRol());
        logsRepository.save(l);
    }
}
