package ru.practice.dev_technology;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/** Represents history activity.
 * @author SmokedKoala
 * @version 0.4.1
 * @since 0.3.0
 */
public class HistoryActivity extends Activity {

    /**List of card values for creating history*/
    protected List<CardFiller> cardFillerList;

    protected RecyclerView recyclerView;
    protected RecyclerViewAdapter recyclerViewAdapter;
    private Button deleteButton;

    /**
     * create history activity
     * @param savedInstanceState saved variables from previous activity launch
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        preferences = getSharedPreferences("converter", Context.MODE_PRIVATE);
        cardData = preferences.getStringSet("cardData", new HashSet<>());

        currentPage = R.id.nav_history;
        drawerLayout = findViewById(R.id.history_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.history_toolbar);
        deleteButton = findViewById(R.id.del_history);
        deleteButton.setOnClickListener(this);

        cardFillerList = new ArrayList<>();
        createList();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewAdapter = new RecyclerViewAdapter(this, cardFillerList);
        recyclerView.setAdapter(recyclerViewAdapter);

        navigationMenuCreation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.del_history:
                
                cardData.clear();
                recyclerViewAdapter.clearRecyclerView();

                System.out.println(cardData);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("cardData");
                editor.apply();
                break;
        }
    }

    /**
     * create data set for cards in recyclerView
     */
    private void createList(){
        if (cardData.size()!=0)
            for (Iterator<String> iterator = cardData.iterator(); iterator.hasNext();)
                cardFillerList.add(new CardFiller(iterator.next()));
    }


}