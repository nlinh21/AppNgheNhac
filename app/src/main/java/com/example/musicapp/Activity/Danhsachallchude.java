package com.example.musicapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Adapter.DanhsachAllchudeAdapter;
import com.example.musicapp.Adapter.DscacplaylistAdapter;
import com.example.musicapp.Model.Chude;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Danhsachallchude extends AppCompatActivity {
    RecyclerView recyclerviewallchude;
    Toolbar toolbar;
    DanhsachAllchudeAdapter danhsachAllchudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachallchude);
        GetData();
        init();


    }
    private void GetData() {

        Dataservice dataservice = APIService.getService();
        Call<List<Chude>> callback = dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<Chude>>() {

            @Override
            public void onResponse(Call<List<Chude>> call, Response<List<Chude>> response) {
                ArrayList<Chude> mangchude = (ArrayList<Chude>) response.body();
                danhsachAllchudeAdapter = new DanhsachAllchudeAdapter(Danhsachallchude.this,mangchude);
                recyclerviewallchude.setLayoutManager(new GridLayoutManager(Danhsachallchude.this, 1));
                recyclerviewallchude.setAdapter(danhsachAllchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<Chude>> call, Throwable t) {
            }
        });
    }



    private void init() {
        recyclerviewallchude = findViewById(R.id.recyclerviewAllchude);
        toolbar = findViewById(R.id.toolbarAllchude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}