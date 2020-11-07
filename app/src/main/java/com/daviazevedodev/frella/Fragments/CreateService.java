package com.daviazevedodev.frella.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daviazevedodev.frella.Model.Service;
import com.daviazevedodev.frella.Model.User;
import com.daviazevedodev.frella.R;
import com.daviazevedodev.frella.RegisterUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class CreateService extends Fragment {

    private TextView text;
    private EditText service_value, name_value, works_value, tell_value, description_value;
    private Button button_criar;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    Service service;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_service, container, false);
        text=view.findViewById(R.id.escolha_servicos);
        service_value=view.findViewById(R.id.service_value);
        name_value=view.findViewById(R.id.name_value);
        works_value=view.findViewById(R.id.works_value);
        tell_value=view.findViewById(R.id.tell_value);
        description_value=view.findViewById(R.id.description_value);
        button_criar=view.findViewById(R.id.button_criar);


        mDatabase = FirebaseDatabase.getInstance().getReference("Services");

        service=new Service();

        button_criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String servicevalue = service_value.getText().toString();
                String namevalue = name_value.getText().toString();
                String worksvalue = works_value.getText().toString();
                String tellvalue = tell_value.getText().toString();
                String descriptionvalue = description_value.getText().toString();
                String buttoncriar = button_criar.getText().toString();

                service.setName_service(servicevalue);
                service.setName_person(namevalue);
                service.setTelephone(tellvalue);
                service.setDescription(descriptionvalue);
                service.setPortifolio(worksvalue);


                mDatabase.push().setValue(service);

            }

    });
        return view;

    }
    }


