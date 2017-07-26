package com.liufeng.npc.service;

import com.liufeng.npc.bean.Column;
import com.liufeng.npc.dao.ColumnMapper;
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
}
