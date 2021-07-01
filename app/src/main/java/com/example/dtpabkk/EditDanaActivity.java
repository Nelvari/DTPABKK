package com.example.dtpabkk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class EditDanaActivity extends AppCompatActivity {

    ImageView ivBack;
    TextView tvToolbar;
    ProgressDialog progressBar;
    Button btnEdit, btnHapus;
    EditText etTanggal;
    String bulan;
    Spinner spinnerBulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dana);

        progressBar = new ProgressDialog(EditDanaActivity.this);

        ivBack = findViewById(R.id.ivBack);
        btnEdit = findViewById(R.id.btnEdit);
        btnHapus = findViewById(R.id.btnHapus);
        etTanggal = findViewById(R.id.etTanggal);
        spinnerBulan = findViewById(R.id.spinnerBulan);
        tvToolbar = findViewById(R.id.tvToolbar);

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        final String tanggal = extras.getString("tanggal");
        final String data = extras.getString("data");
        final String nama = extras.getString("nama");

        if(data.equals("talangan")){
            tvToolbar.setText("Edit Dana Talangan " + nama);
        }else if(data.equals("sendalan")){
            tvToolbar.setText("Edit Dana Sendalan " + nama);
        }else if(data.equals("dadakan")){
            tvToolbar.setText("Edit Dana Dadakan " + nama);
        }

        etTanggal.setText(tanggal);

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bulan = spinnerBulan.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal = etTanggal.getText().toString().trim();
                if(data.equals("talangan")){
                    editTalangan(bulan, tanggal, id);
                }else if(data.equals("sendalan")){
                    editSendalan(bulan, tanggal, id);
                }else if(data.equals("dadakan")){
                    editDadakan(bulan, tanggal, id);
                }
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.equals("talangan")){
                    deleteTalangan(id);
                }else if(data.equals("sendalan")){
                    deleteSendalan(id);
                }else if(data.equals("dadakan")){
                    deleteDadakan(id);
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void deleteDadakan(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
        builder.setMessage("Apakah anda yakin ingin hapus data?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressBar.setMessage("Please wait");
                        progressBar.show();
                        AndroidNetworking.post(BaseUrl.url + "deleteDanaDadakan.php")
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

                                        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
                                        builder.setMessage("Data berhasil di hapus!")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        Intent intent = new Intent(EditDanaActivity.this, HomeActivity.class);
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

    private void deleteSendalan(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
        builder.setMessage("Apakah anda yakin ingin hapus data?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressBar.setMessage("Please wait");
                        progressBar.show();
                        AndroidNetworking.post(BaseUrl.url + "deleteDanaSendalan.php")
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

                                        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
                                        builder.setMessage("Data berhasil di hapus!")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        Intent intent = new Intent(EditDanaActivity.this, HomeActivity.class);
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

    private void deleteTalangan(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
        builder.setMessage("Apakah anda yakin ingin hapus data?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressBar.setMessage("Please wait");
                        progressBar.show();
                        AndroidNetworking.post(BaseUrl.url + "deleteDanaTalangan.php")
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

                                        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
                                        builder.setMessage("Data berhasil di hapus!")
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        Intent intent = new Intent(EditDanaActivity.this, HomeActivity.class);
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

    private void editDadakan(String bulan, String tanggal, String id) {
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "editDanaDadakan.php")
                .addBodyParameter("id", id)
                .addBodyParameter("tanggal", tanggal)
                .addBodyParameter("bulan", bulan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (progressBar.isShowing()) {
                            progressBar.dismiss();
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
                        builder.setMessage("Data berhasil di edit!")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        Intent intent = new Intent(EditDanaActivity.this, HomeActivity.class);
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

    private void editSendalan(String bulan, String tanggal, String id) {
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "editDanaSendalan.php")
                .addBodyParameter("id", id)
                .addBodyParameter("tanggal", tanggal)
                .addBodyParameter("bulan", bulan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (progressBar.isShowing()) {
                            progressBar.dismiss();
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
                        builder.setMessage("Data berhasil di edit!")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        Intent intent = new Intent(EditDanaActivity.this, HomeActivity.class);
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

    private void editTalangan(String bulan, String tanggal, String id) {
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "editDanaTalangan.php")
                .addBodyParameter("id", id)
                .addBodyParameter("tanggal", tanggal)
                .addBodyParameter("bulan", bulan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (progressBar.isShowing()) {
                            progressBar.dismiss();
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(EditDanaActivity.this);
                        builder.setMessage("Data berhasil di edit!")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        Intent intent = new Intent(EditDanaActivity.this, HomeActivity.class);
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
}