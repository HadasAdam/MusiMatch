package com.example.bride2be.models;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.musimatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfileFragment extends Fragment {

    TextView fullNameTV;
    TextView typeTV;
    TextView countryTV;
    TextView cityTV;
    Button logOutBtn;
    Button editBtn;
    Button addNewBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
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
        View view = inflater.inflate(R.layout.user_profile,container, false);
        fullNameTV = view.findViewById(R.id.FullNameTV);
        typeTV = view.findViewById(R.id.TypeTV);
        countryTV = view.findViewById(R.id.CountryTV);
        cityTV = view.findViewById(R.id.CityTV);
        logOutBtn = view.findViewById(R.id.LogOutBtn);
        editBtn = view.findViewById(R.id.EditBtn);
        addNewBtn = view.findViewById(R.id.AddNewBtn);
        logOutBtn.setOnClickListener(v -> logout());
        editBtn.setOnClickListener(v -> editUser());
        addNewBtn.setOnClickListener(v -> addNewPost());
        return view;
    }

    private void editUser() {

    }

    private void logout() {

    }

    private void addNewPost() {

    }
}