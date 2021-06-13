package com.example.newsbreeze.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.newsbreeze.R;
import com.example.newsbreeze.ReadActivity;
import com.example.newsbreeze.database.MyAppDataBase;
import com.example.newsbreeze.database.NewsRoom;
import com.example.newsbreeze.model.Article;
import com.example.newsbreeze.utils.Constants;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterHolder>  {

    private List<Article> articles;
    private int contentLayout;
    private Context context;
    ////////////////////////////////////////Room DataBase/////////////////////////////////////
    NewsRoom newsRoom;
    public MyAppDataBase myAppDataBase;


    public NewsAdapter(List<Article> articles, int contentLayout, Context context, MyAppDataBase myAppDataBase) {
        this.articles = articles;
        this.contentLayout = contentLayout;
        this.context = context;
        this.myAppDataBase = myAppDataBase;
    }

    @NonNull
    @Override
    public NewsAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(contentLayout, parent, false);
        //view.setOnClickListener(mOnClickListener);
        return new NewsAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterHolder holder, int position) {

        holder.title.setText(articles.get(position).getTitle());
        holder.date.setText(articles.get(position).getPublishedAt());
        holder.description.setText(articles.get(position).getDescription());

        holder.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, ReadActivity.class);
                myIntent.putExtra("key", articles.get(position).getUrl());
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }
        });

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newsRoom = new NewsRoom();
                newsRoom.setUrlToImage(articles.get(position).getUrlToImage());
                newsRoom.setTitle(articles.get(position).getTitle());
                newsRoom.setPublishedAt(articles.get(position).getPublishedAt());
                newsRoom.setDescription(articles.get(position).getDescription());
                newsRoom.setUrl(articles.get(position).getUrl());
                myAppDataBase.myDao().addNews(newsRoom);
                Toast.makeText(context, "Article Saved", Toast.LENGTH_SHORT).show();
                holder.save.setVisibility(View.GONE);
            }
        });

        Glide
                .with(context)
                .load(articles.get(position).getUrlToImage())
                .centerCrop()
                .placeholder(R.drawable.defaultimg)
                .into(holder.newsImage);


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class NewsAdapterHolder extends RecyclerView.ViewHolder {

        LinearLayout contentLayout;
        TextView title;
        TextView date;
        TextView description;
        ImageView newsImage;
        Button read,save;
        public NewsAdapterHolder(@NonNull View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.newscontent_layout);
            title = itemView.findViewById(R.id.tv_title);
            date = itemView.findViewById(R.id.tv_date);
            description = itemView.findViewById(R.id.tv_desc);
            newsImage = itemView.findViewById(R.id.news_img);
            read = itemView.findViewById(R.id.btn_read);
            save = itemView.findViewById(R.id.btn_save);
        }
    }
}
