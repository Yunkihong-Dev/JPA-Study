package com.app.jwtsecurity.repository;

import com.app.jwtsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    // SELECT * FROM user WHERE username = ?1
    User findByUserName(String username);

}
