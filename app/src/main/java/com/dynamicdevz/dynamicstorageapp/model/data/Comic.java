package com.dynamicdevz.dynamicstorageapp.model.data;

public class Comic {

    public enum Publisher {
        MARVEL,
        DC,
        IMAGE,
        BOOM
    }

    public int getComicId() {
        return comicId;
    }

    private int comicId;
    private int publishYear;
    private Publisher publisher;
    private double rating;
    private String title;
    private int issue;

    public Comic(int comicId, int publishYear, Publisher publisher, double rating, String title, int issue) {
        this.comicId = comicId;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.rating = rating;
        this.title = title;
        this.issue = issue;
    }

    public Comic(int publishYear, Publisher publisher, double rating, String title, int issue) {
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.rating = rating;
        this.title = title;
        this.issue = issue;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public int getIssue() {
        return issue;
    }
}
