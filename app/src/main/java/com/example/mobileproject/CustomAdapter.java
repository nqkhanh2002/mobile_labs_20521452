package com.example.mobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Person> per;
    LayoutInflater inflater;

    public CustomAdapter(Context ctx, ArrayList<Person> p) {
        this.context = ctx;
        this.per = p;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return per.size();
    }

    @Override
    public Object getItem(int i) {
        return per.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_main2,null);
        TextView text = view.findViewById(R.id.nameT);
        text.setText(per.get(i).getFullName() + ": " + String.format("%.2f",per.get(i).getNSalary()));
        return view;
    }
}
