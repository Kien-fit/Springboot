package com.trainningjavaweb.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPI {
	
	@GetMapping("/api/test")
	public ResponseEntity<String> testSpringBoot(){
		return ResponseEntity.ok("Success");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
