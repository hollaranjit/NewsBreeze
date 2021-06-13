package com.example.newsbreeze.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

////////////////////////////////////////RoomDataBase///////////////////////////////////////////
@Dao
public interface MyDao {

    @Insert
    public void addNews(NewsRoom newsRoom);

    @Query("select * from news")
    public List<NewsRoom> getNewsRoom();

    @Delete
    public void deleteSavedNews(NewsRoom newsRoom);

}
