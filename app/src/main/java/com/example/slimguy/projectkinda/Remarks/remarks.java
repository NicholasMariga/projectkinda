package com.example.slimguy.projectkinda.Remarks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class remarks extends AppCompatActivity {


    Spinner level;
    String To_level,About,Compl,Level,phone;
    EditText etRemarks,number,idp,to_kname;
    String constant,folder;
    SharedPreferences sharedPreferences;
    String File="File";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        constant="192.168.137.101";
        folder = "sem2";
        to_kname=findViewById(R.id.to_kname);
        level=findViewById(R.id.level);
        idp=findViewById(R.id.idp);
        number=findViewById(R.id.number);
        etRemarks=findViewById(R.id.etRemarks);
        sharedPreferences=getSharedPreferences(File, Context.MODE_PRIVATE);
    }

    public void refresh(View view) {
        level.setSelected(false);
        to_kname.setText("");
        idp.setText("");
        number.setText("");
        level.setSelected(false);
        etRemarks.setText("");
    }

    public void submit(View view) {
        To_level=to_kname.getText().toString();
        About=idp.getText().toString();
        phone=number.getText().toString();
        Compl=etRemarks.getText().toString();
        Level=level.getSelectedItem().toString();
        if (To_level.isEmpty()||Level.isEmpty()||About.isEmpty()||Compl.isEmpty()||phone.isEmpty()){
            Toast.makeText(this, "Check all fields", Toast.LENGTH_SHORT).show();
        }else{
            Back back=new Back(remarks.this);
            back.execute(To_level,About,Level,phone,Compl);
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

            progressDialog.setMessage("Sending Remarks......");

            progressDialog.setIndeterminate(true);

            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String kname = params[0];
            String id = params[1];
            String level = params[2];
            String number = params[3];
            String remarks = params[4];
            String logged_in=getUser();


            String sign_url = "http://" + constant + "/" + folder + "/remarks.php";

            if (sign_url != null) {

                try {
                    URL url = new URL(sign_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String post_data = URLEncoder.encode("kname", "UTF-8") + "=" + URLEncoder.encode(kname, "UTF-8") + "&"
                            + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&"
                            + URLEncoder.encode("level", "UTF-8") + "=" + URLEncoder.encode(level, "UTF-8") + "&"
                            + URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(number, "UTF-8") + "&"
                            + URLEncoder.encode("remarks", "UTF-8") + "=" + URLEncoder.encode(remarks, "UTF-8") + "&"
                            + URLEncoder.encode("logged_in", "UTF-8") + "=" + URLEncoder.encode(logged_in, "UTF-8");

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
}
