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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
EditText Username,phone,email,password,cofirmpassword;
Button signup;
TextView signin;
FirebaseAuth mAuth;
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Username = findViewById(R.id.username);
        phone = findViewById(R.id.number);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cofirmpassword = findViewById(R.id.conpassword);
        signin = findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(SignUp.this,LoginActivity.class);
                startActivity(p);
            }
        });
        signup  = findViewById(R.id.btnsignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = Username.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String Confirmpassword = cofirmpassword.getText().toString();
                if (TextUtils.isEmpty(Name)){
                    Toast.makeText(SignUp.this,"Enter your name !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Phone)){
                    Toast.makeText(SignUp.this,"Enter your phone number !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(SignUp.this,"Enter your password !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length()<6){
                    Toast.makeText(SignUp.this,"Password length is too short !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Confirmpassword)){
                    Toast.makeText(SignUp.this,"Enter your confirm-password !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.equals(Confirmpassword)){
                    mAuth.createUserWithEmailAndPassword(Email,Password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        firebaseDatabase = FirebaseDatabase.getInstance();
                                        reference = firebaseDatabase.getReference("users");
                                        UserHelperClass userHelperClass = new UserHelperClass(Name,Phone);
                                        reference.child(Name).setValue(userHelperClass);
                                      Intent o = new Intent(SignUp.this,Id.class);
                                      startActivity(o);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                       Toast.makeText(SignUp.this,"SignIn Failed !!",Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });
                }
            }
        });
    }
}