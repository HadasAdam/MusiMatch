package com.example.musimatch;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

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
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
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

    private void onClickCancelButton() { // cancel the edit of the profile

    }

    private void onClickSaveButton() { // save changes in user profile

    }
}