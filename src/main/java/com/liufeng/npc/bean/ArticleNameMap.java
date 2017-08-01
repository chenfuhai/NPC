package com.liufeng.npc.bean;

/**
 * 存储一个对外的类名和对内列名对应表 O对外类属性名 I内部类属性名 D数据库字段名
 */
public class ArticleNameMap {
    public static final String O_AR_ID="id";
    public static final String I_AR_ID="arId";
    public static final String D_AR_ID="Ar_ID";
    public static final String O_AR_TITLE="title";
    public static final String I_AR_TITLE="arTitle";
    public static final String D_AR_TITLE="Ar_Title";
    public static final String O_AR_SUBTITLE="subTitle";
    public static final String I_AR_SUBTITLE="arSubtitle";
    public static final String D_AR_SUBTITLE="Ar_SubTitle";
    public static final String O_AR_PUBLICTIME="publicTime";
    public static final String I_AR_PUBLICTIME="arPublictime";
    public static final String D_AR_PUBLICTIME="Ar_PublicTime";
    public static final String O_AR_ISHOT="isHot";
    public static final String I_AR_ISHOT="arIshot";
    public static final String D_AR_ISHOT="Ar_IsHot";
    public static final String O_AR_ISNEW="isNew";
    public static final String I_AR_ISNEW="arIsnew";
    public static final String D_AR_ISNEW="Ar_IsNew";
    public static final String O_AR_FROM="from";
    public static final String I_AR_FROM="arFrom";
    public static final String D_AR_FROM="Ar_From";
    public static final String O_AR_CLICKCOUNT="clickCount";
    public static final String I_AR_CLICKCOUNT="arClickCount";
    public static final String D_AR_CLICKCOUNT="Ar_ClickCount";
    public static final String O_AR_COLUMNID="coId";
    public static final String I_AR_COLUMNID="arColumnid";
    public static final String D_AR_COLUMNID="Ar_ColumnID";
    public static final String O_AR_STATUS="status";
    public static final String I_AR_STATUS="arStatus";
    public static final String D_AR_STATUS="Ar_Status";
    public static final String O_AR_CONTENT="content";
    public static final String I_AR_CONTENT="arContent";
    public static final String D_AR_CONTENT="Ar_Content";


    public static String Out2Data(String out){
        String result = null;
        if (out.equals(O_AR_ID)){
            result = D_AR_ID;
        }
        if (out.equals(O_AR_TITLE)){
            result = D_AR_TITLE;
        }
        if (out.equals(O_AR_SUBTITLE)){
            result = D_AR_SUBTITLE;
        }

        if (out.equals(O_AR_PUBLICTIME)){
            result = D_AR_PUBLICTIME;
        }

        if (out.equals(O_AR_ISHOT)){
            result = D_AR_ISHOT;
        }

        if (out.equals(O_AR_ISNEW)){
            result = D_AR_ISNEW;
        }

        if (out.equals(O_AR_FROM)){
            result = D_AR_FROM;
        }

        if (out.equals(O_AR_CLICKCOUNT)){
            result = D_AR_CLICKCOUNT;
        }

        if (out.equals(O_AR_COLUMNID)){
            result = D_AR_COLUMNID;
        }

        if (out.equals(O_AR_STATUS)){
            result = D_AR_STATUS;
        }

        if (out.equals(O_AR_CONTENT)){
            result = D_AR_CONTENT;
        }

        return result;
    }

}
