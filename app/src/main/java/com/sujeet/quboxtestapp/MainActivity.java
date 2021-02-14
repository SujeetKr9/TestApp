package com.sujeet.quboxtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.sujeet.quboxtestapp.adapters.SaveUrlAdapter;
import com.sujeet.quboxtestapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        sharedPreferences = getApplicationContext().getSharedPreferences("test", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mainBinding.addURL.setOnClickListener(v ->{
            if (mainBinding.editText.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "Please enter URL",Toast.LENGTH_LONG).show();
            }else {
                saveNewUrl(mainBinding.editText.getText().toString());
            }
        });

        mainBinding.openVideosBtn.setOnClickListener(v ->{
            openVideo();
        });

        loadAllUrls();

    }

    private void loadAllUrls() {
        if (sharedPreferences.contains("urls")){
            Set<String> set = sharedPreferences.getStringSet("urls", null);
            Toast.makeText(MainActivity.this,""+set.size(),Toast.LENGTH_LONG).show();

            showAllUrls(set);
        }
    }

    private void saveNewUrl(String url){

        mainBinding.editText.setText("");
        if (sharedPreferences.contains("urls")){
            Set<String> set = sharedPreferences.getStringSet("urls", null);

            if (set.contains(url)){
                Toast.makeText(MainActivity.this, "URL already in the list",Toast.LENGTH_LONG).show();
                return;
            }

            set.add(url);
            editor.putStringSet("urls", set);
            editor.commit();
            showAllUrls(set);

        }else {
            Set<String> set = new HashSet<String>();
            set.add(url);
            editor.putStringSet("urls", set);
            editor.commit();
            showAllUrls(set);
        }
    }

    private void showAllUrls( Set<String> set){
        List<String> urlList = new ArrayList<String>();
        urlList.addAll(set);

        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        SaveUrlAdapter adapter = new SaveUrlAdapter(urlList, MainActivity.this);
        mainBinding.recyclerView.setAdapter(adapter);

    }

    private void openVideo(){
        if (sharedPreferences.contains("urls")){
            Set<String> set = sharedPreferences.getStringSet("urls", null);
            if (set.size() > 0){
                startActivity(new Intent(MainActivity.this, VideoActivity.class));

            }else {
                Toast.makeText(MainActivity.this,"Please add video",Toast.LENGTH_LONG).show();
            }

        }
    }
}