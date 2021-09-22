package com.jakubspiewak.ashauthservice.service;

import com.jakubspiewak.ashapimodellib.model.auth.ApiTokenInfo;
import com.jakubspiewak.ashapimodellib.model.auth.ApiUserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping
  ResponseEntity<String> createToken(@RequestBody ApiUserCredentials credentials) {
    final var token = authService.createToken(credentials);

    return ResponseEntity.status(OK).body(token);
  }

  @GetMapping("/{token}")
  ResponseEntity<ApiTokenInfo> resolveToken(@PathVariable String token) {
    final var id = authService.resolveToken(token);

    return ResponseEntity.status(OK).body(id);
  }
}
