package com.example.musicapp.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.musicapp.Activity.Danhsachallchude;
import com.example.musicapp.Activity.DanhsachbaihatActivity;
import com.example.musicapp.Model.Chude;
import com.example.musicapp.Model.Theloai;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Theloai>> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<List<Theloai>>() {
                @Override
            public void onResponse(Call<List<Theloai>> call, Response<List<Theloai>> response) {
                List<Theloai> mangTheloai = response.body();

//                final ArrayList<Chude> chuDeArrayList = new ArrayList<>();
//                chuDeArrayList.addAll(mangTheloai.getChude());
                final ArrayList<Theloai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(mangTheloai);

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 500);
                layout.setMargins(10, 20, 10, 30);
                for (int i = 0; i < (theLoaiArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(i).getHinhTheLoai() != null) {
                        Picasso.get().load(theLoaiArrayList.get(i).getHinhTheLoai()).into(imageView);
                    }


                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                    horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<List<Theloai>> call, Throwable t) {

            }
        });
    }
}
