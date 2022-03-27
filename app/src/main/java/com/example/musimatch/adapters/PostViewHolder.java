package com.example.musimatch.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavAction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musimatch.AllPostsFragmentDirections;
import com.example.musimatch.R;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.UserModel;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public PostAdapter.OnItemClickListener onItemClickListener;
    private boolean isEditAvailable;
    Post currentPost;
    int position;
    Button editButton;
    TextView postTitle;
    TextView postLyrics;
    TextView linkedPosts;
    TextView comments;
    TextView rate;
    TextView uploaderUsername;
    TextView tags;

    public PostViewHolder(@NonNull View itemView, boolean isEditable) {
        super(itemView);
        this.isEditAvailable = isEditable;
        editButton = itemView.findViewById(R.id.postPoem_editButton);
        postTitle = itemView.findViewById(R.id.postPoem_title);
        postLyrics = itemView.findViewById(R.id.postPoem_lyrics);
        linkedPosts = itemView.findViewById(R.id.postPoem_links);
        comments = itemView.findViewById(R.id.postPoem_comments);
        rate = itemView.findViewById(R.id.postPoem_rate);
        uploaderUsername = itemView.findViewById(R.id.postPoem_uploader);
        tags = itemView.findViewById(R.id.postPoem_tags);

        if (this.isEditAvailable) {
            editButton.setVisibility(View.VISIBLE);
        }

        editButton.setOnClickListener(view -> {
            //TODO: Navigate to edit post
        });

        itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
                AllPostsFragmentDirections.ActionAllPostsFragmentToPostDetailsFragment action = AllPostsFragmentDirections.actionAllPostsFragmentToPostDetailsFragment(currentPost);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    public void bindData(Post post, int position) {
        currentPost = post;
        postTitle.setText(post.getTitle());
        postLyrics.setText(post.getPoemLyrics() != null ? post.getPoemLyrics() + "" : "");
        linkedPosts.setText(post.getLinkedPostsIds() != null ? post.getLinkedPostsIds().size() + "" : "0");
        comments.setText(post.getCommentsIds() != null ? post.getCommentsIds().size() + "" : "");
        rate.setText(String.valueOf(post.getAveragePostRate()));
        uploaderUsername.setText(UserModel.instance.findUserById(post.getUploaderId()).getUsername());
        this.position = position;
    }
}
