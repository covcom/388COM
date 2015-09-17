package com.example.jianhuayang.mylists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jianhuayang on 19/02/15.
 */
public class PresidentsAdapter extends ArrayAdapter<Presidents> {

    private ArrayList<Presidents> items;
    private Context adapterContext;

    public PresidentsAdapter(Context context, ArrayList<Presidents> items) {
        super(context, R.layout.list_item, items);
        adapterContext = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        try {
            Presidents president = items.get(position);

            if (v == null) {
                LayoutInflater vi = (LayoutInflater) adapterContext.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item, null);
            }

            TextView PresidentsFirstName = (TextView) v.findViewById(R.id.firstName);
            TextView PresidentsFullName = (TextView) v.findViewById(R.id.fullName);

            PresidentsFirstName.setText(president.getFirstName());
            PresidentsFullName.setText(president.getFullName());
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return v;
    }
}