package com.liufeng.npc.bean;

public class AdminUser {
    private Integer adId;

    private String adName;

    private String adPwd;

    private String adInfo;

    private Integer adPowercode;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public String getAdPwd() {
        return adPwd;
    }

    public void setAdPwd(String adPwd) {
        this.adPwd = adPwd == null ? null : adPwd.trim();
    }

    public String getAdInfo() {
        return adInfo;
    }

    public void setAdInfo(String adInfo) {
        this.adInfo = adInfo == null ? null : adInfo.trim();
    }

    public Integer getAdPowercode() {
        return adPowercode;
    }

    public void setAdPowercode(Integer adPowercode) {
        this.adPowercode = adPowercode;
    }
}