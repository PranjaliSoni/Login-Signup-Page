package com.example.hp.myapplication1kapilsir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class loginpage extends AppCompatActivity {

    EditText username, password;
    Button loginbutton;
    String name, pass;
    RequestQueue requestQueue;
    String inserturl = "http://172.31.73.184/db/dummy.php";
    String showUrl = "http://172.31.73.184/db/show.php",fetchedussername,fetchedpassword;
    String url="";
    StringRequest stringRequest;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText);
        loginbutton = (Button) findViewById(R.id.bt2);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        loginbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                name = username.getText().toString();
                pass = password.getText().toString();
                Toast toast=Toast.makeText(getApplicationContext(),name+pass,Toast.LENGTH_SHORT);
                toast.show();
                stringRequest =new StringRequest(Request.Method.POST,inserturl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(getApplicationContext(),"response:"+response,Toast.LENGTH_LONG).show();
                            //t1t.show();
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray userdetails=jsonObject.getJSONArray("userdetails");

                            for(int i=0;i<userdetails.length();i++) {
                            JSONObject userdetailsobject=userdetails.getJSONObject(i);

                                fetchedussername=userdetailsobject.getString("username");
                                 fetchedpassword=userdetailsobject.getString("password");

                                if(name.equals(fetchedussername)&&pass.equals(fetchedpassword)) {
                                    Toast toast=Toast.makeText(getApplicationContext(),"YOU HAVE SUCCESSFULLY LOGGEDIN",Toast.LENGTH_SHORT);
                                    toast.show();
                                   sharedPreferences = getSharedPreferences("myloginapp", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putBoolean("loggedin",true);
                                }
                                else
                                {
                                    Toast toast=Toast.makeText(getApplicationContext(),"no login",Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        //You can handle error here if you want
                        Toast.makeText(loginpage.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(loginpage.this);
                requestQueue.add(stringRequest);


            };

        });

    };
}
