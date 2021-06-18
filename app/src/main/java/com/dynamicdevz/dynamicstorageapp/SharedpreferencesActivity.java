package com.dynamicdevz.dynamicstorageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.dynamicdevz.dynamicstorageapp.databinding.ActivitySharedpreferencesBinding;

import static com.dynamicdevz.dynamicstorageapp.util.Constants.DATA_KEY;

public class SharedpreferencesActivity extends AppCompatActivity {

    private ActivitySharedpreferencesBinding binding;

    private SharedPreferences sharedPreferences;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySharedpreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        readFromSharedPref();

        binding.insertButton.setOnClickListener(view -> {
            String input = binding.inputEdittext.getText().toString().trim();
            //Clear text
            binding.inputEdittext.setText("");

            if(input.length() == 0)
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show();
            else {
                if(data.equals("empty"))
                    data = "";

                sharedPreferences.edit()
                        .putString(DATA_KEY, data+"\n"+input+"\n")
                        .apply();
                readFromSharedPref();

            }

        });
    }

    private void readFromSharedPref() {
        data = sharedPreferences.getString(DATA_KEY, "empty");
        binding.spDisplayTextview.setText(data);
    }
}