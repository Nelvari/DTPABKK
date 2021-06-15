package com.example.dtpabkk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AddActivity extends AppCompatActivity {

    ImageView ivBack, ivLogout;
    SharedPreferences mLogin;
    LinearLayout btnTalangan, btnSendalan, btnUser, btnDadakan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ivBack = findViewById(R.id.ivBack);
        ivLogout = findViewById(R.id.ivLogout);
        btnTalangan = findViewById(R.id.btnTalangan);
        btnSendalan = findViewById(R.id.btnSendalan);
        btnUser = findViewById(R.id.btnUser);
        btnDadakan = findViewById(R.id.btnDadakan);
        mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        btnTalangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, AddDanaActivity.class);
                intent.putExtra("data", "talangan");
                startActivity(intent);
            }
        });

        btnSendalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, AddDanaActivity.class);
                intent.putExtra("data", "sendalan");
                startActivity(intent);
            }
        });

        btnDadakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, AddDanaActivity.class);
                intent.putExtra("data", "dadakan");
                startActivity(intent);
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogInterface.OnClickListener dialog =new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:

                                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                                SharedPreferences.Editor editor = mLogin.edit();
                                editor.clear();
                                editor.commit();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }

                    }

                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Apakah anda yakin ingin logout?").setPositiveButton("Ya", dialog)
                        .setTitle("Konfirmasi logout")
                        .setNegativeButton("Tidak", dialog).show();
            }
        });

    }
}