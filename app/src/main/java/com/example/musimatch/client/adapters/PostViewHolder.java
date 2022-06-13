package com.example.musimatch.client.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musimatch.R;
import com.example.musimatch.client.AllPostsFragmentDirections;
import com.example.musimatch.models.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public PostAdapter.OnItemClickListener onItemClickListener;
    Post currentPost;
    int position;
    TextView postTitle;
    TextView postLyrics;
    TextView linkedPosts;
    TextView comments;
    TextView rate;
    TextView uploaderUsername;
    TextView tags;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        postTitle = itemView.findViewById(R.id.postPoem_title);
        postLyrics = itemView.findViewById(R.id.postPoem_lyrics);
        linkedPosts = itemView.findViewById(R.id.postPoem_links);
        comments = itemView.findViewById(R.id.postPoem_comments);
        rate = itemView.findViewById(R.id.postPoem_rate);
        uploaderUsername = itemView.findViewById(R.id.postPoem_uploader);
        tags = itemView.findViewById(R.id.postPoem_tags);

        itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    public void bindData(Post post, int position) {
        currentPost = post;
        postTitle.setText(currentPost.getTitle());
        postLyrics.setText(currentPost.getPoemLyrics() != null ? currentPost.getPoemLyrics() + "" : "");
        linkedPosts.setText(currentPost.getLinkedPosts() != null ? currentPost.getLinkedPosts().size() + "" : "0");
        comments.setText(currentPost.getComments() != null ? currentPost.getComments().size() + "" : "");
        tags.setText(getTagsString(currentPost));
        rate.setText(String.valueOf(currentPost.getAveragePostRate()));
        uploaderUsername.setText(currentPost.getCreator().getUsername());
        this.position = position;
    }

    private String getTagsString(Post post)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < post.getTags().size(); i++)
        {
            stringBuilder.append(" #").append(post.getTags().get(i).getName());
        }
        return stringBuilder.toString();
    }
}
