package com.iss.edu.online.controller;


import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.EmployeeDao;
import com.iss.edu.online.model.Department;
import com.iss.edu.online.model.Employee;
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

    /*
    {
        "username":"zhouzhuo",
        "password":"123456",
    }
    */
    // http://localhost:8080/api/login
    @PostMapping("/login")
    public ResultResponse login(@RequestBody User user) {
        ResultResponse response = new ResultResponse<>();
        User currentUser = this.employeeDao.login(user);
        if (currentUser != null) {
            String token = UUID.randomUUID().toString();
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", currentUser);
            response.setCode(20000);
            response.setMessage("Operate Success !");
            response.setData(data);
        } else {
            response.setCode(50000);
            response.setMessage("Operate Failure !");
        }
        return response;
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
    public String insertEmployee(@RequestBody Employee employee) {
        int result = this.employeeDao.insert(employee);
        return result > 0 ? "Insert Success ..." : "Insert Error ...";
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
    public String updateEmployee(@RequestBody Employee employee) {
        int result = this.employeeDao.update(employee);
        return result > 0 ? "Update Success ..." : "Update Error ...";
    }

    // http://localhost:8080/api/deleteEmployee/9527
    @GetMapping("/deleteEmployee/{empno}")
    public String deleteEmployee(@PathVariable("empno") int empno) {
        int result = this.employeeDao.delete(empno);
        return result > 0 ? "Delete Success ..." : "Delete Error ...";
    }


    // http://localhost:8080/api/queryEmployees
    @GetMapping("/queryEmployees/{pageNum}/{pageSize}")
    public ResultResponse queryEmployees(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        ResultResponse response = new ResultResponse<>();
        int start = (pageNum - 1) * pageSize;
        List<Employee> employees = this.employeeDao.queryEmployees(start, pageSize);
        int total = this.employeeDao.count();
        Map<String, Object> data = new HashMap<>();
        data.put("employees", employees);
        data.put("total", total);
        response.setCode(20000);
        response.setMessage("Operate Success !");
        response.setData(data);
        return response;
    }

    // http://localhost:8080/api/queryByParams
    /*
    {
        "ename":"ZZ",
        "job":"BOSS"
    }
    */
    @GetMapping("/queryByParams")
    public List<Employee> queryByParams(@RequestBody Employee employee) {
        return this.employeeDao.queryByParams(employee);
    }

    // http://localhost:8080/api/deletes?empnos=95271,95272,95273,95274
    @GetMapping("/deletes")
    public String deletes(Integer[] empnos) {
        int result = 1; // this.employeeDao.deletes(empnos);
        return result > 0 ? "Delete Success ..." : "Delete Error ...";
    }

    // http://localhost:8080/api/queryEmployeesByDeptno/20
    @GetMapping("/queryEmployeesByDeptno/{deptno}")
    public List<Department> queryEmployeesByDeptno(@PathVariable("deptno") int deptno) {
        return this.employeeDao.queryEmployeesByDeptno(deptno);
    }

    // http://localhost:8080/api/queryEmployeesVO
    @GetMapping("/queryEmployeesVO")
    public List<Map<String, Object>> queryEmployeesVO() {
        return this.employeeDao.queryEmployeesVO();
    }

}
