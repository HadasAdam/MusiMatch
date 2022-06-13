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

import java.util.ArrayList;

public class WatchLinkedPostsFragment extends Fragment {

    private static final String TAG = "LinkPostFragment";

    private Post destinationPost;
    private View view;
    private Button backButton;
    private Button linkButton;
    private ArrayList<Post> linkedPostsArrayList;

    public WatchLinkedPostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_watch_linked_posts, container, false);
        destinationPost = WatchLinkedPostsFragmentArgs.fromBundle(getArguments()).getDestinationPost();
        linkedPostsArrayList = destinationPost.getLinkedPosts();
        backButton = view.findViewById(R.id.watchLinkedPosts_backBtn);
        linkButton = view.findViewById(R.id.watchLinkedPosts_linkPostBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateBackToPostDetails(destinationPost);
            }
        });
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLinkPostFragment();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.watchLinkedPosts_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutInflater layoutInflater = getLayoutInflater();
        PostAdapter adapter = new PostAdapter(layoutInflater);
        recyclerView.setAdapter(adapter);
        adapter.setData(linkedPostsArrayList);
        adapter.setOnClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Post selectedPost = linkedPostsArrayList.get(position);
                navigateBackToPostDetails(selectedPost);
            }
        });

        return view;
    }

    private void navigateBackToPostDetails(Post postToNavigateTo)
    {
        WatchLinkedPostsFragmentDirections.ActionWatchLinkedPostsFragmentToPostDetailsFragment action =
                WatchLinkedPostsFragmentDirections.actionWatchLinkedPostsFragmentToPostDetailsFragment(postToNavigateTo);
        Navigation.findNavController(view).navigate(action);
    }

    private void navigateToLinkPostFragment()
    {
        WatchLinkedPostsFragmentDirections.ActionWatchLinkedPostsFragmentToLinkPostFragment action =
                WatchLinkedPostsFragmentDirections.actionWatchLinkedPostsFragmentToLinkPostFragment(destinationPost);
        Navigation.findNavController(view).navigate(action);
    }
}