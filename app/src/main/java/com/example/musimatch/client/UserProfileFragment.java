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
import com.example.musimatch.client.adapters.PostAdapter;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.PostModel;
import com.example.musimatch.models.User;
import com.example.musimatch.services.LoginService;

import java.util.ArrayList;

public class UserProfileFragment extends Fragment {

    private static final String TAG = "UserProfileFragment";

    User userWhoOwnsTheProfile;
    private ArrayList<Post> postsByUser;
    View view;
    TextView fullNameTV;
    TextView typeTV;
    TextView usernameTV;
    TextView emailTV;
    Button logOutBtn;
    Button editBtn;
    Button addNewBtn;


    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_profile,container, false);
        userWhoOwnsTheProfile = UserProfileFragmentArgs.fromBundle(getArguments()).getProfileOwnerUser();
        linkComponents();
        initializeComponents();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void linkComponents()
    {
        fullNameTV = view.findViewById(R.id.FullNameTV);
        typeTV = view.findViewById(R.id.TypeTV);
        usernameTV = view.findViewById(R.id.userProfile_username);
        logOutBtn = view.findViewById(R.id.LogOutBtn);
        editBtn = view.findViewById(R.id.userProfile_EditBtn);
        addNewBtn = view.findViewById(R.id.userProfile_AddNewBtn);
        emailTV = view.findViewById(R.id.userProfile_email);
        logOutBtn.setOnClickListener(v -> logout());
        editBtn.setOnClickListener(v -> editUser());
        if(LoginService.getUser() == null || !LoginService.getUser().equals(userWhoOwnsTheProfile))
        {
            editBtn.setVisibility(View.INVISIBLE);
            logOutBtn.setVisibility(View.INVISIBLE);
            addNewBtn.setVisibility(View.INVISIBLE);
        }
        addNewBtn.setOnClickListener(v -> addNewPost());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeComponents()
    {
        String fullName = userWhoOwnsTheProfile.getFirstName() + " " + userWhoOwnsTheProfile.getLastName();
        fullNameTV.setText(fullName);
        typeTV.setText(userWhoOwnsTheProfile.getUserType().name());
        usernameTV.setText(userWhoOwnsTheProfile.getUsername());
        emailTV.setText(userWhoOwnsTheProfile.getEmail());
        postsByUser = PostModel.instance.findPostsByUser(userWhoOwnsTheProfile);

        RecyclerView recyclerView = view.findViewById(R.id.userProfile_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutInflater layoutInflater = getLayoutInflater();
        PostAdapter adapter = new PostAdapter(layoutInflater);
        recyclerView.setAdapter(adapter);
        adapter.setData(postsByUser);
        adapter.setOnClickListener(new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Post selectedPost = postsByUser.get(position);
                navigateToPostDetails(selectedPost);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void editUser() {
        if(LoginService.getUser() != null)
        {
            UserProfileFragmentDirections.ActionUserProfileFragment4ToEditProfileFragment action =
                    UserProfileFragmentDirections.actionUserProfileFragment4ToEditProfileFragment(LoginService.getUser());
            Navigation.findNavController(view).navigate(action);
        }
    }

    private void logout() {
        //LoginService.getInstance().signOut();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addNewPost() {
        if(LoginService.getUser() != null)
        {
            UserProfileFragmentDirections.ActionUserProfileFragment4ToNewPostFragment action =
                    UserProfileFragmentDirections.actionUserProfileFragment4ToNewPostFragment(LoginService.getUser());
            Navigation.findNavController(view).navigate(action);
        }
    }

    private void navigateToPostDetails(Post post)
    {
        UserProfileFragmentDirections.ActionUserProfileFragment4ToPostDetailsFragment action =
                UserProfileFragmentDirections.actionUserProfileFragment4ToPostDetailsFragment(post);
        Navigation.findNavController(view).navigate(action);
    }
}