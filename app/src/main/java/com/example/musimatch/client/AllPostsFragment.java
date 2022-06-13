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


public class AllPostsFragment extends Fragment {

    Button profileButton;

    public AllPostsFragment(){}

    private static final String TAG = "AllPostsFragment";
    private View view;
    private ArrayList<Post> postsArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_posts, container, false);
        postsArrayList = new ArrayList<Post>(PostModel.instance.getAllPosts());
        profileButton = view.findViewById(R.id.allPosts_profileBtn);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllPostsFragmentDirections.ActionAllPostsFragmentToUserProfileFragment4 action =
                        AllPostsFragmentDirections.actionAllPostsFragmentToUserProfileFragment4(LoginService.getUser());
                Navigation.findNavController(view).navigate(action);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.allPostsFRG_recyclerView);
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
                Post currentPost = postsArrayList.get(position);
                AllPostsFragmentDirections.ActionAllPostsFragmentToPostDetailsFragment action = AllPostsFragmentDirections.actionAllPostsFragmentToPostDetailsFragment(currentPost);
                Navigation.findNavController(view).navigate(action);
            }
        });

        return view;
    }

}