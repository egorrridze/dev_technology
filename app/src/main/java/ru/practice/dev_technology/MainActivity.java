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

/** Represents main converter activity.
 * @author SmokedKoala
 * @version 0.4.2
 * @since 0.1.0
 */
public class MainActivity extends Activity {

    /**Spinners to choose value types from */
    private Spinner choice1, choice2, values;
    /**Button for starting converting process */
    private Button convertButton;
    /**Field for number input*/
    private EditText inputNum;
    /**Value types array */
    private String[] value_types = {"mass", "length"};
    private String[] first_values = {};
    private String[] second_values = {};
    /**Provide views for an AdapterView. */
    private ArrayAdapter<String> spinnerArrayAdapterValues, spinnerArrayAdapterChoice1, spinnerArrayAdapterChoice2;
    /**Shows the converter result */
    private TextView secondValueNum;
    private ImageButton downloadButton;
    private HttpURLConnection httpURLConnection;
    private String query;



    /**
     * create main converter activity
     * @param savedInstanceState saved variables from previous activity launch
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("converter", Context.MODE_PRIVATE);
        cardData = preferences.getStringSet("cardData", new HashSet<>());
        applied_language = preferences.getString("applied_language","English");

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
        switch (v.getId()){
            case R.id.convert_button:
                if (!inputNum.getText().toString().equals("")) {
                    StringBuilder result = new StringBuilder();
                    result.append(inputNum.getText());
                    result.append(" ");
                    result.append(choice1.getSelectedItem());
                    result.append(" = ");
                    result.append(secondValueNum.getText());
                    result.append(" ");
                    result.append(choice2.getSelectedItem());
                    cardData.add(result.toString());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putStringSet("cardData",cardData);
                    editor.apply();
                } else {
                    Toast.makeText(this,R.string.error_message,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.download_button:
                DownloadThread thread = new DownloadThread(query, values,httpURLConnection);
                System.out.println(thread.execute());
//                second_values = (thread.doInBackground());
//                System.out.println(first_values);
               break;
        }
    }



}