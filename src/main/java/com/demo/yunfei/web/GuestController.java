package com.demo.yunfei.web;

import com.demo.yunfei.common.HttpMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yunfei
 * @date : 2018/11/1 14:16
 */
@RestController
@RequestMapping("/guest")
public class GuestController{

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public HttpMessageResult login() {
        return HttpMessageResult.success("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public HttpMessageResult submitLogin() {
        return HttpMessageResult.success("您拥有获得该接口的信息的权限！");
    }
}
