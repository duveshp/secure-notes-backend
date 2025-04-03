package com.learning.spring_security;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/hello")
    public ResponseEntity<String> getAllItems() {

        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/contact")
    public ResponseEntity<String> sayContact() {

        return ResponseEntity.ok("Contact Us");
    }

    @GetMapping("/public/hi")
    public ResponseEntity<String> publivHi() {

        return ResponseEntity.ok("Hi Guest");
    }

    @GetMapping("/hi")
    public ResponseEntity<String> sayHi() {

        return ResponseEntity.ok("Hey SSUp");
    }
}
