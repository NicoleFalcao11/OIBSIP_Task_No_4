package com.example.SpringAuth.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringAuth.DTO.SignupRequest;
import com.example.SpringAuth.Entity.Student;
import com.example.SpringAuth.Services.AuthService;

@RestController
//@RequestMapping("/signup")
public class SignupController {

	private final AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signupStudent(@RequestBody SignupRequest signupRequest) {
    	System.out.println("\nIn SignUpController");
        System.out.println(signupRequest);
        Student createdStudent = authService.createStudent(signupRequest);
        if (createdStudent != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student Registration Failed ! Please Try Again");
        }
    }
    
    
	
}
