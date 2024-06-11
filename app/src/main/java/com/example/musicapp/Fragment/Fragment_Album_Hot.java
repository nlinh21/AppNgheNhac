package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Adapter.AlbumAdapter;
import com.example.musicapp.Model.AlbumHot;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.Model.Theloai;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewalbum;
//    TextView txtxemthemalbum;
    AlbumAdapter albumAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot, container, false);
        recyclerViewalbum = view.findViewById(R.id.recyclerviewAlbum);
//        txtxemthemalbum = view.findViewById(R.id.textviewxemthem);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<AlbumHot>> callback = dataservice.GetAlbumHot();
        callback.enqueue(new Callback<List<AlbumHot>>() {
            @Override
            public void onResponse(Call<List<AlbumHot>> call, Response<List<AlbumHot>> response) {
              //  List<AlbumHot> albumHotArrayList =  response.body();
                ArrayList<AlbumHot> albumHotArrayList = (ArrayList<AlbumHot>) response.body();
                albumAdapter = new AlbumAdapter(getActivity(),albumHotArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewalbum.setLayoutManager(linearLayoutManager);
                recyclerViewalbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<AlbumHot>> call, Throwable t) {

            }
        });
    }
}
