package mx.com.dongalleto.app.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.dongalleto.app.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User search(@Param("username") String username, @Param("password") String password);


}
