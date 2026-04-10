package com.jwt.auth.jwtauth.service;

import com.jwt.auth.jwtauth.model.User;
import com.jwt.auth.jwtauth.repository.AuthRepository;
import com.jwt.auth.jwtauth.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public AuthRepository authRepository;

    AuthService(AuthRepository authRepository)
    {
        this.authRepository=authRepository;
    }

    public User addUser(User user)
    {
        if(authRepository.findByEmail(user.getEmail())==null)
        {
            return authRepository.save(user);
        }
        throw new RuntimeException("User Already Exists!");
    }




    public String getUser(String email, String password)
    {
        User resultUser =authRepository.findByEmail(email);
        if(resultUser!=null)
        {
            System.out.println(resultUser.getPassword().equals(password));
            if(resultUser.getPassword().equals(password))
            {

                String token = JwtUtil.generateToken(email);
                return token;
            }
            throw new RuntimeException("Invalid credentials");
        }
        throw new RuntimeException("User Not Found");
    }
}
