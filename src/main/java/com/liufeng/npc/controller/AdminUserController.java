package com.liufeng.npc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.AdminUserExample;
import com.liufeng.npc.bean.AdminUserWithBLOBs;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
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
    @RequestMapping("/login")
    public Msg login(@RequestParam("userName")String userName ,@RequestParam("userPwd") String pwd ){

        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if(!userName.matches(regx)){
            return Msg.error().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        boolean flag =false;
                flag = adminUserService.isExistUser(userName,pwd);
        if (flag){

            return Msg.success();
        }else  {
            Msg msgError = Msg.error();
            msgError.msg="登录失败，不存在该用户";
            return msgError;
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
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Msg newUser(AdminUserWithBLOBs adminUserWithBLOBs){
        if (adminUserWithBLOBs!=null){
            if (adminUserWithBLOBs.getAdName()!=null && adminUserWithBLOBs.getAdName().equals("")
                    && adminUserWithBLOBs.getAdPowercode()!=null && adminUserWithBLOBs.getAdPowercode().equals("")){
                AdminUser user = adminUserService.saveUser(adminUserWithBLOBs);
                if (user!=null){
                    return Msg.success();
                }
            }
        }
        Msg msg =  Msg.error();
        msg.msg="新增用户失败 可能已存在用户";
        return msg;

    }







}
