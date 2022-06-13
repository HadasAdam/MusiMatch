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
import com.example.musimatch.models.User;
import com.example.musimatch.models.UserModel;
import com.example.musimatch.models.enums.UserType;

public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    User userToEdit;
    View view;
    EditText firstName;
    EditText lastName;
    EditText email;
    Spinner userType;
    Button cancelBtn;
    Button saveBtn;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        userToEdit = EditProfileFragmentArgs.fromBundle(getArguments()).getUserToEdit();
        firstName = view.findViewById(R.id.editProfileFirstName);
        lastName = view.findViewById(R.id.editProfileLastName);
        email = view.findViewById(R.id.editProfileEmail);

        firstName.setText(userToEdit.getFirstName());
        lastName.setText(userToEdit.getLastName());
        email.setText(userToEdit.getEmail());

        userType = view.findViewById(R.id.editProfileSpinner);
        cancelBtn = view.findViewById(R.id.editProfileCancelBtn);
        saveBtn = view.findViewById(R.id.editProfileSaveBtn);
        saveBtn.setOnClickListener(v -> onClickSaveButton());
        cancelBtn.setOnClickListener(v -> onClickCancelButton());
        initializeTypeSpinner(userType);
        return view;
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

    private void onClickCancelButton() { // cancel the edit of the profile
        Log.d(TAG, "click cancel");
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        alertDialogBuilder.setTitle("profile edit canceled");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        navigateToUserProfile(userToEdit);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClickSaveButton() { // save changes in user profile

        Log.d(TAG, "click save");
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        alertDialogBuilder.setTitle("profile edit success");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        User editedUser = new User(userToEdit.getId(), userToEdit.getUsername(), email.getText().toString(), userToEdit.isAdmin(),
                userToEdit.getCreationDate(), UserType.values()[userType.getSelectedItemPosition()], firstName.getText().toString(), lastName.getText().toString(),
                userToEdit.getAverageRate(), userToEdit.getSpotifyUrl());
        UserModel.instance.updateUser(editedUser);
        navigateToUserProfile(editedUser);
    }

    private void navigateToUserProfile(User user)
    {
        EditProfileFragmentDirections.ActionEditProfileFragmentToUserProfileFragment4 action =
                EditProfileFragmentDirections.actionEditProfileFragmentToUserProfileFragment4(user);
        Navigation.findNavController(view).navigate(action);
    }

    private void initializeTypeSpinner(Spinner spinner)
    {
        String[] userTypes = new String[UserType.values().length];
        for(int i = 0; i < UserType.values().length; i++)
        {
            userTypes[i] = UserType.values()[i].name();
        }

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, userTypes);

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

        spinner.setSelection(userToEdit.getUserType().ordinal());
    }
}