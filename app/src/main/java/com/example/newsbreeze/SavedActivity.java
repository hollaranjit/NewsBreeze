package com.example.newsbreeze;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.newsbreeze.adapter.NewsAdapter;
import com.example.newsbreeze.adapter.SavedNewsAdapter;
import com.example.newsbreeze.database.MyAppDataBase;
import com.example.newsbreeze.database.NewsRoom;
import com.example.newsbreeze.model.Article;
import com.example.newsbreeze.model.News;
import com.example.newsbreeze.rest.Api;
import com.example.newsbreeze.rest.RetrofitClient;
import com.example.newsbreeze.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NewsRoom> newsRooms;
    public static MyAppDataBase myAppDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        myAppDataBase = Room.databaseBuilder(getApplicationContext(),MyAppDataBase.class,"userdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        recyclerView = findViewById(R.id.savedRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        newsRooms = myAppDataBase.myDao().getNewsRoom();

        recyclerView.setAdapter(new SavedNewsAdapter(R.layout.newcontent, getApplicationContext(),newsRooms));


    }




}