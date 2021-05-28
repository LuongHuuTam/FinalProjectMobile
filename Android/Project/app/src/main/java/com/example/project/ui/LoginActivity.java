package com.example.project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.network.repositories.ApiClient;
import com.example.project.network.models.LoginRequest;
import com.example.project.network.models.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView roleSelector;
    EditText username,password;
    Button btnLogin;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        roleSelector = findViewById(R.id.role_selector);
        String []options = {"Admin","Trainer","Trainee"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.list_item,options);
        roleSelector.setText(arrayAdapter.getItem(0).toString(),false);
        roleSelector.setAdapter(arrayAdapter);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(roleSelector.getText().toString())){
                    Toast.makeText(LoginActivity.this,"All fields is required",Toast.LENGTH_LONG).show();
                }else{
                    login();
                }
            }
        });

    }
    public void login(){
        int roleValue = 1;
        switch (roleSelector.getText().toString()){
            case "Admin":
                roleValue = 1;
                break;
            case "Trainer":
                roleValue = 2;
                break;
            case "Trainee":
                roleValue = 3;
                break;
            default:
                break;
        }
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        loginRequest.setRole(roleValue);
        Call<LoginResponse> loginResponseCall = ApiClient.getLoginService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Throwable "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

}