package com.liufeng.npc.service;

import com.liufeng.npc.bean.ArticleExample;
import com.liufeng.npc.bean.ArticleWithBLOBs;
import com.liufeng.npc.dao.ArticleMapper;
import com.liufeng.npc.utils.ArtURLHunter;
import com.liufeng.npc.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public List<ArticleWithBLOBs> getAll(ArticleExample example) {
        List<ArticleWithBLOBs> articles = articleMapper.selectByExampleWithBLOBs(example);

        return articles;

    }


    public ArticleWithBLOBs getArtById(Integer artId) {
        ArticleWithBLOBs article = articleMapper.selectByPrimaryKey(artId);
        return article;
    }


    public boolean saveArt(ArticleWithBLOBs articleWithBLOBs) {
        int i = articleMapper.insertSelective(articleWithBLOBs);
        if (i > 0) {
            //插入成功
            return true;
        }
        return false;
    }


    public boolean updateArt(ArticleWithBLOBs articleWithBLOBs) {
        int i = articleMapper.updateByPrimaryKeySelective(articleWithBLOBs);
        if (i > 0) {

            return true;
        } else {
            return false;
        }
    }

    /**
     * 批量删除 100 删除成功 200 部分删除成功 300 删除失败
     */
    public int deleteBatch(List<Integer> del_ids) {
        ArticleExample example = new ArticleExample();
        example.or().andArIdIn(del_ids);

        List<ArticleWithBLOBs> articles = articleMapper.selectByExampleWithBLOBs(example);
        List<String> fileNames= new ArrayList<String>();
        for (ArticleWithBLOBs a:articles
             ) {
            List<String> temp = ArtURLHunter.getAllFileFromArt(a);
            fileNames.addAll(temp);
        }
        deleteFiles(fileNames);

        int i = articleMapper.deleteByExample(example);
        if (i == del_ids.size()) {

            return 100;
        } else if (i > 0 && i < del_ids.size()) {
            return 200;
        } else {
            return 300;
        }


    }

    public boolean deleteById(Integer artIds) {
        ArticleWithBLOBs article = articleMapper.selectByPrimaryKey(artIds);
        List<String> strings = ArtURLHunter.getAllFileFromArt(article);
        int i = articleMapper.deleteByPrimaryKey(artIds);


        if (i > 0) {
            deleteFiles(strings);
            return true;
        } else {
            return false;
        }

    }

    //需要将服务器里面的附件一块删除
    private void deleteFiles(List<String> fileNames) {
        //真实路径
        String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        for (String s : fileNames
                ) {
            String filePath = path + s;
            File file = new File(filePath);
            if (!file.exists()) {
                continue;
            }
            if (file.isDirectory()) {
                continue;
            }
            file.delete();
            Log.logI("删除文件：" + filePath);
            File parentFile = file.getParentFile();
            if (parentFile!=null){
                String[] files = parentFile.list();
                if (files.length == 0) {
                    parentFile.delete();
                    Log.logI("删除空文件夹：" + parentFile);
                }
            }
        }
    }

}
