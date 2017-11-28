package com.example.user.lab3_3inputcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI linking
        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);

        //initialize the spinner adapter
        //this few lines of code are copied from lab3.3 pdf. just change the R.array.<array_name>
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);

    }

    //function automatically created by AdapterView.OnItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //Toast.makeText(getApplicationContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
    }

    //function automatically created by AdapterView.OnItemSelectedListener
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    public void calculatePremium(View view)
    {
        boolean validInput = false;

        int pos;
        pos = spinnerAge.getSelectedItemPosition();

        int idGender;
        idGender = radioGroupGender.getCheckedRadioButtonId();

        int totalPremium = 0;

        //validation checking
        if (pos == 0)
        {
            Toast.makeText(getApplicationContext(), "Please select your age", Toast.LENGTH_SHORT).show();
            validInput = true;
        }

        if (idGender == -1)
        {
            validInput = true;
            Toast.makeText(getApplicationContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
        }

        //TODO: Calculate insurance premium
        switch(pos)
        {
            case 1: totalPremium += 50; break;
            case 2: totalPremium += 55; break;
            case 3: totalPremium += 60; break;
            case 4: totalPremium += 70; break;
            case 5: totalPremium += 120; break;
            case 6: totalPremium += 160; break;
            case 7: totalPremium += 200; break;
            case 8: totalPremium += 250; break;

        }

        if (idGender == R.id.radioButtonMale)
        {
            switch(pos)
            {
                case 3: totalPremium += 50; break;
                case 4: totalPremium += 100; break;
                case 5: totalPremium += 100; break;
                case 6: totalPremium += 50; break;
            }
        }

        if (checkBoxSmoker.isChecked())
        {
            switch(pos)
            {
                case 4: totalPremium += 100; break;
                case 5: totalPremium += 150; break;
                case 6: totalPremium += 150; break;
                case 7: totalPremium += 250; break;
                case 8: totalPremium += 250; break;
            }
        }
        textViewPremium.setText(getString(R.string.premium) + Integer.toString(totalPremium));
    }

    public void reset(View view)
    {
        spinnerAge.setSelection(0);
        radioGroupGender.clearCheck();
        checkBoxSmoker.setChecked(false);
        textViewPremium.setText(getString(R.string.premium));
    }
}
