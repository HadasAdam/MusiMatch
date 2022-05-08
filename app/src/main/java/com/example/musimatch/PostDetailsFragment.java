package com.example.musimatch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.musimatch.adapters.CommentAdapter;
import com.example.musimatch.models.Comment;
import com.example.musimatch.models.CommentModel;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.UserModel;
import com.example.musimatch.services.LoginService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;

public class PostDetailsFragment extends Fragment {

    private static final String TAG = "PostDetailsFragment";

    ArrayList<Comment> commentsArrayList = new ArrayList<>();

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
        Post post = PostDetailsFragmentArgs.fromBundle(getArguments()).getPost();
        linkComponents();
        initializeComponent(post);
        initializeComments(post);

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

    private void initializeComponent(Post post)
    {
        postTitle.setText(post.getTitle());
        postLyrics.setText(post.getPoemLyrics() != null ? post.getPoemLyrics() + "" : "");
        linkedPosts.setText(post.getLinkedPostsIds() != null ? post.getLinkedPostsIds().size() + "" : "0");
        comments.setText(post.getCommentsIds() != null ? post.getCommentsIds().size() + "" : "");
        rate.setText(String.valueOf(post.getAveragePostRate()));
        uploaderUsername.setText(UserModel.instance.findUserById(post.getUploaderId()).getUsername());

        linkedPosts.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_postDetailsFragment_to_linkPostFragment);
        });

        rate.setOnClickListener(v -> {
            PostDetailsFragmentDirections.ActionPostDetailsFragmentToRatePostFragment action =
                    PostDetailsFragmentDirections.actionPostDetailsFragmentToRatePostFragment(post);
            Navigation.findNavController(view).navigate(action);
        });
    }

    private void initializeComments(Post post) {
        commentsArrayList = new ArrayList<Comment>(CommentModel.instance.getCommentsByPostId(post.getId()));
        RecyclerView recyclerView = view.findViewById(R.id.PostDetails_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutInflater layoutInflater = getLayoutInflater();
        CommentAdapter adapter = new CommentAdapter(layoutInflater);
        recyclerView.setAdapter(adapter);
        adapter.setData(commentsArrayList);
    }
}