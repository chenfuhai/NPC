package com.liufeng.npc.bean;

import java.util.Date;

public class Article {
    private Integer arId;

    private String arTitle;

    private String arSubtitle;

    private Date arPublictime;

    private String arIshot;

    private String arIsnew;

    private String arFrom;

    private Integer arClickarunt;

    private Integer arColumnid;

    private Integer arStatus;

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

    public Integer getArClickarunt() {
        return arClickarunt;
    }

    public void setArClickarunt(Integer arClickarunt) {
        this.arClickarunt = arClickarunt;
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