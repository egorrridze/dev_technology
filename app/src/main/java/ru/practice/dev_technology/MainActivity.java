package ru.practice.dev_technology;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner choice1, choice2;
    Button convertButton;
    EditText inputNum;
    View.OnClickListener clickListener;

//    заглушка
    String[] data = {"one", "two", "three", "four", "five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.convert_button:
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "It works!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }
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