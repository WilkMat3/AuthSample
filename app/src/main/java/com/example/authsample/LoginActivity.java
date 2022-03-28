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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
EditText email2;
EditText password2;
Button signInBt;
TextView register;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email2=findViewById(R.id.etLoginEmail);
        password2 = findViewById(R.id.etLoginPass);
        signInBt = findViewById(R.id.btnLoginSign);
        register = findViewById(R.id.tvLoginReg);
        register.setOnClickListener(view -> {
            startActivity( new Intent(LoginActivity.this, RegisterActivity.class));
        });
        signInBt.setOnClickListener(view -> {
            loginUser();
        });
    }

    private void loginUser() {
        String email = email2.getText().toString();
        String password = password2.getText().toString();
        if(TextUtils.isEmpty(email)){
            email2.setError("Email is empty");
            email2.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            password2.setError("Password is empty");
            password2.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"Login unsuccessful "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}