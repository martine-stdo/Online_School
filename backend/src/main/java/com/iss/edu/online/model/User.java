package com.iss.edu.online.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer sex;
    private Integer age;

}
