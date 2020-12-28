package com.uc.pencatatanmou_uc_mobdev.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.R;

import java.util.List;

public class realAdapter extends RecyclerView.Adapter<realAdapter.CardsViewHolder> {

    private Context context;
    private List<Real> list_real;

    public realAdapter(Context context) {
        this.context = context;
    }

    private List<Real> getList_real() {
        return list_real;
    }

    public void setList_real(List<Real> list_real) {
        this.list_real = list_real;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public realAdapter.CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_real_adapter, parent, false);
        return new realAdapter.CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final realAdapter.CardsViewHolder holder, int position) {
        final Real real = getList_real().get(position);

        //THIS CODE IS TO LOAD THE IMAGE :))))
//        Glide.with(context).load(Constants.BASE_IMG_URL+tv.getCover()).into(holder.cover);
        holder.name.setText("tes");
        holder.company.setText("company");
        holder.date.setText("tanggal");
    }

    @Override
    public int getItemCount() {
        return 3;
//        return getList_real().size();
    }

    class CardsViewHolder extends RecyclerView.ViewHolder {
        TextView name, company, date;

        CardsViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_real_title);
            company = itemView.findViewById(R.id.txt_real_company);
            date = itemView.findViewById(R.id.txt_real_date);
        }
    }
}