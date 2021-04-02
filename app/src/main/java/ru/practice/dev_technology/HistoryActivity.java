package ru.practice.dev_technology;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/** Represents history activity.
 * @author SmokedKoala
 * @version 0.4.0
 * @since 0.3.0
 */
public class HistoryActivity extends Activity {

    /**List of card values for creating history*/
    protected List<CardFiller> cardFillerList;

    protected RecyclerView recyclerView;
    protected RecyclerViewAdapter recyclerViewAdapter;

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

        cardFillerList = new ArrayList<>();
        createList();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewAdapter = new RecyclerViewAdapter(this, cardFillerList);
        recyclerView.setAdapter(recyclerViewAdapter);

        navigationMenuCreation();
    }

    private void createList(){
        for (int i = 0; i<100;i++)
            cardFillerList.add(new CardFiller("1","см","10","мм"));
    }


}