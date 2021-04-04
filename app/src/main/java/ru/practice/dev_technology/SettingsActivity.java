package ru.practice.dev_technology;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;

/** Represents prototype activity for others and controls same methods.
 * @author SmokedKoala
 * @version 0.4.2
 * @since 0.4.0
 */
public class SettingsActivity extends Activity {

    /**Languages array */
    private String[] languages = {"English", "Русский"};
    /**Spinner to choose language from */
    private Spinner languageChoice;
    /**Provide views for an AdapterView. */
    private ArrayAdapter<String> spinnerArrayAdapter;
    /**Button for applying setting changes */
    private Button applyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("converter", Context.MODE_PRIVATE);
        applied_language = preferences.getString("applied_language","English");

        applyButton = findViewById(R.id.apply_button);
        applyButton.setOnClickListener(this);

        languageChoice = findViewById(R.id.language_choice);

        drawerLayout = findViewById(R.id.settings_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.settings_toolbar);
        navigationMenuCreation();

        spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, languages);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        languageChoice.setAdapter(spinnerArrayAdapter);
        languageChoice.setSelection(Arrays.asList(languages).indexOf(applied_language));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apply_button:
                applied_language = languageChoice.getSelectedItem().toString();
                setLanguage();

                Intent intent = new Intent(this,SettingsActivity.class);
                applied_language = languageChoice.getSelectedItem().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("applied_language",applied_language);
                editor.apply();
                startActivity(intent);
                break;
        }
    }


}