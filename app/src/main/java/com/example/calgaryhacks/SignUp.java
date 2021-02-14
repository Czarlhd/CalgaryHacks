package com.example.calgaryhacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.lang.Character;

public class SignUp extends AppCompatActivity {

    TextInputLayout username_mcgill, password_mcgill, username_fb, password_fb;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // retrieve all the TextInputLayout and Buttons
        username_mcgill = findViewById(R.id.email_mcgill);
        password_mcgill = findViewById(R.id.password_mcgill);
        username_fb = findViewById(R.id.password_facebook);
        password_fb = findViewById(R.id.password_facebook);
        regBtn = findViewById(R.id.signup);
        regToLoginBtn = findViewById(R.id.goToLogin);

        // Listener of the signup button
//        regBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rootNode = FirebaseDatabase.getInstance("https://codewithtea-ffcb4-default-rtdb.firebaseio.com/");
//                reference = rootNode.getReference("users");
//
//                // Get all the values
//                String name = regName.getEditText().getText().toString();
//                String username = regUsername.getEditText().getText().toString();
//                String email = regEmail.getEditText().getText().toString();
//                String phoneNo = regPhoneNo.getEditText().getText().toString();
//                String password = regPassword.getEditText().getText().toString();
//
//                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
//                reference.child(phoneNo).setValue(helperClass);
//
//            }
//        });

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SignUp.this, Login.class);
                startActivity(intent2);
            }
        });
    }

    private Boolean validateUsername() {
        String val1 = username_mcgill.getEditText().getText().toString();
        String val2 = username_fb.getEditText().getText().toString();
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (val1.isEmpty()) {
            username_mcgill.setError("Field cannot be empty");
            return false;
        }
        if (val2.isEmpty()) {
            username_fb.setError("Field cannot be empty");
            return false;
        }
//        if (!val1.matches(emailPattern)) {
//            username_mcgill.setError("Invalid email address");
//            return false;
//        }
//        if (!val2.matches(emailPattern)) {
//            username_fb.setError("Invalid email address");
//            return false;
//        }
        else {
            username_mcgill.setError(null);
            username_fb.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val1 = password_mcgill.getEditText().getText().toString();
        String val2 = password_fb.getEditText().getText().toString();
//        String.chars().anyMatch(Character::isUpperCase);
        if (val1.isEmpty()) {
            password_mcgill.setError("Field cannot be empty");
            return false;
        }
        else if (val1.length()<=7) { //WORK ON ACTUAL VALIDATION
            password_mcgill.setError("Password is too weak");
            return false;
        }
        if (val2.isEmpty()) {
            password_fb.setError("Field cannot be empty");
            return false;
        }
        else if (val2.length()<=7) {
            password_fb.setError("Password is too weak");
            return false;
        }
        else {
            password_mcgill.setError(null);
            password_fb.setError(null);
            return true;
        }
    }

    public void registerUser(View v) {

        if (!validateUsername() | !validatePassword()) {
            return;
        }
        rootNode = FirebaseDatabase.getInstance("https://calgaryhacks-64586-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference("users");

        // Get all the values

        String mcgillU = username_mcgill.getEditText().getText().toString();
        String mcgillP = password_mcgill.getEditText().getText().toString();
        String fbU = username_fb.getEditText().getText().toString();
        String fbP = password_fb.getEditText().getText().toString();

        String name = "";
        boolean found = false;
        for (int i = 0; mcgillU.indexOf("@")!=i; i++) {
            if (i==mcgillU.indexOf(".")) {
                name += " ";
                found = true;
            }
            else if (i==0) {
                name += Character.toUpperCase(mcgillU.charAt(i));
            }
            else {
                name += mcgillU.charAt(i);
            }
        }

        System.out.println("THE USERNAME IS" + name);

        UserHelperClass helperClass = new UserHelperClass(mcgillU, mcgillP, fbU, fbP);
        reference.child(name).setValue(helperClass);

        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }
}