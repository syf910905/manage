package com.demo.yunfei.web;

import com.demo.yunfei.common.HttpMessageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : yunfei
 * @date : 2018/10/30 15:13
 */
@Api(description = "secrity登录业务类")
@Controller
@Slf4j
public class SecurityLoginController {

    @RequestMapping("/login")
    public String login(){
        return "login-security";
    }


    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/list")
    public String list(){
        return "list";
    }

    @GetMapping("/errorPage")
    public String error(){
        return "errorPage";
    }

    @GetMapping("/whoim")
    @ResponseBody
    public Object whoim(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping("login/init")
    @ResponseBody
    public Object init(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
