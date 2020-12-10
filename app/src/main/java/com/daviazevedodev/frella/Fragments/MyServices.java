package com.daviazevedodev.frella.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.daviazevedodev.frella.Model.Service;
import com.daviazevedodev.frella.R;
import com.daviazevedodev.frella.RecyclerClick;
import com.daviazevedodev.frella.ServiceAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class MyServices extends Fragment {

    RecyclerView recview2;
    ServiceAdapter adapter;
    Service servicoSelecionado;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_services, container, false);

        recview2=view.findViewById(R.id.recview2);

        recview2.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Service> options =
                new FirebaseRecyclerOptions.Builder<Service>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Services").orderByChild("id_user").equalTo(currentFirebaseUser.getUid()), Service.class)
                        .build();
        adapter = new ServiceAdapter(options);
        recview2.setAdapter(adapter);




        return view;

    }


    @Override
    public  void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
