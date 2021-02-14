package com.example.calgaryhacks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        showAllUserData();
    }

    public void goToFb(View v) {
        String url = "https://www.facebook.com/carl.machaalani.7/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void showAllUserData() {

        // Hook
        TextView fullName = findViewById(R.id.full_name);

        // Start intent
        Intent intent = getIntent();

        // gel full name from database
        String name = intent.getStringExtra("fullName");

        // set the views
        fullName.setText(name);
    }
}