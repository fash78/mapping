package com.example.mapping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText ed2 = findViewById(R.id.emailtxt);
        EditText pass2 = findViewById(R.id.passtxt);
        Button loginbtn = findViewById(R.id.loginbtn);
        TextView signuptxt = findViewById(R.id.signuptxt);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = ed2.getText().toString();
                String userpassword = pass2.getText().toString();


                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(useremail , userpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent i = new Intent(Login.this , Homepage.class);
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Unvalied details", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this , Signup.class);
                startActivity(i);
            }
        });




    }
}