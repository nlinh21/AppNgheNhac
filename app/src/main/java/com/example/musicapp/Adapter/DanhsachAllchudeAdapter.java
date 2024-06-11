package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.DanhsachtheloaiActivity;
import com.example.musicapp.Model.Chude;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachAllchudeAdapter extends RecyclerView.Adapter<DanhsachAllchudeAdapter.ViewHolder> {
    Context context;
    ArrayList<Chude> mangchude;

    public DanhsachAllchudeAdapter(Context context, ArrayList<Chude> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((context));
        View view =  inflater.inflate(R.layout.dong_allchude, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chude chude = mangchude.get(position);
        Picasso.get().load(chude.getHinhChuDe()).into(holder.imgchude);
//        holder.txttenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewdongcacchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachtheloaiActivity.class);
//                    intent.putExtra("chude", mangchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
