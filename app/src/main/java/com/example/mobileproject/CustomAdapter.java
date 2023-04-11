package com.example.mobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<Person> mPersons;
    private final LayoutInflater mInflater;

    public CustomAdapter(Context context, ArrayList<Person> persons) {
        mContext = context;
        mPersons = persons;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mPersons.size();
    }

    @Override
    public Object getItem(int position) {
        return mPersons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.activity_main2,null);
        TextView text = convertView.findViewById(R.id.NameText);
        text.setText(mPersons.get(position).getFullName() + ": " + String.format("%.2f",mPersons.get(position).getNetSalary()));
        return convertView;
    }

}
