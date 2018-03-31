package com.example.administrator.costmanagement.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.costmanagement.Controller.CostInputManager;
import com.example.administrator.costmanagement.Controller.ReportAdapter;
import com.example.administrator.costmanagement.Model.Input_Cost;
import com.example.administrator.costmanagement.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MounthlyListFragment extends android.app.Fragment {


    public MounthlyListFragment() {
        // Required empty public constructor
    }

    private ListView mounthlyReportLV;
    private TextView transportTV;
    private TextView entertainmentTV;
    private TextView foodTV;
    private TextView mobileTV;
    private CostInputManager manager;
    private ArrayList<Input_Cost> salesAllReports;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mounthly_list, container, false);
        mobileTV= (TextView) view.findViewById(R.id.mobileTV);
        transportTV= (TextView) view.findViewById(R.id.transportTV);
        entertainmentTV= (TextView) view.findViewById(R.id.entertainmentTV);
        foodTV= (TextView) view.findViewById(R.id.foodTV);
        mounthlyReportLV= (ListView) view.findViewById(R.id.mounthlyReportLV);

        // Inflate the layout for this fragment
        manager=new CostInputManager(getActivity());
        salesAllReports=manager.getReport(20);

        if (salesAllReports.isEmpty())
        {
            Toast.makeText(getActivity(), "There is no cost record", Toast.LENGTH_SHORT).show();
        }
        else
        {
            transportTV.setVisibility(View.VISIBLE);
            mobileTV.setVisibility(View.VISIBLE);
            entertainmentTV.setVisibility(View.VISIBLE);
            foodTV.setVisibility(View.VISIBLE);
            ReportAdapter adapter=new ReportAdapter(getActivity(),salesAllReports);
            mounthlyReportLV.setAdapter(adapter);
        }
        return view;
    }

}
