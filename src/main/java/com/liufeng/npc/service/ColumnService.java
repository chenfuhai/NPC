package com.liufeng.npc.service;

import com.liufeng.npc.bean.Column;
import com.liufeng.npc.bean.ColumnExample;
import com.liufeng.npc.bean.ColumnWithBLOBs;
import com.liufeng.npc.dao.ColumnMapper;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {

    @Autowired
    ColumnMapper columnMapper;


    public List<Column> getAll() {
        List<Column> columns = columnMapper.selectByExample(null);
        return columns;
    }

    public Column getAllByCol(Integer colId) {

        ColumnWithBLOBs column = columnMapper.selectByPrimaryKey(colId);

        return column;

    }

    public boolean updateCol(ColumnWithBLOBs columnWithBLOBs) {
        int i = columnMapper.updateByPrimaryKeySelective(columnWithBLOBs);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public int deleteBatch(List<Integer> del_ids) {
        ColumnExample example = new ColumnExample();
        example.createCriteria().andCoIdIn(del_ids);
        int i = columnMapper.deleteByExample(example);
        if (i == del_ids.size()){
            return 100;
        }else if (i>0 && i<del_ids.size()){
            return 200;
        }else {
            return 300;
        }


    }

    public boolean deleteById(Integer id) {
        int i = columnMapper.deleteByPrimaryKey(id);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean saveCol(ColumnWithBLOBs columnWithBLOBs) {
        int i = columnMapper.insertSelective(columnWithBLOBs);
        if (i>0){
            //插入成功
            return true;
        }
        return false;
    }
}
