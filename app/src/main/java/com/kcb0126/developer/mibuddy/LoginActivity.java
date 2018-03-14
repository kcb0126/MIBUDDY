package com.kcb0126.developer.mibuddy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.managers.ApiManager;
import com.kcb0126.developer.mibuddy.managers.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // configuration of login and signup button
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        Button btnSignup = (Button)findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);

        // configuration of input form
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtEmail.setText(PreferenceManager.instance().getEmail(this));

        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword.setText(PreferenceManager.instance().getPassword(this));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                final String email = edtEmail.getText().toString();
                final String password = edtPassword.getText().toString();
                final Context context = this;
                ApiManager.instance().login(context, email, password, new Runnable() {
                    @Override
                    public void run() {
                        // save email and password when success.
                        PreferenceManager.instance().putEmail(context, email);
                        PreferenceManager.instance().putPassword(context, password);

                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.btnSignup:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
