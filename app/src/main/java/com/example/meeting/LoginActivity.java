package com.example.meeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
private EditText email,password;
Button btnsignin;
TextView singnup,forgetpassword;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email= (EditText)findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        forgetpassword = findViewById(R.id.forgetpassowd);
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(LoginActivity.this,resetpassword.class);
                startActivity(n);
            }
        });
        password = (EditText)findViewById(R.id.password);
        btnsignin = (Button)findViewById(R.id.btnlogin);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(LoginActivity.this,"please enter email !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(LoginActivity.this,"please enter the password !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length()<6){
                    Toast.makeText(LoginActivity.this,"Password length is too short !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(LoginActivity.this,Id.class);
                                    startActivity(i);

                                } else {
                                    Toast.makeText(LoginActivity.this,"SignIn Failed !!",Toast.LENGTH_SHORT).show();
                                   return;
                                }
                            }
                        });
            }
        });
        singnup= (TextView)findViewById(R.id.signup);
        singnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent p = new Intent(LoginActivity.this,SignUp.class);
             startActivity(p);

            }
        });
    }
}