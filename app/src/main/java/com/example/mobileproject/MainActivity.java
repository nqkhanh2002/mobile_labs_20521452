package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Person> ListPer = new ArrayList<Person>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputLayout text = findViewById(R.id.YourName);
        TextInputLayout number = findViewById(R.id.GrossSalary);
        listView = findViewById(R.id.customListView);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = (text.getEditText().getText()).toString();
                double grossSalary = Double.parseDouble(number.getEditText().getText().toString());
                Person p = new Person(name, grossSalary);
                ListPer.add(p);
//                Một Adapter có trách nhiệm lấy dữ liệu từ bộ dữ liệu và tạo ra các đối tượng View dựa trên dữ liệu đó.
//                Các đối tượng View được tạo ra sau đó được sử dụng để gắn lên bất kỳ Adapter View mà ràng buộc với Adapter
                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),ListPer);
                listView.setAdapter(customAdapter);
            }
        });
    }
}