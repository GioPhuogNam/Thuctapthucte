package com.example.salesmanager.models;

public class Cart {

    private int id;
    private String tenSP;
    private String danhMuc;
    private int soLuong;
    private int giaTien;
    private byte[] hinhAnh;

    public Cart() {
    }

    public Cart(int id, String tenSP, String danhMuc, int soLuong, int giaTien, byte[] hinhAnh) {
        this.id = id;
        this.tenSP = tenSP;
        this.danhMuc = danhMuc;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
