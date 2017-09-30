package com.liufeng.npc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class ShiroTest {
    @Test
    public void Test(){
        //根据数据源获取subjectManager的工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //借用SecurtyUtils获取subjct 要设置manager manager通过工厂获取
        SecurityUtils.setSecurityManager(iniSecurityManagerFactory.getInstance());
        Subject subject = SecurityUtils.getSubject();
        //使用subject的login方法判断 login传入用户输入的凭证token
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        //捕捉错误，如果有错就捕捉出来然后返回信息
        try {
            subject.login(token);
            if (subject.isAuthenticated()){
                System.out.println("login success");
            }

            Assert.assertEquals(true,subject.isAuthenticated());
        }catch (AuthenticationException e){
            System.out.println("login failed");
        }

        subject.logout();
    }
    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }


}
