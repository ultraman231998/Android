package com.example.nguyenpro231998.navibarmenu.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenpro231998.navibarmenu.R;


public class FragmentND extends Fragment {


    private static final String KEY_TITLE="Content";

    public FragmentND() {
        // Required empty public constructor
    }

    public static FragmentND newInstance(String param1, String param2) {
        FragmentND fragment = new FragmentND();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_nd, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceSate){
             super.onViewCreated(view, savedInstanceSate);
             String title = getArguments().getString(KEY_TITLE);
             ((TextView)view.findViewById(R.id.title)).setText(title);
    }
    }

