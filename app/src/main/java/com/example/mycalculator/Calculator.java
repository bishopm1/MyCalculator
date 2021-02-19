package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Calculator extends AppCompatActivity {

    //tag
    private static final String TAG = Calculator.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //initialize layout elements
        Button calculateButton = (Button) findViewById(R.id.calculateButton);
        RadioGroup genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        EditText feetEditText = (EditText) findViewById(R.id.feetEditText);
        EditText inchesEditText = (EditText) findViewById(R.id.inchesEditText);
        TextView errorTextView = (TextView) findViewById(R.id.errorTextView);

        //calculate button on click listener - performs calculation and opens result actvity
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double idealWeight = 0;
                //converts height to inches
                int feet = 0;
                int inches = 0;
                //parses input for feet and inches, unless blank than set error and halt
                try {
                    feet = Integer.parseInt(feetEditText.getText().toString());
                    inches = Integer.parseInt(inchesEditText.getText().toString());
                }catch(NumberFormatException e){
                    errorTextView.setText("Feet and inches input required");
                    errorTextView.setVisibility(View.VISIBLE);
                    return;
                }
                //checks input for feet and inches is within range, set error and halt if not
                if(feet < 4 || feet > 9){
                    //lowest height set at 4 feet, formula is not designed to work with lower than this
                    errorTextView.setText("Feet input must be within 4-9");
                    errorTextView.setVisibility(View.VISIBLE);
                    return;
                } else if(inches < 0 || inches > 11){
                    errorTextView.setText("Inches input must be within 0-11");
                    errorTextView.setVisibility(View.VISIBLE);
                    return;
                }
                int inchHeight = (feet*12) + inches;

                //decimal format for ideal weight string
                DecimalFormat df = new DecimalFormat("0.00");

                //gets selected gender and performs calculation based on selection
                int gender = genderRadioGroup.getCheckedRadioButtonId();
                String mf = "";
                if(gender == -1){
                    errorTextView.setText("Must select male or female");
                    errorTextView.setVisibility(View.VISIBLE);
                    return;
                } else if(gender == R.id.maleRadioButton){
                    //calculate ideal weight
                    idealWeight = 50.0+(2.3*(inchHeight-60.0));
                    mf = "Male";
                } else if (gender == R.id.femaleRadioButton){
                    //calculate ideal weight
                    idealWeight = 45.5+(2.3*(inchHeight-60.0));
                    mf = "Female";
                }
                //creates new intent and passes necessary data
                Intent intent = new Intent(Calculator.this, Result.class);
                intent.putExtra("idealWeight", df.format(idealWeight) + " kg");
                intent.putExtra("gender", mf);
                intent.putExtra("height", feet + "'" + inches + "\"");
                //start next activity
                Calculator.this.startActivity(intent);
            }
        });

    }
}