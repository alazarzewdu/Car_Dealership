package com.example.car_dealership;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {

    private EditText username,password,c_password;
    private Button btn_register;
    private ProgressBar progress_bar;
    private FirebaseAuth mAuth;
    private TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        btn_register = findViewById(R.id.btn_register);
        progress_bar = findViewById(R.id.progress_bar);
        mAuth = FirebaseAuth.getInstance();
        txt_login = findViewById(R.id.txt_login);


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registration.this, login.class);
                startActivity(i);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String c_pass = c_password.getText().toString();
                if(!pass.equals(c_pass)) {
                    Toast.makeText(registration.this, "Please check both passwords are correct",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(user) && TextUtils.isEmpty(pass) && TextUtils.isEmpty(c_pass)){
                    Toast.makeText(registration.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progress_bar.setVisibility(View.GONE);
                                Toast.makeText(registration.this, "User Registered", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(registration.this,login.class);
                                startActivity(i);
                                finish();
                            } else {
                                progress_bar.setVisibility(View.GONE);
                                Toast.makeText(registration.this, "Failed to Register User", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



    }
}