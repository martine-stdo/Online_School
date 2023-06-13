package com.iss.edu.online.mapper;

import com.iss.edu.online.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    User selectUser(@Param("username") String username);
    User seleceById(@Param("id") Integer id);
    int insertUser(User user);
}
