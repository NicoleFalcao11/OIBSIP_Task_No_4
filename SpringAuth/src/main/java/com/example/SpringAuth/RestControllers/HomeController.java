package com.example.SpringAuth.RestControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringAuth.DTO.HelloResponse;

@RestController
@RequestMapping("/bookstore")
public class HomeController {
	
	@GetMapping("/home")
	public HelloResponse hello() {
		return new HelloResponse("Hello You have Successfully Accessed An Authenticated API");
	}
	
}
