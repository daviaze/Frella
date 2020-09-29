package com.daviazevedodev.frella;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    private TextView textview_name;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textview_name = findViewById(R.id.textview_name);

        database = FirebaseDatabase.getInstance();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);

        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");




        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        textview_name.setText("Bem vindo, "+ds.child("name").getValue(String.class)+"!");
                }
            }





            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }
}

