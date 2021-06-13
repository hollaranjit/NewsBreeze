package com.example.newsbreeze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newsbreeze.adapter.NewsAdapter;
import com.example.newsbreeze.database.MyAppDataBase;
import com.example.newsbreeze.model.Article;
import com.example.newsbreeze.model.News;
import com.example.newsbreeze.rest.Api;
import com.example.newsbreeze.rest.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "6c3277777e19460e997f8064e09ad090";
    public static MyAppDataBase myAppDataBase;
    RecyclerView recyclerView;
    boolean connected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAppDataBase = Room.databaseBuilder(getApplicationContext(),MyAppDataBase.class,"userdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectionCheck();
        if(connected)
        {

        }else
            {
                Toast.makeText(this, "Check Network Connection", Toast.LENGTH_LONG).show();
        }

        getNews();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.saved_menu:

                startActivity(new Intent(MainActivity.this,SavedActivity.class));
                return true;

            default:
                break;
        }

        return false;
    }

    void getNews()
    {
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        Api apiService =
                RetrofitClient.getClient().create(Api.class);

        Call<News> call = apiService.getNews(API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                List<Article> articles = response.body().getArticles();
                Log.i("response",articles.get(0).getTitle());
                recyclerView.setAdapter(new NewsAdapter(articles, R.layout.newcontent, getApplicationContext(),myAppDataBase));


            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    void connectionCheck()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
    }

}