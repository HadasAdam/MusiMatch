package com.example.musimatch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musimatch.R;
import com.example.musimatch.models.Comment;
import com.example.musimatch.models.CommentModel;


import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    public List<Comment> data = CommentModel.instance.getAllComments();
    LayoutInflater inflater;
    View view;
    private CommentAdapter.OnItemClickListener onItemClickListener;

    public CommentAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnClickListener(CommentAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.comment_layout, parent, false);
        CommentViewHolder holder = new CommentViewHolder(view);
        holder.onItemClickListener = onItemClickListener;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = data.get(position);
        holder.bindData(comment,position);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setData(List<Comment> data)
    {
        this.data = data;
    }
}
