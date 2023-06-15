package com.iss.edu.online.controller.UserController;

import com.iss.edu.online.common.ResultResponse;
import com.iss.edu.online.mapper.UserDao;
import com.iss.edu.online.model.User;
import com.iss.edu.online.utils.CheckAdmin;
import com.iss.edu.online.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

@RestController
public class ShowUserInfo {
    @Autowired private CheckAdmin checkAdmin;
    @Autowired private UserDao userDao;
    @GetMapping ("/user/info")
    public ResultResponse userInfo(@RequestHeader("Authorization") String authorization) throws Exception {
        String jwt = checkAdmin.extractJwt(authorization);
        Claims claims = JwtUtil.parseJWT(jwt);
        String userId = claims.getSubject();
        int id = Integer.parseInt(userId);
        User user =  userDao.selectById(id);
        user.setPassword(null);
        return ResultResponse.success(user);
    }
}
