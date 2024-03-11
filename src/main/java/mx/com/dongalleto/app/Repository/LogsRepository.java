package mx.com.dongalleto.app.Repository;

import mx.com.dongalleto.app.Model.Logs;
import mx.com.dongalleto.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {

}
