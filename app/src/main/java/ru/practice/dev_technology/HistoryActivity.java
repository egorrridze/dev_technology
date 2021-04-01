package ru.practice.dev_technology;

import android.os.Bundle;


/** Represents history activity.
 * @author SmokedKoala
 * @version 0.0.3
 * @since 0.0.3
 */
public class HistoryActivity extends Activity {

    /**
     * create history activity
     * @param savedInstanceState saved variables from previous activity launch
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        currentPage = R.id.nav_history;
        drawerLayout = findViewById(R.id.history_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.history_toolbar);

        navigationMenuCreation();
    }


}