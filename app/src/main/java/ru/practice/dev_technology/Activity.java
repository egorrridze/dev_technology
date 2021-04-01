package ru.practice.dev_technology;

import android.content.Intent;
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


/** Represents prototype activity for others and controls same methods.
 * @author SmokedKoala
 * @version 0.0.3
 * @since 0.0.3
 */
public class Activity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    /**DrawerLayout acts as a top-level container for window content that allows
     * for interactive "drawer" views to be pulled out from one or both vertical
     * edges of the window. */
    DrawerLayout drawerLayout;
    /**Represents a standard navigation menu for application */
    NavigationView navigationView;
    /**This class provides a handy way to tie together the functionality of DrawerLayout  */
    ActionBarDrawerToggle actionBarDrawerToggle;
    /**A standard toolbar for use within application content. */
    Toolbar toolbar;
    /**Is used to keeping checked current activity in menu*/
    int currentPage;



    /**
     * controls what application have to do if some element was pressed
     * @param v View of the Activity
     */
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


    /**
     * controls what application have to do if some menu element was pressed
     * @param item menu item
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
}