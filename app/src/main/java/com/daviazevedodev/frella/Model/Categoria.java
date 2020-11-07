package com.daviazevedodev.frella.Model;

public class Categoria {

    private String title;
    private int thumbnail;


    public Categoria() {
    }

    public Categoria(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
