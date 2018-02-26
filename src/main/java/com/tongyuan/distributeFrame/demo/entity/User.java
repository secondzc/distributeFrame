package com.tongyuan.distributeFrame.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tongyuan.distributeFrame.base.BaseModel;

import java.io.Serializable;

/**
 * Created by zhangcy on 2018/2/15
 */
@TableName("user")
public class User extends BaseModel{
    private String age;
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @TableField(exist = false)
    private String email;


    public User(String age, String username,  String password) {
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "age='" + age + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}