package com.example.feedmememes.ActivitiesAndFragments.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
                Glide.with(parentView)
                .load(mArray.get(position).getUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_loading_48)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.memeImageView);
        }
    }
}
