//package com.tongyuan.distributeFrame.demo.controller;
//
//import com.tongyuan.distributeFrame.base.BaseController;
//import com.tongyuan.distributeFrame.demo.entity.User;
//import com.tongyuan.distributeFrame.exception.DuplicateNameException;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.ExcessiveAttemptsException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
///**
// * Created by zhangcy on 2018/2/26
// */
//@RestController
//@RequestMapping("/user")
//public class LoginController extends BaseController{
//     private final Logger logger = LogManager.getLogger();
//
//    @Autowired
//    private UserService userService;
//
//    @ResponseBody
//    @PostMapping("/register")
//    public Map<String,Object> register(User user){
//        try{
//            userService.insert(user);
//        }catch (DuplicateNameException e){
//            logger.info("注册失败");
//            return setErrorResponse(e.getMessage());
//        }
//        logger.info("{} 注册成功",user);
//        return setSuccessResponse("注册成功！");
//    }
//
//    @ResponseBody
//    @GetMapping("/login")
//    public Map<String,Object> login(String username, String password, Boolean rememberme){
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberme);
//        Subject subject = SecurityUtils.getSubject();
//        try{
//            subject.login(token);
//        }catch(IncorrectCredentialsException ice){
//            return setErrorResponse("密码错误");
//        }catch(UnknownAccountException uae){
//            return setErrorResponse("未知用户名");
//        }catch(ExcessiveAttemptsException eae){
//            return setErrorResponse("错误登录过多");
//        }
//        User user = userService.queryByUsername(username);
//        //登录完成以后，当前用户信息被保存进Session。这个Session是通过Shiro管理的会话对象，要获取依然必须通过Shiro。传统
//        // 的Session中不存在User对象。
//        subject.getSession().setAttribute("loginUser",user);
//        logger.info("{} 登录成功",user);
//        return setSuccessResponse("登录成功");
//    }
//
//    @ResponseBody
//    @PostMapping("/logout")
//    public Map<String,Object> logout(){
//        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().removeAttribute("loginUser");
//        logger.info("{} 登出成功",subject.getPrincipal());
//        return setSuccessResponse("登出成功");
//    }
//
//}
