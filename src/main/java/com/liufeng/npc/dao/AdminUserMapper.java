package com.liufeng.npc.dao;

import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.AdminUserExample;
import com.liufeng.npc.bean.AdminUserWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    long countByExample(AdminUserExample example);

    int deleteByExample(AdminUserExample example);

    int deleteByPrimaryKey(Integer adId);

    int insert(AdminUserWithBLOBs record);

    int insertSelective(AdminUserWithBLOBs record);

    List<AdminUserWithBLOBs> selectByExampleWithBLOBs(AdminUserExample example);

    List<AdminUser> selectByExample(AdminUserExample example);

    AdminUserWithBLOBs selectByPrimaryKey(Integer adId);

    int updateByExampleSelective(@Param("record") AdminUserWithBLOBs record, @Param("example") AdminUserExample example);

    int updateByExampleWithBLOBs(@Param("record") AdminUserWithBLOBs record, @Param("example") AdminUserExample example);

    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByPrimaryKeySelective(AdminUserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AdminUserWithBLOBs record);

    int updateByPrimaryKey(AdminUser record);
}