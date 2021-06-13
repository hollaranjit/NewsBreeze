package com.example.newsbreeze.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

////////////////////////////////////////RoomDataBase///////////////////////////////////////////
@Database(entities = {NewsRoom.class},version = 3)
public abstract class MyAppDataBase extends RoomDatabase {

    public abstract MyDao myDao();

}
