/*
package com.demo.yunfei.web;

import com.demo.yunfei.common.HttpMessageResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(description = "登录业务类")
@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public HttpMessageResult loginIn(String username, String password, boolean rememberMe){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken passwordToken=new UsernamePasswordToken(username,password,rememberMe);
        try {
            subject.login(passwordToken);
        } catch (AuthenticationException e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            return HttpMessageResult.failure(500,e.getMessage());
        }
        return HttpMessageResult.success();
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
}
*/
