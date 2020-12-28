package com.uc.pencatatanmou_uc_mobdev.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.R;

import java.util.ArrayList;
import java.util.List;

public class mouAdapter extends RecyclerView.Adapter<mouAdapter.CardsViewHolder> {

    private Context context;
    private List<Mou> list_mou;

    public mouAdapter(Context context) {
        this.context = context;
    }

    private List<Mou> getList_mou() {
        return list_mou;
    }

    public void setList_mou(List<Mou> list_mou) {
        this.list_mou = list_mou;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mou_adapter, parent, false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final mouAdapter.CardsViewHolder holder, int position) {
        final Mou mou = getList_mou().get(position);

        //THIS CODE IS TO LOAD THE IMAGE :))))
//        Glide.with(context).load(Constants.BASE_IMG_URL+tv.getCover()).into(holder.cover);
        holder.name.setText("tes");
        holder.date.setText("tanggal");
    }

    @Override
    public int getItemCount() {
        return 3;
//        return getList_mou().size();
    }

    class CardsViewHolder extends RecyclerView.ViewHolder {
        TextView name, date;

        CardsViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_mou_company);
            date = itemView.findViewById(R.id.txt_mou_date);
        }
    }
}