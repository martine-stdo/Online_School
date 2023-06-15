package com.iss.edu.online.controller.CourseController;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.CourseDao;
import com.iss.edu.online.model.Course;
import com.iss.edu.online.model.CourseVO;
import com.iss.edu.online.model.Employee;
import com.iss.edu.online.model.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//以列表的形式返回所有课程信息
@RestController
public class ListOfCourseInfo {
    @Autowired
    private CourseDao courseDao;

    @PostMapping("/queryCourse")
    public ResultResponse queryCourses(@RequestBody PageRequest pageRequest) {
        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();
        if (pageNum == null || pageSize == null) {
            return ResultResponse.fail(-1, "参数错误!");
        }
        int start = (pageNum - 1) * pageSize;
        List<CourseVO> courses = courseDao.queryCourse(start, pageSize);
        int total = courseDao.count();
        int totalPage = (int) Math.ceil(total * 1.0 / pageSize);
        Map<String, Object> data = new HashMap<>();
        data.put("totalPage", totalPage);
        data.put("courses", courses);
        return ResultResponse.success(data);
    }
}


