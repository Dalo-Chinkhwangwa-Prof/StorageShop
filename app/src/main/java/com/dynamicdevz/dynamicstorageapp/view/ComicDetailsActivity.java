package com.dynamicdevz.dynamicstorageapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dynamicdevz.dynamicstorageapp.R;
import com.dynamicdevz.dynamicstorageapp.databinding.ActivityComicDetailsBinding;
import com.dynamicdevz.dynamicstorageapp.model.data.Comic;

public class ComicDetailsActivity extends AppCompatActivity {

    private ActivityComicDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComicDetailsBinding.inflate(
                getLayoutInflater()
        );
        setContentView(binding.getRoot());

        Comic comic = getIntent().getParcelableExtra("COMIC_DATA");
        if(comic != null){
            binding.comicTitleTextview.setText(comic.getTitle());
            binding.ryearTextview.setText("Release in " + comic.getPublishYear());
        }
    }
}