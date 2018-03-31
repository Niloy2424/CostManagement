package com.example.administrator.costmanagement.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.costmanagement.Controller.CategaryAdapter;
import com.example.administrator.costmanagement.Controller.CategaryManager;
import com.example.administrator.costmanagement.Model.Extra_Cost;
import com.example.administrator.costmanagement.R;

import java.util.ArrayList;

import static com.example.administrator.costmanagement.R.id.ExtraPurposeNameET;
import static com.example.administrator.costmanagement.R.id.transportET;

public class ExtraCostPurposeActivity extends AppCompatActivity {
    public ListView categaryLV;
    public EditText ExtraCostPurposeET;
    public EditText ExtraCostPurposeAmountET;
    public Button addItemButton;
    String ExtraCostPurpose, ExtraCostPurposeAmount;
    public CategaryManager manager;
    public Extra_Cost aExtraCost;
    public ArrayList<Extra_Cost> categeries;
    public CategaryAdapter adapter;
    public int recivedCategoryID;
    public ArrayList<String> statuses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_purpose);
        ExtraCostPurposeET = (EditText) findViewById(R.id.ExtraPurposeNameET);
        ExtraCostPurposeAmountET= (EditText) findViewById(R.id.ExtraPurposeAmountET);
        categaryLV = (ListView) findViewById(R.id.categoryLV);
        addItemButton= (Button) findViewById(R.id.addItemButton);
        manager = new CategaryManager(this);
        registerForContextMenu(categaryLV);
        refreshListView();
        recivedCategoryID=getIntent().getIntExtra("cid",0);
        if (recivedCategoryID!=0)
        {
            addItemButton.setText("UPDATE");
            aExtraCost =manager.getSelectedCategory(recivedCategoryID);
            ExtraCostPurposeET.setText(aExtraCost.getExtraCostPurposeName());
            ExtraCostPurposeAmountET.setText(String.valueOf(aExtraCost.getExtraCostPurposeAmount()));

        }
    }

    public void addItemButton(View view) {

        ExtraCostPurpose = ExtraCostPurposeET.getText().toString();

        ExtraCostPurposeAmount=ExtraCostPurposeAmountET.getText().toString();
        if (!ExtraCostPurpose.isEmpty())
        {
            aExtraCost = new Extra_Cost(ExtraCostPurpose, Double.valueOf(ExtraCostPurposeAmount));
            if (recivedCategoryID==0)
            {



                boolean insert = manager.addCatagery(aExtraCost);
                if (insert) {
                    Toast.makeText(this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
                refreshListView();
            }
            else
            {
                boolean updated=manager.updateStudent(aExtraCost,recivedCategoryID);
                if (updated)
                {
                    Toast.makeText(this, "Category is updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Sorry , Category is not updated", Toast.LENGTH_SHORT).show();
                }
                refreshListView();
            }



        }
        else

        {
            Toast.makeText(this, "Please insert Category Name", Toast.LENGTH_SHORT).show();
        }

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");
        menu.add(0,v.getId(),1,"Edit");
        menu.add(0,v.getId(),2,"Delete");
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index=info.position;
        final int categoryID= categeries.get(index).getId();
        if (item.getTitle()=="Edit")
        {
            Intent intent=new Intent(ExtraCostPurposeActivity.this,ExtraCostPurposeActivity.class);
            intent.putExtra("cid",categoryID);

            startActivity(intent);
        }
        else if (item.getTitle()=="Delete")
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Delete");
            builder.setMessage("Are you sure");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //contacts.remove(index);
                    // RefreshData();
                    boolean deleted=  manager.deleteCategory(categoryID);
                    if (deleted)
                    {
                        Toast.makeText(ExtraCostPurposeActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                        refreshListView();
                    }
                    else
                        Toast.makeText(ExtraCostPurposeActivity.this, "Error in delete", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        return super.onContextItemSelected(item);
    }


    public void resetButton(View view) {
    }

    public void refreshListView() {
        categeries = manager.getAllCategary();
        adapter = new CategaryAdapter(this, categeries);
        categaryLV.setAdapter(adapter);
    }

    public void purposeResetButton(View view) {
        ExtraCostPurposeET.setText("");
        ExtraCostPurposeAmountET.setText("");

    }
}
