package com.jwt.auth.jwtauth.filter;

import com.jwt.auth.jwtauth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Spring will automatically create and use this filter
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,   // incoming request
                                    HttpServletResponse response, // response we can send back
                                    FilterChain filterChain)      // used to pass request forward
            throws ServletException, IOException {

        // Step 1: get request path (example: /login, /register, /tasks)
        String path = request.getRequestURI();

        // Step 2: skip authentication for open routes
        if (path.equals("/login") || path.equals("/register")) {
            // allow request to continue without checking token
            filterChain.doFilter(request, response);
            return; // stop filter execution here
        }

        //Get Data from Cookies
        Cookie[] cookies = request.getCookies();

        // Step 4: check if header is missing or incorrect
        if (cookies == null || cookies.length==0) {

            // set HTTP status to 401 (Unauthorized)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // send message in response body
            response.getWriter().write("Missing token");

            return; // block request (do not continue)
        }

        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token not found");
            return;
        }
        try {
            // Step 6: validate token and extract user (subject)
            String subject = JwtUtil.extractSubject(token);

            // Step 7: Attach user to the request
            request.setAttribute("user", subject);

        } catch (Exception e) {

            // if token is invalid (expired, wrong signature, etc.)

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");

            return; // block request
        }

        // Step 8: token is valid → allow request to reach controller
        filterChain.doFilter(request, response);
    }
}