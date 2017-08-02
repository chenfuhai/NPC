package com.liufeng.npc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liufeng.npc.bean.*;
import com.liufeng.npc.service.ArticleService;
import com.liufeng.npc.utils.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.print.attribute.Attribute;
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
    @RequestMapping(value = "/arts/{coId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getArtsByCol(@PathVariable("coId") Integer coId,
                               @RequestParam(value = "page", defaultValue = "1") Integer pn,
                               @RequestParam(value = "pagesize", defaultValue = "1") Integer pagesize,
                               @RequestParam(value = "sortname", defaultValue = "publicTime") String sortname,
                               @RequestParam(value = "sortorder", defaultValue = "desc") String sortorder
    ) {

        String d_sortname = ArticleNameMap.Out2Data(sortname);
        if (d_sortname == null) {
            return EditorMsg.getError("排序列名错误").toJson();
        }
        String userSort = "," + d_sortname + " " + sortorder;

        String defaultSortStatus = "Ar_Status asc";
        if (sortname.equals("status")) {
            defaultSortStatus = "";
            userSort = userSort.substring(1);//去掉前面的，号
        }
        String defaultSortTime = ",Ar_PublicTime desc";
        if (sortname.equals("publicTime")) {
            defaultSortTime = "";
        }



        ArticleExample example = new ArticleExample();
        example.or().andArColumnidEqualTo(coId);
        example.setOrderByClause(defaultSortStatus + userSort + defaultSortTime);

        PageHelper.startPage(pn, pagesize);
        List<ArticleWithBLOBs> articles = articleService.getAll(example);
        PageInfo pageInfo = new PageInfo(articles, 5);


        return dealArtsToJson(pageInfo.getList(), pageInfo.getTotal());
    }


    //获取所有文章
    @ResponseBody
    @RequestMapping(value = "/arts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getArts(@RequestParam(value = "page", defaultValue = "1") Integer pn,
                          @RequestParam(value = "pagesize", defaultValue = "1") Integer pagesize,
                          @RequestParam(value = "sortname", defaultValue = "publicTime") String sortname,
                          @RequestParam(value = "sortorder", defaultValue = "desc") String sortorder
    ) {

        String d_sortname = ArticleNameMap.Out2Data(sortname);
        if (d_sortname == null) {
            return EditorMsg.getError("排序列名错误").toJson();
        }

        ArticleExample example = new ArticleExample();
        example.setOrderByClause(d_sortname + " " + sortorder);


        PageHelper.startPage(pn, pagesize);
        List<ArticleWithBLOBs> articles = articleService.getAll(example);
        PageInfo pageInfo = new PageInfo(articles, 5);


        return dealArtsToJson(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 处理pageInfo里面的数据 转化成Ligerui可接受的JSON
     *
     * @param arts
     * @param total
     * @return
     */
    private String dealArtsToJson(List arts, long total) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONArray array = new JSONArray();
        for (Object art : arts) {
            Article a = (Article) art;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(ArticleNameMap.O_AR_TITLE, a.getArTitle());
            jsonObject.put(ArticleNameMap.O_AR_SUBTITLE, a.getArSubtitle());

            jsonObject.put(ArticleNameMap.O_AR_PUBLICTIME, simpleDateFormat.format(a.getArPublictime()));
            jsonObject.put(ArticleNameMap.O_AR_FROM, a.getArFrom());
            jsonObject.put(ArticleNameMap.O_AR_STATUS, a.getArStatus());
            jsonObject.put(ArticleNameMap.O_AR_CLICKCOUNT, a.getArClickCount());
            jsonObject.put(ArticleNameMap.O_AR_ISHOT, a.getArIshot());
            jsonObject.put(ArticleNameMap.O_AR_ISNEW, a.getArIsnew());
            jsonObject.put(ArticleNameMap.O_AR_ID, a.getArId());
            jsonObject.put(ArticleNameMap.O_AR_COLUMNID, a.getArColumnid());
            array.add(jsonObject);
        }


        JSONObject obj = new JSONObject();
        obj.put("Rows", array);
        obj.put("Total", total);
        System.out.println(obj.toString());
        return obj.toString();

    }


    //获取对应ID的文章
    @ResponseBody
    @RequestMapping(value = "/art/{artId}", method = RequestMethod.GET)
    public Msg getArtById(@PathVariable("artId") Integer artId) {
        ArticleWithBLOBs art = articleService.getArtById(artId);

        return Msg.success().add("art", art);
    }

    //保存文章

    @ResponseBody
    @RequestMapping(value = "/art", method = RequestMethod.POST)
    public Msg newArt(ArticleWithBLOBs articleWithBLOBs) {
        boolean flag = false;
        System.out.println("ddd"+articleWithBLOBs.getArTitle()+"adddd");
        if(articleWithBLOBs.getArTitle()==null || articleWithBLOBs.getArTitle().trim().equals("")){
            Msg msg = Msg.error();
            msg.msg = "标题为空，保存失败";
            return msg;
        }
        articleWithBLOBs.setArPublictime(new Date());
        flag = articleService.saveArt(articleWithBLOBs);
        if (flag) {
            return Msg.success();
        } else {
            Msg msg = Msg.error();
            msg.msg = "保存失败";
            return msg;
        }
    }

    //更新文章信息
    //Spring自动封装 URL上的占位符加上原生map中的值以及自己封装的emap中的值
    @ResponseBody
    @RequestMapping(value = "/art/{arId}", method = RequestMethod.PUT)
    public Msg updateArt(ArticleWithBLOBs articleWithBLOBs) {
        boolean flag = false;
        if(articleWithBLOBs.getArTitle()==null || articleWithBLOBs.getArTitle().trim().equals("")){
            Msg msg = Msg.error();
            msg.msg = "标题为空，更新失败";
            return msg;
        }
        flag = articleService.updateArt(articleWithBLOBs);
        if (flag) {
            return Msg.success();
        } else {
            return Msg.error();

        }
    }

    //复制文章 将文章从一个栏目复制到另一个栏目 注意这里的主键要去掉 不然数据库报错
    @ResponseBody
    @RequestMapping(value = "/art/copy", method = RequestMethod.POST)
    public Msg copyArt(@RequestParam(value = "artId", defaultValue = "0") Integer artId,
                       @RequestParam(value = "coId", defaultValue = "0") Integer coId) {
        if (artId == 0 || coId == 0) {
            return Msg.error();
        }
        ArticleWithBLOBs art = articleService.getArtById(artId);
        art.setArColumnid(coId);
        art.setArClickCount(0);
        art.setArIshot("F");
        art.setArIsnew("F");
        art.setArId(null);
        boolean flag;
        flag = articleService.saveArt(art);
        if (flag) {
            return Msg.success();
        } else {
            return Msg.error();
        }

    }

    //删除文章 单个文章和多个文章一起处理 单个1 多个1-2-3
    @ResponseBody
    @RequestMapping(value = "/art/{artIds}", method = RequestMethod.DELETE)
    public Msg delArt(@PathVariable("artIds") String artIds) {
        Log.logI("artIds=" + artIds);
//批量删除
        if (artIds.contains("-")) {
            List<Integer> del_ids = new ArrayList();
            String[] str_ids = artIds.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            //执行批量删除
            int code = articleService.deleteBatch(del_ids);
            Msg msg100 = Msg.success();
            msg100.msg = "删除成功";
            Msg msg200 = Msg.success();
            msg200.msg = "部分删除成功";
            Msg msg300 = Msg.success();
            msg300.msg = "删除失败";
            switch (code) {
                case 100:
                    return msg100;
                case 200:
                    return msg200;
                case 300:
                    return msg300;
                default:
                    return Msg.error();
            }
        } else {
            Integer id = Integer.parseInt(artIds);
            //直接删除
            boolean b = false;
            b = articleService.deleteById(id);
            if (b) {
                return Msg.success();
            } else {
                return Msg.error();
            }
        }


    }

}
