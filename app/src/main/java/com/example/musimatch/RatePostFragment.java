package com.example.musimatch;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musimatch.models.Post;
import com.example.musimatch.models.UserModel;
import com.example.musimatch.services.LoginService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RatePostFragment extends Fragment {

    private static final String TAG = "RatePostFragment";
    private static final int MIN_RATE_VALUE = 1;
    private static final int MAX_RATE_VALUE = 5;

    View view;
    Post post;
    Button saveButton;
    Button cancelButton;

    TextView rate1star;
    TextView rate1title;
    SeekBar rate1slider;

    TextView rate2star;
    TextView rate2title;
    SeekBar rate2slider;

    TextView rate3star;
    TextView rate3title;
    SeekBar rate3slider;


    public RatePostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rate_post,container, false);
        post = PostDetailsFragmentArgs.fromBundle(getArguments()).getPost();
        linkComponents();
        initializeComponent();
        return view;
    }

    private void linkComponents()
    {
        saveButton = view.findViewById(R.id.rate_post_frag_savebtn);
        cancelButton = view.findViewById(R.id.rate_post_frag_cancelbtn);

        rate1star = view.findViewById(R.id.rate_post_frag_star1);
        rate1title = view.findViewById(R.id.rate_post_frag_headline1);
        rate1slider = view.findViewById(R.id.rate_post_frag_seekbar1);

        rate2star = view.findViewById(R.id.rate_post_frag_star2);
        rate2title = view.findViewById(R.id.rate_post_frag_headline2);
        rate2slider = view.findViewById(R.id.rate_post_frag_seekbar2);

        rate3star = view.findViewById(R.id.rate_post_frag_star3);
        rate3title = view.findViewById(R.id.rate_post_frag_headline3);
        rate3slider = view.findViewById(R.id.rate_post_frag_seekbar3);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeComponent()
    {
        saveButton.setOnClickListener(v -> save());
        cancelButton.setOnClickListener(v -> cancel());

        initializeRate1();
        initializeRate2();
        initializeRate3();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeRate1()
    {
        rate1slider.setMin(MIN_RATE_VALUE);
        rate1slider.setMax(MAX_RATE_VALUE);
        rate1star.setText(String.valueOf(rate1slider.getProgress()));
        rate1slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rate1star.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeRate2()
    {
        rate2slider.setMin(MIN_RATE_VALUE);
        rate2slider.setMax(MAX_RATE_VALUE);
        rate2star.setText(String.valueOf(rate2slider.getProgress()));
        rate2slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rate2star.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initializeRate3()
    {
        rate3slider.setMin(MIN_RATE_VALUE);
        rate3slider.setMax(MAX_RATE_VALUE);
        rate3star.setText(String.valueOf(rate3slider.getProgress()));
        rate3slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rate3star.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void save()
    {
        //TODO: SAVE THE RATE
        RatePostFragmentDirections.ActionRatePostFragmentToPostDetailsFragment action =
                RatePostFragmentDirections.actionRatePostFragmentToPostDetailsFragment(post);
        Navigation.findNavController(view).navigate(action);
    }

    private void cancel()
    {
        RatePostFragmentDirections.ActionRatePostFragmentToPostDetailsFragment action =
                RatePostFragmentDirections.actionRatePostFragmentToPostDetailsFragment(post);
        Navigation.findNavController(view).navigate(action);
    }
}