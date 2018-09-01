package com.example.shikharjai.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class third extends AppCompatActivity {
    Button saved;
    EditText name, email, phone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.third);
        super.onCreate(savedInstanceState);


        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        saved=(Button) findViewById(R.id.saved);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = myRef.push().getKey();
                myRef.child(uid).setValue(new model(name.getText().toString(), Long.parseLong(phone.getText().toString()), email.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(third.this, "Your Contact Saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                            Toast.makeText(third.this, "Kindly enter valid input fields", Toast.LENGTH_SHORT).show();

                    }
                });
                Toast.makeText(third.this, "Your Contact Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(third.this, second.class));
                finish();
//                Log.d("aaaa","ssssssssss");
            }
        });
    }
}
