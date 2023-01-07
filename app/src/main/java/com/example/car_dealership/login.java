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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
 private EditText userNameEdit,pwdEdit;
 private Button loginBtn;
 private ProgressBar loadingPB;
 private TextView registerTv;
 private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEdit = findViewById(R.id.username);
        pwdEdit = findViewById(R.id.password);
        loginBtn = findViewById(R.id.btn_login);
        loadingPB = findViewById(R.id.progress_bar);
        registerTv = findViewById(R.id.txt_register);
        Auth = FirebaseAuth.getInstance();

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,registration.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String username = userNameEdit.getText().toString();
                String password = pwdEdit.getText().toString();
                if(TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Please Enter Your Username and Password", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    Auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(login.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(login.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = Auth.getCurrentUser();
        if(user!=null){
            Intent i = new Intent(login.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}