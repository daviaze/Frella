package com.daviazevedodev.frella;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterUser extends AppCompatActivity {
    private EditText edit_name, edit_city, edit_tell, edit_register_email, edit_register_password;
    private Button button_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        edit_name = findViewById(R.id.edit_name);
        edit_city = findViewById(R.id.edit_city);
        edit_tell = findViewById(R.id.edit_tell);
        edit_register_email = findViewById(R.id.edit_register_email);
        edit_register_password = findViewById(R.id.edit_register_password);
        button_register = findViewById(R.id.button_register);



    }
}
