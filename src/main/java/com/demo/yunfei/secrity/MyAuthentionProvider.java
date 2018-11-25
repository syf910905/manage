package com.demo.yunfei.secrity;

import cn.hutool.crypto.SecureUtil;
import com.demo.yunfei.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author : yunfei
 * @date : 2018/11/25 13:44
 */
@Component
public class MyAuthentionProvider implements AuthenticationProvider {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MyUserDetailServiceImpl userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String passWord = (String) authentication.getCredentials();
        UserDetails user = userDetailsService.loadUserByUsername(name);
        if (user == null) {
            throw new BadCredentialsException("用户不存在");
        }
        String encryptPassword = SecureUtil.md5(passWord);
        if (!user.getPassword().equals(encryptPassword)) {
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(user, passWord, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {

        //这里直接改成retrun true;表示是支持这个执行
        return true;
    }
}
