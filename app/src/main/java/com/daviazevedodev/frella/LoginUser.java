package com.daviazevedodev.frella;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUser extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edit_email, edit_password;
    private Button button_login;
    private Button button_register;
    private Button sair;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);

        mAuth = FirebaseAuth.getInstance();

        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        button_login = findViewById(R.id.button_login);
        button_register = findViewById(R.id.button_register);
        sair = findViewById(R.id.sair);



        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edit_email.getText().toString(), edit_password.getText().toString());
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            Toast.makeText(LoginUser.this, "Login sucess!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginUser.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}
