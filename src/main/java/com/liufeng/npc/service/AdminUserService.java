package com.liufeng.npc.service;

import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.AdminUserExample;
import com.liufeng.npc.bean.AdminUserWithBLOBs;
import com.liufeng.npc.dao.AdminUserMapper;
import com.liufeng.npc.utils.Md5Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AdminUser getUserByNameAndPwd(String userName,String pwd){
        pwd = Md5Tool.getMd5(pwd);
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andAdPwdEqualTo(pwd).andAdNameEqualTo(userName);

        List<AdminUser> adminUsers = adminUserMapper.selectByExample(example);
        if (adminUsers.size()>0){
            return adminUsers.get(0);
        }else {
            return null;
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

    public boolean isExistUser(String userName){

        //判断是否已经存在这个用户了
        AdminUserExample e = new AdminUserExample();
        e.createCriteria().andAdNameEqualTo(userName);
        List<AdminUser> adminUsers = adminUserMapper.selectByExample(e);
        if (adminUsers.size()>0){
            //已存在用户
            return true;
        }else {
            return false;
        }
    }

    public boolean saveUser(AdminUserWithBLOBs adminUserWithBLOBs) {
        if (isExistUser(adminUserWithBLOBs.getAdName())){
            return false;
        }
        //对密码进行MD5计算 将摘要值存入数据库 对比的时候对比摘要值
        adminUserWithBLOBs.setAdPwd(Md5Tool.getMd5(adminUserWithBLOBs.getAdPwd()));
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

    //登录判断 0 用户不存在 1 密码不正确 2 正确
    public int login(String userName,String pwd) {
        boolean flag = false;
        flag = isExistUser(userName);
        if (!flag){
            return 0;
        }
        String pwdMd5 = Md5Tool .getMd5(pwd);

        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andAdNameEqualTo(userName).andAdPwdEqualTo(pwdMd5);
        List<AdminUser> adminUsers = adminUserMapper.selectByExample(example);
        if (adminUsers.size()>0){
            return 2;
        }else  {
            return 1;
        }

    }
}
