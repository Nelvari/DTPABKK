package com.example.dtpabkk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    SharedPreferences mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = getSharedPreferences("login", Context.MODE_PRIVATE);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUsername = etEmail.getText().toString().trim();
                String mPassword = etPassword.getText().toString().trim();
                if(TextUtils.isEmpty(mUsername)){
                    etEmail.setError("Email tidak boleh kosong.");
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    etPassword.setError("Password tidak boleh kosong.");
                    return;
                }
                if(mPassword.length() < 6){
                    etPassword.setError("Password minimal 6 karakter.");
                    return;
                }
                if(mUsername.equalsIgnoreCase("DANATALANGAN") && mPassword.equalsIgnoreCase("123456")){
                    SharedPreferences.Editor editor = mLogin.edit();
                    editor.putString("username", mUsername);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, AddActivity.class);
                    startActivity(intent);
                    finish();
                }else if(!mUsername.equalsIgnoreCase("DANATALANGAN")){
                    final DialogInterface.OnClickListener dialog =new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            switch (i){
                                case DialogInterface.BUTTON_POSITIVE:

                                    break;
                            }

                        }

                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Username salah!").setPositiveButton("OK", dialog)
                            .setTitle("Login").show();
                }else if(!mPassword.equalsIgnoreCase("123456")){
                    final DialogInterface.OnClickListener dialog =new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            switch (i){
                                case DialogInterface.BUTTON_POSITIVE:

                                    break;
                            }

                        }

                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Password salah!").setPositiveButton("OK", dialog)
                            .setTitle("Login").show();
                }
            }
        });

    }
}