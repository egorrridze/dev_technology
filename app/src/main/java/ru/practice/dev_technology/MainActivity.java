package ru.practice.dev_technology;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner choice1;
    Spinner choice2;
    Button convertButton;
    EditText inputNum;

//    заглушка
    String[] data = {"one", "two", "three", "four", "five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        адаптеры для списков
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_main,data);
//        adapter.setDropDownViewResource(R.layout.activity_main);

        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
//        choice1.setAdapter(adapter);
//        choice2.setAdapter(adapter);
        convertButton = findViewById(R.id.convert_button);
        inputNum = findViewById(R.id.number_input);
    }
}