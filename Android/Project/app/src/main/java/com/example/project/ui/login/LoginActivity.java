package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.models.LoginRequest;
import com.example.project.models.LoginResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.example.project.ui.MainActivity;
import com.example.project.ui.classes.ClassViewModel;


public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;
    AutoCompleteTextView roleSelector;
    EditText username, password;
    Button btnLogin;
    CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        //roleSelector setup options
        roleSelector = findViewById(R.id.role_selector);
        String[] options = {"Admin", "Trainer", "Trainee"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_item, options);
        roleSelector.setText(arrayAdapter.getItem(0).toString(), false);
        roleSelector.setAdapter(arrayAdapter);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);

        loginViewModel.getLoginResponseLiveData().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                //set LoginResponse Object to sharePreference
                SharedPreferencesManager.setLoginResponseValue(getApplicationContext(),loginResponse);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginActivity.this,
                                MainActivity.class));
                    }
                }, 700);
            }
        });

        loginViewModel.getLoginFailureLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this,s, Toast.LENGTH_LONG).show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(roleSelector.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "All fields is required", Toast.LENGTH_LONG).show();
                } else {
                    login();
                }
            }
        });

    }

    public void login() {
        int roleValue = 1;
        switch (roleSelector.getText().toString()) {
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

        loginViewModel.login(loginRequest);
    }

}