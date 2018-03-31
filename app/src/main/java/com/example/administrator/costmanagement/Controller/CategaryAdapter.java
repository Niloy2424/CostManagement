package com.example.administrator.costmanagement.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.costmanagement.Model.Extra_Cost;
import com.example.administrator.costmanagement.R;


import java.util.ArrayList;

/**
 * Created by Niloy Chowdhury on 2017-08-31.
 */

public class CategaryAdapter extends ArrayAdapter<Extra_Cost> {
    private Context context;
    private ArrayList<Extra_Cost>categaries;
    public CategaryAdapter(@NonNull Context context,  ArrayList<Extra_Cost>categories) {
        super(context, R.layout.categary_row_layout,categories);
        this.context=context;
        this.categaries=categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.categary_row_layout,parent,false);
        TextView nameTV= (TextView) view.findViewById(R.id.nameTV);
        TextView statusTV= (TextView) view.findViewById(R.id.amountTV);
        nameTV.setText(categaries.get(position).getExtraCostPurposeName());
        statusTV.setText(String.valueOf(categaries.get(position).getExtraCostPurposeAmount()));
        return view;
    }
}
