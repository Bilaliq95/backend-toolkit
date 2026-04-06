package com.jwt.auth.jwtauth.controller;

import com.jwt.auth.jwtauth.model.User;
import com.jwt.auth.jwtauth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;

    AuthController(AuthService authService){

        this.authService=authService;
    }

    @GetMapping("/")
    public String getHomePage()
    {
        return "This is home";
    }

    @PostMapping("register")
    public User addUser(@RequestBody User user){

        return authService.addUser(user);

    }

    @PostMapping("login")
    public User getUser(@RequestBody User user)
    {
        return authService.getUser(user.getEmail(),user.getPassword());
    }

    @PostMapping("logout")
    public String logUserOff()
    {
        return "This is logout";
    }



}
