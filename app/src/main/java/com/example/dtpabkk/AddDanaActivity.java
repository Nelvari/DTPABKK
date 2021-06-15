package com.example.dtpabkk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.dtpabkk.Adapter.DanaAdapter;
import com.example.dtpabkk.Adapter.UserAdapter;
import com.example.dtpabkk.Model.ModelDana;
import com.example.dtpabkk.Model.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddDanaActivity extends AppCompatActivity {

    ImageView ivBack;
    TextView tvToolbar;
    ArrayList<String> namaAnggota;
    Spinner spinnerNama, spinnerBulan;
    ProgressDialog progressBar;
    String nama, bulan;
    Button btnTambah;
    EditText etTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dana);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("data");

        namaAnggota = new ArrayList<>();
        progressBar = new ProgressDialog(AddDanaActivity.this);

        ivBack = findViewById(R.id.ivBack);
        tvToolbar = findViewById(R.id.tvToolbar);
        spinnerNama = findViewById(R.id.spinnerNama);
        spinnerBulan = findViewById(R.id.spinnerBulan);
        btnTambah = findViewById(R.id.btnTambah);
        etTanggal = findViewById(R.id.etTanggal);

        if(data.equals("talangan")){
            tvToolbar.setText("Tambah Dana Talangan");
        }else if(data.equals("sendalan")){
            tvToolbar.setText("Tambah Dana Sendalan");
        }else if(data.equals("dadakan")){
            tvToolbar.setText("Tambah Dana Dadakan");
        }

        loadUser();
        spinnerNama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nama = spinnerNama.getItemAtPosition(spinnerNama.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bulan = spinnerBulan.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal = etTanggal.getText().toString().trim();
                Log.e("geo", "talangan: " + tanggal );
                if(data.equals("talangan")){
                    talangan(bulan, tanggal, nama);
                }else if(data.equals("sendalan")){
                    sendalan(bulan, tanggal, nama);
                }else if(data.equals("dadakan")){
                    dadakan(bulan, tanggal, nama);
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

    public void loadUser(){
        progressBar.setMessage("Please wait");
        progressBar.show();
        progressBar.setCancelable(false);
        AndroidNetworking.get(BaseUrl.url + "getUser.php")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (progressBar.isShowing()) {
                                progressBar.dismiss();
                            }
                            JSONArray data = response.getJSONArray("PAYLOAD");
                            for (int i = 0; i < data.length(); i++) {

                                JSONObject object = data.getJSONObject(i);
                                String name = object.getString("nama");
                                namaAnggota.add(name);

                            }

                            spinnerNama.setAdapter(new ArrayAdapter<String>(AddDanaActivity.this, android.R.layout.simple_spinner_dropdown_item, namaAnggota));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (progressBar.isShowing()) {
                            progressBar.dismiss();
                        }
                        Toast.makeText(AddDanaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        Log.e("geo", "onError: " + anError.getErrorDetail());
                        Log.e("geo", "onError: " + anError.getErrorBody());
                        Log.e("geo", "onError: " + anError.getErrorCode());
                    }
                });
    }

    public void talangan(String bulan, String tanggal, String nama){
        progressBar.setMessage("Please wait");
        progressBar.show();
        progressBar.setCancelable(false);
        AndroidNetworking.post(BaseUrl.url + "insertDanaTalangan.php")
                .addBodyParameter("bulan", bulan)
                .addBodyParameter("tanggal", tanggal)
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

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddDanaActivity.this);
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

    public void sendalan(String bulan, String tanggal, String nama){
        progressBar.setMessage("Please wait");
        progressBar.show();
        progressBar.setCancelable(false);
        AndroidNetworking.post(BaseUrl.url + "insertDanaSendalan.php")
                .addBodyParameter("bulan", bulan)
                .addBodyParameter("tanggal", tanggal)
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

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddDanaActivity.this);
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

    public void dadakan(String bulan, String tanggal, String nama){
        progressBar.setMessage("Please wait");
        progressBar.show();
        progressBar.setCancelable(false);
        AndroidNetworking.post(BaseUrl.url + "insertDanaDadakan.php")
                .addBodyParameter("bulan", bulan)
                .addBodyParameter("tanggal", tanggal)
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

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddDanaActivity.this);
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

}