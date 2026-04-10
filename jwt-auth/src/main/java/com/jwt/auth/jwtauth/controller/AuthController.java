package com.jwt.auth.jwtauth.controller;

import com.jwt.auth.jwtauth.model.User;
import com.jwt.auth.jwtauth.service.AuthService;
import com.jwt.auth.jwtauth.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

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
    public String getUser(@RequestBody User user, HttpServletResponse response)
    {
        String token = authService.getUser(user.getEmail(), user.getPassword());
        // send token as cookie
        response.addHeader("Set-Cookie", "token=" + token + "; HttpOnly");
        return "Login successful";
    }

    @GetMapping("/test")
    public String test(@RequestHeader("Authorization") String header) {

        String token = header.substring(7); // remove "Bearer "
        String email = JwtUtil.extractSubject(token);

        return "Token belongs to: " + email;
    }

    @PostMapping("logout")
    public String logUserOff()
    {
        return "This is logout";
    }




}
