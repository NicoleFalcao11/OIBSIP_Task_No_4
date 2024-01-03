package com.example.SpringAuth.Services.JWT;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringAuth.Entity.Student;
import com.example.SpringAuth.Repository.StudentRepository;

@Service
public class StudentServiceImpl implements UserDetailsService {

	private final StudentRepository srepo;

    @Autowired
    public StudentServiceImpl(StudentRepository srepo) {
        this.srepo = srepo;
    }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Student s = srepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Student with with email: " + email+" doesn't Exist") );

        return new User(s.getEmail(), s.getPassword(), Collections.emptyList());
	}
   
}
