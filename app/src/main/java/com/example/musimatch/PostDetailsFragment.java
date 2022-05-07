package com.example.musimatch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.musimatch.adapters.CommentAdapter;
import com.example.musimatch.models.Comment;
import com.example.musimatch.models.CommentModel;
import com.example.musimatch.models.Post;
import com.example.musimatch.models.UserModel;
import com.example.musimatch.services.LoginService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;

public class PostDetailsFragment extends Fragment {

    private static final String TAG = "PostDetailsFragment";
    private MenuItem signOutButton;
    private MenuItem signInButton;
    private MenuItem loggedInUsername;
    ArrayList<Comment> commentsArrayList = new ArrayList<>();

    View view;
    TextView postTitle;
    TextView postLyrics;
    TextView linkedPosts;
    TextView comments;
    TextView rate;
    TextView uploaderUsername;
    TextView tags;

    TextView rate1star;
    TextView rate1title;
    TextView rate2star;
    TextView rate2title;
    TextView rate3star;
    TextView rate3title;

    public PostDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post_details,container, false);
        Post post = PostDetailsFragmentArgs.fromBundle(getArguments()).getPost();
        linkComponents();
        initializeComponent(post);
        initializeComments(post);

        return view;
    }

    private void linkComponents()
    {
        postTitle = view.findViewById(R.id.postDetails_title);
        postLyrics = view.findViewById(R.id.postDetails_content);
        linkedPosts = view.findViewById(R.id.postDetails_linkedPosts);
        comments = view.findViewById(R.id.postDetails_comments);
        rate = view.findViewById(R.id.postDetails_rate);
        uploaderUsername = view.findViewById(R.id.postDetails_uploader);
        tags = view.findViewById(R.id.postDetails_tags);
        rate1star = view.findViewById(R.id.postDetails_rate1star);
        rate1title = view.findViewById(R.id.postDetails_rate1title);
        rate2star = view.findViewById(R.id.postDetails_rate2star);
        rate2title = view.findViewById(R.id.postDetails_rate2title);
        rate3star = view.findViewById(R.id.postDetails_rate3star);
        rate3title = view.findViewById(R.id.postDetails_rate3title);
    }

    private void initializeComponent(Post post)
    {
        postTitle.setText(post.getTitle());
        postLyrics.setText(post.getPoemLyrics() != null ? post.getPoemLyrics() + "" : "");
        linkedPosts.setText(post.getLinkedPostsIds() != null ? post.getLinkedPostsIds().size() + "" : "0");
        comments.setText(post.getCommentsIds() != null ? post.getCommentsIds().size() + "" : "");
        rate.setText(String.valueOf(post.getAveragePostRate()));
        uploaderUsername.setText(UserModel.instance.findUserById(post.getUploaderId()).getUsername());

        linkedPosts.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_postDetailsFragment_to_linkPostFragment);
        });

        rate.setOnClickListener(v -> {
            PostDetailsFragmentDirections.ActionPostDetailsFragmentToRatePostFragment action =
                    PostDetailsFragmentDirections.actionPostDetailsFragmentToRatePostFragment(post);
            Navigation.findNavController(view).navigate(action);
        });
    }

    private void initializeComments(Post post) {
        commentsArrayList = new ArrayList<Comment>(CommentModel.instance.getCommentsByPostId(post.getId()));
        RecyclerView recyclerView = view.findViewById(R.id.PostDetails_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutInflater layoutInflater = getLayoutInflater();
        CommentAdapter adapter = new CommentAdapter(layoutInflater);
        recyclerView.setAdapter(adapter);
        adapter.setData(commentsArrayList);
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