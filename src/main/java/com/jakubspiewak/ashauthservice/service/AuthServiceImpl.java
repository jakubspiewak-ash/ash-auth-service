package com.jakubspiewak.ashauthservice.service;

import com.jakubspiewak.ashapimodellib.model.auth.ApiTokenInfo;
import com.jakubspiewak.ashapimodellib.model.auth.ApiUserCredentials;
import com.jakubspiewak.ashauthservice.feign.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final JwtService jwtService;

  @Override
  public String createToken(ApiUserCredentials credentials) {
    final var userId = userService.findIdByCredentials(credentials);

    return jwtService.createTokenForUser(userId);
  }

  // TODO: validate if token is at least parseable
  @Override
  public ApiTokenInfo resolveToken(String token) {
    final var claims = jwtService.getAllClaimsFromToken(token);

    final var userId = UUID.fromString(claims.getSubject());
    final var expirationDate = claims.getExpiration();
    final var isExpired = expirationDate.before(new Date());

    // TODO: make a change in model (isExpired -> isAccepted/isOK)
    return ApiTokenInfo.builder()
        .userId(userId)
        .expirationDate(expirationDate)
        .isExpired(isExpired)
        .build();
  }
}
