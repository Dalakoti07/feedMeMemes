package com.example.feedmememes.ActivitiesAndFragments.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.feedmememes.ActivitiesAndFragments.models.imageDetails;
import com.example.feedmememes.R;

import java.util.ArrayList;

public class memesAdapter extends RecyclerView.Adapter<memesAdapter.ViewHolder> {
//    optimization for glide inspired from https://github.com/bumptech/glide/issues/1779
//    one way remaining
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
    public void onBindViewHolder(@NonNull final memesAdapter.ViewHolder holder, int position) {
        holder.gif_title.setText(mArray.get(position).getTitle());
//        see how to put place holder while image is loading
        holder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(parentView)
                .load(mArray.get(position).getUrl())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.1f)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
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
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.memeImageView);
            gif_title=itemView.findViewById(R.id.gif_title);
            progressBar=itemView.findViewById(R.id.indeterminateBar);
        }

        public void recycle() {
            Glide.with(itemView).clear(imageView);
        }
    }

}
