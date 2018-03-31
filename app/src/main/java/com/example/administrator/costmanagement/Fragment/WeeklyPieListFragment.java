package com.example.administrator.costmanagement.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.costmanagement.Controller.CategaryManager;
import com.example.administrator.costmanagement.Controller.CostInputManager;
import com.example.administrator.costmanagement.Model.Extra_Cost;
import com.example.administrator.costmanagement.Model.Input_Cost;
import com.example.administrator.costmanagement.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyPieListFragment extends android.app.Fragment {
    private Float[] yData;
    private String[]xData;

    public WeeklyPieListFragment() {
        // Required empty public constructor
    }

    PieChart allReportPieChart;
    private CategaryManager categaryManager;
    private CostInputManager costInputManager;
    ArrayList<Input_Cost> costs;
    ArrayList<Input_Cost> costsInSpecificCategory;
    ArrayList<Extra_Cost> extraCostsInSpecificCategory;
    ArrayList<Float> dailyCostTotals;
    ArrayList<String>categaryNames;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_weekly_pie_list, container, false);

        categaryManager=new CategaryManager(getActivity());

       /* if (categaries.isEmpty())
        {
            Toast.makeText(getActivity(), "Please Insert Category And POS Then you can see the chart", Toast.LENGTH_SHORT).show();
        }*/

        dailyCostTotals =new ArrayList<>();
        costInputManager=new CostInputManager(getActivity());
        costsInSpecificCategory =costInputManager.getReport(7);
        Float totPriceTransport=0f;
        Float totPriceMobile=0f;
        Float totPriceFood=0f;
        Float totPriceEntertainment=0f;
        for (Input_Cost cost : costsInSpecificCategory)
        {
            totPriceTransport =   Float.valueOf(String.valueOf(cost.getTransportAmount())) + totPriceTransport;
            totPriceMobile =   Float.valueOf(String.valueOf(cost.getMobileAmount())) + totPriceMobile;
            totPriceFood =   Float.valueOf(String.valueOf(cost.getFoodAmount())) + totPriceFood;
            totPriceEntertainment =   Float.valueOf(String.valueOf(cost.getEntertainmentAmount())) + totPriceEntertainment;
        }

        extraCostsInSpecificCategory=categaryManager.getAllExtaCost(1);
        Float totExtra=0f;
        for (Extra_Cost eCost : extraCostsInSpecificCategory)
        {
            totExtra =   Float.valueOf(String.valueOf(eCost.getExtraCostPurposeAmount())) + totExtra;
        }
        dailyCostTotals.add(totPriceFood);
        dailyCostTotals.add(totPriceMobile);
        dailyCostTotals.add(totPriceTransport);
        dailyCostTotals.add(totPriceEntertainment);
        dailyCostTotals.add(totExtra);
        if (dailyCostTotals.isEmpty())
        {
            Toast.makeText(getActivity(), "There is no Cost Today", Toast.LENGTH_SHORT).show();
        }

        categaryNames=new ArrayList<>();
        categaryNames.add("Food");
        categaryNames.add("Mobile");
        categaryNames.add("Transport");
        categaryNames.add("Entertainment");
        categaryNames.add("Extras");
        xData = categaryNames.toArray(new String[categaryNames.size()]);
        yData = dailyCostTotals.toArray(new Float[dailyCostTotals.size()]);
          /*      for (String x:xData
                     ) {
                    Toast.makeText(getActivity(), ""+x, Toast.LENGTH_SHORT).show();
                }
                for (Float y:yData
                        ) {
                    Toast.makeText(getActivity(), ""+y, Toast.LENGTH_SHORT).show();
                }*/







        allReportPieChart= (PieChart) view.findViewById(R.id.dailiesCostsPieChart);
/*allReportPieChart.setHoleRadius(25f);
        allReportPieChart.setRotationEnabled(true);
        allReportPieChart.setTransparentCircleAlpha(0);
        allReportPieChart.setCenterText("Daily Pie Chart");
        allReportPieChart.setCenterTextSize(0);*/
        //allReportPieChart.setDrawEntryLabels(true);
        allReportPieChart.setUsePercentValues(true);
        allReportPieChart.getDescription().setEnabled(false);
        allReportPieChart.setExtraOffsets(5,10,5,5);
        allReportPieChart.setDragDecelerationFrictionCoef(0.95f);
        allReportPieChart.setDrawHoleEnabled(true);
        allReportPieChart.setHoleColor(Color.WHITE);
        allReportPieChart.setTransparentCircleRadius(61f);
        addDateSet();

        return view;
    }

    private void addDateSet() {
        ArrayList<PieEntry>yEntries=new ArrayList<>();
        ArrayList<String>xEntries=new ArrayList<>();
        for (int i=0;i<xData.length;i++)
        {xEntries.add(xData[i]);}
        for (int i=0;i<yData.length;i++)
        {
            String name=xEntries.get(i);
            yEntries.add(new PieEntry(yData[i],name));
        }



        PieDataSet pieDataSet=new PieDataSet(yEntries,"Personal cost");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setSelectionShift(5f);
      /*  ArrayList<Integer>colors=new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);*/
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);



        PieData pieData=new PieData(pieDataSet);
        allReportPieChart.setData(pieData);
        pieData.setValueTextSize(10f);
        pieData.setValueTextSize(Color.YELLOW);
        allReportPieChart.invalidate();



    }

}
