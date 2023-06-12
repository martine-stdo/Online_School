package com.iss.edu.online.mapper;

import com.iss.edu.online.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User selectUser(@Param("username") String username);
}
