package com.dynamicdevz.dynamicstorageapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dynamicdevz.dynamicstorageapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sharedprefButton.setOnClickListener(whatever -> {
           Intent intent = new Intent(this, SharedPreferencesActivity.class);
           startActivity(intent);
        });

        binding.databaseButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SQLiteDatabaseActivity.class);
            startActivity(intent);
        });

    }
}