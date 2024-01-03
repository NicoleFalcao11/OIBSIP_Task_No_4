package com.example.SpringAuth.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringAuth.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
	
	boolean existsByEmail(String email);

    Optional<Student> findByEmail(String email);
	
}
