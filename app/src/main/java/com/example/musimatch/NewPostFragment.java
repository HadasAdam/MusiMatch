package com.example.musimatch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.musimatch.services.LoginService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPostFragment extends Fragment {

    private static final String TAG = "NewPostFragment";

    private MenuItem signOutButton;
    private MenuItem signInButton;
    private MenuItem loggedInUsername;

    EditText titleET;
    Spinner postTypeSpinner;
    EditText lyricsET;
    Spinner firstTag;
    Spinner secondTag;
    Spinner thirdTag;
    Button saveBtn;
    Button cancelBtn;
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewPostFragment newInstance(String param1, String param2) {
        NewPostFragment fragment = new NewPostFragment();
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
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);
        titleET = view.findViewById(R.id.newPostTitle);
        postTypeSpinner = view.findViewById(R.id.newPostPostTypeSpinner);
        lyricsET = view.findViewById(R.id.newPostLyricsMultiLine);
        firstTag = view.findViewById(R.id.newPostTags1);
        secondTag = view.findViewById(R.id.newPostTags2);
        thirdTag = view.findViewById(R.id.newPostTags3);
        saveBtn = view.findViewById(R.id.newPostSaveButton);
        cancelBtn = view.findViewById(R.id.newPostCancelButton);
        progressBar = view.findViewById(R.id.progressBar);
        saveBtn.setOnClickListener(v -> onClickSaveButton());
        cancelBtn.setOnClickListener(v -> onClickCancelButton());
        return view;
    }

    private void onClickSaveButton() { // save new post
        progressBar.setVisibility(View.VISIBLE);
    }

    private void onClickCancelButton() { // cancel new post

    }

    /************************************ Login Methods ************************************/

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

    private void signIn() {
        Log.d(TAG,"Trying to sign in...");
        Intent signInIntent = LoginService.getInstance(this.getContext()).getGoogleSignInClient().getSignInIntent();
        startActivityForResult(signInIntent, LoginService.RC_SIGN_IN);
    }

    private void signOut() {
        Log.d(TAG,"Trying to sign out...");
        LoginService.getInstance(this.getContext()).signOut();
        setSignedInView(this.getString(R.string.default_sign_in_name_display), false);
    }
}