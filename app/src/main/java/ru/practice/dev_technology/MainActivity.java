package ru.practice.dev_technology;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/** Represents main converter activity.
 * @author SmokedKoala
 * @version 0.4.0
 * @since 0.1.0
 */
public class MainActivity extends Activity {

    /**Spinners to choose value types from */
    private Spinner choice1, choice2;
    /**Button for starting converting process */
    private Button convertButton;
    /**Field for number input*/
    private EditText inputNum;
    /**Test array */
    private String[] data = {"one", "two", "three", "four", "five"};
    /**Provide views for an AdapterView. */
    private ArrayAdapter<String> spinnerArrayAdapter;
    /**Shows the converter result */
    private TextView result;



    /**
     * create main converter activity
     * @param savedInstanceState saved variables from previous activity launch
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentPage = R.id.nav_converter;
        drawerLayout = findViewById(R.id.converter_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.converter_toolbar);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(this);
        inputNum = findViewById(R.id.number_input);
        inputNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        result = findViewById(R.id.result);


        navigationMenuCreation();

        spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        choice1.setAdapter(spinnerArrayAdapter);
        choice2.setAdapter(spinnerArrayAdapter);
    }



}