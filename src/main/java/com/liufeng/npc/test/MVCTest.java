package com.liufeng.npc.test;

import com.liufeng.npc.utils.Md5Tool;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {
    @Autowired
    WebApplicationContext context;
    //虚拟mvc
    MockMvc mockMvc;

    @Before
    public void initMokMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("userName","liufengkj")
                .param("userPwd","123456")).andReturn();
        //请求成功以后 在请求域中会有pageInfo 取出进行验证

        System.out.println(result.getRequest().getRequestURI());

//        System.out.println(result.getRequest().getSession().getAttribute("loginedUser").toString());

        System.out.println(Md5Tool.getMd5("123456"));

        String path = result.getRequest().getServletContext().getRealPath("/");
        System.out.println(path);
        System.out.println(result.getRequest().getContextPath());

    }

}
