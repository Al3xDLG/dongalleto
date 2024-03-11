package mx.com.dongalleto.app.Repository;

import mx.com.dongalleto.app.Model.OneTimePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {
    
}
