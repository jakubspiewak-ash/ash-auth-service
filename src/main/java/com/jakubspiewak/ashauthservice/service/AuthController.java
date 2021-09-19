package com.jakubspiewak.ashauthservice.service;

import com.jakubspiewak.ashapimodellib.model.auth.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/token")
  ResponseEntity<String> getToken(@RequestBody UserCredentials credentials) {
    final var token = authService.createToken(credentials);

    return ResponseEntity.status(OK).body(token);
  }

  @GetMapping("/validate/{token}")
  ResponseEntity<Boolean> isTokenValid(@PathVariable String token) {
    final var isValid = authService.isTokenValid(token);

    return ResponseEntity.status(OK).body(isValid);
  }

  @GetMapping("/id/{token}")
  ResponseEntity<UUID> resolveToken(@PathVariable String token) {
    final var id = authService.resolveToken(token);

    return ResponseEntity.status(OK).body(id);
  }
}
