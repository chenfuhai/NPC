package com.liufeng.npc.controller;

import com.liufeng.npc.bean.AdminUserWithBLOBs;
import com.liufeng.npc.bean.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminUserController {
    //获取所有的用户
    @ResponseBody
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Msg getUsers(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        return Msg.success();
    }
    //获取对应ID的用户信息
    @ResponseBody
    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    public Msg getUser(@PathVariable("userId") Integer userId){
        return Msg.success();
    }

    //检查用户名密码是否合法
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(@RequestParam("userName")String userName ){
        //先判断用户名是否是合法的表达式;
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if(!userName.matches(regx)){
            return Msg.error().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }

        //数据库用户名重复校验
        //boolean b = employeeService.checkUser(empName);
//        if(b){
//            return Msg.success();
//        }else{
//            return Msg.error().add("va_msg", "用户名不可用");
//        }
        return Msg.error();
    }
    //更新对应的用户信息
    @ResponseBody
    @RequestMapping(value = "/user/{adId}",method = RequestMethod.PUT)
    public Msg updateUser(AdminUserWithBLOBs adminUserWithBLOBs){
        return Msg.success();
    }
    //删除对应的用户信息
    @ResponseBody
    @RequestMapping(value = "/user/{userIds}",method = RequestMethod.DELETE)
    public Msg delUser(@PathVariable("userIds") String userIds){
        //判断是删除单个还是删除多个 再分别执行操作
        return Msg.success();
    }
    //保存对应的用户
    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Msg saveUser(AdminUserWithBLOBs adminUserWithBLOBs){
        return Msg.success();
    }







}
