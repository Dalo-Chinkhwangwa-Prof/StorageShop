package com.dynamicdevz.dynamicstorageapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dynamicdevz.dynamicstorageapp.R;
import com.dynamicdevz.dynamicstorageapp.databinding.ActivitySqliteDatabaseBinding;
import com.dynamicdevz.dynamicstorageapp.model.data.Comic;
import com.dynamicdevz.dynamicstorageapp.model.data.Comic.Publisher;
import com.dynamicdevz.dynamicstorageapp.model.db.ComicDBHelper;
import com.dynamicdevz.dynamicstorageapp.presenter.ComicPresenter;
import com.dynamicdevz.dynamicstorageapp.presenter.PresenterContract;
import com.dynamicdevz.dynamicstorageapp.view.adapter.ComicAdapter;

import java.util.List;

public class SQLiteDatabaseActivity extends AppCompatActivity implements ComicAdapter.ComicDelegate, PresenterContract.ComicView {

    private ActivitySqliteDatabaseBinding binding;
    private Publisher setPublisher = Publisher.BOOM;
    private ComicAdapter comicAdapter = new ComicAdapter(this);
    private PresenterContract.Presenter comicPresenter;

    private String[] options = {
            Publisher.BOOM.name(),
            Publisher.DC.name(),
            Publisher.MARVEL.name(),
            Publisher.IMAGE.name()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqliteDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.comicListview.setAdapter(comicAdapter);
        comicPresenter = new ComicPresenter(this);
        comicPresenter.getComics();

        binding.publisherSpinner.setAdapter( new ArrayAdapter<String>(this, R.layout.spinner_item,R.id.publisher_name, options ));
        binding.publisherSpinner.setSelection(0);

        binding.publisherSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setPublisher = Publisher.valueOf(options[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//  Do  nothing
            }
        });

        binding.insertComicButton.setOnClickListener(view -> {
            String comicTitle = binding.titleEdittext.getText().toString().trim();
            int publishYear = Integer.parseInt(binding.publishYearEdittext.getText().toString());
            int issue = Integer.parseInt(binding.issueEdittext.getText().toString());
            double rating = (double) binding.ratingBar.getRating();

            Comic newComic = new Comic(publishYear, setPublisher,rating, comicTitle, issue);
            comicPresenter.insertNewComic(newComic);
        });
    }

    @Override
    public void selectComic(Comic comic) {
        Toast.makeText(this, comic.getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ComicDetailsActivity.class);
        intent.putExtra("COMIC_DATA", comic);
        startActivity(intent);
    }

    @Override
    public void displayComics(List<Comic> comics) {
        comicAdapter.setComicList(comics);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
















