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

    private List<Comic> comicList = new ArrayList<>();

    public ComicAdapter(List<Comic> comicList) {
        this.comicList = comicList;
    }

    public void setComicList(List<Comic> comicList) {
        this.comicList = comicList;
        notifyDataSetChanged();
    }

    public ComicAdapter() { }



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

        return binding.getRoot();
    }
}
