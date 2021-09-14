package com.example.app_studentmanagement_master.Modal;

public class Lop {
    private String id;
    private String tentheloai;

    public Lop(String id, String tentheloai) {
        this.id = id;
        this.tentheloai = tentheloai;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }
}
