package mx.com.dongalleto.app.repository;

import org.springframework.stereotype.Repository;

import mx.com.dongalleto.app.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByUsername(String username);
}
