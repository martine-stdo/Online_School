package com.iss.edu.online.controller.Teacher;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.utils.CheckAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelEduInfo {
    @Autowired
    private CheckAdmin checkAdmin;
    @Autowired
    private UserDao userDao;
    @GetMapping("/delEduInfo")
    public ResultResponse createEduInfo(@RequestHeader("Authorization") String authorization) {
        if (checkAdmin.isAdmin(authorization)) {
            //删除课程业务
            return ResultResponse.success("删除课程成功");
        } else {
            return ResultResponse.fail(403, "您不是管理员，无法创建课程");
        }
    }

}
