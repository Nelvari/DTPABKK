package com.example.dtpabkk.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtpabkk.EditDanaActivity;
import com.example.dtpabkk.EditUserActivity;
import com.example.dtpabkk.Model.ModelUser;
import com.example.dtpabkk.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<ModelUser> dataList;
    View viewku;

    public UserAdapter(ArrayList<ModelUser> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.user_view, parent, false);
        return new UserAdapter.UserViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tvNama.setText(dataList.get(position).getNama());
        holder.tvKodeNama.setText("Kode Anggota : " + dataList.get(position).getKodeNama());
        holder.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), EditUserActivity.class);
                in.putExtra("id", dataList.get(position).getId());
                in.putExtra("nama", dataList.get(position).getNama());
                holder.itemView.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNama, tvKodeNama;
        CardView cvUser;

        UserViewHolder(View itemView) {
            super(itemView);
            cvUser = itemView.findViewById(R.id.cvUser);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvKodeNama = itemView.findViewById(R.id.tvKodeNama);
        }
    }

}
