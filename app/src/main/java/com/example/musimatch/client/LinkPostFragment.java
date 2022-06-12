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

import com.example.musimatch.R;
import com.example.musimatch.client.adapters.PostAdapter;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.PostModel;
import com.example.musimatch.services.LoginService;


import java.util.ArrayList;

public class LinkPostFragment extends Fragment {

    private static final String TAG = "LinkPostFragment";

    private Post destinationPost;
    private View view;
    private Button backButton;
    private ArrayList<Post> postsArrayList;

    public LinkPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_link_post, container, false);
        postsArrayList = new ArrayList<Post>(PostModel.instance.findPostsByUser(LoginService.getUser()));
        destinationPost = LinkPostFragmentArgs.fromBundle(getArguments()).getDestinationPost();
        backButton = view.findViewById(R.id.linkPost_backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToBackPostDetails();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.linkPost_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutInflater layoutInflater = getLayoutInflater();
        PostAdapter adapter = new PostAdapter(layoutInflater);
        recyclerView.setAdapter(adapter);
        adapter.setData(postsArrayList);
        adapter.setOnClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Post myPost = postsArrayList.get(position);
                linkPostToPost(myPost, destinationPost);
                navigateToBackPostDetails();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void linkPostToPost(Post myPost, Post destinationPost) {
        if(!myPost.equals(destinationPost))
        {
            PostModel.instance.linkPosts(myPost, destinationPost);
        }
    }

    private void navigateToBackPostDetails()
    {
        try {
            LinkPostFragmentDirections.ActionLinkPostFragmentToPostDetailsFragment action = LinkPostFragmentDirections.actionLinkPostFragmentToPostDetailsFragment(destinationPost);
            Navigation.findNavController(view).navigate(action);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}