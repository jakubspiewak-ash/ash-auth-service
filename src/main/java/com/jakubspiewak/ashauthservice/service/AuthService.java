package com.jakubspiewak.ashauthservice.service;

import com.jakubspiewak.ashapimodellib.model.auth.UserCredentials;

import java.util.UUID;

public interface AuthService {

  String createToken(UserCredentials credentials);

  boolean isTokenValid(String token);

  UUID resolveToken(String token);
}
