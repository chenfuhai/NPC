package com.liufeng.npc.controller;

import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.utils.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorityController {

    /**
     * 判断用户是否有权限 request里面存放已经登录的用户类
     *
     * @return
     */
    public static boolean isAuthorized(String url,AdminUser loginedUser) {
        //如果是index这个URL 就放行 如果不是 就检查权限
        //结合request取出来的用户类信息


        Log.logI(url);
        if (url.indexOf("index") >= 0) {
            Log.logI(url.indexOf("index") + "");
            return true;
        }


        Log.logI(loginedUser + "");
        if (loginedUser != null) {
            Log.logI("dfdsfdsf");
            return true;
        }
        return false;

    }

    @RequestMapping("/admin/main")
    public String toMain(){
        return "main";
    }


    @RequestMapping("/admin/home")
    public String toHome(){
        return "home";
    }

    @RequestMapping("/admin")
    public String toAdmin(){
        return "adminLogin";
    }

    @RequestMapping("/invalidAccess")
    public String toInvalidAccess(){
        return "invalidAccess";
    }

    //========================================ligerUI================
    @RequestMapping("/admin/draftBox")
    public String toDrftBox(){
        return "DraftBox";
    }

    @RequestMapping("/admin/welcome_main")
    public String toWelcome(){
        return "welcome_main";
    }

    //=====================kindEditor===============
    @RequestMapping("/admin/kindEditor")
    public String toKindEditor(){
        return "kindEditor";
    }


}
