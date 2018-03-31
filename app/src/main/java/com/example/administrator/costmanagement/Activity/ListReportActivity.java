package com.example.administrator.costmanagement.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.costmanagement.Fragment.AllListFragment;
import com.example.administrator.costmanagement.Fragment.DailyListFragment;
import com.example.administrator.costmanagement.Fragment.MounthlyListFragment;
import com.example.administrator.costmanagement.Fragment.WeeklyListFragment;
import com.example.administrator.costmanagement.R;

public class ListReportActivity extends AppCompatActivity implements View.OnClickListener {
    private Button dailyListButton;
    private Button weeklyListButton;
    private Button mounthlyListButton;
    private Button allListButton;
    public android.app.Fragment currentFragment;
    public FragmentManager manager;
    public FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_report);
        dailyListButton= (Button) findViewById(R.id.DailyListButton);
        weeklyListButton= (Button) findViewById(R.id.WeeklyListButton);
        mounthlyListButton= (Button)findViewById(R.id.mounthlyListButton);
        allListButton= (Button)findViewById(R.id.allListButton);
        dailyListButton.setOnClickListener(this);
        weeklyListButton.setOnClickListener(this);
        mounthlyListButton.setOnClickListener(this);
        allListButton.setOnClickListener(this);

        currentFragment=new DailyListFragment();
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.List_Fragment,currentFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id)
        {
            case R.id.DailyListButton:

                currentFragment=new DailyListFragment();
                break;
            case R.id.WeeklyListButton:
                currentFragment=new WeeklyListFragment();
                break;
            case R.id.mounthlyListButton:
                currentFragment=new MounthlyListFragment();
                break;
            case R.id.allListButton:
                currentFragment=new AllListFragment();
                break;
        }
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.List_Fragment,currentFragment);
        transaction.commit();
    }

}

