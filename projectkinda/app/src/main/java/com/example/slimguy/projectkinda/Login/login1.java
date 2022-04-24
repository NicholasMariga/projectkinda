package com.example.slimguy.projectkinda.Login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slimguy.projectkinda.R;
import com.example.slimguy.projectkinda.Parent.parent;

public class login1 extends AppCompatActivity {

    // final String TAG = "login1";
    Button btnenter;
    EditText username, password;
    String userp, passp;
    SharedPreferences sharedPreferences;
    String File="File";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        // btnenter.setOnClickListener(this);
        sharedPreferences=getSharedPreferences(File, Context.MODE_PRIVATE);
    }

    public void parentlogclicked(View v) {
        username = (EditText) findViewById(R.id.usernameparent);
        password = (EditText) findViewById(R.id.passparent);
        userp = username.getText().toString().trim();
        ;
        passp = password.getText().toString().trim();
        ;
        if ((userp.equalsIgnoreCase("Parent")) && (passp.equalsIgnoreCase("parent"))) {

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("log",userp);
            editor.commit();
            Intent scrollo = new Intent(login1.this, parent.class);
            scrollo.putExtra("log", userp);

            startActivity(scrollo);

            // Toast.makeText(login1.this," Incorrect username or password",Toast.LENGTH_SHORT).show();
        } else if ((userp.equalsIgnoreCase("slimguy@gmail.com")) && (passp.equalsIgnoreCase("parent"))) {

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("log",userp);
            editor.commit();
            Intent scrollo = new Intent(login1.this, parent.class);
            scrollo.putExtra("log", userp);
            startActivity(scrollo);
        } else {
            Toast.makeText(login1.this, " Incorrect username or password", Toast.LENGTH_SHORT).show();
        }
        username.getText().clear(); //or you can use editText.setText("");
        password.getText().clear(); //or you can use editText.setText("");

    }
}
// String password = "";

 /*  public void logonclick(View v) {

 username =(EditText)findViewById(R.id.usernameparent);
 password = (EditText)findViewById(R.id.passparent);

 userp = username.getText().toString();
 passp = password.getText().toString();
 // final String userp = username.getText().toString();
 //  password =  MD5.encrypt(password.getText().toString());
 // userp = password.getText().toString();

 HashMap<String, String> loginData = new HashMap<>();
 loginData.put("username", userp);
 loginData.put("password", passp);

 PostResponseAsyncTask loginTask = new PostResponseAsyncTask(this,
 loginData, new AsyncResponse() {
 @Override
 public void processFinish(String s) {
 Log.d(TAG, s);
 if(s.contains("LoginSuccess")){
 SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
 SharedPreferences.Editor editor = pref.edit();
 editor.putString("username", userp);
 editor.putString("password", passp);
 editor.commit();
 Intent in = new Intent(getApplicationContext(), parent.class);
 startActivity(in);
 }
 else{
 Toast.makeText(getApplicationContext(),
 "Something went wrong. Cannot login.", Toast.LENGTH_LONG).show();
 }
 }
 });
 loginTask.setExceptionHandler(new ExceptionHandler() {
 @Override
 public void handleException(Exception e) {
 if(e != null && e.getMessage() != null){
 Log.d(TAG, e.getMessage());
 }
 }
 });
 loginTask.execute("http://10.0.3.2:8089/sem2/login.php");
 }
 }
 */
 /*
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login1 extends AppCompatActivity implements View.OnClickListener{
    private EditText usernameparent,passparent;
    private Button btnenter;
    private TextView textViewRegister;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
//declaring the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);


//        FirebaseApp.initializeApp(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(),parent.class));

        }


        usernameparent=(EditText)findViewById(R.id.usernameparent);
        passparent=(EditText)findViewById(R.id.passparent);
        btnenter=(Button)findViewById(R.id.btnenter);
        textViewRegister=(TextView)findViewById(R.id.editRegister);

        progressDialog =new ProgressDialog(this);
        btnenter.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);

    }

    private void adminLogin(){
        String email= usernameparent.getText().toString().trim();
        String password= passparent.getText().toString().trim();
        //vibrator.vibrate(300);

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), parent.class));
                            Toast.makeText(login1.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            //when the login is successful
                        } else {
                            Toast.makeText(login1.this, "Invalid email or password...", Toast.LENGTH_SHORT).show();
                        }
                        //when login is not successful
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v==btnenter){
            adminLogin();
        }
        if(v==textViewRegister){
            finish();
            startActivity(new Intent(this,reg.class));
        }
    }
}
*/