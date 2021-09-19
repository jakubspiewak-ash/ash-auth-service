package com.jakubspiewak.ashauthservice.service;

import com.jakubspiewak.ashapimodellib.model.auth.UserCredentials;
import com.jakubspiewak.ashauthservice.service.feign.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final JwtUtils jwtUtils;

  @Override
  public String createToken(UserCredentials credentials) {
    final var userId = userService.findByCredentials(credentials);

    return jwtUtils.createTokenForUser(userId);
  }

  @Override
  public boolean isTokenValid(String token) {
    return jwtUtils.isInvalid(token);
  }

  @Override
  public UUID resolveToken(String token) {
    final var userId = jwtUtils.getAllClaimsFromToken(token).getSubject();

    return UUID.fromString(userId);
  }
}
