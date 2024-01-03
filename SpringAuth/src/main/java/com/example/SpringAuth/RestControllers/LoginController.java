package com.example.SpringAuth.RestControllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringAuth.DTO.LoginRequest;
import com.example.SpringAuth.DTO.LoginResponse;
import com.example.SpringAuth.Services.JWT.StudentServiceImpl;
import com.example.SpringAuth.Utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final StudentServiceImpl customerService;

    private final JwtUtil jwtUtil;


    @Autowired
    public LoginController(AuthenticationManager authenticationManager, StudentServiceImpl customerService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Email Or Password");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Student Does Not Exist");
            return null;
        }
        final UserDetails userDetails = customerService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new LoginResponse(jwt);
    }

}
