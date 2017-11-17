package com.liufeng.npc.realm;

import com.liufeng.npc.bean.AdminUserWithBLOBs;
import com.liufeng.npc.service.AdminUserService;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.ehcache.impl.internal.concurrent.JSR166Helper;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRealm extends AuthenticatingRealm {
@Autowired
    AdminUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //authenticationToken 保存了从前台输入的密码
        //AuthenticationInfo 保存了从数据库中获取的用户密码
        //shiro在比对的时候 源代码发现 使用的是eauql shiro 获取了两个token Credentials的 然后对比这两个对象是否是同一个
        //同理 认证的实体对象 虽然现在在前台传进去的是一个string 其实两个都是principal

        //1.把AuthenticationToken 转化为UsernamePasswoedTken
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //源码分析 这里的getPrincipal 就是getUsername getCredentials就是getPassword 而且还是char【】 类型的 所以强制类型转化出错了
        String username = (String) token.getPrincipal();
        // String userpassword = (String) token.getCredentials();
        String userpassword = new String (token.getPassword());
        System.out.println(username+"    "+userpassword);
        //2.调用数据库方法 获取用户 用户不存在可以抛出AuthenticationException异常的子类
        //3.是否需要抛出其他异常
        if (!userService.isExistUser(username)){
            throw new UnknownAccountException("用户不存在");
        }
        AdminUserWithBLOBs user = userService.getUserByNameAndPwd(username, userpassword);
        System.out.println(user);

        //4. 根据用户的情况构建Info对象 通常使用的是其并返回
        //参数 principal 认证的实体信息 可以是username 也可以是数据库对应的用户的实体类对象
        //credentials 数据库表中获取的密码
        //realmname 当前realm对象的name调用父类的getname

        //因为前台输入的token中 类型是char【】 所以这里注意要两个对应 不然shiro内部转化的时候会出异常 并且要判断下user会不会存在 如果不存在报空指针异常也很麻烦
        Object principal;
        Object credentials;
        if (user==null){
            principal = token.getUsername();
            credentials = new Object();
        }else{
            principal = user.getAdName();
            credentials = user.getAdPwd().toCharArray();
        }


        String realmname = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmname);
        return info;
    }
}
