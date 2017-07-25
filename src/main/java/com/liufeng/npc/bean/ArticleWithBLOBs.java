package com.liufeng.npc.bean;

public class ArticleWithBLOBs extends Article {
    private String arContent;

    private String ar1;

    private String ar2;

    private String ar3;

    private String ar4;

    private String ar5;

    public String getArContent() {
        return arContent;
    }

    public void setArContent(String arContent) {
        this.arContent = arContent == null ? null : arContent.trim();
    }

    public String getAr1() {
        return ar1;
    }

    public void setAr1(String ar1) {
        this.ar1 = ar1 == null ? null : ar1.trim();
    }

    public String getAr2() {
        return ar2;
    }

    public void setAr2(String ar2) {
        this.ar2 = ar2 == null ? null : ar2.trim();
    }

    public String getAr3() {
        return ar3;
    }

    public void setAr3(String ar3) {
        this.ar3 = ar3 == null ? null : ar3.trim();
    }

    public String getAr4() {
        return ar4;
    }

    public void setAr4(String ar4) {
        this.ar4 = ar4 == null ? null : ar4.trim();
    }

    public String getAr5() {
        return ar5;
    }

    public void setAr5(String ar5) {
        this.ar5 = ar5 == null ? null : ar5.trim();
    }
}