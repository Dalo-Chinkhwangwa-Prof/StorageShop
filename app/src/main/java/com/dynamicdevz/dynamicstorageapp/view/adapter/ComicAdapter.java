package com.dynamicdevz.dynamicstorageapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dynamicdevz.dynamicstorageapp.databinding.ComicItemLayoutBinding;
import com.dynamicdevz.dynamicstorageapp.model.data.Comic;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends BaseAdapter {

    public interface ComicDelegate {
        void selectComic(Comic comic);
    }
    private List<Comic> comicList = new ArrayList<>();

    private ComicDelegate comicDelegate;

    public ComicAdapter(ComicDelegate comicDelegate) {
        this.comicDelegate = comicDelegate;
    }

    public ComicAdapter(List<Comic> comicList, ComicDelegate comicDelegate) {
        this.comicList = comicList;
        this.comicDelegate = comicDelegate;
    }

    public void setComicList(List<Comic> comicList) {
        this.comicList = comicList;
    }

    @Override
    public int getCount() {
        return comicList.size();
    }

    @Override
    public Comic getItem(int i) {
        return comicList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    private ComicItemLayoutBinding binding;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

//        View layout = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.comic_item_layout, viewGroup, false);
        binding = ComicItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        Comic comic = comicList.get(i);
        binding.comicRatingbar.setRating((float)comic.getRating());
        binding.titleTextview.setText(comic.getTitle());
        binding.issueTextview.setText("Issue #"+comic.getIssue());
        binding.releaseTextview.setText("Release: "+comic.getPublishYear());

        binding.getRoot().setOnClickListener(v -> {
            comicDelegate.selectComic(comic);
        });
        return binding.getRoot();
    }

}











