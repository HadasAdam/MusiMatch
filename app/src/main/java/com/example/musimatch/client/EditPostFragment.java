package com.example.musimatch.client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.musimatch.R;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.PostModel;
import com.example.musimatch.models.Tag;
import com.example.musimatch.models.TagModel;
import com.example.musimatch.services.LoginService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditPostFragment extends Fragment {

    private static final String TAG = "EditPostFragment";

    private View view;
    Post postToEdit;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_post, container, false);
        postToEdit = EditPostFragmentArgs.fromBundle(getArguments()).getPostToEdit();
        if(doesPostBelongToLoggedInUser())
        {
            linkComponents();
            initializeComponents();
        }
        else
        {
            //TODO: YOU CAN'T EDIT THIS POST BECAUSE IT DOESN'T BELONG TO YOU!
            navigateToPostDetails();
        }
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean doesPostBelongToLoggedInUser()
    {
        return postToEdit.getCreator().equals(LoginService.getUser());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void linkComponents()
    {
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
    }

    private void initializeComponents()
    {
        titleET.setText(postToEdit.getTitle());
        postTypeSpinner.setSelection(postToEdit.getPostType().ordinal());
        postTypeSpinner.setEnabled(false);
        if(postToEdit.getPoemLyrics() != null)
        {
            lyricsET.setText(postToEdit.getPoemLyrics());
        }
        initializeTagSpinner(firstTag, 0);
        initializeTagSpinner(secondTag, 1);
        initializeTagSpinner(thirdTag, 2);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClickSaveButton() {
        updatePost();
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        alertDialogBuilder.setTitle("The post has been successfully updated");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        navigateToPostDetails();
    }

    private void onClickCancelButton() {
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        alertDialogBuilder.setTitle("Post update canceled");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        navigateToPostDetails();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClickDeleteButton() {
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        alertDialogBuilder.setTitle("The post has been successfully deleted");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        PostModel.instance.removePost(postToEdit);
        navigateToAllPostsFragment();
    }

    private void navigateToPostDetails()
    {
        EditPostFragmentDirections.ActionEditPostFragmentToPostDetailsFragment action = EditPostFragmentDirections.actionEditPostFragmentToPostDetailsFragment(postToEdit);
        Navigation.findNavController(view).navigate(action);
    }

    private void navigateToAllPostsFragment()
    {
        Navigation.findNavController(view).navigate(R.id.action_editPostFragment_to_allPostsFragment);
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

        getRightTagAsSelected(spinner, dataAdapter, index);
    }

    private void getRightTagAsSelected(Spinner spinner, ArrayAdapter dataAdapter, int tagIndex)
    {
        if(tagIndex < postToEdit.getTags().size())
        {
            for (int i = 0; i < dataAdapter.getCount(); i++) {
                if (spinner.getItemAtPosition(i).equals(postToEdit.getTags().get(tagIndex).getName())) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updatePost()
    {
        postToEdit.setTitle(titleET.getText().toString());
        if(postToEdit.getPoemLyrics() != null) {
            postToEdit.setPoemLyrics(lyricsET.getText().toString());
        }
        else {
            // TODO: set file path!
        }
        ArrayList<Tag> newSelectedTags = new ArrayList<>();
        if(!firstTag.getSelectedItem().toString().isEmpty()) {
            newSelectedTags.add(TagModel.instance.findTagByName(firstTag.getSelectedItem().toString()));
        }
        if(!secondTag.getSelectedItem().toString().isEmpty()) {
            newSelectedTags.add(TagModel.instance.findTagByName(secondTag.getSelectedItem().toString()));
        }
        if(!thirdTag.getSelectedItem().toString().isEmpty()) {
            newSelectedTags.add(TagModel.instance.findTagByName(thirdTag.getSelectedItem().toString()));
        }
        postToEdit.setTags(newSelectedTags);
        PostModel.instance.updatePost(postToEdit);
    }

    private AlertDialog.Builder getAlertDialogBuilder() {
        Context context = view.getContext();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set dialog message
        alertDialogBuilder
                .setMessage("Click ok to exit")
                .setCancelable(false)
                .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
        return alertDialogBuilder;
    }
}