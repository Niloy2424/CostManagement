package com.example.administrator.costmanagement.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.costmanagement.Model.Input_Cost;

import com.example.administrator.costmanagement.R;

import java.util.ArrayList;

/**
 * Created by Niloy Chowdhury on 2017-09-13.
 */

public class ReportAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Input_Cost> costs;
    private CostInputManager manager;
    public ReportAdapter(@NonNull Context context, ArrayList<Input_Cost>costs) {
        super(context, R.layout.items_row_layout,costs);
        this.context=context;
        this.costs=costs;
        manager=new CostInputManager(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.items_row_layout,parent,false);
       TextView entertainmentTV= (TextView) view.findViewById(R.id.entertainmentTV);
       TextView foodTV= (TextView) view.findViewById(R.id.foodTV);
       TextView transportTV= (TextView) view.findViewById(R.id.transportTV);
       TextView mobileTV= (TextView) view.findViewById(R.id.mobileTV);
       TextView dateTimeTV= (TextView) view.findViewById(R.id.datetimeTV);
       TextView totalTV= (TextView) view.findViewById(R.id.totalTV);
        entertainmentTV.setText(String.valueOf(costs.get(position).getEntertainmentAmount()));
        transportTV.setText(String.valueOf(costs.get(position).getTransportAmount()));
        foodTV.setText(String.valueOf(costs.get(position).getFoodAmount()));
        mobileTV.setText(String.valueOf(costs.get(position).getMobileAmount()));
        dateTimeTV.setText(costs.get(position).getDatetime());
        double total=(costs.get(position).getEntertainmentAmount())+(costs.get(position).getFoodAmount())+(costs.get(position).getMobileAmount())+(costs.get(position).getTransportAmount());
       totalTV.setText(String.valueOf(total));
        return view;
    }
}
