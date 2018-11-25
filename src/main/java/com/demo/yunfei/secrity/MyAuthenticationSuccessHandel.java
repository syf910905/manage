package com.demo.yunfei.secrity;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yunfei
 * @date : 2018/11/25 15:05
 */
@Component
public class MyAuthenticationSuccessHandel extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
        Map<String,Object> map=new HashMap<>(2);
        map.put("code",200);
        map.put("message","success");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(map));

        new DefaultRedirectStrategy().sendRedirect(request,response,"/whoim");
    }
}
