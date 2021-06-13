package com.example.newsbreeze.rest;

import com.example.newsbreeze.model.Article;
import com.example.newsbreeze.model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {


    @GET("top-headlines?country=in")
    Call<News> getNews(@Query("apiKey") String apiKey);


}
