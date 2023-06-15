package com.iss.edu.online.controller.Teacher;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.Course;
import com.iss.edu.online.utils.CheckAdmin;
import com.iss.edu.online.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class CreateEduInfo {
    @Autowired
    private CheckAdmin checkAdmin;
    @Autowired
    private UserDao userDao;
    @GetMapping("/createEduInfo")
    public ResultResponse createEduInfo(@RequestHeader("Authorization") String authorization, @RequestBody Map<String, String> map) throws Exception {
        if (checkAdmin.isAdmin(authorization)) {
            //前端需要传回的字段有
            //courseName
            //courseDescription
            //courseStartTime
            //courseEndTime
            Course course = new Course();
            course.setCourseName(map.get("courseName"));
            course.setCourseDescription(map.get("courseDescription"));
            course.setCourseStartTime(LocalDateTime.parse(map.get("courseStartTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            course.setCourseEndTime(LocalDateTime.parse(map.get("courseEndTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            String jwt = checkAdmin.extractJwt(authorization);
            // 从JWT中提取用户ID
            Claims claims = JwtUtil.parseJWT(jwt);
            String userId = claims.getSubject();
            course.setTeacherId(Integer.parseInt(userId));
            userDao.insertCourse(course); // Assuming userDao.insertCourse() method takes a Course object as parameter
            return ResultResponse.success("创建课程成功");
        } else {
            return ResultResponse.fail(403, "您不是管理员，无法创建课程");
        }
    }

}
