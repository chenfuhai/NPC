package com.liufeng.npc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liufeng.npc.bean.*;
import com.liufeng.npc.service.AdminUserService;
import com.liufeng.npc.utils.Md5Tool;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
        //始终从数据库中提取最准确的用户信息
        if (user==null){
            //返回警告信息
            return Msg.error().add("msg","您尚未登录或登录已过期，请重新登录");
        }else{
            AdminUser userCurrent = adminUserService.getUserById(user.getAdId());
            return Msg.success().add("loginedUser",userCurrent);
        }

    }


    //获取所有的用户
    @ResponseBody
    @RequestMapping(value = "/users",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") Integer pn,
                        @RequestParam(value = "pagesize", defaultValue = "1") Integer pagesize,
                        @RequestParam(value = "sortname", defaultValue = "publicTime") String sortname,
                        @RequestParam(value = "sortorder", defaultValue = "desc") String sortorder
    ){

        //使用PAGEHelper
        PageHelper.startPage(pn,pagesize);
        List<AdminUser> users =  adminUserService.getAll();
        PageInfo pageInfo = new PageInfo(users,5);

        return dealUsersToJson(pageInfo);
    }


    /**
     * 处理pageInfo里面的数据 转化成Ligerui可接受的JSON
     *

     * @return
     */
    private String dealUsersToJson( PageInfo pageInfo) {
        List users = pageInfo.getList();
        long total = pageInfo.getTotal();



        JSONArray array = new JSONArray();
        for (Object user : users) {
            AdminUser u = (AdminUser) user;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", u.getAdId());
            jsonObject.put("name", u.getAdName());
            jsonObject.put("info", u.getAdInfo());

            jsonObject.put("powercode", u.getAdPowercode());

            array.add(jsonObject);
        }

        Gson gson = new GsonBuilder().create();
        pageInfo.setList(null);
        JSONObject obj = new JSONObject();
        obj.put("Rows", array);
        obj.put("Total", total);
        obj.put("pageInfo",gson.toJsonTree(pageInfo));
        System.out.println(obj.toString());
        return obj.toString();

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
    public Msg login(@RequestParam("userName")String userName , @RequestParam("userPwd") String pwd , Model model, HttpSession session){

        String regx = "(^[a-zA-Z0-9_-]{3,16}$)";
        if(!userName.matches(regx)){
            Msg msg = Msg.error();
            msg.msg="用户名必须是3-16位数字和字母的组合";
            return msg;
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
                AdminUserWithBLOBs user = adminUserService.getUserByNameAndPwd(userName,pwd);
                if (user!=null){
                    model.addAttribute("loginedUser",user);
                    session.setMaxInactiveInterval(30*60);
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

    //更改密码
    @ResponseBody
    @RequestMapping(value = "/user/changePwd",method = RequestMethod.PUT)
    public Msg updateUser(@RequestParam(value = "newpwd",defaultValue = "")String newpwd,@RequestParam(value = "oldpwd",defaultValue = "")String oldpwd,HttpSession session){

        AdminUserWithBLOBs user = (AdminUserWithBLOBs) session.getAttribute("loginedUser");
        if (user==null){
            //返回警告信息
            return Msg.error().add("msg","您尚未登录或登录已过期");
        }else{

            if (oldpwd.equals("")){
                return Msg.error().add("msg","没有填写旧密码");
            }else{
                if(!user.getAdPwd().equals(Md5Tool.getMd5(oldpwd))){
                    return Msg.error().add("msg","旧密码不正确");
                }
            }
            if (newpwd.equals("")){
                return Msg.error().add("msg","没有填写新密码");
            }else{
                user.setAdPwd(newpwd);
                boolean flag = false;
                flag = adminUserService.updateUser(user);
                if (flag){
                    return Msg.success().add("msg","修改成功");
                }else  {
                    Msg msgError = Msg.error().add("msg","修改失败");
                    return msgError;
                }
            }
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

                adminUserWithBLOBs.setAdPowercode(1);
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
