package com.liufeng.npc.controller;

import com.liufeng.npc.bean.ArticleWithBLOBs;
import com.liufeng.npc.bean.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    //获取某个栏目的所有文章
    @ResponseBody
    @RequestMapping(value = "/arts/{coId}", method = RequestMethod.GET)
    public Msg getArtsByCol(@PathVariable("coId") Integer coId) {

        return Msg.success();
    }

    //获取所有文章
    @ResponseBody
    @RequestMapping(value = "/arts",method = RequestMethod.GET)
    public Msg getArts(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        return Msg.success();
    }

    //获取对应ID的文章
    @ResponseBody
    @RequestMapping(value = "/art/{artId}", method = RequestMethod.GET)
    public Msg getArtById(@PathVariable("artId") Integer artId) {
        return Msg.success();
    }

    //保存文章

    @ResponseBody
    @RequestMapping(value = "/art", method = RequestMethod.POST)
    public Msg saveArt(ArticleWithBLOBs articleWithBLOBs) {
        return Msg.success();
    }

    //更新文章信息
    //Spring自动封装 URL上的占位符加上原生map中的值以及自己封装的emap中的值
    @ResponseBody
    @RequestMapping(value = "/art/{arId}" ,method = RequestMethod.PUT)
    public Msg updateArt(ArticleWithBLOBs articleWithBLOBs){

        return Msg.success();
    }

    //删除文章 单个文章和多个文章一起处理 单个1 多个1-2-3
    @ResponseBody
    @RequestMapping(value = "/art/{artIds}",method = RequestMethod.DELETE)
    public Msg delArt(@PathVariable("artIds")String artIds){
//批量删除
        if(artIds.contains("-")){
            List<Integer> del_ids = new ArrayList();
            String[] str_ids = artIds.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            //执行批量删除
//            employeeService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(artIds);
            //直接删除
            //employeeService.deleteEmp(id);
        }

        return Msg.success();
    }

}
