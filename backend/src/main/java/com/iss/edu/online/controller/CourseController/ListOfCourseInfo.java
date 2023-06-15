package com.iss.edu.online.controller.CourseController;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.model.Course;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//以列表的形式返回所有课程信息
@RestController
public class ListOfCourseInfo {

    @PostMapping("/course/list")
    public ResultResponse listAllCourseInfo() {


        return null;
    }

}
