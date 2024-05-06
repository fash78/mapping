package com.example.mapping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        EditText ed1 = findViewById(R.id.emailtxt1);
        EditText pas1 = findViewById(R.id.passtxt2);
        EditText conpas1 = findViewById(R.id.conpasstxt2);
        EditText name = findViewById(R.id.nametxt);
        Button signupbtn = findViewById(R.id.signupbtn);
        TextView signintxt = findViewById(R.id.signintxt);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ed1.getText().toString();
                String password = pas1.getText().toString();
                String conpassword = conpas1.getText().toString();
                String username = name.getText().toString();


                Map<String , Object> adduser = new HashMap<>();

                adduser.put("Name" , username);
                adduser.put("Email" ,email);

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(email,password);




                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
               firestore.collection("User Information").add(adduser)
                       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                   @Override
                   public void onSuccess(DocumentReference documentReference) {
                       Toast.makeText(Signup.this, "Details Added", Toast.LENGTH_SHORT).show();
                   }
               }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(Signup.this, "Unvalied Deatils", Toast.LENGTH_SHORT).show();
                           }
                       });
            }
        });

        signintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup.this , Login.class);
                startActivity(i);
            }
        });
    }
}