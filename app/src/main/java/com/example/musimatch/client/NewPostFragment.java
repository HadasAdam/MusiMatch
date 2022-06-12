package com.example.musimatch.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.musimatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewPostFragment extends Fragment {

    private static final String TAG = "NewPostFragment";

    EditText titleET;
    Spinner postTypeSpinner;
    EditText lyricsET;
    Spinner firstTag;
    Spinner secondTag;
    Spinner thirdTag;
    Button saveBtn;
    Button cancelBtn;
    ProgressBar progressBar;
    ImageButton forwardbtn, backwardbtn, pausebtn, playbtn;
    SeekBar songPrgs;
    static int oTime =0, sTime =0, eTime =0, fTime = 5000, bTime = 5000;
    Handler hdlr = new Handler();
    View view;





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
        view = inflater.inflate(R.layout.fragment_new_post, container, false);
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
        backwardbtn = view.findViewById(R.id.btnBackward);
        forwardbtn = view.findViewById(R.id.btnForward);
        playbtn = view.findViewById(R.id.btnPlay);
        pausebtn = view.findViewById(R.id.btnPause);
        songPrgs = view.findViewById(R.id.sBar);
        pausebtn.setEnabled(false);
        return view;
    }

    


    private void onClickSaveButton() { // save new post
        progressBar.setVisibility(View.VISIBLE);
    }

    private void onClickCancelButton() {
        // cancel new post
        Navigation.findNavController(view).navigate(R.id.action_newPostFragment_to_allPostsFragment);
    }



    int requestcode=1;
    

    public void onActivityResult(int requestcode,int resulCode, Intent data )
    {
        super.onActivityResult(resulCode,resulCode,data);
        Context context = null;
        {
            context = context.getApplicationContext();
        }
        if(requestcode==requestcode && resulCode== Activity.RESULT_OK){
            if(data==null)
            {
                return;
            }
            Uri uri = data.getData();
            Toast.makeText(context,uri.getPath(),Toast.LENGTH_SHORT).show();
        }
    }

    public void openfilechooser(View view)
    {
        Context context = null;

            context = context.getApplicationContext();


        Intent intent = new Intent(String.valueOf(context));
        intent.setType("*");
        startActivityForResult(intent,requestcode);



    }


}