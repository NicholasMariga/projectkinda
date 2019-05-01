package com.example.slimguy.projectkinda.Parent;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.slimguy.projectkinda.R;
import com.example.slimguy.projectkinda.Complaint.complaint;
import com.example.slimguy.projectkinda.Frags.dashboardfrag;
import com.example.slimguy.projectkinda.Frags.eventsfrag;
import com.example.slimguy.projectkinda.Frags.galleryfrag;
import com.example.slimguy.projectkinda.Home.home;
import com.example.slimguy.projectkinda.News.newss;
import com.example.slimguy.projectkinda.Frags.noticefrag;
import com.example.slimguy.projectkinda.Remarks.remarks;

public class parent extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button btnenter;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedscreen(R.id.nav_dash);
    }
    public void achievementclick(View v){
        Intent logg = new Intent(parent.this, achievementsParent.class);
        startActivity(logg);
    }
    public void complaintclick(View v){
        Intent logg = new Intent(parent.this, complaint.class);
        startActivity(logg);
    }
    public void behaviourclick(View v){
        Intent logg = new Intent(parent.this, behaviourParent.class);
        startActivity(logg);
    }
    public void progressclick(View v){
        Intent logg = new Intent(parent.this, progressParent.class);
        startActivity(logg);
    }
    public void assignmentclick(View v){
        Intent logg = new Intent(parent.this, assignmentsParent.class);
        startActivity(logg);
    }
    public void notificationclick(View v){
        Intent logg = new Intent(parent.this, notifications.class);
        startActivity(logg);
    }
    public void generalremarksclick(View v){
        Intent logg = new Intent(parent.this, remarks.class);
        startActivity(logg);
    }
    public void newsclick(View v){
        Intent logg = new Intent(parent.this, newscenter.class);
        startActivity(logg);
    }
    public void logoutclick(View v){
        Intent logg = new Intent(parent.this, home.class);
        startActivity(logg);
    }
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }
    /*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    */
    private void displaySelectedscreen(int id){
        Fragment fragment = null;

        switch (id){
            case R.id.nav_dash:
                fragment = new dashboardfrag();
                break;
            case R.id.nav_gallery:
                fragment = new galleryfrag();
                break;
            case R.id.nav_event:
                fragment = new eventsfrag();
                break;
            case R.id.nav_notice:
                fragment = new noticefrag();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(parent.this, home.class);
                startActivity(intent);
                finish();  // This call is missing.
                break;
        }
        if(fragment !=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.nav_main_parent,fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_about) {

       //     return true;
    //    }

        return super.onOptionsItemSelected(item);
    }

    public void clickexit(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
public void abt(View v){
    Intent intent = new Intent(parent.this, about.class);
    startActivity(intent);
}
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;*/
        displaySelectedscreen(id);

        return true;
    }
}
