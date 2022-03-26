package com.example.musimatch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musimatch.R;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.PostModel;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

    public List<Post> data = PostModel.instance.getAllPosts();
    public boolean isEditAvailable;
    LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public PostAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_poem_layout, parent, false);
        PostViewHolder holder = new PostViewHolder(view, this.isEditAvailable);
        holder.onItemClickListener = onItemClickListener;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = data.get(position);
        holder.bindData(post,position);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setData(List<Post> data)
    {
        this.data = data;
    }
}
