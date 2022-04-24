package com.example.slimguy.projectkinda.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.slimguy.projectkinda.Login.login1;
import com.example.slimguy.projectkinda.R;

public class home extends AppCompatActivity {
    Button btnparent,btnteacher,btnevents,btnabout,btngallery,btnnotice,btndirector;

    RelativeLayout rellay1,rellay2;
    private long backPressedTime;
    private Toast backToast;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnparent = (Button)findViewById(R.id.parentlog);
        btnparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parentlo = new Intent(home.this, login1.class);
                startActivity(parentlo);
            }
        });
/*
        btnteacher = (Button)findViewById(R.id.teacherlog);
        btnteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teacherlo = new Intent(home.this,login2.class);
                startActivity(teacherlo);
            }
        });

        btndirector = (Button)findViewById(R.id.directorlog);
        btndirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventlo = new Intent(home.this,login3.class);
                startActivity(eventlo);
            }
        });
        */
        /*

        btnabout = (Button)findViewById(R.id.about);
        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutlo = new Intent(home.this,about.class);
                startActivity(aboutlo);
            }
        });
         */

    }

    @Override
    public void onBackPressed() {


        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Peess back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }

}