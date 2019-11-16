package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author ofek gani
 * @version 0.1
 * @since 16.11
 */
public class SeriesCalculator extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnCreateContextMenuListener{

    TextView tv;
    ListView listView;
    Button btn_back, btn_credits;
    Intent gi_difference, gi_firstElement, gi_type, credits;

    double difference, firstElement;

    int type, pos;

    Double[] seriesNumbers = new Double[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_calculator);

        tv = findViewById (R.id.tv);
        listView = findViewById (R.id.listView);
        btn_back = findViewById (R.id.btn_back);
        btn_credits = findViewById (R.id.btn_credits);

        gi_difference = getIntent ();
        gi_firstElement = getIntent ();
        gi_type = getIntent ();

        difference = gi_difference.getDoubleExtra ("difference",0);
        firstElement = gi_difference.getDoubleExtra ("firstElement",1);
        type = gi_type.getIntExtra ("type",1);

        listView.setOnItemLongClickListener (this);
        listView.setOnCreateContextMenuListener (this);
        listView.setChoiceMode (ListView.CHOICE_MODE_SINGLE);

        if(type==R.id.rd_Invoice) {
            getInvoiceSeries (seriesNumbers, difference);
        }
        else
        {
            getGeometricSeries(seriesNumbers, difference);
        }

        ArrayAdapter<Double> adp = new ArrayAdapter<Double> (this,R.layout.support_simple_spinner_dropdown_item,seriesNumbers);
        listView.setAdapter (adp);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        pos = position+1;
        return false;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add("Sum");
        menu.add("Index");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().toString().equals("index")){
            tv.setText(""+pos);
        } else{
            int sum = 0;
            for(int i = 0; i<pos; i++){
                sum += seriesNumbers[i];
            }
            tv.setText(""+ sum);
        }

        return true;
    }

    /**
     *
     * @param seriesNumbers get array to fill numbers of series numbers
     * @param difference get number of Difference / Multiplier of series numbers
     * @return array of series numbers
     */
    private Double[] getInvoiceSeries(Double[] seriesNumbers, double difference){
        seriesNumbers[0] = firstElement;
        for(int i= 1; i < seriesNumbers.length; i++){
            seriesNumbers[i] = seriesNumbers[i-1]+difference;
        }
        return seriesNumbers;
    }

    /**
     *
     * @param seriesNumbers get array to fill numbers of series numbers
     * @param difference get number of Difference / Multiplier of series numbers
     * @return array of series numbers
     */
    private Double[] getGeometricSeries(Double[] seriesNumbers, double difference){
        seriesNumbers[0] = firstElement;
        for(int i = 2; i<seriesNumbers.length+1; i ++){
            seriesNumbers[i-1] = seriesNumbers[0]*Math.pow(difference, i-1);
        }
        return seriesNumbers;
    }
    /**
     *
     * @param view get onClick
     */
    public void credits(View view) {
        credits = new Intent (this, Credits.class);
        startActivity (credits);
    }
    /**
     *
     * @param view get onClick
     */
    public void back(View view) {
        finish ();
    }
}
