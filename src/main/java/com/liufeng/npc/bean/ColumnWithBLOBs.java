package com.liufeng.npc.bean;

public class ColumnWithBLOBs extends Column {
    private String co1;

    private String co2;

    private String co3;

    private String co4;

    private String co5;

    public ColumnWithBLOBs(String coName, String coInfo) {
        super(coName, coInfo);
    }

    public ColumnWithBLOBs() {
        super();
    }

    public String getCo1() {
        return co1;
    }

    public void setCo1(String co1) {
        this.co1 = co1 == null ? null : co1.trim();
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2 == null ? null : co2.trim();
    }

    public String getCo3() {
        return co3;
    }

    public void setCo3(String co3) {
        this.co3 = co3 == null ? null : co3.trim();
    }

    public String getCo4() {
        return co4;
    }

    public void setCo4(String co4) {
        this.co4 = co4 == null ? null : co4.trim();
    }

    public String getCo5() {
        return co5;
    }

    public void setCo5(String co5) {
        this.co5 = co5 == null ? null : co5.trim();
    }
}