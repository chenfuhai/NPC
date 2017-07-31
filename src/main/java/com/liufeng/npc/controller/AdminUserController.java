package com.liufeng.npc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.AdminUserWithBLOBs;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loginedUser")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;


    //获取目前登录用户的信息
    @ResponseBody
    @RequestMapping(value = "/user/logined",method = RequestMethod.GET)
    public Msg getLoginedUser(HttpServletRequest request){
        AdminUser user = (AdminUser) request.getSession().getAttribute("loginedUser");
        if (user==null){
            //返回警告信息
            return Msg.error().add("msg","您尚未登录或登录已过期，请重新登录");
        }else{
            return Msg.success().add("loginedUser",user);
        }

    }


    //获取所有的用户
    @ResponseBody
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Msg getUsers(@RequestParam(value = "pn",defaultValue = "1")Integer pn){

        //使用PAGEHelper
        PageHelper.startPage(pn,10);
        List<AdminUser> users =  adminUserService.getAll();
        PageInfo pageInfo = new PageInfo(users,5);

        return Msg.success().add("pageInfo",pageInfo);
    }
    //获取对应ID的用户信息
    @ResponseBody
    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    public Msg getUser(@PathVariable("userId") Integer userId){
        AdminUser user = adminUserService.getUserById(userId);
        return Msg.success().add("user",user);
    }

    //登录
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg login(@RequestParam("userName")String userName ,@RequestParam("userPwd") String pwd ,Model model){

        String regx = "(^[a-zA-Z0-9_-]{6,16}$)";
        if(!userName.matches(regx)){
            return Msg.error().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        int type  = adminUserService.login(userName,pwd);

        Msg msg0 = Msg.error();
        msg0.msg="登录失败，不存在该用户";
        Msg msg1 = Msg.error();
        msg1.msg="登录失败，密码不正确";
        Msg msg2 = Msg.success();
        msg2.msg="登录成功";
        switch (type){
            case 0:return msg0;
            case 1:return msg1;
            case 2:
                AdminUser user = adminUserService.getUserByNameAndPwd(userName,pwd);
                if (user!=null){
                    model.addAttribute("loginedUser",user);
                    return msg2;
                }
            default:return Msg.error();
        }

    }
    //更新对应的用户信息
    @ResponseBody
    @RequestMapping(value = "/user/{adId}",method = RequestMethod.PUT)
    public Msg updateUser(AdminUserWithBLOBs adminUser){

        boolean flag = false;
        flag = adminUserService.updateUser(adminUser);
        if (flag){
            return Msg.success();
        }else  {
            Msg msgError = Msg.error();
            msgError.msg="更新失败，可能不存在该用户";
            return msgError;
        }

    }
    //删除对应的用户信息
    @ResponseBody
    @RequestMapping(value = "/user/{userIds}",method = RequestMethod.DELETE)
    public Msg delUser(@PathVariable("userIds") String userIds){
        //判断是删除单个还是删除多个 再分别执行操作
        System.out.println(userIds);
        if(userIds.contains("-")){

            List<Integer> del_ids = new ArrayList();

            String[] str_ids = userIds.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            //执行批量删除
            adminUserService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(userIds);
            //直接删除
            adminUserService.deleteUser(id);
        }

        return Msg.success();
    }
    //保存对应的用户
    @ResponseBody
    @RequestMapping(value ="/user",method = RequestMethod.POST)
    public Msg newUser(AdminUserWithBLOBs adminUserWithBLOBs){

        if (adminUserWithBLOBs!=null){
            if (adminUserWithBLOBs.getAdName()!=null && !adminUserWithBLOBs.getAdName().equals("")
                    && adminUserWithBLOBs.getAdPwd()!=null && !adminUserWithBLOBs.getAdPwd().equals("")){
                boolean flag = false;
                flag = adminUserService.saveUser(adminUserWithBLOBs);

                    if (flag){
                        return Msg.success();

                    }

            }
        }

        Msg msg =  Msg.error();
        msg.msg="新增用户失败 可能已存在用户";
        return msg;

    }

    @ResponseBody
    @RequestMapping("/logout")
    public Msg logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return Msg.success();
    }





}
