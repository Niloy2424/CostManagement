package com.example.administrator.costmanagement.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.costmanagement.Model.Input_Cost;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 11/10/2017.
 */

public class CostInputManager {

    private Input_Cost inputCost;
    private SQLiteDatabase database;
    private DatabaseHelper helper;


    public CostInputManager(Context context) {
helper=new DatabaseHelper(context);
    }
    public void open()
    {
        database=helper.getWritableDatabase();
    }
    public void close()
    {
        helper.close();
    }
    public boolean addInputCost(Input_Cost inputCost)
    {
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(helper.CREATED_AT,inputCost.getDatetime());
        contentValues.put(helper.TRANSPORT_COST_AMOUNT,inputCost.getTransportAmount());
        contentValues.put(helper.MOBILE_COST_AMOUNT,inputCost.getMobileAmount());
        contentValues.put(helper.FOOD_COST_AMOUNT,inputCost.getFoodAmount());
        contentValues.put(helper.ENTERTAINMENT_COST_AMOUNT,inputCost.getEntertainmentAmount());
long insert=database.insert(helper.TABLE_NAME_COSTINPUT,null,contentValues);

        this.close();
        if (insert>0)
        {
            return true;
        }
        else
        return false;

    }
    public ArrayList<Input_Cost> getAllReport() {

        ArrayList<Input_Cost>sales=new ArrayList<>();
        this.open();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_COSTINPUT,null,null,null,null,null,null);
        if (cursor!=null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COST_INPUT_ID));
                double transportCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANSPORT_COST_AMOUNT));
                double mobileCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.MOBILE_COST_AMOUNT));
                double foodCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.FOOD_COST_AMOUNT));
                double entertainmentCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.ENTERTAINMENT_COST_AMOUNT));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                inputCost=new Input_Cost(_id,transportCostAmount,foodCostAmount,mobileCostAmount,entertainmentCostAmount,dateTime);
                sales.add(inputCost);
            }
        }



        this.close();
        return sales;

    }

    public ArrayList<Input_Cost> getReport(int days) {
        this.open();
        long milis = days *86400000;
        long oldMillis = System.currentTimeMillis()-milis;

        ArrayList<Input_Cost>costs=new ArrayList<>();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_COSTINPUT,null,DatabaseHelper.CREATED_AT + " BETWEEN " + oldMillis + " AND "
                + System.currentTimeMillis() ,null,null,null,null);

        if (cursor!=null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COST_INPUT_ID));
                double transportCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANSPORT_COST_AMOUNT));
                double mobileCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.MOBILE_COST_AMOUNT));
                double foodCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.FOOD_COST_AMOUNT));
                double entertainmentCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.ENTERTAINMENT_COST_AMOUNT));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                inputCost=new Input_Cost(_id,transportCostAmount,foodCostAmount,mobileCostAmount,entertainmentCostAmount,convertMilisecoundIntoDate(Long.valueOf(dateTime), "dd/MM/yyyy hh:mm:ss"));
                costs.add(inputCost);
            }
        }



        this.close();
        return costs;
    }

    public ArrayList<Input_Cost> getReportBetweenDate(long oneMillis, long twoMillis) {

        this.open();
        Cursor cursor=database.query(DatabaseHelper.TABLE_NAME_COSTINPUT,null,DatabaseHelper.CREATED_AT + " BETWEEN " + oneMillis + " AND "
                + twoMillis ,null,null,null,null);

        ArrayList<Input_Cost>sales=storeSpecificDataInPOS(cursor);
        this.close();
        return sales;
    }

    private ArrayList<Input_Cost> storeSpecificDataInPOS(Cursor cursor) {

        ArrayList<Input_Cost>sales=new ArrayList<>();
        if (cursor!=null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int _id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COST_INPUT_ID));
                double transportCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.TRANSPORT_COST_AMOUNT));
                double mobileCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.MOBILE_COST_AMOUNT));
                double foodCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.FOOD_COST_AMOUNT));
                double entertainmentCostAmount=cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.ENTERTAINMENT_COST_AMOUNT));
                String dateTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
                inputCost=new Input_Cost(_id,transportCostAmount,foodCostAmount,mobileCostAmount,entertainmentCostAmount,convertMilisecoundIntoDate(Long.valueOf(dateTime), "dd/MM/yyyy hh:mm:ss"));


                sales.add(inputCost);
            }
        }
        return sales;

    }
    public  String convertMilisecoundIntoDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
