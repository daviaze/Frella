package com.daviazevedodev.frella.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
import com.daviazevedodev.frella.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateService extends Fragment {

    private TextView text;
    private EditText service_value, name_value, works_value, tell_value, description_value;
    private Button button_criar;
    private RadioGroup radioGroup;
    private RadioButton radio_tecnologia, radio_design, radio_reforco, radio_musica, radio_arquitetura, radio_saude, radioButton;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private TabLayout tabs;
    private TabItem tabCriar;
    private TabItem tabTodos;
    private ViewPager viewPager;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

    Service service;

    public static CreateService getInstance(){
        CreateService createService = new CreateService();
        return createService;
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_create_service, container, false);
        text=view.findViewById(R.id.escolha_servicos);
        service_value=view.findViewById(R.id.service_value);
        name_value=view.findViewById(R.id.name_value);
        works_value=view.findViewById(R.id.works_value);
        tell_value=view.findViewById(R.id.tell_value);
        description_value=view.findViewById(R.id.description_value);
        button_criar=view.findViewById(R.id.button_criar);
        radioGroup=view.findViewById(R.id.radio_group);
        radio_tecnologia=view.findViewById(R.id.radio_tecnologia);
        radio_design=view.findViewById(R.id.radio_design);
        radio_reforco=view.findViewById(R.id.radio_reforco);
        radio_musica=view.findViewById(R.id.radio_musica);
        radio_arquitetura=view.findViewById(R.id.radio_arquitetura);
        radio_saude=view.findViewById(R.id.radio_saude);





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
                String id_value = currentFirebaseUser.getUid();

                service.setName_service(servicevalue);
                service.setName_person(namevalue);
                service.setTelephone(tellvalue);
                service.setDescription(descriptionvalue);
                service.setPortifolio(worksvalue);
                service.setId_user(id_value);

                if (service_value.getText().toString().isEmpty() || (name_value.getText().toString().isEmpty()) || (works_value.getText().toString().isEmpty()) || (tell_value.getText().toString().isEmpty()) || (description_value.getText().toString().isEmpty()))  {
                    Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }else {
                    mDatabase.push().setValue(service);
                    Toast.makeText(getContext(), "Serviço criado com sucesso!", Toast.LENGTH_SHORT).show();
                }






            }

    });

        radio_tecnologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioid);

                service.setArea(radioButton.getText().toString());
            }
        });

        radio_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioid);

                service.setArea(radioButton.getText().toString());
            }
        });

        radio_reforco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioid);

                service.setArea(radioButton.getText().toString());
            }
        });

        radio_musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioid);

                service.setArea(radioButton.getText().toString());
            }
        });

        radio_arquitetura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioid);

                service.setArea(radioButton.getText().toString());
            }
        });

        radio_saude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioid);

                service.setArea(radioButton.getText().toString());
            }
        });



        return view;

    }



}


