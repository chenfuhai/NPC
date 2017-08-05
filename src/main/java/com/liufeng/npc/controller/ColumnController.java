package com.liufeng.npc.controller;

import com.liufeng.npc.bean.Column;
import com.liufeng.npc.bean.ColumnWithBLOBs;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.service.ColumnService;
import com.liufeng.npc.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
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
    //获取栏目名对应的栏目信息 栏目不多 获取所有栏目 然后对比


    //获取对应ID的栏目信息
    @ResponseBody
    @RequestMapping(value = "/col/{coId}",method = RequestMethod.GET)
    public Msg getCol(@PathVariable("coId") Integer coId){
        Column col = columnService.getColByCol(coId);

        return Msg.success().add("col",col);
    }
    //更新对应的栏目信息
    @ResponseBody
    @RequestMapping(value = "/col/{coId}",method = RequestMethod.PUT)
    public Msg updateCol(ColumnWithBLOBs columnWithBLOBs){
        boolean b = false;
        Log.logI(columnWithBLOBs.toString());
         b = columnService.updateCol(columnWithBLOBs);
        if (b){
            return Msg.success();
        }else{
            return Msg.error();
        }

    }
    //删除对应的栏目信息
    @ResponseBody
    @RequestMapping(value = "/col/{coIds}",method = RequestMethod.DELETE)
    public Msg delCol(@PathVariable("coIds") String coIds){
        //判断是删除单个还是删除多个 再分别执行操作
        if(coIds.contains("-")){
            List<Integer> del_ids = new ArrayList();
            String[] str_ids = coIds.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            //执行批量删除
            int code = columnService.deleteBatch(del_ids);
            Msg msg100 = Msg.success();
            msg100.msg="删除成功";
            Msg msg200= Msg.success();
            msg200.msg="部分删除成功";
            Msg msg300 = Msg.success();
            msg300.msg="删除失败";
            switch (code){
                case 100:return msg100;
                case 200:return msg200;
                case 300:return msg300;
                default:return Msg.error();
            }
        }else{
            Integer id = Integer.parseInt(coIds);
            //直接删除
            boolean b = false;
            b = columnService.deleteById(id);
            if (b){
                return Msg.success();
            }else {
                return Msg.error();
            }

        }
    }
    //保存对应的栏目
    @ResponseBody
    @RequestMapping(value ="/col",method = RequestMethod.POST)
    public Msg saveCol(ColumnWithBLOBs columnWithBLOBs){
        boolean flag = false;
        flag = columnService.saveCol(columnWithBLOBs);
        if (flag){
            return Msg.success();
        }else{
            Msg msg =  Msg.error();
            msg.msg="保存失败";
            return msg;
        }
    }

}
