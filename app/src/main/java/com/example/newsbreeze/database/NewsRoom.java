package com.example.newsbreeze.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



////////////////////////////////////////RoomDataBase///////////////////////////////////////////
@Entity(tableName = "news")
public class NewsRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "news_urlToImage")
    private String urlToImage;

    @ColumnInfo(name = "news_title")
    private String title;

    @ColumnInfo(name = "news_publishedAt")
    private String publishedAt;

    @ColumnInfo(name = "news_description")
    private String description;

    @ColumnInfo(name = "news_url")
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
