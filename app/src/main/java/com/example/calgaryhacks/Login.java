package com.example.calgaryhacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    // variables for the animation
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;
    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        // Hooks for animation (transition)
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.logo_desc);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, SignUp.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[3] = new Pair<View, String>(username, "username");
                pairs[4] = new Pair<View, String>(password, "password");
                pairs[5] = new Pair<View, String>(login, "log_to_signup");
                pairs[6] = new Pair<View, String>(signup, "signup");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent2, options.toBundle());
                }
            }
        });

//        logToSign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, UserProfile.class);
//                startActivity(intent);
//            }
//        });
    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser (View view) {
        System.out.println("START OF LOGINUSER");
        // Validate that they're not empty
        if (!validatePassword() || !validateUsername()) {
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://calgaryhacks-64586-default-rtdb.firebaseio.com/").getReference("users");
        Query checkUser = reference.orderByChild("username_mcgill").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
//                    username.setError(null);
//                    username.setErrorEnabled(false);

                    String name = "";
                    boolean found = false;
                    for (int i = 0; userEnteredUsername.indexOf("@")!=i; i++) {
                        if (i==userEnteredUsername.indexOf(".")) {
                            name += " ";
                            found = true;
                        }
                        else if (i==0 || found) {
                            name += Character.toUpperCase(userEnteredUsername.charAt(i));
                            found = false;
                        }
                        else {
                            name += userEnteredUsername.charAt(i);
                        }
                    }

                    String passwordFromDB = dataSnapshot.child(name).child("password_mcgill").getValue(String.class);
                    System.out.println("PASSWORD FROM DATABASE:" + passwordFromDB);

                    if (passwordFromDB.equals(userEnteredPassword)) {

//                        password.setError(null);
//                        password.setErrorEnabled(false);


                        String usernameFromDB = username.getEditText().getText().toString();
                        System.out.println("USERNAME FROM DB" + usernameFromDB);
                        Intent intent = new Intent (getApplicationContext(), UserProfile.class);
                        intent.putExtra("fullName", name);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                    }
                    else {
                        System.out.print("WRONG PASSWORD");
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else {
                    System.out.println("WRONG EMAIL");
                    username.setError("Email does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}