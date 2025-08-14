package com.vc.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vc.admin.dto.UserInfoResponse;
import com.vc.admin.dto.Validate;


@FeignClient(name = "login-service", path = "/api/auth")
@Component
public interface AuthFeignClient {
    
    @PostMapping("/validate")
    UserInfoResponse validate(@RequestBody Validate token);
}