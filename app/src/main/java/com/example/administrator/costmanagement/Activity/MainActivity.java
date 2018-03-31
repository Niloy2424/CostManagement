package com.example.administrator.costmanagement.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.costmanagement.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void costInputButton(View view) {

        Intent intent =new Intent(this,CostInputActivity.class);
        startActivity(intent);
    }

    public void reportButton(View view) {
        Intent intent =new Intent(this,ReportActivity.class);
        startActivity(intent);
    }
}
