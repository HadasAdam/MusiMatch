package com.example.musimatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.musimatch.models.Tag;
import com.example.musimatch.models.User;
import com.example.musimatch.server.ejb.TagServiceEJB;
import com.example.musimatch.server.ejb.UserServiceEJB;
import com.example.musimatch.services.ConnectionService;
import com.example.musimatch.services.LoginService;
import com.example.musimatch.services.MusimatchServices;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private MenuItem signOutButton;
    private MenuItem signInButton;
    private MenuItem loggedInUsername;
    public Button MyProfileBtn;
    private Connection connect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_top_menu, menu);
        loggedInUsername = menu.findItem(R.id.logged_in_display_name);
        signInButton = menu.findItem(R.id.google_sign_in_button);
        signOutButton = menu.findItem(R.id.google_sign_out_button);

        if (LoginService.getInstance(this).getGoogleAccount() != null && !LoginService.getInstance(this).isLoggedIn()) {
            LoginService.getInstance(this).signOut();
        }

        setSignedInView(LoginService.getInstance(this).getUserDisplayName(),
                LoginService.getInstance(this).isLoggedIn());
        return true;
    }

    /************************************ Login Methods ************************************/

    @SuppressLint("NonConstantResourceId")
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

    private Executor executor = Executors.newSingleThreadExecutor();

    @SuppressLint("NewApi")
    private void testUserDao() {
        executor.execute(() -> {
            try {
                connect = ConnectionService.instance.getConnection();
                if (connect != null) {
                    User user = MusimatchServices.instance.getUserService().getUserById(1L);
                    Log.d(TAG, user.getUsername());
                } else {
                    Log.d("Connect", "can't connect to db - check connection");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressLint("NewApi")
    private void testTagDao() {
        executor.execute(() -> {
            try {
                connect = ConnectionService.instance.getConnection();
                if (connect != null) {
                    List<Tag> tags = TagServiceEJB.instance.getAllTags();
                    Log.d(TAG, "" + tags.size());
                } else {
                    Log.d("Connect", "can't connect to db - check connection");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressLint("NewApi")
    private void testDB() {
        executor.execute(() -> {
            try {
                connect = ConnectionService.instance.getConnection();
                if (connect != null) {
                    String query = "Select * from Tags;";
                    Statement st = connect.createStatement();
                    ResultSet resultSet = st.executeQuery(query);
                    if(resultSet.next()){
                        Log.d(TAG, resultSet.getString("first_name"));
                    }
                } else {
                    Log.d("Connect", "can't connect to db - check connection");
                }
            } catch (Exception e) {
                Log.e(TAG, "testDB: ", e);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LoginService.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                LoginService.getInstance(this).setGoogleAccount(account);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getDisplayName());
                Task<AuthResult> authResultTask = LoginService.getInstance(this).firebaseAuthWithGoogle(account.getIdToken());

                authResultTask.addOnCompleteListener(this, firebaseLoginRes -> {
                    if (firebaseLoginRes.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        setSignedInView(LoginService.getInstance(this).getGoogleAccount().getDisplayName(), true);
                        LoginService.getInstance(this).createNewUser(authResultTask);
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
        Log.d(TAG, "Trying to sign in...");
        Intent signInIntent = LoginService.getInstance(this).getGoogleSignInClient().getSignInIntent();
        startActivityForResult(signInIntent, LoginService.RC_SIGN_IN);
    }

    private void signOut() {
        Log.d(TAG, "Trying to sign out...");
        LoginService.getInstance(this).signOut();
        setSignedInView(this.getString(R.string.default_sign_in_name_display), false);
    }
}