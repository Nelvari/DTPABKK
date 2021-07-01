package com.example.dtpabkk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class EditUserActivity extends AppCompatActivity {

    ImageView ivBack;
    ProgressDialog progressBar;
    Button btnEdit, btnHapus;
    EditText etNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        progressBar = new ProgressDialog(EditUserActivity.this);

        ivBack = findViewById(R.id.ivBack);
        btnEdit = findViewById(R.id.btnEdit);
        btnHapus = findViewById(R.id.btnHapus);
        etNama = findViewById(R.id.etNama);

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        final String nama = extras.getString("nama");

        etNama.setText(nama);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setMessage("Please wait");
                progressBar.show();
                AndroidNetworking.post(BaseUrl.url + "editUser.php")
                        .addBodyParameter("id", id)
                        .addBodyParameter("nama", etNama.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (progressBar.isShowing()) {
                                    progressBar.dismiss();
                                }

                                AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.this);
                                builder.setMessage("Data berhasil di edit!")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Intent intent = new Intent(EditUserActivity.this, HomeActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);

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

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.this);
                builder.setMessage("Apakah anda yakin ingin hapus data?")
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                progressBar.setMessage("Please wait");
                                progressBar.show();
                                AndroidNetworking.post(BaseUrl.url + "deleteUser.php")
                                        .addBodyParameter("id", id)
                                        .setTag("test")
                                        .setPriority(Priority.MEDIUM)
                                        .build()
                                        .getAsJSONObject(new JSONObjectRequestListener() {
                                            @Override
                                            public void onResponse(JSONObject response) {

                                                if (progressBar.isShowing()) {
                                                    progressBar.dismiss();
                                                }

                                                AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.this);
                                                builder.setMessage("Data berhasil di hapus!")
                                                        .setCancelable(false)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                Intent intent = new Intent(EditUserActivity.this, HomeActivity.class);
                                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);

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
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }
}