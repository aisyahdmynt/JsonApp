package com.example.jsonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle, txtBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtBody = (TextView) findViewById(R.id.txtBody);

        getData();
    }

    //membuat function getData() untuk memparsing data JSON
    void getData(){
        //menginisialisasi antrian menggunakan RequestWueue
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://jsonplaceholder.typicode.com/posts/2";

        JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();

        //merequest sebuah string dari URL(sample-JSONPlaceholder.typecode.com)
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //menempatkan data JSON kedalam sebuah variable
                    JSONObject jsonTry = new JSONObject(response.toString());

                    //mengset data json ke dalam variable
                    String name;
                    txtTitle.setText(jsonTry.getString(name: "title"));
                    txtBody.setText(jsonTry.getString(name:"body"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error response", error.toString());
            }
        });
        //menambahkan request ke antrian
        queue.add(stringRequest);
    }
}