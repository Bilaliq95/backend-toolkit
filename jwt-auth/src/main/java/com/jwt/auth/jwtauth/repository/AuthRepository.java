package com.jwt.auth.jwtauth.repository;


import com.jwt.auth.jwtauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User,String> {
    public User findByEmail(String email);
}
