package com.sujeet.quboxtestapp.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sujeet.quboxtestapp.R;
import com.sujeet.quboxtestapp.databinding.VideoItemLayoutBinding;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    Context context;
    List<String> urlList;

    public VideoAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.video_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {


        holder.binding.videoView.setVideoPath(urlList.get(position));
        holder.binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();

              holder.binding.progressBar.setVisibility(View.GONE);
            }
        });

        holder.binding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });


    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        VideoItemLayoutBinding binding;

        public MyViewHolder(@NonNull VideoItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }

}
