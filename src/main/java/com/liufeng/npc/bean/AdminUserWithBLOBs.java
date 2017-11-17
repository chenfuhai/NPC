package com.liufeng.npc.bean;

public class AdminUserWithBLOBs extends AdminUser {

    @Override
    public String toString() {
        return "AdminUserWithBLOBs{" +
                "ad1='" + ad1 + '\'' +
                ", ad2='" + ad2 + '\'' +
                ", ad3='" + ad3 + '\'' +
                ", ad4='" + ad4 + '\'' +
                ", ad5='" + ad5 + '\'' +
                '}'+super.toString();
    }

    private String ad1;

    private String ad2;

    private String ad3;

    private String ad4;

    private String ad5;

    public AdminUserWithBLOBs(String adName, String adPwd, String adInfo, Integer adPowercode) {
        super(adName, adPwd, adInfo, adPowercode);
    }
    public AdminUserWithBLOBs(){
        super();
    }

    public String getAd1() {
        return ad1;
    }

    public void setAd1(String ad1) {
        this.ad1 = ad1 == null ? null : ad1.trim();
    }

    public String getAd2() {
        return ad2;
    }

    public void setAd2(String ad2) {
        this.ad2 = ad2 == null ? null : ad2.trim();
    }

    public String getAd3() {
        return ad3;
    }

    public void setAd3(String ad3) {
        this.ad3 = ad3 == null ? null : ad3.trim();
    }

    public String getAd4() {
        return ad4;
    }

    public void setAd4(String ad4) {
        this.ad4 = ad4 == null ? null : ad4.trim();
    }

    public String getAd5() {
        return ad5;
    }

    public void setAd5(String ad5) {
        this.ad5 = ad5 == null ? null : ad5.trim();
    }
}