package com.example.musimatch.services;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.musimatch.R;
import com.example.musimatch.models.User;
import com.example.musimatch.models.UserModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginService {
    public static final int RC_SIGN_IN = 9001;
    private static LoginService loginService;
    private FirebaseAuth firebaseAuth;

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth mAuth) {
        this.firebaseAuth = mAuth;
    }

    private GoogleSignInClient mGoogleSignInClient;

    public GoogleSignInClient getGoogleSignInClient() {
        return mGoogleSignInClient;
    }

    public void setGoogleSignInClient(GoogleSignInClient mGoogleSignInClient) {
        this.mGoogleSignInClient = mGoogleSignInClient;
    }

    private GoogleSignInAccount googleAccount;

    public void setGoogleAccount(GoogleSignInAccount googleAccount) {
        this.googleAccount = googleAccount;
    }

    public GoogleSignInAccount getGoogleAccount() {
        return googleAccount;
    }


    public FirebaseUser getFirebaseUser() {
        return getFirebaseAuth().getCurrentUser();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static User getUser() {
        return UserModel.instance.findUserById(0L);
//        FirebaseUser firebaseUser = getFirebaseUser();
//        if(firebaseUser == null)
//            return null;
//        else
//            return UserModel.instance.findUserByUsername(firebaseUser.getDisplayName());
    }

    private LoginService() {
    }

    public static LoginService getInstance(Context context) {
        if (loginService == null) {
            loginService = new LoginService();

            if (loginService.getGoogleSignInClient() == null) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(context.getString(R.string.the_default_web_client_id))
                        .requestEmail()
                        .build();
                loginService.setGoogleSignInClient(GoogleSignIn.getClient(context, gso));
                loginService.setGoogleAccount(GoogleSignIn.getLastSignedInAccount(context));
            }

            if (loginService.getFirebaseAuth() == null) {
                loginService.setFirebaseAuth(FirebaseAuth.getInstance());
            }

            if (loginService.getGoogleAccount() != null && loginService.getFirebaseUser() == null) {
                LoginService.getInstance(context).firebaseAuthWithGoogle(loginService.getGoogleAccount().getIdToken());
            }
        }

        return loginService;
    }

    public Task<AuthResult> firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        return loginService.getFirebaseAuth().signInWithCredential(credential);
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut();
        loginService.setGoogleAccount(null);
    }

    public boolean isLoggedIn() {
        return getGoogleAccount() != null && getFirebaseUser() != null;
    }

    public String getUserDisplayName() {
        return this.googleAccount != null ? this.googleAccount.getDisplayName() : "מחובר";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNewUser(Task<AuthResult> authResultTask) {
        if (authResultTask.getResult().getAdditionalUserInfo().isNewUser()) {
            User user = new User(); //we want to use sequence for this
            //user.setId(authResultTask.getResult().getUser().getUid());
            user.setAdmin(false);
            user.setUsername(authResultTask.getResult().getUser().getDisplayName());

            UserModel.instance.createUser(user);
        }
    }
}
