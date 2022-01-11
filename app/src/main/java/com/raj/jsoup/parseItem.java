package com.raj.jsoup;

public class parseItem {
    private String imageurl;
    private String title;
    private String language;
    private String detailUrl;

    public parseItem(){

    }

    public parseItem(String imageurl, String title, String language, String detailUrl) {
        this.imageurl = imageurl;
        this.title = title;
        this.language = language;
        this.detailUrl = detailUrl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
