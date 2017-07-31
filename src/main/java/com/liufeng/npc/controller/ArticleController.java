package com.liufeng.npc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liufeng.npc.bean.Article;
import com.liufeng.npc.bean.ArticleWithBLOBs;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.service.ArticleService;
import com.liufeng.npc.utils.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    //获取某个栏目的所有文章  produces = "application/json;charset=utf-8" 使用ResponseBody并且返回时String的时候加 其他不要
    @ResponseBody
    @RequestMapping(value = "/arts/{coId}", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getArtsByCol(@PathVariable("coId") Integer coId,
                            @RequestParam(value = "page", defaultValue = "1") Integer pn,
                            @RequestParam(value = "pagesize", defaultValue = "1") Integer pagesize) {

        PageHelper.startPage(pn,pagesize);
        List<ArticleWithBLOBs> articles = articleService.getAllByColId(coId);
        PageInfo pageInfo = new PageInfo(articles,5);


        return dealArtsToJson(pageInfo.getList(),pageInfo.getTotal());
    }


    //获取所有文章
    @ResponseBody
    @RequestMapping(value = "/arts",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getArts(@RequestParam(value = "page", defaultValue = "1") Integer pn,
                          @RequestParam(value = "pagesize", defaultValue = "1") Integer pagesize) {

        PageHelper.startPage(pn,pagesize);
        List<ArticleWithBLOBs> articles = articleService.getAll();
        PageInfo pageInfo = new PageInfo(articles,5);


        return dealArtsToJson(pageInfo.getList(),pageInfo.getTotal());
    }

    /**
     * 处理pageInfo里面的数据 转化成Ligerui可接受的JSON
     * @param arts
     * @param total
     * @return
     */
    private String dealArtsToJson(List arts,long total){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONArray array = new JSONArray();
        for (Object art : arts) {
            Article a = (Article) art;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", a.getArTitle());
            jsonObject.put("subTitle", a.getArSubtitle());

            jsonObject.put("publicTime", simpleDateFormat.format(a.getArPublictime()));
            jsonObject.put("from", a.getArFrom());
            jsonObject.put("status", a.getArStatus());
            jsonObject.put("clickCount", a.getArClickarunt());
            jsonObject.put("isHot", a.getArIshot());
            jsonObject.put("isNew", a.getArIsnew());
            jsonObject.put("id", a.getArId());
            jsonObject.put("coId", a.getArColumnid());
            array.add(jsonObject);
        }


        JSONObject obj  = new JSONObject();
        obj.put("Rows",array);
        obj.put("Total",total);
        System.out.println(obj.toString());
        return obj.toString();

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
        articleWithBLOBs.setArPublictime(new Date());
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

        System.out.println("dddd"+articleWithBLOBs.toString());

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
