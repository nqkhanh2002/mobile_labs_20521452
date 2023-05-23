package com.example.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register extends AppCompatActivity {

    private TextView navLogin;
    private Button signUp;
    private TextInputLayout tpFullName, tpPhone, tpUsername, tpPassword;

    private static final String TAG = "SignUp";
    private static final String KEY_NAME = "Fullname";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db.setFirestoreSettings(settings);

        navLogin = findViewById(R.id.loginNow);
        navLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        tpFullName = findViewById(R.id.name);
        tpPhone = findViewById(R.id.phone);
        tpUsername = findViewById(R.id.email);
        tpPassword = findViewById(R.id.password);

        signUp = findViewById(R.id.btn_register);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });
    }

    protected boolean checkValidation(String username, String password) {
        if (username.isEmpty()) {
            tpUsername.setError("Required!");
            return false;
        } else if (username.length() < 6) {
            tpUsername.setError("Username must be at least 6 characters");
            return false;
        }
        tpUsername.setError(null);

        if (password.isEmpty()) {
            tpPassword.setError("Required");
            return false;
        } else if (password.length() < 6) {
            tpPassword.setError("Password must be at least 6 characters");
            return false;
        }
        tpPassword.setError(null);
        return true;
    }

    protected void insertUser() {
        String fullName = tpFullName.getEditText().getText().toString();
        String phone = tpPhone.getEditText().getText().toString();
        String userName = tpUsername.getEditText().getText().toString();
        String password = tpPassword.getEditText().getText().toString();
        String hashedPassword = Hash.sha256(password);

        if (!checkValidation(userName, password)) {
            return;
        }

        Map<String, Object> user = new HashMap<>();
        user.put(KEY_NAME, fullName);
        user.put(KEY_PHONE, phone);
        user.put(KEY_USERNAME, userName);
        user.put(KEY_PASSWORD, hashedPassword);

        db.collection("User").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}
