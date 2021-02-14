package com.sujeet.quboxtestapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sujeet.quboxtestapp.R;
import com.sujeet.quboxtestapp.databinding.LinkItemLayoutBinding;

import java.util.List;


public class SaveUrlAdapter extends RecyclerView.Adapter<SaveUrlAdapter.MyViewHolder> {

    List<String> urlList;
    Context context;

    public SaveUrlAdapter(List<String> urlList, Context context) {
        this.urlList = urlList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.link_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.urlTV.setText(urlList.get(position));

    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        LinkItemLayoutBinding binding;

        public MyViewHolder(@NonNull LinkItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
