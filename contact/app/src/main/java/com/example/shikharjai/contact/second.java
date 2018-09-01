package com.example.shikharjai.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class second extends AppCompatActivity{
    Toolbar toolbar;
    Button add_con;
    List<model> modelList;
    List<String> contact_string_list;
    RecyclerView phonebook;
    ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        phonebook = findViewById(R.id.rec);
        modelList = new ArrayList<>();
        toolbar = findViewById(R.id.tl);
        toolbar.setTitle("Welcome");
        setSupportActionBar(toolbar);
        add_con=(Button) findViewById(R.id.add_con);
        add_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(second.this, third.class));
            }
        });

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("user");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                modelList.clear();
                for(DataSnapshot phone: dataSnapshot.getChildren()){
                    modelList.add(phone.getValue(model.class));
                }
                contact_string_list = new ArrayList<>();
                for(model a: modelList ){
                    contact_string_list.add(a.getName()+":"+a.getPhone()+":"+a.getEmail());
                }
                for(String abc:contact_string_list) {
                        Log.d("aaaaaaaaa", abc);
                }
                phonebook.setLayoutManager(new LinearLayoutManager(second.this));
                phonebook.setAdapter(new contactAdapter(contact_string_list, second.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError){
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        add_con = findViewById(R.id.add_con);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(second.this,contact.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
