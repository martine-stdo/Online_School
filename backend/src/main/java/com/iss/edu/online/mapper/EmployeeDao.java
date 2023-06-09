package com.iss.edu.online.mapper;

import com.iss.edu.online.model.Department;
import com.iss.edu.online.model.Employee;
import com.iss.edu.online.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface EmployeeDao {

    int insert(Employee employee);

    int update(Employee employee);

    int delete(int empno);

    List<Employee> queryEmployees();

    List<Employee> queryByParams(Employee employee);

    int deletes(Integer[] empnos);

    List<Department> queryEmployeesByDeptno(int deptno);

    List<Map<String, Object>> queryEmployeesVO();

    User login(User user);
}
