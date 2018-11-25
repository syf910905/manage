package com.demo.yunfei.secrity;

import com.demo.yunfei.dao.UserDao;
import com.demo.yunfei.mapper.master.UserRoleMapper;
import com.demo.yunfei.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author : yunfei
 * @date : 2018/11/22 20:17
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Admin admin = userDao.getUserByUserName(userName);
        if (admin != null) {
            String roleId = userDao.getUserRole(admin.getId());
            UserInfoRealm user = new UserInfoRealm(admin.getUsername(), admin.getPassword(), roleId, true, true, true, true);
            return user;
        }
        return null;
    }


}
