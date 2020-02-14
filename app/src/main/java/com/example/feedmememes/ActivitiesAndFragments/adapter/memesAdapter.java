package com.example.feedmememes.ActivitiesAndFragments.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.feedmememes.ActivitiesAndFragments.models.imageDetails;
import com.example.feedmememes.R;

import java.util.ArrayList;

public class memesAdapter extends RecyclerView.Adapter<memesAdapter.ViewHolder> {
    private ArrayList<imageDetails> mArray= new ArrayList<>();
    private View parentView;
    public memesAdapter(ArrayList<imageDetails> mArray){
        this.mArray=mArray;
    }

    @NonNull
    @Override
    public memesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        parentView=parent;
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.each_meme_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull memesAdapter.ViewHolder holder, int position) {
        holder.gif_title.setText(mArray.get(position).getTitle());
//        see how to put place holder while image is loading
        Glide.with(parentView)
                .load(mArray.get(position).getUrl())
                .centerCrop()
                .thumbnail(0.1f)
                .into(holder.imageView);

        Log.d("commonTag"," title is "+mArray.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView gif_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.memeImageView);
            gif_title=itemView.findViewById(R.id.gif_title);
        }
    }
}
