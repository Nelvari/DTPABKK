package com.example.dtpabkk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BulanActivity extends AppCompatActivity {

    ImageView ivBack;
    TextView tvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulan);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("data");

        ivBack = findViewById(R.id.ivBack);
        tvToolbar = findViewById(R.id.tvToolbar);

        if(data.equals("talangan")){
            tvToolbar.setText("Dana Talangan");
        }else if(data.equals("sendalan")){
            tvToolbar.setText("Dana Sendalan");
        }else if(data.equals("dadakan")){
            tvToolbar.setText("Dana Dadakan");
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void ButtonClick(View v){
        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("data");
        switch (v.getId()) {
            case R.id.januari:
                Intent intent = new Intent(BulanActivity.this, DanaActivity.class);
                intent.putExtra("bulan", "Januari");
                intent.putExtra("data", data);
                startActivity(intent);
                break;
            case R.id.febuari:
                Intent intent2 = new Intent(BulanActivity.this, DanaActivity.class);
                intent2.putExtra("bulan", "Febuari");
                intent2.putExtra("data", data);
                startActivity(intent2);
                break;
            case R.id.maret:
                Intent intent3 = new Intent(BulanActivity.this, DanaActivity.class);
                intent3.putExtra("bulan", "Maret");
                intent3.putExtra("data", data);
                startActivity(intent3);
                break;
            case R.id.april:
                Intent intent4 = new Intent(BulanActivity.this, DanaActivity.class);
                intent4.putExtra("bulan", "April");
                intent4.putExtra("data", data);
                startActivity(intent4);
                break;
            case R.id.mei:
                Intent intent5 = new Intent(BulanActivity.this, DanaActivity.class);
                intent5.putExtra("bulan", "Mei");
                intent5.putExtra("data", data);
                startActivity(intent5);
                break;
            case R.id.juni:
                Intent intent6 = new Intent(BulanActivity.this, DanaActivity.class);
                intent6.putExtra("bulan", "Juni");
                intent6.putExtra("data", data);
                startActivity(intent6);
                break;
            case R.id.juli:
                Intent intent7 = new Intent(BulanActivity.this, DanaActivity.class);
                intent7.putExtra("bulan", "Juli");
                intent7.putExtra("data", data);
                startActivity(intent7);
                break;
            case R.id.agustus:
                Intent intent8 = new Intent(BulanActivity.this, DanaActivity.class);
                intent8.putExtra("bulan", "Agustus");
                intent8.putExtra("data", data);
                startActivity(intent8);
                break;
            case R.id.september:
                Intent intent9 = new Intent(BulanActivity.this, DanaActivity.class);
                intent9.putExtra("bulan", "September");
                intent9.putExtra("data", data);
                startActivity(intent9);
                break;
            case R.id.oktober:
                Intent intent10 = new Intent(BulanActivity.this, DanaActivity.class);
                intent10.putExtra("bulan", "Oktober");
                intent10.putExtra("data", data);
                startActivity(intent10);
                break;
            case R.id.november:
                Intent intent11 = new Intent(BulanActivity.this, DanaActivity.class);
                intent11.putExtra("bulan", "November");
                intent11.putExtra("data", data);
                startActivity(intent11);
                break;
            case R.id.desember:
                Intent intent12 = new Intent(BulanActivity.this, DanaActivity.class);
                intent12.putExtra("bulan", "Desember");
                intent12.putExtra("data", data);
                startActivity(intent12);
                break;
        }
    }

}