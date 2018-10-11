package com.fang.ychat.config;

import com.fang.ychat.dao.PermissionMapper;
import com.fang.ychat.dao.RoleMapper;
import com.fang.ychat.dao.Role_PermissionMapper;
import com.fang.ychat.dao.User_RoleMapper;
import com.fang.ychat.pojo.Role;
import com.fang.ychat.pojo.User;
import com.fang.ychat.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    User_RoleMapper user_roleMapper;
    //下面的几个由于没有扩展，没写service 直接调用了Mapper
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    Role_PermissionMapper role_permissionMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username =(String) principalCollection.getPrimaryPrincipal();
        User user = userService.selectByUsername(username);

        //角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        int roleid = user_roleMapper.findUserRoleId(user.getId());
        Role role = roleMapper.findRole(roleid);
        simpleAuthorizationInfo.addRole(role.getRoleName());
        int permissionid = role_permissionMapper.findPermissionId(role.getId());
        simpleAuthorizationInfo.addStringPermission(permissionMapper.findPermission(permissionid));
        return simpleAuthorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.selectByUsername(username);
        System.out.println("***********");
            if(user == null){
                throw new UnknownAccountException();
            }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
            return authenticationInfo;
    }
}
