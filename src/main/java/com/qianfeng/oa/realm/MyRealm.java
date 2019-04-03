package com.qianfeng.oa.realm;

import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("登录认证。。。");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //得到用户名
        String userName = usernamePasswordToken.getUsername();
        //通过用户名查询用户对象
        SysUser  sysUser = sysUserService.getUserByName(userName);
        if (sysUser != null){ //根据用户名查询到对象不为空
            //给前端传来密码加盐值后和数据库密码比较
            ByteSource byteSource = ByteSource.Util.bytes(userName);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getUserPassword(), byteSource, this.getName());
            return info;
        }
        return null;
    }
}
