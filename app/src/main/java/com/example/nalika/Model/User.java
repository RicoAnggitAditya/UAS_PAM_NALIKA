package com.example.nalika.Model;

public class User {

    private String id, name,alamat,no_telepon, namaorder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String name, String alamat, String no_telepon, String namaorder) {
        this.name = name;
        this.alamat = alamat;
        this.no_telepon = no_telepon;
        this.namaorder = namaorder;
    }

    public String getNamaorder() {
        return namaorder;
    }

    public void setNamaorder(String namaorder) {
        this.namaorder = namaorder;
    }

    public String getName(){

        return name;
    }
    public void setName(String name){

        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }
}
