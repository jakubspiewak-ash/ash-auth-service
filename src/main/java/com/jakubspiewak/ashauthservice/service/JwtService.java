package com.jakubspiewak.ashauthservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.HOURS;

@Component
public class JwtService {
  // TODO: use enviroment
  private static final String SECRET = "SECRETTTTTTTTTTTTTTTTTTTTTTTTTTTTTT";
  // TODO: decrease this amount
  private static final Long EXPIRATION_TOKEN_HOURS = 256L;

  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
  }

  public String createTokenForUser(UUID userId) {
    return Jwts.builder()
        .signWith(key)
        .setExpiration(Date.from(Instant.now().plus(EXPIRATION_TOKEN_HOURS, HOURS)))
        .setSubject(userId.toString())
        .compact();
  }

  public Claims getAllClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }
}
