package com.liufeng.npc.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {
    public String getName() {
        return "myRealm";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    ///根据Token获取认证信息  如果身份认证验证成功，返回一个AuthenticationInfo实现；
    //Authenticator的职责是验证用户帐号，是Shiro API中身份验证核心的入口点：
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password =new String((char[])authenticationToken.getCredentials());
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
