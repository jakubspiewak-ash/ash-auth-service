package com.jakubspiewak.ashauthservice.service;

import com.jakubspiewak.ashapimodellib.model.auth.ApiTokenInfo;
import com.jakubspiewak.ashapimodellib.model.auth.ApiUserCredentials;

public interface AuthService {

  String createToken(ApiUserCredentials credentials);

  ApiTokenInfo resolveToken(String token);
}
