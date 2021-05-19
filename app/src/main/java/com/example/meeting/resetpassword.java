package com.example.meeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class resetpassword extends AppCompatActivity {
EditText email,password,confirmpassword;
private FirebaseAuth mAuth;
Button btnreset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.remail);
        btnreset = findViewById(R.id.btnreset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(resetpassword.this,"Please enter email !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.sendPasswordResetEmail(Email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(resetpassword.this,"please check your email",Toast.LENGTH_SHORT).show();
                                        Intent n = new Intent(resetpassword.this,LoginActivity.class);
                                        startActivity(n);
                                    }else {
                                        Toast.makeText(resetpassword.this,"Error",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });
    }
}