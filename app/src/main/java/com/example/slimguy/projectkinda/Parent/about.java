package com.example.slimguy.projectkinda.Parent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.slimguy.projectkinda.R;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar =  findViewById(R.id.as);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About App");
    }
}
