package com.example.musimatch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musimatch.adapters.PostAdapter;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.PostModel;

import java.util.ArrayList;

public class LinkPostFragment extends Fragment {

    private View view;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_link_post, container, false);

        //TODO: put real loggedInUser id and use method findPostsByUserIdAndPostType(...)
        postsArrayList = new ArrayList<Post>(PostModel.instance.findPostsByUserId(String.valueOf(1)));

        RecyclerView recyclerView = view.findViewById(R.id.linkPost_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutInflater layoutInflater = getLayoutInflater();
        PostAdapter adapter = new PostAdapter(layoutInflater);
        recyclerView.setAdapter(adapter);
        adapter.setData(postsArrayList);

        return view;
    }

    private void linkPostToPost(Post myPost, Post destinationPost) {
        if(!myPost.getId().equals(destinationPost.getId()))
        {
            myPost.getLinkedPostsIds().add(destinationPost.getId());
            destinationPost.getLinkedPostsIds().add(myPost.getId());
        }
    }
}