package com.vc.dashboard.controller;

import com.vc.dashboard.client.AuthFeignClient;
import com.vc.dashboard.dto.UserInfoResponse;
import com.vc.dashboard.dto.Validate;
import com.vc.dashboard.entity.CardDetails;
import com.vc.dashboard.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private AuthFeignClient authFeignClient;
    
    @Autowired
    private DashboardService dashboardService;


    @PostMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestBody Validate token) {
        UserInfoResponse user = authFeignClient.validate(token);
       CardDetails userDetails = dashboardService.getUserDetails(user.getUsername());
       System.out.println(userDetails);
        return ResponseEntity.ok(userDetails);
    }
}
