package com.demo.yunfei.secrity;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : yunfei
 * @date : 2018/11/25 16:09
 */
public interface VerifyService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
