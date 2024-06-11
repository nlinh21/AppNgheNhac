package com.example.musicapp.Service;

import com.example.musicapp.Model.AlbumHot;
import com.example.musicapp.Model.Baihat;
import com.example.musicapp.Model.Chude;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Quangcao;
import com.example.musicapp.Model.Theloai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();
    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistcurrentday();
    @GET("chudevatheloaitrongngay.php")
    Call<List<Theloai>> GetCategoryMusic();

    @GET("baihatlike.php")
    Call<List<Baihat>> GetHotSong();

    @GET("albumhot.php")
    Call<List<AlbumHot>> GetAlbumHot();
    @FormUrlEncoded
    @POST("dsbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("dsbxh.php")
    Call<List<Baihat>> GetDanhsachbaitheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("dscacplaylist.php")
    Call<List<Playlist>> GetDscacplaylist();

    @FormUrlEncoded
    @POST("dsbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("allchude.php")
    Call<List<Chude>> GetAllChuDe();

    @FormUrlEncoded
    @POST("updatelike.php")
    Call<String> UpdateLike(@Field("luotThich") String luotThich, @Field("idBaiHat") String idBaiHat);

    @FormUrlEncoded
    @POST("sreach.php")
    Call<List<Baihat>> GetSearch(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("login.php")
    Call<String> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<String> register(@Field("email") String email, @Field("password") String password);

}
