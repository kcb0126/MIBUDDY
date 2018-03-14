package com.kcb0126.developer.mibuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.managers.ApiManager;

public class SignupActivity extends AppCompatActivity implements OnClickListener {

    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtReenterPassword;
    private EditText edtUsername;
    private EditText edtGender;
    private EditText edtAge;
    private EditText edtNationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // configure cancel button
        View btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        //configure join now button
        Button btnJoinnow = findViewById(R.id.btnJoinnow);
        btnJoinnow.setOnClickListener(this);

        // configure input form
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtReenterPassword = (EditText)findViewById(R.id.edtReenterPassword);
        edtUsername = (EditText)findViewById(R.id.edtUsername);
        edtGender = (EditText)findViewById(R.id.edtGender);
        edtAge = (EditText)findViewById(R.id.edtAge);
        edtNationality = (EditText)findViewById(R.id.edtNationality);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                finish();
                break;

            case R.id.btnJoinnow:
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String passwordconfirm = edtReenterPassword.getText().toString();
                String username = edtUsername.getText().toString();
                String gender = edtGender.getText().toString();
                int age = Integer.parseInt(edtAge.getText().toString());
                String nationality = edtNationality.getText().toString();

                if(!password.equals(passwordconfirm)) {
                    Toast.makeText(this, "Password mismatch.", Toast.LENGTH_LONG).show();
                    return;
                }
                ApiManager.instance().signup(this, email, password, username, gender, age, nationality, new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
                break;

            default:
                break;
        }
    }
}
