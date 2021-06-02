package ru.practice.dev_technology;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

/**
 * Represents main converter activity.
 *
 * @author SmokedKoala
 * @version 0.4.2
 * @since 0.1.0
 */
public class MainActivity extends Activity {

    /**
     * Spinners to choose value types from
     */
    Spinner choice1, choice2, values;
    /**
     * Button for starting converting process
     */
    Button convertButton;
    /**
     * Field for number input
     */
    EditText inputNum;
    /**
     * Value types array
     */
    String[] value_types = {"mass", "length", "volume","speed", "time"};
    String[] mass = {"кг", "г", "ц", "т", "фунтов"};
    String[] length = {"м", "км", "см", "мм", "дюймов", "миль"};
    String[] volume = {"кубм", "л", "кубсм", "пинт"};
    String[] speed = {"км_ч", "м_с", "узлов", "миль_ч"};
    String[] time = {"с", "мин", "час", "дней", "лет"};
    String[] first_values = {};
    String[] second_values = {};
    /**
     * Provide views for an AdapterView.
     */
    ArrayAdapter<String> spinnerArrayAdapterValues, spinnerArrayAdapterChoice1, spinnerArrayAdapterChoice2;
    /**
     * Shows the converter result
     */
    TextView secondValueNum;
    ImageButton downloadButton;
    HttpURLConnection httpURLConnection;
    String query;


    /**
     * create main converter activity
     *
     * @param savedInstanceState saved variables from previous activity launch
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("converter", Context.MODE_PRIVATE);
        cardData = preferences.getStringSet("cardData", new HashSet<>());
        applied_language = preferences.getString("applied_language", "English");

        setLanguage();
        setContentView(R.layout.activity_main);

        currentPage = R.id.nav_converter;
        drawerLayout = findViewById(R.id.converter_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.converter_toolbar);
        values = findViewById(R.id.values_choice);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(this);
        downloadButton = findViewById(R.id.download_button);
        downloadButton.setOnClickListener(this);
        inputNum = findViewById(R.id.number_input);
        inputNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        secondValueNum = findViewById(R.id.result);

        navigationMenuCreation();

        spinnerArrayAdapterValues = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, value_types);
        spinnerArrayAdapterValues.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerArrayAdapterChoice1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, first_values);
        spinnerArrayAdapterChoice1.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerArrayAdapterChoice2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, second_values);
        spinnerArrayAdapterChoice2.setDropDownViewResource(android.R.layout.simple_spinner_item);

        values.setAdapter(spinnerArrayAdapterValues);
        choice1.setAdapter(spinnerArrayAdapterChoice1);
        choice2.setAdapter(spinnerArrayAdapterChoice2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.convert_button:
                if (!inputNum.getText().toString().equals("")) {
                    DownloadThread downloadThread = new DownloadThread(this,query,values,httpURLConnection,choice1.getSelectedItem().toString(),choice2.getSelectedItem().toString(),inputNum.getText().toString());
                    downloadThread.execute();
//                    secondValueNum.setText(downloadThread);

//                        StringBuilder result = new StringBuilder();
//                        result.append(inputNum.getText());
//                        result.append(" ");
//                        result.append(choice1.getSelectedItem());
//                        result.append(" = ");
//                        result.append(secondValueNum.getText());
//                        result.append(" ");
//                        result.append(choice2.getSelectedItem());
//                        cardData.add(result.toString());
//                        SharedPreferences.Editor editor = preferences.edit();
//                        editor.putStringSet("cardData", cardData);
//                        editor.apply();
                } else {
                    Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.download_button:
                String[] chosen_array = null;
                switch (values.getSelectedItem().toString()){
                    case "mass":
                        chosen_array = mass;
                        break;
                    case "length":
                        chosen_array = length;
                        break;
                    case "time":
                        chosen_array = time;
                        break;
                    case "volume":
                        chosen_array = volume;
                        break;
                    case "speed":
                        chosen_array = speed;
                        break;
                }

                spinnerArrayAdapterChoice1 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, chosen_array);
                spinnerArrayAdapterChoice1.setDropDownViewResource(android.R.layout.simple_spinner_item);

                spinnerArrayAdapterChoice2 = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, chosen_array);
                spinnerArrayAdapterChoice2.setDropDownViewResource(android.R.layout.simple_spinner_item);

                choice1.setAdapter(spinnerArrayAdapterChoice1);
                choice2.setAdapter(spinnerArrayAdapterChoice2);
                break;
        }
    }


}