package com.daviazevedodev.frella;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviazevedodev.frella.Fragments.CreateService;
import com.daviazevedodev.frella.Fragments.ProfileUser;
import com.daviazevedodev.frella.Model.Service;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class Service_Activity extends AppCompatActivity {
    RecyclerView recview;
    ServiceAdapter adapter;
    CardView card_feed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_);


        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        CardView card_feed = findViewById(R.id.card_feed);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("title");



        FirebaseRecyclerOptions<Service> options =
                new FirebaseRecyclerOptions.Builder<Service>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Services").orderByChild("area").equalTo(Title), Service.class)
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

                    getSupportFragmentManager().beginTransaction().replace(R.id.bloco2, timesFragment).commit();
                    FrameLayout fl = (FrameLayout) findViewById(R.id.bloco2);
                    RecyclerView recview = (RecyclerView) findViewById(R.id.recview) ;

                    TextView text_name = (TextView) findViewById(R.id.textview_service);
                    ImageView image_person = (ImageView) findViewById(R.id.image_service);



                    text_name.setText("Criar Serviço"); image_person.setImageResource(R.drawable.ic_baseline_add_circle_24);



                    recview.setVisibility(View.INVISIBLE);
                    fl.setPadding(0, 200, 0, 0);
                    fl.setVisibility(View.VISIBLE);
                    return true;

                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.perfil:
                    ProfileUser timesFragment2 = new ProfileUser();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bloco2, timesFragment2).commit();

                    FrameLayout fl2 = (FrameLayout) findViewById(R.id.bloco2);
                    RecyclerView recview2 = (RecyclerView) findViewById(R.id.recview);


                    TextView text_name2 = (TextView) findViewById(R.id.textview_service);
                    ImageView image_person2 = (ImageView) findViewById(R.id.image_service);

                    text_name2.setText("Meu Perfil"); image_person2.setImageResource(R.drawable.ic_baseline_person_24);


                    recview2.setVisibility(View.INVISIBLE);
                    fl2.setVisibility(View.VISIBLE);
                    return true;


            }
            return false;
        }

    };
}