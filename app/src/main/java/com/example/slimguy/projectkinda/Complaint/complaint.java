package com.example.slimguy.projectkinda.Complaint;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slimguy.projectkinda.Parent.achievementsParent;
import com.example.slimguy.projectkinda.Parent.parent;
import com.example.slimguy.projectkinda.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class complaint extends AppCompatActivity {
    Spinner to_level,about,level;
    String To_level,About,Compl,Level,kidnumber,Loggedas;
    EditText etComplaint;
    String constant,folder;
    SharedPreferences sharedPreferences;
    String File="File";
    private TextView loggedas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loggedas = findViewById(R.id.loggedas);
        String log = getIntent().getStringExtra("log");

        loggedas.setText(log);

        constant="192.168.43.107";
        folder = "sem2";
        to_level=findViewById(R.id.to_level);
        level=findViewById(R.id.level);
        about=findViewById(R.id.about);
        etComplaint=findViewById(R.id.etComplaint);
        sharedPreferences=getSharedPreferences(File, Context.MODE_PRIVATE);
    }
    public void refresh(View view) {
        to_level.setSelected(false);
        about.setSelected(false);
        level.setSelected(false);
        etComplaint.setText("");
    }
    public void submit(View view) {
        Loggedas=loggedas.getText().toString();
        To_level=to_level.getSelectedItem().toString();
        About=about.getSelectedItem().toString();
        Compl=etComplaint.getText().toString();
        Level=level.getSelectedItem().toString();
        if (To_level.equalsIgnoreCase("Select")||Level.equalsIgnoreCase("Select")||About.equalsIgnoreCase("Select")||Compl.isEmpty()){
            Toast.makeText(this, "Check all fields", Toast.LENGTH_SHORT).show();
        }else{
            Back back=new Back(complaint.this);
            back.execute(Loggedas,To_level,About,Level,Compl);
        }

    }

    public class Back extends AsyncTask<String, String, String> {

        Context context;

        ProgressDialog progressDialog;

        public Back(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(context);

            progressDialog.setMessage("Sending Complaint......");

            progressDialog.setIndeterminate(true);

            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String kidnumber = params[0];
            String towho = params[1];
            String about = params[2];
            String leve = params[3];
            String complain = params[4];
            String logged_in=getUser();


            String sign_url = "http://" + constant + "/" + folder + "/complaint2.php";

            if (sign_url != null) {

                try {
                    URL url = new URL(sign_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String post_data =
                              URLEncoder.encode("kidnumber", "UTF-8") + "=" + URLEncoder.encode(kidnumber, "UTF-8") + "&"
                            + URLEncoder.encode("towho", "UTF-8") + "=" + URLEncoder.encode(towho, "UTF-8") + "&"
                            + URLEncoder.encode("about", "UTF-8") + "=" + URLEncoder.encode(about, "UTF-8") + "&"
                            + URLEncoder.encode("leve", "UTF-8") + "=" + URLEncoder.encode(leve, "UTF-8") + "&"
                            + URLEncoder.encode("logged_in", "UTF-8") + "=" + URLEncoder.encode(logged_in, "UTF-8") + "&"
                            + URLEncoder.encode("complain", "UTF-8") + "=" + URLEncoder.encode(complain, "UTF-8");


                    bufferedWriter.write(post_data);

                    bufferedWriter.flush();

                    bufferedWriter.close();

                    InputStream inputStream = httpURLConnection.getInputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                    String result = "";

                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {

                        result += line;
                    }
                    bufferedReader.close();

                    inputStream.close();

                    httpURLConnection.disconnect();

                    return result;

                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            progressDialog.dismiss();

            Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();


        }
    }

    private String getUser() {

        String value=sharedPreferences.getString("log","empty");
        return value;
    }
    public void viewcompl(View view){
        Intent logg = new Intent(complaint.this, viewcomplaint.class);
        startActivity(logg);
    }
}
