package ru.practice.dev_technology;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/** Represents main converter activity.
 * @author SmokedKoala
 * @version 0.0.1
 * @since 0.0.1
 */
public class MainActivity extends AppCompatActivity {

    /**Spinners to choose value types from */
    Spinner choice1, choice2;

    /**Button for starting converting process */
    Button convertButton;

    /**Field for number input*/
    EditText inputNum;

    /**ClickListener to process user clicks */
    View.OnClickListener clickListener;

    /**Test array */
    String[] data = {"one", "two", "three", "four", "five"};

    /**
     * create main converter activity
     * @param savedInstanceState saved variables from previous activity launch
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickListener = v -> {
            switch (v.getId()){
                case R.id.convert_button:
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "It works!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    break;
            }
        };

//        адаптеры для списков
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, data);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice1.setAdapter(spinnerArrayAdapter);
        choice2.setAdapter(spinnerArrayAdapter);
        convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(clickListener);
        inputNum = findViewById(R.id.number_input);
        inputNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


    }

}