package com.example.musimatch;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditPostFragment extends Fragment {

    private static final String TAG = "EditPostFragment";

    EditText titleET;
    Spinner postTypeSpinner;
    EditText lyricsET;
    Spinner firstTag;
    Spinner secondTag;
    Spinner thirdTag;
    Button saveBtn;
    Button cancelBtn;
    Button deleteBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditPostFragment newInstance(String param1, String param2) {
        EditPostFragment fragment = new EditPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_post, container, false);
        titleET = view.findViewById(R.id.editPostTitle);
        postTypeSpinner = view.findViewById(R.id.editPostPostTypeSpinner);
        lyricsET = view.findViewById(R.id.editPostLyricsMultiLine);
        firstTag = view.findViewById(R.id.editPostTags1);
        secondTag = view.findViewById(R.id.editPostTags2);
        thirdTag = view.findViewById(R.id.editPostTags3);
        saveBtn = view.findViewById(R.id.editPostSaveButton);
        cancelBtn = view.findViewById(R.id.editPostCancelButton);
        deleteBtn = view.findViewById(R.id.editPostDeleteButton);
        saveBtn.setOnClickListener(v -> onClickSaveButton());
        cancelBtn.setOnClickListener(v -> onClickCancelButton());
        deleteBtn.setOnClickListener(v -> onClickDeleteButton());
        return view;
    }

    private void onClickSaveButton() { // save the edit of the post

    }

    private void onClickCancelButton() { // cancel the edit of the post

    }

    private void onClickDeleteButton() { // delete the post

    }
}