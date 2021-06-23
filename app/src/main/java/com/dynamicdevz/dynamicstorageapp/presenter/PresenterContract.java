package com.dynamicdevz.dynamicstorageapp.presenter;

import android.content.Context;

import com.dynamicdevz.dynamicstorageapp.model.data.Comic;

import java.util.List;

public interface PresenterContract {

    interface Presenter {
        void getComics();
        void insertNewComic(Comic comic);
    }

    interface ComicView {
        void displayComics(List<Comic> comics);
        void displayError(String message);
        Context getContext();
    }

}
