package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * @author ofek gani
 * @version 0.1
 * @since 16.11
 */
public class Credits extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_credits);

        button = findViewById (R.id.btnCredits_Back);
    }

    /**
     *
     * @param view get onClick
     */
    public void back(View view) {
        finish ();
    }
}
