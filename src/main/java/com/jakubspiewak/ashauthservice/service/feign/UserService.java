package com.jakubspiewak.ashauthservice.service.feign;

import com.jakubspiewak.ashapimodellib.model.auth.UserCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient("ash-user-service")
public interface UserService {

  @PostMapping("/credentials")
  UUID findByCredentials(UserCredentials credentials);
}
