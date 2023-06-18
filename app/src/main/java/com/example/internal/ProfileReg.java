package com.example.internal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.internal.databinding.ActivityProfileRegBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileReg extends AppCompatActivity {
    ActivityProfileRegBinding binding;
    String firstName, lastName, age, userName;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_reg);
        binding = ActivityProfileRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = binding.firstName.getText().toString();
                lastName = binding.lastName.getText().toString();
                age = binding.age.getText().toString();
                userName = binding.userName.getText().toString();
                if(!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){

                    Users users = new Users(firstName, lastName, age, userName);
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("Users");
                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.firstName.setText("");
                            binding.lastName.setText("");
                            binding.age.setText("");
                            binding.userName.setText("");
                            Toast.makeText(ProfileReg.this,"Successfuly updated", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            }
        });
    }
}