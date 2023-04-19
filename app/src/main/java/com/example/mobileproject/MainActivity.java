package com.example.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPhone;
    private Button btnAdd;
    private Button btnList;
    private ListView listView;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        listView = findViewById(R.id.listView);
        firestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firestore.collection("items");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> item = new HashMap<>();
                item.put("name", etName.getText().toString());
                item.put("phone", etPhone.getText().toString());
                reference.add(item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            QuerySnapshot snapshot = task.getResult();
                            List<Item> list = new ArrayList<>();
                            for (QueryDocumentSnapshot doc : snapshot) {
                                Item item = new Item();
                                item.setName(doc.get("name").toString());
                                item.setPhone(doc.get("phone").toString());
                                list.add(item);
                            }
                            ArrayAdapter<Item> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,list);
                            listView.setAdapter(adapter);
                        }
                    }
                });
            }
        });
    }
}