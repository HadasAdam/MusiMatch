package com.example.musimatch.client;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.musimatch.R;
import com.example.musimatch.client.adapters.CommentAdapter;
import com.example.musimatch.models.Comment;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.enums.MelodyRateSections;
import com.example.musimatch.models.enums.PoemRateSections;
import com.example.musimatch.services.LoginService;

import java.util.ArrayList;

public class PostDetailsFragment extends Fragment {

    private static final String TAG = "PostDetailsFragment";

    ArrayList<Comment> commentsArrayList = new ArrayList<>();
    Post post;

    View view;
    Button editButton;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post_details,container, false);
        post = PostDetailsFragmentArgs.fromBundle(getArguments()).getPost();
        linkComponents();
        initializeComponent();
        initializeComments();
        initializeRaters();

        return view;
    }

    private void linkComponents()
    {
        editButton = view.findViewById(R.id.postDetails_editButton);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeComponent()
    {
        postTitle.setText(post.getTitle());
        postLyrics.setText(post.getPoemLyrics() != null ? post.getPoemLyrics() + "" : "");
        linkedPosts.setText(post.getLinkedPosts() != null ? post.getLinkedPosts().size() + "" : "0");
        comments.setText(post.getComments() != null ? post.getComments().size() + "" : "");
        rate.setText(String.valueOf(post.getAveragePostRate()));
        uploaderUsername.setText(post.getCreator().getUsername());

        if(!post.getCreator().equals(LoginService.getUser()))
        {
            editButton.setVisibility(View.INVISIBLE);
        }
        else {
            editButton.setOnClickListener(v -> {
                PostDetailsFragmentDirections.ActionPostDetailsFragmentToEditPostFragment action =
                        PostDetailsFragmentDirections.actionPostDetailsFragmentToEditPostFragment(post);
                Navigation.findNavController(view).navigate(action);
            });
        }

        linkedPosts.setOnClickListener(v -> {
            PostDetailsFragmentDirections.ActionPostDetailsFragmentToWatchLinkedPostsFragment action =
                    PostDetailsFragmentDirections.actionPostDetailsFragmentToWatchLinkedPostsFragment(post);
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

    private void initializeRaters() {
        switch (post.getPostType())
        {
            case POEM:
                rate1title.setText(PoemRateSections.DEPT.name());
                rate2title.setText(PoemRateSections.RHYMES.name());
                rate3title.setText(PoemRateSections.LANGUAGE.name());
                break;
            case MELODY:
                rate1title.setText(MelodyRateSections.RHYTHM.name());
                rate2title.setText(MelodyRateSections.QUALITY.name());
                rate3title.setText(MelodyRateSections.UNIQUENESS.name());
        }

        rate1star.setText(String.valueOf(post.getAverageRater().getRaterSection1()));
        rate2star.setText(String.valueOf(post.getAverageRater().getRaterSection2()));
        rate3star.setText(String.valueOf(post.getAverageRater().getRaterSection3()));
    }
}