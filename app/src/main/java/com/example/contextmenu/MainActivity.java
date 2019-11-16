package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author ofek gani
 * @version 0.1
 * @since 16.11
 */
public class MainActivity extends AppCompatActivity {

    Button button;
    EditText ed_firstElement, ed_Difference;
    RadioButton rd_Geometric,rd_Invoice;
    RadioGroup radioGroup;

    int type;
    double difference,firstElement;
    String st_difference, st_firstElement;

    Intent next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_next);

        ed_firstElement = findViewById(R.id.ed_firstElement);
        ed_Difference = findViewById(R.id.ed_difference);

        rd_Invoice = findViewById(R.id.rd_Invoice);
        rd_Geometric = findViewById(R.id.rd_Geometric);

        radioGroup = findViewById(R.id.radioGroup);
    }

    /**
     *
     * @param view get onClick
     */
    public void next(View view) {
        if(radioGroup.getCheckedRadioButtonId () == -1) {
            rd_Invoice.setError("choose something");
            rd_Geometric.setError("choose something");
        }
        else
        {
            if (ed_firstElement.length () == 0) {
                ed_firstElement.setError("please input number");
            }
            else
            {
                if (ed_Difference.length () == 0) {
                    ed_Difference.setError("please input number");
                }
                else
                {
                    st_difference = ed_Difference.getText ().toString ();
                    st_firstElement = ed_firstElement.getText ().toString ();

                    difference = Double.parseDouble (st_difference);
                    firstElement = Double.parseDouble (st_firstElement);

                    type = radioGroup.getCheckedRadioButtonId ();
                    intent ();
                }
            }
        }
    }

    /**
     * save Variables to next activity
     */
    private void intent(){
        next = new Intent (this, SeriesCalculator.class);
        next.putExtra ("type", type);
        next.putExtra ("firstElement", firstElement);
        next.putExtra ("difference", difference);
        startActivity (next);
    }
}
