package com.example.administrator.costmanagement.Activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.costmanagement.Controller.CostInputManager;
import com.example.administrator.costmanagement.Controller.ReportAdapter;
import com.example.administrator.costmanagement.Model.Input_Cost;
import com.example.administrator.costmanagement.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchReportActivity extends AppCompatActivity {
    private EditText fromDateET;
    private EditText toDateET;
    private Button searchButton;
    private ListView allreportLV;
    Calendar mCurrentCalender;
    int day,mounth,year;
    private TextView cTv;
    private TextView itemTv;
    private TextView quantityTv;
    private TextView priceTv;
    private TextView dateTimeTv;
    long fromMilies;
    long toMilies;
    private ArrayList<Input_Cost> salesAllReports;
    String fromDate,toDate;
    CostInputManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_report);


        fromDateET= (EditText) findViewById(R.id.fromDateET);
        toDateET= (EditText) findViewById(R.id.toDateEt);
        searchButton= (Button)findViewById(R.id.searchButton);
   /*     cTv= (TextView)findViewById(R.id.cTV);
        itemTv= (TextView)findViewById(R.id.itemTV);
        quantityTv= (TextView)findViewById(R.id.quantityTV);
        priceTv= (TextView)findViewById(R.id.priceTV);
        dateTimeTv= (TextView)findViewById(R.id.DateTimeTV);*/
        allreportLV= (ListView)findViewById(R.id.allReportLV);
        mCurrentCalender=Calendar.getInstance();
        mounth=mCurrentCalender.get(Calendar.MONTH);
        day=mCurrentCalender.get(Calendar.DAY_OF_MONTH);
        year=mCurrentCalender.get(Calendar.YEAR);
        mounth=mounth+1;
        // fromDateET.setText(day+"/"+mounth+"/"+year);
        //toDateET.setText(day+"/"+mounth+"/"+year);

        fromDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fromDateET);

                //fromMilies=mCurrentCalender.getTimeInMillis();
            }
        });
        toDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(toDateET);

                // toMilies=mCurrentCalender.getTimeInMillis();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                fromDate=fromDateET.getText().toString();
                toDate=toDateET.getText().toString();
                long oneMillis=0,twoMillis=0;
                // String dateT=fromDate+",00:00";
                try {
                    oneMillis = formatter.parse(fromDate).getTime();

                } catch (ParseException e) {
                    e.printStackTrace();

                }
                try {
                    twoMillis = formatter.parse(toDate).getTime();

                } catch (ParseException e) {
                    e.printStackTrace();

                }
               //Toast.makeText(getActivity(), "From Date="+oneMillis +"To Date="+twoMillis, Toast.LENGTH_SHORT).show();
                manager=new CostInputManager(SearchReportActivity.this);
                salesAllReports=new ArrayList<Input_Cost>();
                salesAllReports=manager.getReportBetweenDate(oneMillis,twoMillis);

                // Toast.makeText(getActivity(), ""+fromMilies, Toast.LENGTH_SHORT).show();
            /*    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
                formatter.setLenient(false);
                //Toast.makeText(getActivity(), "The time="+dateT,Toast.LENGTH_SHORT).show();
                Date oldDate = null;
                try {
                    oldDate = formatter.parse(dateT);
                    Toast.makeText(getActivity(), ""+oldDate, Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();

                }*/
//                long oldMillis = oldDate.getTime();


                if (!salesAllReports.isEmpty())
                {
                   /* cTv.setVisibility(View.VISIBLE);
                    itemTv.setVisibility(View.VISIBLE);
                    dateTimeTv.setVisibility(View.VISIBLE);
                    priceTv.setVisibility(View.VISIBLE);
                    quantityTv.setVisibility(View.VISIBLE);*/
                    ReportAdapter adapter=new ReportAdapter(SearchReportActivity.this,salesAllReports);
                    allreportLV.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(SearchReportActivity.this, "There do'nt have any data in between those date !!! ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void showDatePickerDialog(final EditText dateET)
    {
        DatePickerDialog dialog=new DatePickerDialog(SearchReportActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int mounthOfYear, int dayOfMonth) {
                mounthOfYear=mounthOfYear+1;
                dateET.setText(dayOfMonth+"."+mounthOfYear+"."+year);

            }
        },year,mounth,day);
        dialog.show();
    }

    }
