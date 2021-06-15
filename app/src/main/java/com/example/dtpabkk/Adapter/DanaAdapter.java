package com.example.dtpabkk.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtpabkk.DetailActivity;
import com.example.dtpabkk.Model.ModelDana;
import com.example.dtpabkk.R;

import java.util.ArrayList;
import java.util.List;

public class DanaAdapter extends RecyclerView.Adapter<DanaAdapter.DanaViewHolder>{

    private List<ModelDana> dataList;
    View viewku;

    public DanaAdapter(ArrayList<ModelDana> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DanaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.user_view, parent, false);
        return new DanaAdapter.DanaViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull DanaViewHolder holder, int position) {
        holder.tvNama.setText(dataList.get(position).getNama());
        holder.tvKodeNama.setText("Jumlah : " + dataList.get(position).getJumlah());
        holder.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("data", dataList.get(position).getDana());
                intent.putExtra("bulan", dataList.get(position).getBulan());
                intent.putExtra("nama", dataList.get(position).getNama());
                intent.putExtra("kodeNama", dataList.get(position).getKodeNama());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DanaViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNama, tvKodeNama;
        CardView cvUser;

        DanaViewHolder(View itemView) {
            super(itemView);
            cvUser = itemView.findViewById(R.id.cvUser);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvKodeNama = itemView.findViewById(R.id.tvKodeNama);
        }
    }

}
