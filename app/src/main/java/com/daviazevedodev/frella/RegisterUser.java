package com.daviazevedodev.frella;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.daviazevedodev.frella.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterUser extends AppCompatActivity {
    private EditText edit_name, edit_city, edit_tell, edit_register_email, edit_register_password;
    private Button button_register, button_back_login;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

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
        button_back_login = findViewById(R.id.button_back_login);



        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_register_email.getText().toString().isEmpty() || edit_name.getText().toString().isEmpty() || edit_city.getText().toString().isEmpty() || edit_tell.getText().toString().isEmpty() || edit_register_password.getText().toString().isEmpty() ) {
                    Toast.makeText(RegisterUser.this, "Fill in all the fields!", Toast.LENGTH_SHORT).show();
                }else {
                    register(edit_register_email.getText().toString(), edit_register_password.getText().toString());
                }
            }
        });

        button_back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginUser.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void register(String email, String password) {
        final String email_register = edit_register_email.getText().toString();
        final String name_register = edit_name.getText().toString();
        final String tell_register = edit_tell.getText().toString();
        final String city_register = edit_city.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User person = new User(name_register, email_register, tell_register, city_register);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(person).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(RegisterUser.this, "Sign Up Sucess !",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginUser.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterUser.this, "Sign Up failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterUser.this, "Sign Up failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



    }



