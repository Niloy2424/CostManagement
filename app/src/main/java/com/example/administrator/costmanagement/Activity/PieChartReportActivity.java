package com.example.administrator.costmanagement.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.costmanagement.Fragment.DailyListFragment;
import com.example.administrator.costmanagement.Fragment.DailyPieListFragment;
import com.example.administrator.costmanagement.Fragment.MounthlyListFragment;
import com.example.administrator.costmanagement.Fragment.MounthlyPieListFragment;
import com.example.administrator.costmanagement.Fragment.WeeklyListFragment;
import com.example.administrator.costmanagement.Fragment.WeeklyPieListFragment;
import com.example.administrator.costmanagement.R;

public class PieChartReportActivity extends AppCompatActivity implements View.OnClickListener {
    private Button dailyPieChartButton;
    private Button weeklyPieChartButton;
    private Button mounthlyPieChartButton;
    public android.app.Fragment currentFragment;
    public FragmentManager manager;
    public FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_report);
        dailyPieChartButton= (Button) findViewById(R.id.dailyPieChartButton);
        weeklyPieChartButton= (Button) findViewById(R.id.weeklyPieChartButton);
        mounthlyPieChartButton= (Button)findViewById(R.id.mounthlyPieChartButton);
        dailyPieChartButton.setOnClickListener(this);
        weeklyPieChartButton.setOnClickListener(this);
        mounthlyPieChartButton.setOnClickListener(this);


        currentFragment=new DailyPieListFragment();
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.pieChartFragment,currentFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id)
        {
            case R.id.dailyPieChartButton:

                currentFragment=new DailyPieListFragment();
                break;
            case R.id.weeklyPieChartButton:
                currentFragment=new WeeklyPieListFragment();
                break;
            case R.id.mounthlyPieChartButton:
                currentFragment=new MounthlyPieListFragment();
                break;
        }
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.pieChartFragment,currentFragment);
        transaction.commit();
    }


}
