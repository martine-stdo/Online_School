package com.iss.edu.online.controller;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.utils.CheckAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEduInfo {
    @Autowired
    private CheckAdmin checkAdmin;
    @GetMapping("/createEduInfo")
    public ResultResponse createEduInfo(@RequestHeader("Authorization") String authorization) {
        if (checkAdmin.isAdmin(authorization)) {
            return ResultResponse.success("success");
        } else {
            return ResultResponse.fail(477,"error");
        }
    }

}
