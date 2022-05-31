package com.example.musimatch.adapters;


import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musimatch.R;
import com.example.musimatch.models.Comment;
import com.example.musimatch.models.UserModel;


public class CommentViewHolder extends RecyclerView.ViewHolder {
    public CommentAdapter.OnItemClickListener onItemClickListener;
    Comment currentComment;
    int position;
    TextView commentUploader;
    TextView commentContent;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        commentUploader = itemView.findViewById(R.id.comment_uploader);
        commentContent = itemView.findViewById(R.id.comment_content);

        itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    public void bindData(Comment comment, int position) {
        currentComment = comment;
        commentUploader.setText(comment.getUser().getUsername());
        commentContent.setText(comment.getContent());
        this.position = position;
    }
}

