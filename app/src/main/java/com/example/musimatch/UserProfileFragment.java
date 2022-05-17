package com.example.musimatch;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class UserProfileFragment extends Fragment {

    private static final String TAG = "UserProfileFragment";

    View view;
    TextView fullNameTV;
    TextView typeTV;
    TextView countryTV;
    TextView cityTV;
    Button logOutBtn;
    Button editBtn;
    Button addNewBtn;


    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_profile,container, false);
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