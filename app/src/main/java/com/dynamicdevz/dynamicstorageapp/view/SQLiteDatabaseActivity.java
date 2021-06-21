package com.dynamicdevz.dynamicstorageapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.dynamicdevz.dynamicstorageapp.R;
import com.dynamicdevz.dynamicstorageapp.databinding.ActivitySqliteDatabaseBinding;
import com.dynamicdevz.dynamicstorageapp.model.data.Comic;
import com.dynamicdevz.dynamicstorageapp.model.data.Comic.Publisher;
import com.dynamicdevz.dynamicstorageapp.model.db.ComicDBHelper;

import java.util.List;

public class SQLiteDatabaseActivity extends AppCompatActivity {

    private ActivitySqliteDatabaseBinding binding;
    private Publisher setPublisher = Publisher.BOOM;

    private ComicDBHelper dbHelper;

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

        dbHelper = new ComicDBHelper(this);
        readAllComics();


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

            // public Comic(int publishYear, Publisher publisher, double rating, String title, int issue)
            Comic newComic = new Comic(publishYear, setPublisher,rating, comicTitle, issue);
            dbHelper.insertComic(newComic);
            readAllComics();
        });
    }

    private void readAllComics() {

        List<Comic> comics = dbHelper.getAllComics();

        StringBuilder s = new StringBuilder();
        for(int i = 0; i < comics.size(); i++){
            s.append(comics.get(i).getTitle()).append("\n");
        }
        binding.outputTextview.setText(s.toString());
    }
}