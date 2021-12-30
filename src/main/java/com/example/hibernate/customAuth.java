package com.example.hibernate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.hibernate.DAO.StudentDAO;
import com.example.hibernate.DAO.StudentDAOImpl;
import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
public class customAuth extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public customAuth(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("User is trying to login");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        log.info("username = {}",username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
//        System.out.println(usernamePasswordAuthenticationToken);
        log.debug("Trying to authenticate");
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("Congratulations! You have successfully passed the login test");
        StudentHibernateEntity studentHibernateEntity = new StudentDAOImpl().findStudentByName(request.getParameter("username"));
        Algorithm algorithm = Algorithm.HMAC512("secret".getBytes());
        log.debug(algorithm.toString());
        String accessToken = JWT.create()
                .withSubject(studentHibernateEntity.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(studentHibernateEntity.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader("access",accessToken);
        response.setHeader("refresh",refreshToken);
//        System.out.println(authResult.getPrincipal());
//        System.out.println("Congratulations! You have successfully passed the login test");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("Sorry you have failed the login test");
    }
}