package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    //tag
    private static final String TAG = Result.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get intent that started activity, and get extras
        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String height = intent.getStringExtra("height");
        String idealWeight = intent.getStringExtra("idealWeight");
        Log.d(TAG, gender + " " + height + " " + idealWeight);

        //initialize layout elements
        TextView youAreTextView = (TextView) findViewById(R.id.youAreTextView);
        TextView yourHeightTextView = (TextView) findViewById(R.id.yourHeightTextView);
        TextView yourIdealTextView = (TextView) findViewById(R.id.yourIdealTextView);

        youAreTextView.setText("You are " + gender);
        yourHeightTextView.setText("Your height is " + height);
        yourIdealTextView.setText("The standard weight is " + idealWeight);

    }
}