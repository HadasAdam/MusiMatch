package com.example.musimatch.client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.musimatch.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    private View view;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText phoneNumber;
    Spinner country;
    Button cancelBtn;
    Button saveBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
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
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        firstName = view.findViewById(R.id.editProfileFirstName);
        lastName = view.findViewById(R.id.editProfileLastName);
        email = view.findViewById(R.id.editProfileEmail);
        phoneNumber = view.findViewById(R.id.editProfilePhoneNumber);
        country = view.findViewById(R.id.editProfileSpinner);
        cancelBtn = view.findViewById(R.id.editProfileCancelBtn);
        saveBtn = view.findViewById(R.id.editProfileSaveBtn);
        saveBtn.setOnClickListener(v -> onClickSaveButton());
        cancelBtn.setOnClickListener(v -> onClickCancelButton());
        return view;
    }

    private AlertDialog.Builder getAlertDialogBuilder() {
        Context context = view.getContext();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set dialog message
        alertDialogBuilder
                .setMessage("Click ok to exit and try again")
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

        Log.d("Log", "cancel clicked");
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        alertDialogBuilder.setTitle("profile edit canceled");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void onClickSaveButton() { // save changes in user profile

        Log.d("Log", "save clicked");

        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        alertDialogBuilder.setTitle("profile edit success");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}