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

public class SettingsActivity extends Activity {

    /**Languages array */
    private String[] languages = {"English", "Русский"};
    /**Spinner to choose language from */
    private Spinner languageChoice;
    /**Provide views for an AdapterView. */
    private ArrayAdapter<String> spinnerArrayAdapter;
    /**Button for applying setting changes */
    private Button applyButton;

    private String applied_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("converter", Context.MODE_PRIVATE);
        applied_language = preferences.getString("applied_language","English");

        applyButton = findViewById(R.id.apply_button);
        applyButton.setOnClickListener(this);

        languageChoice = findViewById(R.id.language_choice);
        languageChoice.setSelection(Arrays.asList(languages).indexOf(applied_language));


        drawerLayout = findViewById(R.id.settings_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.settings_toolbar);
        navigationMenuCreation();

        spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, languages);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        languageChoice.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apply_button:
                if (languageChoice.getSelectedItem()=="Русский") {
                    setLanguage("ru");
                }
                if (languageChoice.getSelectedItem()=="English") {
                    setLanguage("en");
                }

                Intent intent = new Intent(this,SettingsActivity.class);
                applied_language = languageChoice.getSelectedItem().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("applied_language",applied_language);
                editor.apply();
                startActivity(intent);
                break;
        }
    }

    public void setLanguage(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

//    public void
}