package com.daviazevedodev.frella;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviazevedodev.frella.Fragments.CreateService;
import com.daviazevedodev.frella.Model.Service;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class Service_Activity extends AppCompatActivity {
    RecyclerView recview;
    ServiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Service> options =
                new FirebaseRecyclerOptions.Builder<Service>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Services"), Service.class)
                .build();
         adapter = new ServiceAdapter(options);
         recview.setAdapter(adapter);
}

        @Override
        protected  void onStart(){
            super.onStart();
            adapter.startListening();
        }

        @Override
        protected void onStop(){
            super.onStop();
            adapter.stopListening();
        }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.adicionar:
                    CreateService timesFragment = new CreateService();

                    getSupportFragmentManager().beginTransaction().replace(R.id.bloco, timesFragment).commit();
                    FrameLayout fl = (FrameLayout) findViewById(R.id.bloco);

                    fl.setVisibility(View.VISIBLE);
                    return true;

                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }

    };
}