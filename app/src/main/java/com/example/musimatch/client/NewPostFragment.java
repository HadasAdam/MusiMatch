package com.example.musimatch.client;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.musimatch.R;
import com.example.musimatch.models.AverageRater;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.PostModel;
import com.example.musimatch.models.TagModel;
import com.example.musimatch.models.User;
import com.example.musimatch.models.UserModel;
import com.example.musimatch.models.enums.PostType;
import com.example.musimatch.services.LoginService;

public class NewPostFragment extends Fragment {

    private static final String TAG = "NewPostFragment";

    View view;
    EditText titleET;
    Spinner postTypeSpinner;
    EditText lyricsET;
    Spinner firstTag;
    Spinner secondTag;
    Spinner thirdTag;
    Button saveBtn;
    Button cancelBtn;


    public NewPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_post, container, false);
        titleET = view.findViewById(R.id.newPostTitle);
        postTypeSpinner = view.findViewById(R.id.newPostPostTypeSpinner);
        lyricsET = view.findViewById(R.id.newPostLyricsMultiLine);
        firstTag = view.findViewById(R.id.newPostTags1);
        secondTag = view.findViewById(R.id.newPostTags2);
        thirdTag = view.findViewById(R.id.newPostTags3);
        saveBtn = view.findViewById(R.id.newPostSaveButton);
        cancelBtn = view.findViewById(R.id.newPostCancelButton);
        saveBtn.setOnClickListener(v -> onClickSaveButton());
        cancelBtn.setOnClickListener(v -> onClickCancelButton());
        initializeTagSpinner(firstTag, 0);
        initializeTagSpinner(secondTag, 1);
        initializeTagSpinner(thirdTag, 2);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClickSaveButton() {
        PostModel.instance.createPost(new Post(321L, titleET.getText().toString(),
                lyricsET.getText().toString(), null, LoginService.getUser(),
                PostType.values()[postTypeSpinner.getSelectedItemPosition()]));
        navigateToUserProfile();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClickCancelButton() {
        navigateToUserProfile();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void navigateToUserProfile()
    {
        User user = LoginService.getUser();
        NewPostFragmentDirections.ActionNewPostFragmentToUserProfileFragment4 action =
                NewPostFragmentDirections.actionNewPostFragmentToUserProfileFragment4(user);
        Navigation.findNavController(view).navigate(action);
    }

    private void initializeTagSpinner(Spinner spinner, int index)
    {
        String[] tags = new String[TagModel.instance.getAllTags().size() + 1];
        tags[0] = "";
        for(int i = 1; i <= TagModel.instance.getAllTags().size(); i++)
        {
            tags[i] = TagModel.instance.getAllTags().get(i-1).getName();
        }

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, tags);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}