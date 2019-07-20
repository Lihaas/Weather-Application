package com.stwalkerster.android.apps.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.stwalkerster.android.apps.weatherapp.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

 //Set up variables
    String weatherFor,getNameOfCity;
    TextView cityName,temperature,tempType;
    LinearLayout layout;
    Double d;
    String nameOfCity;
    Button findoutCity;
    ProgressBar progressBar;
    List<User> user;
    RequestQueue reqQue;
    String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Ids
        cityName = findViewById(R.id.cityName);
        temperature = findViewById(R.id.temperature);
        tempType = findViewById(R.id.weatherForcast);
        findoutCity = findViewById(R.id.findCity);
        progressBar = findViewById(R.id.progress);
        layout = findViewById(R.id.layout);

        //Set up progress bar and hide dummy data
        progressBar.setVisibility(View.VISIBLE);
        layout.setVisibility(View.INVISIBLE);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);


        //On click findCity
        findoutCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,selectCity.class));
            }
        });

        // City name = null
        String getCityName = null;
         getCityName = getIntent().getStringExtra("CityName");

         //On city not given
        if(getCityName==null){
           nameOfCity = "Jaipur";
        }
        else {
            nameOfCity = getCityName;
        }

        //Methos of load url
        loadUrl();
    }


    //Load Url
    private void loadUrl(){

        //Json data
        String  URL_DATA ="https://api.openweathermap.org/data/2.5/weather?q="+ nameOfCity +"&appid=29e7b79e384c9a3aa3219f36a6558d0f";

        //Request for Json Object
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,URL_DATA, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                        getValue(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //set up RequestQueue
        reqQue = Volley.newRequestQueue(this);
        reqQue.add(stringRequest);


    }

    //Getting value from JSON Data
    public void getValue(JSONObject object){

        for(int i = 0 ;i<object.length();i++){

            //Define JSONObject as objectJson
            JSONObject objectJson = null;

            try{

                getNameOfCity = object.getString("name");
                objectJson = object.getJSONObject("main");
                temp = objectJson.getString("temp");

                //Here convert kelvin temperature into Celsius
                d = Double.parseDouble(temp) - 273.15;
                JSONArray uss = object.getJSONArray("weather");

          for(int j =0 ;j<uss.length();j++){
              JSONObject json = null;
              try{
                  json = uss.getJSONObject(i);
                  weatherFor = json.getString("description");
              }catch (Exception e){}}
            }catch (Exception e){} }

        tempType.setText(weatherFor);
        cityName.setText(getNameOfCity);
        temperature.setText(String.format("%.2f", d)+"\u2103");
        progressBar.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);


    }



}
