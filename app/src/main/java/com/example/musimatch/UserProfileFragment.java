package com.example.musimatch;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.musimatch.services.LoginService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


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