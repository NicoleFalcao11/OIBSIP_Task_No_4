package com.example.SpringAuth.Services;

import com.example.SpringAuth.DTO.SignupRequest;
import com.example.SpringAuth.Entity.Student;

public interface AuthService {
	
	Student createStudent(SignupRequest sr);
}
