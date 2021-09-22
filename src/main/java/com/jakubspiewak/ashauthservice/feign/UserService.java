package com.jakubspiewak.ashauthservice.feign;

import com.jakubspiewak.ashapimodellib.model.auth.ApiUserCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/user")
@FeignClient("ash-user-service")
public interface UserService {

  @PostMapping("/id")
  UUID findIdByCredentials(ApiUserCredentials credentials);
}
