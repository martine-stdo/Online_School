package com.iss.edu.online.controller.ManageCourses;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.Course;
import com.iss.edu.online.utils.CheckAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEduInfo {
    @Autowired
    private CheckAdmin checkAdmin;
    @Autowired
    private UserDao userDao;
    @GetMapping("/createEduInfo")
    public ResultResponse createEduInfo(@RequestHeader("Authorization") String authorization, @RequestBody Course course) {
        if (checkAdmin.isAdmin(authorization)) {
            userDao.insertCourse(course);
            return ResultResponse.success("创建课程成功");
        } else {
            return ResultResponse.fail(403, "您不是管理员，无法创建课程");
        }
    }

}
