package com.example.SpringAuth.Services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringAuth.DTO.SignupRequest;
import com.example.SpringAuth.Entity.Student;
import com.example.SpringAuth.Repository.StudentRepository;

@Service
public class AuthServiceImpl implements AuthService{
	
	private final StudentRepository studentRepo;

    private final PasswordEncoder passwordEncoder;

	@Autowired
    public AuthServiceImpl(StudentRepository studentRepo, PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
    }
	
	@Override
	public Student createStudent(SignupRequest sr) {
		// TODO Auto-generated method stub
		//Check if Student Already Exists
		if (studentRepo.existsByEmail(sr.getEmail())) {
            return null;
        }
		
		Student student = new Student();
        BeanUtils.copyProperties(sr , student);
        System.out.println("\nIn AuthServiceImpl");
        System.out.println(student);
        //Hash the password before saving
        String hashPassword = passwordEncoder.encode(sr.getPassword());
        student.setPassword(hashPassword);
        Long roll = sr.getRollNo();
        student.setRollNo(roll);
        //Student createdStudent = 
        studentRepo.save(student);
        
		
		return student;
	}

}
