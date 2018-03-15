package com.redberrystudios.whatsfordinner.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.redberrystudios.whatsfordinner.R;
import com.redberrystudios.whatsfordinner.view.fragment.ChecklistsFragment;
import com.redberrystudios.whatsfordinner.view.fragment.DaysFragment;
import com.redberrystudios.whatsfordinner.view.fragment.GroupFragment;
import com.redberrystudios.whatsfordinner.view.fragment.ProfileFragment;
import com.redberrystudios.whatsfordinner.view.settings.SettingsActivity;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String PROVIDER_KEY = "PROVIDER_KEY";
    public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, DaysFragment.newInstance()).commit();
        setTitle(R.string.title_activity_days);

        Bundle tokenBundle = getIntent().getExtras();
        if (tokenBundle != null) {
            String tokenString = tokenBundle.getString(ACCESS_TOKEN_KEY);
            String providerString = tokenBundle.getString(PROVIDER_KEY);

            Log.d(TAG, "Token: " + providerString + " - " + tokenString);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;

        // Handle navigation view item clicks here.
        switch (menuItem.getItemId()) {
            case R.id.main_nav_days:
                fragmentClass = DaysFragment.class;
                break;
            case R.id.main_nav_checklists:
                fragmentClass = ChecklistsFragment.class;
                break;
            case R.id.main_nav_profile:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.main_nav_group:
                fragmentClass = GroupFragment.class;
                break;
            case R.id.main_nav_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                fragmentClass = DaysFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());

        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
