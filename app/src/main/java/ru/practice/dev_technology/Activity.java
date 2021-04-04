package ru.practice.dev_technology;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Locale;
import java.util.Set;


/** Represents prototype activity for others and controls same methods.
 * @author SmokedKoala
 * @version 0.4.2
 * @since 0.3.0
 */
public class Activity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    /**DrawerLayout acts as a top-level container for window content that allows
     * for interactive "drawer" views to be pulled out from one or both vertical
     * edges of the window. */
    protected DrawerLayout drawerLayout;
    /**Represents a standard navigation menu for application */
    protected NavigationView navigationView;
    /**This class provides a handy way to tie together the functionality of DrawerLayout  */
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    /**A standard toolbar for use within application content. */
    protected Toolbar toolbar;
    /**Is used to keeping checked current activity in menu*/
    protected int currentPage;
    /**Interface for accessing and modifying preference data returned*/
    protected SharedPreferences preferences;
    /**The collection that contains no duplicate history data*/
    protected Set<String> cardData;
    /**String for chosen language */
    protected  String applied_language;


    /**
     * controls what application have to do if some element was pressed
     * @param v View of the Activity
     */
    @Override
    public void onClick(View v) {
    }


    /**
     * controls what application have to do if some menu element was pressed
     * @param item menu item
     * @return true, if menu item was pressed
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.nav_history:
                if(this.getClass()!=HistoryActivity.class) {
                    intent = new Intent(this,HistoryActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.nav_converter:
                if(this.getClass()!=MainActivity.class) {
                    intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.nav_settings:
                if(this.getClass()!=SettingsActivity.class) {
                    intent = new Intent(this,SettingsActivity.class);
                    startActivity(intent);
                }
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * controls what application have to do if back is pressed when menu is open
     */
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }



    /**
     * creates toggle and set NavigationItemSelectedListener for NavigationView
     */
    public void navigationMenuCreation(){
        navigationView.bringToFront();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(currentPage);
    }

    /**
     * Sets chosen language through configuration update
     */
    public void setLanguage(){
        String language = "";
        if (applied_language.equals("Русский"))
            language = "ru";
        else
            language = "en";
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
