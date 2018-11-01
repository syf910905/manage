package com.demo.yunfei.web;

import com.demo.yunfei.common.HttpMessageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yunfei
 * @date : 2018/11/1 14:21
 */
@RestController
@RequestMapping("/sys")
public class AdminController {

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public HttpMessageResult getMessage() {
        return HttpMessageResult.success("您拥有管理员权限，可以获得该接口的信息！");
    }
}
