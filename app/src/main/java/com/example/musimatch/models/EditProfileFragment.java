package com.example.musimatch.models;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.musimatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    EditText firstNameEditProfile;
    EditText lastNameEditProfile;
    EditText emailEditProfile;
    EditText phoneNumberEditProfile;
    EditText passwordEditProfile;
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
        View view = inflater.inflate(R.layout.edit_profile, container, false);
        firstNameEditProfile = view.findViewById(R.id.FirstNameEditProfile);
        lastNameEditProfile = view.findViewById(R.id.LastNameEditProfile);
        emailEditProfile = view.findViewById(R.id.EmailEditProfile);
        phoneNumberEditProfile = view.findViewById(R.id.PhoneNumberEditProfile);
        passwordEditProfile = view.findViewById(R.id.PasswordEditProfile);
        cancelBtn = view.findViewById(R.id.CancelEditProfileBtn);
        saveBtn = view.findViewById(R.id.SaveEditProfileBtn);

        cancelBtn.setOnClickListener(v -> cancelEditProfile());
        saveBtn.setOnClickListener(v -> saveProfileChanges(firstNameEditProfile, lastNameEditProfile,
                emailEditProfile, phoneNumberEditProfile, passwordEditProfile));

        return view;
    }

    private void saveProfileChanges(EditText firstname, EditText lastname, EditText email, EditText phonenumber, EditText password) {
        // todo: check in database if changed
        String FirstName = firstname.getText().toString();
        String LastName = lastname.getText().toString();
        String Email = email.getText().toString();
        String PhoneNumber = phonenumber.getText().toString();
        String Password = password.getText().toString();
    }

    private void cancelEditProfile() {
        // return to last screen
    }
}