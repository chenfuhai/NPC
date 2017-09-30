package com.liufeng.npc.test;

import com.liufeng.npc.dao.AdminUserMapper;
import com.liufeng.npc.dao.ArticleMapper;
import com.liufeng.npc.dao.ColumnMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    /**
     * 测试Mapper
     * 使用Spring的测试单元 自动注入需要的组件
     * 1、pom中导入SpringTest模块
     * 2.使用@ContextConfiguration指定Spring配置的位置
     * 3.直接autowired要使用的组件即可
     */
    @Autowired
    AdminUserMapper adminUserMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ColumnMapper columnMapper;

    @Test
    public void testnpc() {


//
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj", Md5Tool.getMd5("123456"), "超级管理员", 1));
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj2", Md5Tool.getMd5("123456"), "超级管理员", 1));
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj3", Md5Tool.getMd5("123456"), "超级管理员", 1));
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj4", Md5Tool.getMd5("123456"), "超级管理员", 1));
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj5", Md5Tool.getMd5("123456"), "超级管理员", 1));
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj6", Md5Tool.getMd5("123456"), "超级管理员", 1));
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj7", Md5Tool.getMd5("123456"), "超级管理员", 1));
//        adminUserMapper.insertSelective(new AdminUserWithBLOBs("liufengkj8", Md5Tool.getMd5("123456"), "超级管理员", 1));

//        AdminUserExample adminUserExample = new AdminUserExample();
//		adminUserExample.or().andAdNameEqualTo("liufengkj");
//      int i=  adminUserMapper.deleteByExample(adminUserExample);
//        System.out.println(i);

//        AdminUserExample adminUserExample = new AdminUserExample();
//		adminUserExample.or().andAdNameEqualTo("liufengkj111");
//        adminUserMapper.updateByExampleSelective(new AdminUserWithBLOBs("liufengkj11",null,null,null),adminUserExample);

//        AdminUserExample adminUserExample = new AdminUserExample();
//		adminUserExample.or().andAdNameEqualTo("liufengkj");
//        List<AdminUserWithBLOBs> adminUsers = adminUserMapper.selectByExampleWithBLOBs(null);
//        for (AdminUserWithBLOBs adminUser:adminUsers
//             ) {
//            System.out.println(adminUser.toString());
//        }


//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//        columnMapper.insertSelective(new ColumnWithBLOBs("测试栏目"+UUID.randomUUID().toString().substring(0,4),"栏目详情介绍"));
//
////        ColumnExample columnExample = new ColumnExample();
//        columnExample.or().andCoNameLike("测试栏目%");
//
//        List<Column> columns = columnMapper.selectByExample(columnExample);
//        for (Column column:columns
//                ) {
//            System.out.println(column.toString());
//        }
//
//        int i = columnMapper.updateByExampleSelective(new ColumnWithBLOBs("测试栏目2"+UUID.randomUUID().toString().substring(0,4),"测试详情2"),columnExample);
//        System.out.println(i);
//
//
//        List<Column> columns2 = columnMapper.selectByExample(columnExample);
//        for (Column column:columns2
//             ) {
//            System.out.println(column.toString());
//        }
//        int o =columnMapper.deleteByExample(columnExample);
//        System.out.println(o);



//        articleMapper.insertSelective(new ArticleWithBLOBs("测试文章"+UUID.randomUUID().toString().substring(0,4),null,new Date(),
//                null,null,null,null,10,null,null));
//        articleMapper.insertSelective(new ArticleWithBLOBs("测试文章"+UUID.randomUUID().toString().substring(0,4),null,new Date(),
//                null,null,null,null,14,null,null));
//        articleMapper.insertSelective(new ArticleWithBLOBs("测试文章"+UUID.randomUUID().toString().substring(0,4),null,new Date(),
//                null,null,null,null,15,null,null));
//        articleMapper.insertSelective(new ArticleWithBLOBs("测试文章"+UUID.randomUUID().toString().substring(0,4),null,new Date(),
//                null,null,null,null,12,null,null));
//        articleMapper.insertSelective(new ArticleWithBLOBs("测试文章"+UUID.randomUUID().toString().substring(0,4),null,new Date(),
//                null,null,null,null,15,null,null));
//        articleMapper.insertSelective(new ArticleWithBLOBs("测试文章"+UUID.randomUUID().toString().substring(0,4),null,new Date(),
//                null,null,null,null,14,null,null));

//        ArticleExample articleExample = new ArticleExample();
//        articleExample.or().andArTitleLike("测试文章%");
//
////        articleMapper.updateByExampleSelective(new ArticleWithBLOBs("测试文章2"+UUID.randomUUID().toString().substring(0,4),null,new Date(),
////                null,null,null,null,null,null,null),articleExample);
//
//        int i = articleMapper.deleteByExample(articleExample);
//        System.out.println(i);
//
//        List<Article> articles = articleMapper.selectByExample(articleExample);
//
//        System.out.println(articles.size());
//        for (Article article:articles) {
//            System.out.println(article);
//        }
//



    }

}
