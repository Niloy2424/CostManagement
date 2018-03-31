package com.example.administrator.costmanagement.Activity;

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.costmanagement.R;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    public void listReportButton(View view) {
        Intent intent=new Intent(ReportActivity.this,ListReportActivity.class);
        startActivity(intent);

    }

    public void pieChartReportButton(View view) {

Intent intent=new Intent(ReportActivity.this,PieChartReportActivity.class);
        startActivity(intent);
    }

    public void searchReportButton(View view) {
        Intent intent=new Intent(ReportActivity.this,SearchReportActivity.class);
        startActivity(intent);
    }
}


