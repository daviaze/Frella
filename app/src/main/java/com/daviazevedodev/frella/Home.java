package com.daviazevedodev.frella;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daviazevedodev.frella.Fragments.CreateService;
import com.daviazevedodev.frella.Fragments.ProfileUser;
import com.daviazevedodev.frella.Model.Categoria;
import com.daviazevedodev.frella.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private TextView textview_name, textview_city;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    List<Categoria> listcategoria;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textview_name = findViewById(R.id.textview_name);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

                String name_db = dataSnapshot.child("Users").child(currentFirebaseUser.getUid()).child("name").getValue(String.class);
                String city_db = dataSnapshot.child("Users").child(currentFirebaseUser.getUid()).child("city").getValue(String.class);

                  textview_name.setText(name_db+", "+city_db);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        listcategoria = new ArrayList<>();
        listcategoria.add((new Categoria("Tecnologia", R.drawable.pcazul)));
        listcategoria.add((new Categoria("Design", R.drawable.designazul)));
        listcategoria.add((new Categoria("Reforço escolar", R.drawable.reforcoazul)));
        listcategoria.add((new Categoria("Música", R.drawable.musicaazul)));
        listcategoria.add((new Categoria("Arquitetura", R.drawable.engenhariaazulk)));
        listcategoria.add((new Categoria("Saúde", R.drawable.saudeazul)));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_feed);
        FeedAdapter myAdapter = new FeedAdapter(this,listcategoria);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    RecyclerView feed = (RecyclerView) findViewById(R.id.recyclerview_feed);
                    TextView text_name = (TextView) findViewById(R.id.textview_name);
                    ImageView image_person = (ImageView) findViewById(R.id.image_person);



                    feed.setVisibility(View.INVISIBLE);
                    text_name.setText("Criar Serviço"); image_person.setImageResource(R.drawable.ic_baseline_add_circle_24);


                    fl.setVisibility(View.VISIBLE);
                    return true;

                case R.id.home:
                    startActivity(new Intent (getApplicationContext(), Home.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.perfil:
                    ProfileUser timesFragment2 = new ProfileUser();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bloco, timesFragment2).commit();

                    FrameLayout fl2 = (FrameLayout) findViewById(R.id.bloco);
                    RecyclerView feed2 = (RecyclerView) findViewById(R.id.recyclerview_feed);
                    TextView text_name2 = (TextView) findViewById(R.id.textview_name);
                    ImageView image_person2 = (ImageView) findViewById(R.id.image_person);

                    text_name2.setText("Meu Perfil"); image_person2.setImageResource(R.drawable.ic_baseline_person_24);


                    feed2.setVisibility(View.INVISIBLE);
                    fl2.setVisibility(View.VISIBLE);
                    return true;

            }

            return false;
        }

    };



}

