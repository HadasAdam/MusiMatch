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
    private MenuItem signOutButton;
    private MenuItem signInButton;
    private MenuItem loggedInUsername;

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

    /************************************ Login Methods ************************************/

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.user_top_menu, menu);
        loggedInUsername = menu.findItem(R.id.logged_in_display_name);
        signInButton = menu.findItem(R.id.google_sign_in_button);
        signOutButton = menu.findItem(R.id.google_sign_out_button);

        if (LoginService.getInstance(this.getContext()).getGoogleAccount() != null && !LoginService.getInstance(this.getContext()).isLoggedIn()) {
            LoginService.getInstance(this.getContext()).signOut();
        }

        setSignedInView(LoginService.getInstance(this.getContext()).getUserDisplayName(),
                LoginService.getInstance(this.getContext()).isLoggedIn());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.google_sign_in_button:
                signIn();
                return true;
            case R.id.google_sign_out_button:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LoginService.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                LoginService.getInstance(this.getContext()).setGoogleAccount(account);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getDisplayName());
                Task<AuthResult> authResultTask = LoginService.getInstance(this.getContext()).firebaseAuthWithGoogle(account.getIdToken());

                authResultTask.addOnCompleteListener(this.getActivity(), firebaseLoginRes -> {
                    if (firebaseLoginRes.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        setSignedInView(LoginService.getInstance(this.getContext()).getGoogleAccount().getDisplayName(), true);
                        LoginService.getInstance(this.getContext()).createNewUser(authResultTask);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", firebaseLoginRes.getException());
                    }
                });

                setSignedInView(account.getDisplayName(), true);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.w(TAG, "Null pointer exception while login trial", e);
                e.printStackTrace();
            }
        }
    }

    private void setSignedInView(String displayName, boolean isLoggedIn) {
        this.loggedInUsername.setTitle(displayName);
        this.signInButton.setVisible(!isLoggedIn);
        this.signOutButton.setVisible(isLoggedIn);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void signIn() {
        Log.d(TAG,"Trying to sign in...");
        Intent signInIntent = LoginService.getInstance(this.getContext()).getGoogleSignInClient().getSignInIntent();
        startActivityForResult(signInIntent, LoginService.RC_SIGN_IN);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void signOut() {
        Log.d(TAG,"Trying to sign out...");
        LoginService.getInstance(this.getContext()).signOut();
        setSignedInView(this.getString(R.string.default_sign_in_name_display), false);
    }
}