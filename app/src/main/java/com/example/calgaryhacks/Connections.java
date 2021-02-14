package com.example.calgaryhacks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;


public class Connections extends AppCompatActivity {
    String [] filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();

        Spinner filterSpinner = (Spinner) findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filter_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);


        GridLayout profileLayout = (GridLayout) findViewById(R.id.profileLayout);
        GridLayout coursesLayout = (GridLayout) findViewById(R.id.coursesLayout);
        GridLayout majorLayout = (GridLayout) findViewById(R.id.majorLayout);
        GridLayout yearLayout = (GridLayout) findViewById(R.id.yearLayout);

        CheckBox kikibox1 = (CheckBox) findViewById(R.id.kikibox1);
        CheckBox kikibox2 = (CheckBox) findViewById(R.id.kikibox2);
        CheckBox kikibox3 = (CheckBox) findViewById(R.id.kikibox3);
        CheckBox kikibox4 = (CheckBox) findViewById(R.id.kikibox4);

        CheckBox cesarbox1 = (CheckBox) findViewById(R.id.cesarbox1);
        CheckBox cesarbox2 = (CheckBox) findViewById(R.id.cesarbox2);

        CheckBox tamarabox1 = (CheckBox) findViewById(R.id.tamarabox1);
        CheckBox tamarabox2 = (CheckBox) findViewById(R.id.tamarabox2);

        CheckBox johnbox1 = (CheckBox) findViewById(R.id.johnbox1);
        CheckBox johnbox2 = (CheckBox) findViewById(R.id.johnbox2);

        ArrayList<String> cesarsCourses = new ArrayList<String>(Arrays.asList("ECSE 206",
                "ECSE 210", "ECSE 324", "ECSE 321", "ECSE 211"));
        UserHelperClass cesar = new UserHelperClass("Cesar Lahoud", cesarsCourses, "Computer Engineering", "U1");

        ArrayList<String> tamarasCourses = new ArrayList<String>(Arrays.asList("ECSE 206",
                "ECSE 210", "ECSE 324", "ECSE 321", "ECSE 211"));
        UserHelperClass tamara = new UserHelperClass("Tamara Zard", tamarasCourses, "Computer Engineering", "U1");

        ArrayList<String> carlsCourses = new ArrayList<String>(Arrays.asList("COMP 206", "COMP 251",
                "COMP 273", "PSYC 101", "COMP 303"));
        UserHelperClass carl = new UserHelperClass("Carl Machaalani", carlsCourses, "Computer Science", "U1");

        ArrayList<String> kikisCourses = new ArrayList<String>(Arrays.asList("COMP 206", "COMP 250",
                "COMP 273", "COMP 202", "COMP 303"));
        UserHelperClass kiki = new UserHelperClass("Kiki Xiao", kikisCourses, "Computer Science", "U1");

        ArrayList<String> johnsCourses = new ArrayList<String>(Arrays.asList("PSYC 101", "PSYC 211",
                "MGCR 352", "MATH 123"));
        UserHelperClass john = new UserHelperClass("John Doe", johnsCourses, "Psychology", "U1");

        ArrayList<UserHelperClass> users = new ArrayList<>(Arrays.asList(cesar, tamara, carl, kiki, john));

        UserHelperClass loggedInUser = carl;

        for (UserHelperClass user : users) {
            if (users.equals(loggedInUser)) users.remove(loggedInUser);
        }

        FriendsMatching matching = new FriendsMatching(loggedInUser, users);

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(kikibox1.isChecked() || kikibox2.isChecked() || kikibox3.isChecked() || kikibox4.isChecked()){
                    kikibox1.setChecked(true);
                    kikibox2.setChecked(true);
                    kikibox3.setChecked(true);
                    kikibox4.setChecked(true);
                }

                if(tamarabox1.isChecked() || tamarabox2.isChecked()){
                    tamarabox1.setChecked(true);
                    tamarabox2.setChecked(true);
                }

                if(cesarbox1.isChecked() || cesarbox2.isChecked()){
                    cesarbox1.setChecked(true);
                    cesarbox2.setChecked(true);
                }

                if(johnbox1.isChecked() || johnbox2.isChecked()){
                    johnbox1.setChecked(true);
                    johnbox2.setChecked(true);
                }
                String filterChosen = parent.getItemAtPosition(position).toString();
                if(filterChosen.equals("All"))
                    coursesLayout.setVisibility(View.GONE);
                profileLayout.setVisibility(View.VISIBLE);
                yearLayout.setVisibility(View.GONE);
                majorLayout.setVisibility(View.GONE);


                if (filterChosen.equals("Courses")) {
                    coursesLayout.setVisibility(View.VISIBLE);
                    profileLayout.setVisibility(View.GONE);
                    yearLayout.setVisibility(View.GONE);
                    majorLayout.setVisibility(View.GONE);

                }
                if (filterChosen.equals("Major")) {
                    coursesLayout.setVisibility(View.GONE);
                    profileLayout.setVisibility(View.GONE);
                    yearLayout.setVisibility(View.GONE);
                    majorLayout.setVisibility(View.VISIBLE);
                }
                if (filterChosen.equals("Year")) {
                    coursesLayout.setVisibility(View.GONE);
                    profileLayout.setVisibility(View.GONE);
                    yearLayout.setVisibility(View.VISIBLE);
                    majorLayout.setVisibility(View.GONE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });


    }

}