package com.liufeng.npc.dao;

import com.liufeng.npc.bean.Column;
import com.liufeng.npc.bean.ColumnExample;
import com.liufeng.npc.bean.ColumnWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ColumnMapper {
    long countByExample(ColumnExample example);

    int deleteByExample(ColumnExample example);

    int deleteByPrimaryKey(Integer coId);

    int insert(ColumnWithBLOBs record);

    int insertSelective(ColumnWithBLOBs record);

    List<ColumnWithBLOBs> selectByExampleWithBLOBs(ColumnExample example);

    List<Column> selectByExample(ColumnExample example);

    ColumnWithBLOBs selectByPrimaryKey(Integer coId);

    int updateByExampleSelective(@Param("record") ColumnWithBLOBs record, @Param("example") ColumnExample example);

    int updateByExampleWithBLOBs(@Param("record") ColumnWithBLOBs record, @Param("example") ColumnExample example);

    int updateByExample(@Param("record") Column record, @Param("example") ColumnExample example);

    int updateByPrimaryKeySelective(ColumnWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ColumnWithBLOBs record);

    int updateByPrimaryKey(Column record);
}