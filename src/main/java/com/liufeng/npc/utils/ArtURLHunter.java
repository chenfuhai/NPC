package com.liufeng.npc.utils;

import com.liufeng.npc.bean.ArticleWithBLOBs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从一篇文章中找出（第一个）图片链接
 */
public class ArtURLHunter {

    /**
     * 返回找到的第一个图片链接 如果文章里面没有 返回空null map取值为 src alt
     * @param article
     * @return
     */
    public static Map<String,String> getFristImgUrlFromArt(ArticleWithBLOBs article){
        String regexImg = "<img src=\"/upload/.*/>";
        String regexSrc = "\"/upload/.*?\"";
        String regexAlt = "alt=\".*?\"";
        String input = article.getArContent();
        String resultImg = null;
        String resultSrc = null;
        String resultAlt = null;


        Matcher matcherImg =  Pattern.compile(regexImg).matcher(input);
        matcherImg.find();
        resultImg = matcherImg.group();
        if (resultImg!=null&&!resultImg.equals("")){

            Matcher matcherSrc = Pattern.compile(regexSrc).matcher(resultImg);
            matcherSrc.find();
            String temp1 = matcherSrc.group();
            if (temp1!=null&&!temp1.equals("")){
                resultSrc = temp1.substring(1,temp1.length()-1);
            }

            Matcher matcherAlt = Pattern.compile(regexAlt).matcher(resultImg);
            matcherAlt.find();
            String temp2 =matcherAlt.group();
            if (temp2!=null&&!temp2.equals("")){
                resultAlt =temp2.substring(5,temp2.length()-1);
            }
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("src",resultSrc);
        map.put("alt",resultAlt);

        return map;
    }

    /**
     *  将文章中的所有文件的文件的文件名取出来 然后删除
     * @param articleWithBLOBs
     * @return
     */
    public static List<String> getAllFileFromArt(ArticleWithBLOBs articleWithBLOBs){
        String regex = "upload/.*?\"";//还有一个"要去除
        List<String> results = new ArrayList<String>();
        Matcher matcher =  Pattern.compile(regex).matcher(articleWithBLOBs.getArContent());
        while (matcher.find()){
            String temp = matcher.group();
            temp = temp.substring(0,temp.length()-1);
            results.add(temp);
        }
        return results;

    };
}
