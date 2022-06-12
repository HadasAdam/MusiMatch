package com.example.musimatch.client;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musimatch.R;
import com.example.musimatch.client.adapters.CommentAdapter;
import com.example.musimatch.models.Comment;
import com.example.musimatch.models.Post;

import java.util.ArrayList;

public class PostDetailsFragment extends Fragment {

    private static final String TAG = "PostDetailsFragment";

    ArrayList<Comment> commentsArrayList = new ArrayList<>();
    Post post;

    View view;
    TextView postTitle;
    TextView postLyrics;
    TextView linkedPosts;
    TextView comments;
    TextView rate;
    TextView uploaderUsername;
    TextView tags;

    TextView rate1star;
    TextView rate1title;
    TextView rate2star;
    TextView rate2title;
    TextView rate3star;
    TextView rate3title;

    public PostDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post_details,container, false);
        post = PostDetailsFragmentArgs.fromBundle(getArguments()).getPost();
        linkComponents();
        initializeComponent();
        initializeComments();

        return view;
    }

    private void linkComponents()
    {
        postTitle = view.findViewById(R.id.postDetails_title);
        postLyrics = view.findViewById(R.id.postDetails_content);
        linkedPosts = view.findViewById(R.id.postDetails_linkedPosts);
        comments = view.findViewById(R.id.postDetails_comments);
        rate = view.findViewById(R.id.postDetails_rate);
        uploaderUsername = view.findViewById(R.id.postDetails_uploader);
        tags = view.findViewById(R.id.postDetails_tags);
        rate1star = view.findViewById(R.id.postDetails_rate1star);
        rate1title = view.findViewById(R.id.postDetails_rate1title);
        rate2star = view.findViewById(R.id.postDetails_rate2star);
        rate2title = view.findViewById(R.id.postDetails_rate2title);
        rate3star = view.findViewById(R.id.postDetails_rate3star);
        rate3title = view.findViewById(R.id.postDetails_rate3title);
    }

    private void initializeComponent()
    {
        postTitle.setText(post.getTitle());
        postLyrics.setText(post.getPoemLyrics() != null ? post.getPoemLyrics() + "" : "");
        linkedPosts.setText(post.getLinkedPosts() != null ? post.getLinkedPosts().size() + "" : "0");
        comments.setText(post.getComments() != null ? post.getComments().size() + "" : "");
        rate.setText(String.valueOf(post.getAveragePostRate()));
        uploaderUsername.setText(post.getCreator().getUsername());

        linkedPosts.setOnClickListener(v -> {
            PostDetailsFragmentDirections.ActionPostDetailsFragmentToLinkPostFragment action = PostDetailsFragmentDirections.actionPostDetailsFragmentToLinkPostFragment(post);
            Navigation.findNavController(view).navigate(action);
        });

        rate.setOnClickListener(v -> {
            PostDetailsFragmentDirections.ActionPostDetailsFragmentToRatePostFragment action =
                    PostDetailsFragmentDirections.actionPostDetailsFragmentToRatePostFragment(post);
            Navigation.findNavController(view).navigate(action);
        });
    }

    private void initializeComments() {
        commentsArrayList = post.getComments();
        RecyclerView recyclerView = view.findViewById(R.id.PostDetails_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutInflater layoutInflater = getLayoutInflater();

        // TODO: find a way to initialize the comments properly
        CommentAdapter adapter = new CommentAdapter(layoutInflater);
        adapter.setData(commentsArrayList);
        recyclerView.setAdapter(adapter);
    }
}