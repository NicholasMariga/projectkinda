package com.example.slimguy.projectkinda.Parent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slimguy.projectkinda.Models.Model;
import com.example.slimguy.projectkinda.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class    achievementsParent extends AppCompatActivity {
    ListView listView;
    String constant,folder;
    SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences sharedPreferences;
    String File="File";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements_parent);
        listView = findViewById(R.id.list);
        Toolbar toolbar =  findViewById(R.id.as);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Achievements");
        constant="192.168.137.1";
        folder = "sem2";
        listView.setDivider(null);
        swipeRefreshLayout=findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });
        sharedPreferences=getSharedPreferences(File, Context.MODE_PRIVATE);

        load();
    }

    private void load() {
        Background background = new Background(getApplicationContext(), listView);
        background.execute();
    }

    private class Background extends AsyncTask<String, String, String> {
        Context context;
        ListView listView;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;

        public Background(Context context, ListView listView) {
            this.context = context;
            this.listView = listView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {
            return DownloadData();
        }

        private String DownloadData() {
            stringBuilder = new StringBuilder();
            String line;
            httpURLConnection = new GetConnected().GetConnected();
            try {
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setConnectTimeout(120000);
                httpURLConnection.connect();
                bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(httpURLConnection.getInputStream())));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                httpURLConnection.disconnect();
            } catch (ProtocolException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
            return stringBuilder.toString();
        }

        protected class GetConnected {
            URL url;
            HttpURLConnection httpU;
            String sign_url = "http://" + constant + "/" + folder + "/achievements.php";
            public HttpURLConnection GetConnected() {
                if (sign_url != null) {
                    try {
                        url = new URL(sign_url);
                        httpU = (HttpURLConnection) url.openConnection();
                    } catch (MalformedURLException e) {
                        return null;
                    } catch (IOException e) {
                        return null;
                    }
                    return httpU;
                }
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            swipeRefreshLayout.setRefreshing(false);
            if (s != null) {
                ParserData parserData = new ParserData(context, listView, s);
                parserData.execute(s);

            } else {
                Toast.makeText(context, "Error Encountered\nCan't reach the Server:" , Toast.LENGTH_SHORT).show();
            }
        }

        private class ParserData extends AsyncTask<String, Void, Boolean> {
            Context context;
            ListView listView;
            String jsondata;
            ArrayList<Model> arrayList = new ArrayList<>();

            public ParserData(Context context, ListView listView, String jsondata) {
                this.context = context;
                this.listView = listView;
                this.jsondata = jsondata;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            protected Boolean doInBackground(String... params) {

                jsondata = params[0];

                return ParseJsonDt();
            }

            @Override
            protected void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);
                swipeRefreshLayout.setRefreshing(false);
                if (bool) {
                    AdapterClass adapterClass = new AdapterClass(arrayList, context, listView);

                    listView.setAdapter(adapterClass);
                } else {
                    Toast.makeText(context, "Encountered Error During processing", Toast.LENGTH_LONG).show();
                }
            }

            private Boolean ParseJsonDt() {
                boolean vall = false;
                try {
                    JSONArray jsonArray = new JSONArray(jsondata);
                    JSONObject jsonObject = null;

                    int i = 0;
                    Model model;
                    while (i < jsonArray.length()) {
                        jsonObject = jsonArray.getJSONObject(i);
                        model = new Model();
                        model.setKidnumber(jsonObject.getString("kidnumber"));
                        model.setLevel(jsonObject.getString("level"));
                        model.setKidname(jsonObject.getString("kidname"));
                        model.setParentname(jsonObject.getString("parentname"));
                        model.setAchieve(jsonObject.getString("achieve"));
                        model.setAchie(jsonObject.getString("achie"));
                        model.setDate(jsonObject.getString("date"));
                        String username=jsonObject.getString("kidnumber");
                        String logged_in=getUser();
                        if (username.equalsIgnoreCase(logged_in)){
                            arrayList.add(model);
                        }



                        i++;

                    }

                    vall = true;

                } catch (JSONException e) {

                }

                return vall;
            }
        }



        private class AdapterClass extends BaseAdapter {

            ArrayList<Model> models;
            Context context;
            ListView listView;

            public AdapterClass(ArrayList<Model> spacecraftArrayList, Context context, ListView listView) {
                this.models = spacecraftArrayList;
                this.context = context;
                this.listView = listView;
            }

            @Override
            public int getCount() {
                return models.size();
            }

            @Override
            public Object getItem(int position) {
                return models.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;

                if (viewHolder == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.achieve, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);

                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                Model model = models.get(position);
                String kidnumber,level, kidname, parentname, achieve, achie, date;
                kidnumber = model.getKidnumber();
                level = model.getLevel();
                kidname = model.getKidname();
                parentname = model.getParentname();
                achieve = model.getAchieve();
                achie = model.getAchie();
                date = model.getDate();
                viewHolder.kidnumber.setText(kidnumber);
                viewHolder.level.setText(level);
                viewHolder.kidname.setText(kidname);
                viewHolder.parentname.setText(parentname);
                viewHolder.achieve.setText(achieve);
                viewHolder.achie.setText(achie);
                viewHolder.date.setText(date);
                return convertView;
            }


            public class ViewHolder {
                TextView kidnumber,level, kidname, parentname, achieve, achie, date;
                View view;

                public ViewHolder(View view) {
                    this.view = view;
                    kidnumber = view.findViewById(R.id.kidnumber);
                    level = view.findViewById(R.id.level);
                    kidname = view.findViewById(R.id.kidname);
                    parentname = view.findViewById(R.id.parentname);
                    achieve = view.findViewById(R.id.achieve);
                    achie = view.findViewById(R.id.achie);
                    date = view.findViewById(R.id.date);
                }
            }
        }
    }

    private String getUser() {

        String value=sharedPreferences.getString("log","empty");
        return value;
    }
}
