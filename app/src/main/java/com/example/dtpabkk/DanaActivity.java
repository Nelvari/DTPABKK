package com.example.dtpabkk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class DanaActivity extends AppCompatActivity {

    ImageView ivBack;
    TextView tvToolbar;
    ProgressDialog progressBar;
    ArrayList<ModelDana> mDana;
    RecyclerView recyclerView;
    DanaAdapter danaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dana);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("data");
        final String bulan = extras.getString("bulan");

        ivBack = findViewById(R.id.ivBack);
        tvToolbar = findViewById(R.id.tvToolbar);
        recyclerView = findViewById(R.id.rvDana);
        progressBar = new ProgressDialog(DanaActivity.this);

        if(data.equals("talangan")){
            tvToolbar.setText("Dana Talangan ( " + bulan + " )");
            talangan(bulan, data);
        }else if(data.equals("sendalan")){
            tvToolbar.setText("Dana Sendalan ( " + bulan + " )");
            sendalan(bulan, data);
        }
        else if(data.equals("dadakan")){
            tvToolbar.setText("Dana Dadakan ( " + bulan + " )");
            dadakan(bulan, data);
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void talangan(String bulan, String dana){
        mDana = new ArrayList<>();
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "getDanaTalangan.php")
                .addBodyParameter("bulan", bulan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
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

                                ModelDana model = new ModelDana();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setKodeNama(object.getString("kodeNama"));
                                model.setNama(object.getString("nama"));
                                model.setJumlah(object.getString("jumlah"));
                                model.setDana(dana);
                                model.setBulan(bulan);
                                mDana.add(model);

                            }

                            danaAdapter = new DanaAdapter(mDana);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DanaActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(danaAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public void sendalan(String bulan, String dana){
        mDana = new ArrayList<>();
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "getDanaSendalan.php")
                .addBodyParameter("bulan", bulan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
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

                                ModelDana model = new ModelDana();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setKodeNama(object.getString("kodeNama"));
                                model.setNama(object.getString("nama"));
                                model.setJumlah(object.getString("jumlah"));
                                model.setDana(dana);
                                model.setBulan(bulan);
                                mDana.add(model);

                            }

                            danaAdapter = new DanaAdapter(mDana);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DanaActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(danaAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public void dadakan(String bulan, String dana){
        mDana = new ArrayList<>();
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "getDanaDadakan.php")
                .addBodyParameter("bulan", bulan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
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

                                ModelDana model = new ModelDana();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setKodeNama(object.getString("kodeNama"));
                                model.setNama(object.getString("nama"));
                                model.setJumlah(object.getString("jumlah"));
                                model.setDana(dana);
                                model.setBulan(bulan);
                                mDana.add(model);

                            }

                            danaAdapter = new DanaAdapter(mDana);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DanaActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(danaAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}