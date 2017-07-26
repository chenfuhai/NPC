package com.liufeng.npc.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.liufeng.npc.bean.Article;
import com.liufeng.npc.bean.ArticleWithBLOBs;
import com.liufeng.npc.dao.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public List<ArticleWithBLOBs> getAll(){
        List<ArticleWithBLOBs> articles = articleMapper.selectByExampleWithBLOBs(null);

        System.out.println(articles.size());
        for (Article a :articles) {
            System.out.println(a.toString());
        }
        return articles;

    }




}
