package com.liufeng.npc.bean;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

public class Article {
    private Integer arId;

    private String arTitle;

    private String arSubtitle;

    //这里一个符号也不能差 查了就报400错 比如传过来的事2017-02-11 12:25 就不行
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date arPublictime;

    private String arIshot;

    private String arIsnew;

    private String arFrom;

    private Integer arClickCount;

    private Integer arColumnid;

    private Integer arStatus;

    public Article(String arTitle, String arSubtitle, Date arPublictime, String arIshot, String arIsnew, String arFrom, Integer arClickCount, Integer arColumnid, Integer arStatus) {
        this.arTitle = arTitle;
        this.arSubtitle = arSubtitle;
        this.arPublictime = arPublictime;
        this.arIshot = arIshot;
        this.arIsnew = arIsnew;
        this.arFrom = arFrom;
        this.arClickCount = arClickCount;
        this.arColumnid = arColumnid;
        this.arStatus = arStatus;
    }

    @Override
    public String toString() {
        return "Article{" +
                "arId=" + arId +
                ", arTitle='" + arTitle + '\'' +
                ", arSubtitle='" + arSubtitle + '\'' +
                ", arPublictime=" + arPublictime +
                ", arIshot='" + arIshot + '\'' +
                ", arIsnew='" + arIsnew + '\'' +
                ", arFrom='" + arFrom + '\'' +
                ", arClickCount=" + arClickCount +
                ", arColumnid=" + arColumnid +
                ", arStatus=" + arStatus +
                '}';
    }

    public Article() {
        super();
    }

    public Integer getArId() {
        return arId;
    }

    public void setArId(Integer arId) {
        this.arId = arId;
    }

    public String getArTitle() {
        return arTitle;
    }

    public void setArTitle(String arTitle) {
        this.arTitle = arTitle == null ? null : arTitle.trim();
    }

    public String getArSubtitle() {
        return arSubtitle;
    }

    public void setArSubtitle(String arSubtitle) {
        this.arSubtitle = arSubtitle == null ? null : arSubtitle.trim();
    }

    public Date getArPublictime() {
        return arPublictime;
    }

    public void setArPublictime(Date arPublictime) {
        this.arPublictime = arPublictime;
    }

    public String getArIshot() {
        return arIshot;
    }

    public void setArIshot(String arIshot) {
        this.arIshot = arIshot == null ? null : arIshot.trim();
    }

    public String getArIsnew() {
        return arIsnew;
    }

    public void setArIsnew(String arIsnew) {
        this.arIsnew = arIsnew == null ? null : arIsnew.trim();
    }

    public String getArFrom() {
        return arFrom;
    }

    public void setArFrom(String arFrom) {
        this.arFrom = arFrom == null ? null : arFrom.trim();
    }

    public Integer getArClickCount() {
        return arClickCount;
    }

    public void setArClickCount(Integer arClickCount) {
        this.arClickCount = arClickCount;
    }

    public Integer getArColumnid() {
        return arColumnid;
    }

    public void setArColumnid(Integer arColumnid) {
        this.arColumnid = arColumnid;
    }

    public Integer getArStatus() {
        return arStatus;
    }

    public void setArStatus(Integer arStatus) {
        this.arStatus = arStatus;
    }
}