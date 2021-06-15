package com.example.dtpabkk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    LinearLayout btnTalangan, btnSendalan, btnUser, btnDadakan, btnTambah;
    SharedPreferences mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnTalangan = findViewById(R.id.btnTalangan);
        btnSendalan = findViewById(R.id.btnSendalan);
        btnUser = findViewById(R.id.btnUser);
        btnDadakan = findViewById(R.id.btnDadakan);
        btnTambah = findViewById(R.id.btnTambah);

        mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        btnTalangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BulanActivity.class);
                intent.putExtra("data", "talangan");
                startActivity(intent);
            }
        });

        btnSendalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BulanActivity.class);
                intent.putExtra("data", "sendalan");
                startActivity(intent);
            }
        });

        btnDadakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BulanActivity.class);
                intent.putExtra("data", "dadakan");
                startActivity(intent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLogin.getString("username", "")==null
                        ||mLogin.getString("username","").equalsIgnoreCase("")
                        ||mLogin.getString("username","")==""){

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}