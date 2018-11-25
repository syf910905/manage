package com.demo.yunfei.dao;

import com.demo.yunfei.mapper.master.AdminMapper;
import com.demo.yunfei.mapper.master.PermissionMapper;
import com.demo.yunfei.mapper.master.RolePermissionMapper;
import com.demo.yunfei.mapper.master.UserRoleMapper;
import com.demo.yunfei.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : yunfei
 * @date : 2018/11/25 13:48
 */
@Repository
public class UserDao {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserRoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    public Admin getUserByUserName(String name) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public String getUserRole(Long id) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(id);
        List<UserRole> userRoles = roleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(userRoles)) {
            return userRoles.get(0).getRoleId().toString();
        }
        return null;
    }

    public List<String> getRoleUrlList(String username) {
        Admin admin = getUserByUserName(username);
        String role = getUserRole(admin.getId());
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(Integer.parseInt(role));
        List<RolePermission> list = rolePermissionMapper.selectByExample(example);
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andIdIn(list.stream().map(RolePermission::getPermissionId).collect(Collectors.toList()));
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        return permissionList.stream().map(Permission::getUrl).collect(Collectors.toList());

    }
}
