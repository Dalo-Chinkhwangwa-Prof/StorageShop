package com.dynamicdevz.dynamicstorageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dynamicdevz.dynamicstorageapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sharedprefButton.setOnClickListener(whatever -> {
           Intent intent = new Intent(this, SharedpreferencesActivity.class);
           startActivity(intent);
        });
    }
}