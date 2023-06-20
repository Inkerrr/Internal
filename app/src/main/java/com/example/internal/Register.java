package com.example.internal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword; //declare variables for initializing
    Button buttonReg;         //1step is declare variables for initializing, 2step initialize them in the method
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {    //onCreate(method that invokes the activity)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email); //initialize the email and password objects
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register); //initialize the button object
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class); // THIS PART IS IMPORTANT(IT SHOWS HOW YOU CAN ANOTHER ACTIVITY)
                startActivity(intent);
                finish(); //finish activity and start another (login)
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {  //declaring method onClick for buttonReg
            @Override
            public void onClick(View v) { //inside it we will write the code to execute
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText()); //Using basic OOP get method(getText()) and converting it to String
                password = String.valueOf(editTextPassword.getText()); //however instead of toString we can use String.ValueOf method

                if(TextUtils.isEmpty(email)){  // checking if the text is empty
                Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show(); //then show enter email via Toast.makeText
                    return;
                }
                if(TextUtils.isEmpty(password)){
                Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    Toast.makeText(Register.this, "Account created.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), ProfileReg.class); // THIS PART IS IMPORTANT(IT SHOWS HOW YOU CAN ANOTHER ACTIVITY)
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentification failed. ",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}