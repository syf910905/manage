package com.demo.yunfei.dao;

import com.demo.yunfei.mapper.master.*;
import com.demo.yunfei.model.*;
import com.demo.yunfei.model.dto.SysUser;
import org.apache.catalina.security.SecurityUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : yunfei
 * @date : 2018/10/30 19:43
 */
@Repository
public class LoginDao {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public Set<String> selectMenuByUserName(String userName) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(userName);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdIn(adminList.stream().map(admin -> admin.getId()).collect(Collectors.toList()));
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdIn(userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
        Set<String> set = rolePermissionList.stream().map(rolePermission -> String.valueOf(rolePermission.getPermissionId())).collect(Collectors.toSet());
        return set;
    }

    public Set<String> selectRolesByUser(String userName) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(userName);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdIn(adminList.stream().map(admin -> admin.getId()).collect(Collectors.toList()));
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
        Set<String> set = userRoleList.stream().map(userRole -> String.valueOf(userRole.getRoleId())).collect(Collectors.toSet());
        return set;
    }

    public SysUser login(String username, String password) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        if(CollectionUtils.isNotEmpty(adminList)){
            return SysUser.builder().admin(adminList.get(0)).build();
        }else{
            throw new RuntimeException("用户不存在");
        }
    }

    /**
     * 密码加密
     *
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + "yunfei").toHex().toString();
    }
}
