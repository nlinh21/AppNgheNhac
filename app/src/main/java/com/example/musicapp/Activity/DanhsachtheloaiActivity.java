package com.example.musicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Adapter.DanhsachAllchudeAdapter;
import com.example.musicapp.Model.Chude;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaiActivity extends AppCompatActivity {
    Chude chuDe;
    RecyclerView recyclerViewtheloai;
    Toolbar toolbartheloai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloai);
//        init();
//        GetIntent();
    }
//    private void GetData() {
//
//        Dataservice dataservice = APIService.getService();
//        Call<List<Chude>> callback = dataservice.GetAllChuDe();
//        callback.enqueue(new Callback<List<Chude>>() {
//
//            @Override
//            public void onResponse(Call<List<Chude>> call, Response<List<Chude>> response) {
//                ArrayList<Chude> mangchude = (ArrayList<Chude>) response.body();
//                danhsachAllchudeAdapter = new DanhsachAllchudeAdapter(Danhsachallchude.this,mangchude);
//                recyclerviewallchude.setLayoutManager(new GridLayoutManager(Danhsachallchude.this, 1));
//                recyclerviewallchude.setAdapter(danhsachAllchudeAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Chude>> call, Throwable t) {
//            }
//        });
//    }



//    private void init() {
//        recyclerViewtheloai = findViewById(R.id.recyclerviewtheloai);
//        toolbartheloai = findViewById(R.id.toolbartheloai);
//        setSupportActionBar(toolbartheloai);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(chuDe.getTenChuDe());
//        toolbartheloai.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//    }


//    private void GetIntent() {
//        Intent intent = getIntent();
//        if (intent.hasExtra("chude")) {
//            chuDe = (Chude) intent.getSerializableExtra("chude");
//            Toast.makeText(this, chuDe.getTenChuDe(), Toast.LENGTH_SHORT).show();
//        }
//    }
}
