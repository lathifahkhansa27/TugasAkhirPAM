package com.example.taappbiodata.database;

import java.io.Serializable;

public class karyawan implements Serializable {

    String id;
    String nama;
    String tanggallahir;
    String jeniskelamin;

    public  karyawan() {
    }

    public karyawan (String id, String nama, String tanggallahir, String jeniskelamin){
        this.id = id;
        this.nama = nama;
        this.tanggallahir = tanggallahir;
        this.jeniskelamin = jeniskelamin;
    }

    public  String getId() {
        return id;
    }

    public  void  setId(String id) {
        this.id = id;
    }

    public  String getNama(){
        return  nama;
    }

    public  void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggallahir(){
        return tanggallahir;
    }

    public  void setTanggallahir(String tanggallahir){
        this.tanggallahir = tanggallahir;
    }

    public String getJeniskelamin() {return jeniskelamin;}

    public void  setJeniskelamin(String jeniskelamin) {this.jeniskelamin = jeniskelamin;}
}


