package com.liufeng.npc.controller;

import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.EditorMsg;
import com.liufeng.npc.utils.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthorityController {

    /**
     * 判断用户是否有权限 request里面存放已经登录的用户类
     *
     * @return
     */

    private final static String invalidAccessUrl = "/invalidAccess";

    public static boolean isAuthorized(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //如果是index这个URL 就放行 如果不是 就检查权限
        //结合request取出来的用户类信息
        if (httpServletRequest.getRequestURI().contains("/admin")) {
            String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getRequestURI().lastIndexOf("/admin") + 6);
            Log.logI(url);
            AdminUser user = (AdminUser) httpServletRequest.getSession().getAttribute("loginedUser");
            System.out.println(httpServletRequest.getMethod());
            if (user == null) {

                if (httpServletRequest.getMethod().equals("GET") && !url.startsWith("/users")) {
                    return true;

                } else if (url.startsWith("/login") || url.equals("") || url.startsWith("/invalidAccess")
                        || url.startsWith("/error")) {
                    return true;

                } else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    try {
                        httpServletResponse.getWriter().print(EditorMsg.getError("非法访问，可能登陆已过期"));
                        httpServletResponse.sendRedirect(invalidAccessUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            } else {
                //用户不为空 正常访问 可以添加日志 或者在这里检查权限
                return true;
            }
        } else {
            return true;
        }


    }

    @RequestMapping("/admin/main")
    public String toMain() {
        return "main";
    }


    @RequestMapping("/admin/home")
    public String toHome() {
        return "home";
    }

    @RequestMapping("/admin")
    public String toAdmin() {
        return "adminLogin";
    }

    @RequestMapping("/invalidAccess")
    public String toInvalidAccess() {
        return "invalidAccess";
    }

    //========================================ligerUI================
    @RequestMapping("/admin/manager")
    public String toDrftBox() {
        return "ManagerArt";
    }

    @RequestMapping("/admin/welcome_main")
    public String toWelcome() {
        return "welcome_main";
    }

    //=====================kindEditor===============
    @RequestMapping("/admin/kindEditor")
    public String toKindEditor() {
        return "kindEditor";
    }


}
