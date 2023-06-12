package com.iss.edu.online.controller;

import com.iss.edu.online.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/user/token")
    public Map<String, String> getToken(Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");

        return loginService.getToken(username, password);
    }

}
