package com.example.app_studentmanagement_master.Modal;

public class Sinhvien {
    private String id;
    private String tensv;
    private String mssv;
    private String nganh;
    private String malop;

    public Sinhvien(String id, String tensv, String mssv, String nganh, String malop) {
        this.id = id;
        this.tensv = tensv;
        this.mssv = mssv;
        this.nganh = nganh;
        this.malop = malop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }
}
