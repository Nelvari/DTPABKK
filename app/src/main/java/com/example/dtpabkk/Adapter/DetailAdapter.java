package com.example.dtpabkk.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtpabkk.EditDanaActivity;
import com.example.dtpabkk.Model.ModelDana;
import com.example.dtpabkk.Model.ModelDetail;
import com.example.dtpabkk.R;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    private List<ModelDetail> dataList;
    View viewku;

    public DetailAdapter(ArrayList<ModelDetail> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.detail_view, parent, false);
        return new DetailAdapter.DetailViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.tvTanggal.setText(dataList.get(position).getTanggal());
        holder.linearDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), EditDanaActivity.class);
                in.putExtra("data", dataList.get(position).getDana());
                in.putExtra("id", dataList.get(position).getId());
                holder.itemView.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTanggal;
        LinearLayout linearDetail;

        DetailViewHolder(View itemView) {
            super(itemView);
            linearDetail = itemView.findViewById(R.id.linearDetail);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
        }
    }

}
