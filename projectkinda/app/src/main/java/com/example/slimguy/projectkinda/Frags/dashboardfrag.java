package com.example.slimguy.projectkinda.Frags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slimguy.projectkinda.R;

public class dashboardfrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.fragdashboard,container,false);
        return myview;
    }

}
