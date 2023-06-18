package com.example.internal;

import static com.example.internal.R.id.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button button;
    TextView textView;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment(); //Change later
    AccountFragment accountFragment = new AccountFragment();
    FriendsFragment friendsFragment = new FriendsFragment();
    NotificationFragment notificationFragment = new NotificationFragment(); //Change later



    /*EditText yValue;  //Gr
    Button btn_insert;       //Gr
    FirebaseDatabase database;  //Gr
    DatabaseReference reference;  //Gr

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss"); //gr
    GraphView graphView; //gr
    LineGraphSeries series; //gr
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //yValue=(EditText) findViewById(R.id.y_value)

        bottomNavigationView = findViewById(R.id.bottom_navigation); //navigation tool bar
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                    if (item.getItemId() == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                    return true;}
                    if (item.getItemId() == R.id.settings) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsFragment).commit();
                    return true;}
                    if (item.getItemId() == R.id.Account) {                                                         
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,accountFragment).commit();
                    return true;}
                    if (item.getItemId() == R.id.friends) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,friendsFragment).commit();
                    return true;}
                    if (item.getItemId() == R.id.notification) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationFragment).commit();
                    return true;}
                    return false;
                }

            }
        );

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);

        user = auth.getCurrentUser();
        if (user==null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}