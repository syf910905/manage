package com.demo.yunfei.secrity;

import com.demo.yunfei.dao.UserDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : yunfei
 * @date : 2018/11/25 16:11
 */
@Component("verifyService")
public class VerifyServiceImpl implements VerifyService {

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private UserDao userDao;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            List<String> list = userDao.getRoleUrlList(username);
            for (String url : list) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
