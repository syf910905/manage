package com.demo.yunfei.web;

import com.demo.yunfei.common.HttpMessageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yunfei
 * @date : 2018/11/1 14:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public HttpMessageResult submitLogin() {
        return HttpMessageResult.success("您拥有用户权限，可以获得该接口的信息！！");
    }

}
