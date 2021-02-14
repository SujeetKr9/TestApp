package com.sujeet.quboxtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.sujeet.quboxtestapp.adapters.VideoAdapter;
import com.sujeet.quboxtestapp.databinding.ActivityVideoBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VideoActivity extends AppCompatActivity {

    ActivityVideoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("test", MODE_PRIVATE);
        if (sharedPreferences.contains("urls")){
            Set<String> setURL = sharedPreferences.getStringSet("urls", null);
            List<String> urlList = new ArrayList<String>();
            urlList.addAll(setURL);
            binding.viewPager.setAdapter(new VideoAdapter(VideoActivity.this, urlList));

        }

    }
}