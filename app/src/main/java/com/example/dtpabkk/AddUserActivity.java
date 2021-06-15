package com.example.dtpabkk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class AddUserActivity extends AppCompatActivity {

    ImageView ivBack;
    EditText etNama, etKodeNama;
    Button btnTambah;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        ivBack = findViewById(R.id.ivBack);
        etNama = findViewById(R.id.etNama);
        etKodeNama = findViewById(R.id.etKodeNama);
        btnTambah = findViewById(R.id.btnTambah);

        progressBar = new ProgressDialog(AddUserActivity.this);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String kodeNama = etKodeNama.getText().toString();
                progressBar.setMessage("Please wait");
                progressBar.show();
                progressBar.setCancelable(false);
                AndroidNetworking.post(BaseUrl.url + "insertUser.php")
                        .addBodyParameter("kodeNama", kodeNama)
                        .addBodyParameter("nama", nama)
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                AlertDialog.Builder builder = new AlertDialog.Builder(AddUserActivity.this);
                                builder.setMessage("Data berhasil terkirim!")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });

    }
}