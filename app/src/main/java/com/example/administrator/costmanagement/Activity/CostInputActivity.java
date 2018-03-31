package com.example.administrator.costmanagement.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.costmanagement.Controller.CategaryManager;
import com.example.administrator.costmanagement.Controller.CostInputManager;
import com.example.administrator.costmanagement.Model.Extra_Cost;
import com.example.administrator.costmanagement.Model.Input_Cost;
import com.example.administrator.costmanagement.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CostInputActivity extends AppCompatActivity {
private EditText datetimeEt;
    private EditText transportAmountET;
    private EditText mobileAmountET;
    private EditText foodAmountET;
    private TextView totAmountTV;
    private EditText entertainmentAmountET;
    public CategaryManager categaryManager;
    private  int categoryID;
    private double transportCostAmount,mobileCostAmount,foodCostAmount,entertainmentCostAmount;
    private Input_Cost input_cost;
    private CostInputManager costInputManager;
    private String dateTime;
    public ArrayList<Extra_Cost>categories;
    Calendar mCurrentCalender;
    int day,mounth,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_input);
        datetimeEt= (EditText) findViewById(R.id.datetimeET);
        transportAmountET= (EditText) findViewById(R.id.transportET);
        totAmountTV= (TextView) findViewById(R.id.totAmountTV);
        mobileAmountET= (EditText) findViewById(R.id.mobileEditText);
        foodAmountET= (EditText) findViewById(R.id.foodET);
        entertainmentAmountET= (EditText) findViewById(R.id.entertainmentET);
        mCurrentCalender= Calendar.getInstance();
        costInputManager=new CostInputManager(this);
        categaryManager=new CategaryManager(this);
        mounth=mCurrentCalender.get(Calendar.MONTH);
        day=mCurrentCalender.get(Calendar.DAY_OF_MONTH);
        year=mCurrentCalender.get(Calendar.YEAR);
        mounth=mounth+1;
        // fromDateET.setText(day+"/"+mounth+"/"+year);
        //toDateET.setText(day+"/"+mounth+"/"+year);

        datetimeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(datetimeEt);


            }
        });
    }
    private void showDatePickerDialog(final EditText dateET)
    {
        final DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int mounthOfYear, int dayOfMonth) {
                mounthOfYear=mounthOfYear+1;
                dateET.setText(dayOfMonth+"."+mounthOfYear+"."+year);

            }
        },year,mounth,day);
        dialog.show();
    }

    public void saveButton(View view) {
      if ((transportAmountET.getText().toString().isEmpty())&&(foodAmountET.getText().toString().isEmpty())&&(entertainmentAmountET.getText().toString().isEmpty())&&(mobileAmountET.getText().toString().isEmpty())&&(datetimeEt.getText().toString().isEmpty()))
        {
            Toast.makeText(this, "Please insert all information correctly", Toast.LENGTH_SHORT).show();
        }
        else {
          transportCostAmount = Double.valueOf(transportAmountET.getText().toString());
          mobileCostAmount = Double.valueOf(mobileAmountET.getText().toString());
          foodCostAmount = Double.valueOf(foodAmountET.getText().toString());
          entertainmentCostAmount = Double.valueOf(entertainmentAmountET.getText().toString());
          SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
          dateTime = datetimeEt.getText().toString();
String total=String.valueOf((transportCostAmount+mobileCostAmount+foodCostAmount+entertainmentCostAmount));
          long dateMillis = 0;
          // String dateT=fromDate+",00:00";
          try {
              dateMillis = formatter.parse(dateTime).getTime();

          } catch (ParseException e) {
              e.printStackTrace();

          }

          input_cost = new Input_Cost(transportCostAmount, foodCostAmount, mobileCostAmount, entertainmentCostAmount, String.valueOf(dateMillis));
          CostInputManager manager=new CostInputManager(this);
          boolean inserted=manager.addInputCost(input_cost);
          if (inserted) {
              Toast.makeText(this, "Cost Details Added Successfully", Toast.LENGTH_SHORT).show();
              totAmountTV.setText((total));
          } else {
              Toast.makeText(this, "Error in add details", Toast.LENGTH_SHORT).show();
          }

      }

    }

    public void resetButton(View view) {

        transportAmountET.setText("");
        foodAmountET.setText("");
        mobileAmountET.setText("");
        entertainmentAmountET.setText("");
        datetimeEt.setText("");
    }

    public void addExtraFieldButton(View view) {
        Intent intent =new Intent(CostInputActivity.this,ExtraCostPurposeActivity.class);
        startActivity(intent);
    }
}
