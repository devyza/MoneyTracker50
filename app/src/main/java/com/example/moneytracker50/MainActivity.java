package com.example.moneytracker50;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    HomeFragment mainFragment = new HomeFragment();
    HistoryFragment historyFragment = new HistoryFragment();

    BottomNavigationView btm_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set HomeFragment as Default
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container, mainFragment
        ).commit();

        btm_nav = findViewById(R.id.btm_nav);
        btm_nav.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {

                        case R.id.action_main:
                            selectedFragment = mainFragment;
                            break;
                        case R.id.action_history:
                            selectedFragment = historyFragment;
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fragment_container, selectedFragment
                    ).commit();

                    return true;
                }
            }
        );
    }
}