package com.example.feedmememes.ActivitiesAndFragments.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.feedmememes.ActivitiesAndFragments.models.imageDetails;
import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;
import com.example.feedmememes.R;

import java.io.File;
import java.util.ArrayList;

public class favMemesAdapter extends RecyclerView.Adapter<favMemesAdapter.ViewHolder>{
    private ArrayList<imageDetails> mArray= new ArrayList<>();
    private View parentView;

    public favMemesAdapter(ArrayList<imageDetails> mArray){
        this.mArray=mArray;
    }

    @NonNull
    @Override
    public favMemesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        parentView=parent;
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.each_meme_layout, parent, false);

        // Return a new holder instance
        favMemesAdapter.ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final favMemesAdapter.ViewHolder holder, int position) {
        holder.gif_title.setText(mArray.get(position).getTitle());
//        see how to put place holder while image is loading
        holder.progressBar.setVisibility(View.VISIBLE);
        Uri uri=Uri.fromFile(new File(mArray.get(position).getUrl()));
        if(mArray.get(position).isDownloaded()){
//            Glide.with(parentView)
//                    .load(uri)
//                    .centerCrop()
//                    .into(holder.imageView);
//            Log.d(constantsClass.logTag," loading from file system, uri is "+ uri);
//            switch (uri.toString()){
//                case "file:///storage/emulated/0/Download/228418b4f4347a94700ea30526b56235.gif": Log.d(constantsClass.logTag," match one"); break;
//                case "file:///storage/emulated/0/Download/e2baf02d2393de9ff2ce37971ab11534.gif": Log.d(constantsClass.logTag," match two"); break;
//                case "file:///storage/emulated/0/Download/9392e44c930a5023c19e29e028ce61a5.gif": Log.d(constantsClass.logTag," match three"); break;
//            }
//            holder.progressBar.setVisibility(View.GONE);
        }
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

    public memesDBObject getObjectAtPosition(int position){
        imageDetails temporalObject=mArray.get(position);
        memesDBObject obj=new memesDBObject(temporalObject.getHash(),temporalObject.getUrl(),temporalObject.getTitle(),"favourite");
        return obj;
    }

}
