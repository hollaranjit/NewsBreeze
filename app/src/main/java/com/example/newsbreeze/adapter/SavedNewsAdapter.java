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
import com.example.newsbreeze.SavedActivity;
import com.example.newsbreeze.database.MyAppDataBase;
import com.example.newsbreeze.database.NewsRoom;
import com.example.newsbreeze.model.Article;
import com.example.newsbreeze.utils.Constants;

import java.util.List;

public class SavedNewsAdapter extends RecyclerView.Adapter<SavedNewsAdapter.SavedNewsAdapterHolder> {


    private int contentLayout;
    private Context context;
    List<NewsRoom> newsRooms;

    public SavedNewsAdapter(int contentLayout, Context context, List<NewsRoom> newsRooms) {
        this.contentLayout = contentLayout;
        this.context = context;
        this.newsRooms = newsRooms;
    }

    @NonNull
    @Override
    public SavedNewsAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(contentLayout, parent, false);
        //view.setOnClickListener(mOnClickListener);
        return new SavedNewsAdapter.SavedNewsAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedNewsAdapterHolder holder, int position) {


        holder.title.setText(newsRooms.get(position).getTitle());
        holder.date.setText(newsRooms.get(position).getPublishedAt());
        holder.description.setText(newsRooms.get(position).getDescription());

        holder.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, ReadActivity.class);
                myIntent.putExtra("key", newsRooms.get(position).getUrl());
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }
        });

        holder.save.setText("Remove");

        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsRoom newsRoom = new NewsRoom();
                int id=newsRooms.get(position).getId();
                newsRoom.setId(id);
                SavedActivity.myAppDataBase.myDao().deleteSavedNews(newsRoom);
                removeAt(position);
                Toast.makeText(context, "Article Removed", Toast.LENGTH_SHORT).show();
            }
        });

        Glide
                .with(context)
                .load(newsRooms.get(position).getUrlToImage())
                .centerCrop()
                .placeholder(R.drawable.defaultimg)
                .into(holder.newsImage);

    }

    @Override
    public int getItemCount() {
        return newsRooms.size();
    }

    public class SavedNewsAdapterHolder extends RecyclerView.ViewHolder {
        LinearLayout contentLayout;
        TextView title;
        TextView date;
        TextView description;
        ImageView newsImage;
        Button read,save;
        public SavedNewsAdapterHolder(@NonNull View itemView) {
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

    public void removeAt(int position) {
        newsRooms.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, newsRooms.size());
    }
}
