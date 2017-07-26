package com.liufeng.npc.service;

import com.liufeng.npc.bean.Article;
import com.liufeng.npc.bean.ArticleExample;
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


    public List<ArticleWithBLOBs> getAllByColId(Integer coId) {

        ArticleExample example = new ArticleExample();
        example.createCriteria().andArColumnidEqualTo(coId);

        List<ArticleWithBLOBs> articles = articleMapper.selectByExampleWithBLOBs(example);

        return articles;
    }

    public ArticleWithBLOBs getArtById(Integer artId) {
        ArticleWithBLOBs article = articleMapper.selectByPrimaryKey(artId);
        return article;
    }

    public boolean saveArt(ArticleWithBLOBs articleWithBLOBs) {
        int i = articleMapper.insertSelective(articleWithBLOBs);
        if (i>0){
            //插入成功
            return true;
        }
        return false;
    }


    public boolean updateArt(ArticleWithBLOBs articleWithBLOBs) {
        int i = articleMapper.updateByPrimaryKeySelective(articleWithBLOBs);
        if (i>0){

            return true;
        }else{
            return false;
        }
    }
/**
 *  批量删除 100 删除成功 200 部分删除成功 300 删除失败
 */
    public int deleteBatch(List<Integer> del_ids) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andArIdIn(del_ids);
        int i = articleMapper.deleteByExample(example);
        if (i == del_ids.size()){
            return 100;
        }else if (i>0 && i<del_ids.size()){
            return 200;
        }else {
            return 300;
        }


    }

    public boolean deleteById(Integer artIds) {
        int i = articleMapper.deleteByPrimaryKey(artIds);
        if (i>0){
            return true;
        }else {
            return false;
        }

    }
}
