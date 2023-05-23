package com.example.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    private Button login;
    private TextView navSignup;
    private TextInputLayout tpUsername, tpPassword;

    private static final String TAG = "Login";

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private FirebaseFirestore db;
    private CollectionReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = FirebaseFirestore.getInstance();
        usersRef = db.collection("User");

        navSignup = findViewById(R.id.registerNow);
        navSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        tpUsername = findViewById(R.id.email);
        tpPassword = findViewById(R.id.password);

        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    protected void checkLogin() {
        String username = tpUsername.getEditText().getText().toString();
        String password = tpPassword.getEditText().getText().toString();

        String hashedPassword = Hash.sha256(password);

        Query query = usersRef.whereEqualTo("email", username);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Check if the password matches
                        if (document.getString("password").equals(hashedPassword)) {
                            // Password is correct
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                            String fullName = document.getString("Fullname");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("Fullname", fullName);
                            startActivity(intent);
                        } else {
                            // Password is incorrect
                            Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
