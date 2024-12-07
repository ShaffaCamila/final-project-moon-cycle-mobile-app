package com.example.final_moon_cycle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set listener untuk item bottom navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Menggunakan if else untuk memilih fragment berdasarkan item yang dipilih
            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_article) {
                selectedFragment = new ArticleFragment();
            } else if (item.getItemId() == R.id.nav_discuss) {
                selectedFragment = new DiscussFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            // Replace fragment yang ada dengan fragment yang dipilih
            if (selectedFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
            }

            return true;
        });

        // Set fragment default ketika activity pertama kali dimulai
        if (savedInstanceState == null) {
            // Hanya set fragment pertama kali
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }
    }
}
