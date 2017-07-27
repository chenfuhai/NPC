package com.liufeng.npc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liufeng.npc.bean.ArticleWithBLOBs;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.service.ArticleService;
import com.liufeng.npc.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    //获取某个栏目的所有文章
    @ResponseBody
    @RequestMapping(value = "/arts/{coId}", method = RequestMethod.GET)
    public Msg getArtsByCol(@PathVariable("coId") Integer coId) {
        List<ArticleWithBLOBs> articles = articleService.getAllByColId(coId);

        return Msg.success().add("arts",articles);
    }

    //获取所有文章
    @ResponseBody
    @RequestMapping(value = "/arts",method = RequestMethod.GET)
    public Msg getArts(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        PageHelper.startPage(pn,10);
        List<ArticleWithBLOBs> articles = articleService.getAll();
        PageInfo pageInfo = new PageInfo(articles,5);

        return Msg.success().add("arts",pageInfo);
    }

    //获取对应ID的文章
    @ResponseBody
    @RequestMapping(value = "/art/{artId}", method = RequestMethod.GET)
    public Msg getArtById(@PathVariable("artId") Integer artId) {
        ArticleWithBLOBs art = articleService.getArtById(artId);

        return Msg.success().add("art",art);
    }

    //保存文章

    @ResponseBody
    @RequestMapping(value = "/art", method = RequestMethod.POST)
    public Msg newArt(ArticleWithBLOBs articleWithBLOBs) {
        boolean flag = false;
         flag = articleService.saveArt(articleWithBLOBs);
         if (flag){
             return Msg.success();
         }else{
             Msg msg =  Msg.error();
             msg.msg="保存失败";
             return msg;
         }
    }

    //更新文章信息
    //Spring自动封装 URL上的占位符加上原生map中的值以及自己封装的emap中的值
    @ResponseBody
    @RequestMapping(value = "/art/{arId}" ,method = RequestMethod.PUT)
    public Msg updateArt(ArticleWithBLOBs articleWithBLOBs){
        boolean flag =false;
        flag= articleService.updateArt(articleWithBLOBs);
        if (flag){
            return Msg.success();
        }else{
            return Msg.error();

        }
    }

    //删除文章 单个文章和多个文章一起处理 单个1 多个1-2-3
    @ResponseBody
    @RequestMapping(value = "/art/{artIds}",method = RequestMethod.DELETE)
    public Msg delArt(@PathVariable("artIds")String artIds){
        Log.logI("artIds="+artIds);
//批量删除
        if(artIds.contains("-")){
            List<Integer> del_ids = new ArrayList();
            String[] str_ids = artIds.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            //执行批量删除
            int code = articleService.deleteBatch(del_ids);
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
            Integer id = Integer.parseInt(artIds);
            //直接删除
            boolean b = false;
            b = articleService.deleteById(id);
            if (b){
                return Msg.success();
            }else {
                return Msg.error();
            }
        }


    }

}
