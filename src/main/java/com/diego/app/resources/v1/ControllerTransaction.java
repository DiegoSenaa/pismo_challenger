package com.diego.app.resources.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/transactions")
public class ControllerTransaction {

    @PostMapping()
    public ResponseEntity<String> saveTransaction(){
        return new  ResponseEntity<>("oi", HttpStatus.OK);
    }
}
