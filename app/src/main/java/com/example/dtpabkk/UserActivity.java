package com.example.dtpabkk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.dtpabkk.Adapter.UserAdapter;
import com.example.dtpabkk.Model.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ImageView ivBack;
    ArrayList<ModelUser> mUser;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mUser = new ArrayList<>();
        progressBar = new ProgressDialog(UserActivity.this);

        recyclerView = findViewById(R.id.rvUser);
        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBar.setMessage("Please wait");
        progressBar.show();

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

                                ModelUser model = new ModelUser();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setKodeNama(object.getString("kodeNama"));
                                model.setNama(object.getString("nama"));
                                mUser.add(model);

                            }

                            userAdapter = new UserAdapter(mUser);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(userAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (progressBar.isShowing()) {
                            progressBar.dismiss();
                        }
                        Toast.makeText(UserActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        Log.e("geo", "onError: " + anError.getErrorDetail());
                        Log.e("geo", "onError: " + anError.getErrorBody());
                        Log.e("geo", "onError: " + anError.getErrorCode());
                    }
                });

    }
}