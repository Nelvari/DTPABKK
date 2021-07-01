package com.example.dtpabkk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.dtpabkk.Adapter.DanaAdapter;
import com.example.dtpabkk.Adapter.DetailAdapter;
import com.example.dtpabkk.Model.ModelDana;
import com.example.dtpabkk.Model.ModelDetail;
import com.example.dtpabkk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ImageView ivBack;
    TextView tvToolbar;
    ProgressDialog progressBar;
    ArrayList<ModelDetail> mDetail;
    RecyclerView recyclerView;
    DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        final String data = extras.getString("data");
        final String bulan = extras.getString("bulan");
        final String nama = extras.getString("nama");
        final String kodeNama = extras.getString("kodeNama");

        ivBack = findViewById(R.id.ivBack);
        tvToolbar = findViewById(R.id.tvToolbar);
        recyclerView = findViewById(R.id.rvDetail);
        progressBar = new ProgressDialog(DetailActivity.this);

        if(data.equals("talangan")){
            tvToolbar.setText("Detail Talangan " + nama + " ( " + bulan + " )");
            talangan(bulan, data, kodeNama);
        }else if(data.equals("sendalan")){
            tvToolbar.setText("Detail Sendalan " + nama + " ( " + bulan + " )");
            sendalan(bulan, data, kodeNama);
        }else if(data.equals("dadakan")){
            tvToolbar.setText("Detail Dadakan " + nama + " ( " + bulan + " )");
            dadakan(bulan, data, kodeNama);
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void talangan(String bulan, String dana, String kodeNama) {
        mDetail = new ArrayList<>();
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "getDetailDanaTalangan.php")
                .addBodyParameter("bulan", bulan)
                .addBodyParameter("kodeNama", kodeNama)
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

                                ModelDetail model = new ModelDetail();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setKodeNama(object.getString("kodeNama"));
                                model.setNama(object.getString("nama"));
                                model.setTanggal(object.getString("tanggal"));
                                model.setDana(dana);
                                model.setBulan(bulan);
                                mDetail.add(model);

                            }

                            detailAdapter = new DetailAdapter(mDetail);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailActivity.this, 3);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(detailAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public void sendalan(String bulan, String dana, String kodeNama) {
        mDetail = new ArrayList<>();
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "getDetailDanaSendalan.php")
                .addBodyParameter("bulan", bulan)
                .addBodyParameter("kodeNama", kodeNama)
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

                                ModelDetail model = new ModelDetail();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setKodeNama(object.getString("kodeNama"));
                                model.setNama(object.getString("nama"));
                                model.setTanggal(object.getString("tanggal"));
                                model.setDana(dana);
                                model.setBulan(bulan);
                                mDetail.add(model);

                            }

                            detailAdapter = new DetailAdapter(mDetail);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailActivity.this, 3);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(detailAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public void dadakan(String bulan, String dana, String kodeNama) {
        mDetail = new ArrayList<>();
        progressBar.setMessage("Please wait");
        progressBar.show();
        AndroidNetworking.post(BaseUrl.url + "getDetailDanaDadakan.php")
                .addBodyParameter("bulan", bulan)
                .addBodyParameter("kodeNama", kodeNama)
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

                                ModelDetail model = new ModelDetail();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setKodeNama(object.getString("kodeNama"));
                                model.setNama(object.getString("nama"));
                                model.setTanggal(object.getString("tanggal"));
                                model.setDana(dana);
                                model.setBulan(bulan);
                                mDetail.add(model);

                            }

                            detailAdapter = new DetailAdapter(mDetail);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(DetailActivity.this, 3);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.setAdapter(detailAdapter);
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