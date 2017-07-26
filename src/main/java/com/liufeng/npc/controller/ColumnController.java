package com.liufeng.npc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liufeng.npc.bean.Column;
import com.liufeng.npc.bean.ColumnWithBLOBs;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ColumnController {
    @Autowired
    ColumnService columnService;

    //获取所有的栏目
    @ResponseBody
    @RequestMapping(value = "/cols",method = RequestMethod.GET)
    public Msg getCols(){
        List<Column> columns = columnService.getAll();
        return Msg.success().add("cols",columns);
    }
    //获取对应ID的栏目信息
    @ResponseBody
    @RequestMapping(value = "/col/{colId}",method = RequestMethod.GET)
    public Msg getCol(@PathVariable("colId") Integer colId){
        return Msg.success();
    }
    //更新对应的栏目信息
    @ResponseBody
    @RequestMapping(value = "/col/{colId}",method = RequestMethod.PUT)
    public Msg updateCol(ColumnWithBLOBs columnWithBLOBs){
        return Msg.success();
    }
    //删除对应的栏目信息
    @ResponseBody
    @RequestMapping(value = "/col/{colIds}",method = RequestMethod.DELETE)
    public Msg delCol(@PathVariable("colIds") String colIds){
        //判断是删除单个还是删除多个 再分别执行操作
        return Msg.success();
    }
    //保存对应的栏目
    @ResponseBody
    @RequestMapping(value = "/col",method = RequestMethod.POST)
    public Msg saveCol(ColumnWithBLOBs columnWithBLOBs){
        return Msg.success();
    }

}
