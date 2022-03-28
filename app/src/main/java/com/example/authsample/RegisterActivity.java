package com.example.authsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText eTemail;
    EditText eTpassword;
    Button done;
    TextView signInSTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        done = findViewById(R.id.btnLoginSign);
        eTemail=findViewById(R.id.etLoginEmail);
        eTpassword=findViewById(R.id.etLoginPass);
        signInSTR = findViewById(R.id.tvLoginReg);
        done.setOnClickListener(view -> {
            createUser();

        });
        signInSTR.setOnClickListener(view -> {
            startActivity( new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
    private void createUser(){
        String email = eTemail.getText().toString();
        String password = eTpassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            eTemail.setError("Email is empty");
            eTemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            eTpassword.setError("Password is empty");
            eTpassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Registration completed successfuly",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this,"Registration unsuccessful " +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}