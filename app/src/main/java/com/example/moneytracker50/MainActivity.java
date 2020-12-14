package com.example.moneytracker50;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    RecordDatabase recordDatabase;

    final FragmentManager fragmentManager = getSupportFragmentManager();
    final HomeFragment mainFragment = new HomeFragment();
    final HistoryFragment historyFragment = new HistoryFragment();
    final AnalyticsFragment analyticsFragment = new AnalyticsFragment();

    Fragment selectedFragment = mainFragment;
    BottomNavigationView btm_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordDatabase = RecordDatabase.getInstance(getApplicationContext());
        if (recordDatabase.categoryDao().getTotal() == 0) insertDefaultCategory();

        fragmentManager.beginTransaction().add(R.id.fragment_container, analyticsFragment, "analytics").hide(analyticsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, historyFragment, "history").hide(historyFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, mainFragment, "main").commit();

        btm_nav = findViewById(R.id.btm_nav);
        btm_nav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_main:
                                fragmentManager.beginTransaction().hide(selectedFragment).show(mainFragment).commit();
                                selectedFragment = mainFragment;
                                return true;
                            case R.id.action_history:
                                fragmentManager.beginTransaction().hide(selectedFragment).show(historyFragment).commit();
                                selectedFragment = historyFragment;
                                return true;
                            case R.id.action_analytics:
                                fragmentManager.beginTransaction().hide(selectedFragment).show(analyticsFragment).commit();
                                selectedFragment = analyticsFragment;
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();
        reload();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) reload();
    }

    void insertDefaultCategory() {
        String[] category = {
                "None", "Cloth", "Debt", "Entertainment", "Food",
                "Housing", "Medical/Healthcare", "Transportation", "Utilities"
        };

        for (String i : category)
            recordDatabase.categoryDao().addCategory(new Category(i));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void reload(){
        mainFragment.reloadBalance();
        historyFragment.reload();
        analyticsFragment.reloadAll();
    }

}