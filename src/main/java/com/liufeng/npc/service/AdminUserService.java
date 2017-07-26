package com.liufeng.npc.service;

import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.AdminUserExample;
import com.liufeng.npc.bean.AdminUserWithBLOBs;
import com.liufeng.npc.dao.AdminUserMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.AdjustmentEvent;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    AdminUserMapper adminUserMapper;


    //获取全部的用户信息
    public List<AdminUser> getAll() {

        List<AdminUser> users = adminUserMapper.selectByExample(null);
        return users;
    }

    public AdminUser getUserById(Integer userId) {
        AdminUserWithBLOBs user = adminUserMapper.selectByPrimaryKey(userId);
        return user;
    }


    public boolean isExistUser(String userName, String pwd) {
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andAdNameEqualTo(userName).andAdPwdEqualTo(pwd);
        List<AdminUser> users = adminUserMapper.selectByExample(example);
        if (users.size()>0){
            return  true;
        }else  {
            return false;
        }
    }

    public boolean updateUser(AdminUserWithBLOBs adminUser) {

        int i = adminUserMapper.updateByPrimaryKeySelective(adminUser);
        if (i==0){
            return false;
        }else{
            return true;
        }
    }

    //批量删除用户
    public void deleteBatch(List<Integer> del_ids) {
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andAdIdIn(del_ids);

        int i = adminUserMapper.deleteByExample(example);

    }

    //删除用户
    public void deleteUser(Integer id) {

        adminUserMapper.deleteByPrimaryKey(id);
    }

    public boolean saveUser(AdminUserWithBLOBs adminUserWithBLOBs) {
        //判断是否已经存在这个用户了
        AdminUserExample e = new AdminUserExample();
        e.createCriteria().andAdNameEqualTo(adminUserWithBLOBs.getAdName());
        List<AdminUser> adminUsers = adminUserMapper.selectByExample(e);
        if (adminUsers.size()>0){
            //已存在用户
            return false;
        }
        int i = adminUserMapper.insertSelective(adminUserWithBLOBs);
        if (i>0){
            //c插入成功
            AdminUserExample example = new AdminUserExample();
            example.createCriteria().andAdNameEqualTo(adminUserWithBLOBs.getAdName());

            List<AdminUser> users = adminUserMapper.selectByExample(example);
            if (users.size()>0){

                return  true;
            }
        }
        return false;
    }
}
