package com.iss.edu.online.controller;


import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.EmployeeDao;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.Department;
import com.iss.edu.online.model.Employee;
import com.iss.edu.online.model.EmployeeVO;
import com.iss.edu.online.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${server.port}")
    int port;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    UserDao userDao;

    /*
    {
        "username":"zhouzhuo",
        "password":"123456",
    }
    */
    // http://localhost:8080/api/login
    @PostMapping("/login")
    public ResultResponse login(@RequestBody User user) {
        if(user == null){
            return ResultResponse.fail(-1,"参数错误!");
        }
        User currentUser = this.userDao.selectUser(user.getUsername());
        if (currentUser != null) {
            String token = UUID.randomUUID().toString();
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", currentUser);
            return ResultResponse.success(data);
        }
        return ResultResponse.fail(-2,"登录失败!");
    }


    // http://localhost:8080/api/insertEmployee
    /*
    {
        "empno":9527,
        "ename":"zhouzhuo",
        "password":"123456",
        "job":"Teacher",
        "mgr":0,
        "hiredate":"2023-06-06",
        "sal":10086.66,
        "comm":6.6,
        "deptno":10
    }
    */
    @PostMapping("/insertEmployee")
    public ResultResponse insertEmployee(@RequestBody Employee employee) {
        if(employee == null){
            return ResultResponse.fail(-1,"参数错误!");
        }
        int result = this.employeeDao.insert(employee);
        if(result > 0){
            return ResultResponse.success(result);
        }
        return ResultResponse.fail(-2,"插入失败!");
    }


    // http://localhost:8080/api/updateEmployee
    /*
    {
        "empno":9527,
        "ename":"ZZ",
        "password":"111111",
        "job":"BOSS"
    }
    */
    @PostMapping("/updateEmployee")
    public ResultResponse updateEmployee(@RequestBody Employee employee) {
        if(employee == null){
            return ResultResponse.fail(-1,"参数错误!");
        }
        int result = this.employeeDao.update(employee);
        if(result > 0){
            return ResultResponse.success(result);
        }
        return ResultResponse.fail(-2,"更新失败!");
    }

    // http://localhost:8080/api/deleteEmployee/9527
    @GetMapping("/deleteEmployee/{empno}")
    public ResultResponse deleteEmployee(@PathVariable("empno") Integer empno) {
        if(empno == null){
            return ResultResponse.fail(-1,"参数错误!");
        }
        int result = this.employeeDao.delete(empno);
        if(result > 0){
            return ResultResponse.success(result);
        }
        return ResultResponse.fail(-2,"删除失败!");
    }


    // http://localhost:8080/api/queryEmployees
    @GetMapping("/queryEmployees/{pageNum}/{pageSize}")
    public ResultResponse queryEmployees(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        if(pageNum == null || pageSize == null){
            return ResultResponse.fail(-1,"参数错误!");
        }
        int start = (pageNum - 1) * pageSize;
        List<Employee> employees = this.employeeDao.queryEmployees(start, pageSize);
        int total = this.employeeDao.count();
        Map<String, Object> data = new HashMap<>();
        data.put("employees", employees);
        data.put("total", total);
        return ResultResponse.success(data);
    }

    // http://localhost:8080/api/queryByParams
    /*
    {
        "ename":"ZZ",
        "job":"BOSS"
    }
    */
    @GetMapping("/queryByParams")
    public ResultResponse queryByParams(@RequestBody Employee employee) {
        if(employee == null){
            return ResultResponse.fail(-1,"参数错误!");
        }
        List<Employee> ret = this.employeeDao.queryByParams(employee);
        return ResultResponse.success(ret);
    }

    // http://localhost:8080/api/deletes?empnos=95271,95272,95273,95274
    @GetMapping("/deletes")
    public ResultResponse deletes(Integer[] empnos) {
        if(empnos == null){
            return ResultResponse.fail(-1,"参数异常!");
        }
        int result = this.employeeDao.deletes(empnos);
        if(result > 0){
            return ResultResponse.success(result);
        }
        return ResultResponse.fail(-2,"删除失败!");
    }

    // http://localhost:8080/api/queryEmployeesByDeptno/20
    @GetMapping("/queryEmployeesByDeptno/{deptno}")
    public ResultResponse queryEmployeesByDeptno(@PathVariable("deptno") Integer deptno) {
        if(deptno == null){
            return ResultResponse.fail(-1,"参数异常!");
        }
        List<Department> ret = this.employeeDao.queryEmployeesByDeptno(deptno);
        return ResultResponse.success(ret);
    }

    // http://localhost:8080/api/queryEmployeesVO
    @GetMapping("/queryEmployeesVO")
    public ResultResponse queryEmployeesVO() {
        List<EmployeeVO> ret = this.employeeDao.queryEmployeesVO();
        return ResultResponse.success(ret);
    }

}
