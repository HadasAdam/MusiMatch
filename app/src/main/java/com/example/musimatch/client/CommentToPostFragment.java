package com.example.musimatch.client;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.musimatch.R;
import com.example.musimatch.models.Comment;
import com.example.musimatch.models.Post;
import com.example.musimatch.services.LoginService;

import java.util.Date;

public class CommentToPostFragment extends Fragment {

    View view;
    Post postToCommentOn;
    Button SaveCommentBtn;
    Button CancelCommentBtn;
    EditText commentContent;

    public CommentToPostFragment() {
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
        view = inflater.inflate(R.layout.fragment_comment_to_post, container, false);
        postToCommentOn = CommentToPostFragmentArgs.fromBundle(getArguments()).getPostToCommentOn();
        SaveCommentBtn = view.findViewById(R.id.SaveCommentBtn);
        CancelCommentBtn = view.findViewById(R.id.CancelCommentBtn);
        commentContent = view.findViewById(R.id.commentfragment_content);
        SaveCommentBtn.setOnClickListener(v -> onClickSaveButton());
        CancelCommentBtn.setOnClickListener(v -> onClickCancelButton());
        return view;
    }

    public void onClickCancelButton() {
        navigateToPostDetails();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickSaveButton() {
        Comment comment = new Comment((postToCommentOn.getId()*23), LoginService.getUser(),
                postToCommentOn, commentContent.getText().toString(), new Date());
        postToCommentOn.addComment(comment);
        navigateToPostDetails();
    }

    private void navigateToPostDetails() {
        CommentToPostFragmentDirections.ActionCommentToPostFragmentToPostDetailsFragment action =
                CommentToPostFragmentDirections.actionCommentToPostFragmentToPostDetailsFragment(postToCommentOn);
        Navigation.findNavController(view).navigate(action);
    }
}