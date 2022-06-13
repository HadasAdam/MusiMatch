package com.example.musimatch.client;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.musimatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentToPost#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CommentToPost extends Fragment {

    Button SaveCommentBtn;
    Button CancelCommentBtn;
    EditText textViewComment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommentToPost.
     */
    // TODO: Rename and change types and number of parameters
    public static CommentToPost newInstance(String param1, String param2) {
        CommentToPost fragment = new CommentToPost();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CommentToPost() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_to_post, container, false);
        SaveCommentBtn = view.findViewById(R.id.SaveCommentBtn);
        CancelCommentBtn = view.findViewById(R.id.CancelCommentBtn);
        SaveCommentBtn.setOnClickListener(v -> onClickSaveButton());
        CancelCommentBtn.setOnClickListener(v -> onClickCancelButton());
        return view;
    }

    public Button onClickCancelButton() {
        return CancelCommentBtn;
    }

    public Button onClickSaveButton() {
        return SaveCommentBtn;
    }
}