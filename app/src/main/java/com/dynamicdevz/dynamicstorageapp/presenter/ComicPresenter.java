package com.dynamicdevz.dynamicstorageapp.presenter;

import android.content.Context;

import com.dynamicdevz.dynamicstorageapp.model.data.Comic;
import com.dynamicdevz.dynamicstorageapp.model.db.ComicDBHelper;

public class ComicPresenter implements PresenterContract.Presenter {

    private Context context;

    private PresenterContract.ComicView view;
    private ComicDBHelper dbHelper;

    public ComicPresenter(PresenterContract.ComicView view) {
        this.view = view;
        dbHelper = new ComicDBHelper(view.getContext());
    }

    @Override
    public void getComics() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    view.displayComics(dbHelper.getAllComics());
                } catch(Exception e){
                    view.displayError(e.getMessage());
                }
            }
        }.start();

    }

    @Override
    public void insertNewComic(Comic comic) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    dbHelper.insertComic(comic);
                    getComics();
                } catch (Exception e) {
                    view.displayError(e.getMessage());
                }
            }
        }.start();
    }
}
