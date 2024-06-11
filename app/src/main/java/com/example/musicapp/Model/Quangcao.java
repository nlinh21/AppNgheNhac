package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quangcao implements Serializable {

@SerializedName("Id")
@Expose
private String id;
@SerializedName("HinhAnh")
@Expose
private String hinhAnh;
@SerializedName("NoiDung")
@Expose
private String noiDung;
@SerializedName("Idbaihat")
@Expose
private String idbaihat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getHinhAnh() {
return hinhAnh;
}

public void setHinhAnh(String hinhAnh) {
this.hinhAnh = hinhAnh;
}

public String getNoiDung() {
return noiDung;
}

public void setNoiDung(String noiDung) {
this.noiDung = noiDung;
}

public String getIdbaihat() {
return idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getHinhBaiHat() {
return hinhBaiHat;
}

public void setHinhBaiHat(String hinhBaiHat) {
this.hinhBaiHat = hinhBaiHat;
}

}