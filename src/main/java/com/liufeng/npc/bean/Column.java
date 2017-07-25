package com.liufeng.npc.bean;

public class Column {
    private Integer coId;

    private String coName;

    private String coInfo;

    public Integer getCoId() {
        return coId;
    }

    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName == null ? null : coName.trim();
    }

    public String getCoInfo() {
        return coInfo;
    }

    public void setCoInfo(String coInfo) {
        this.coInfo = coInfo == null ? null : coInfo.trim();
    }
}