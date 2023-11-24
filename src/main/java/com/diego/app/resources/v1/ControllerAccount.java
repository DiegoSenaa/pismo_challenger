package com.diego.app.resources.v1;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class ControllerAccount {

    @GetMapping()
    public ResponseEntity<String> getAccountList() {
        return new ResponseEntity<>("Oi", HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<String> getAccount(@PathVariable("accountId") int accountId) {
        return new ResponseEntity<>("tchau", HttpStatus.OK);
    }

}
