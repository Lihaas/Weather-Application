package com.stwalkerster.android.apps.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class selectCity extends AppCompatActivity {
EditText txtCity;
Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        txtCity = findViewById(R.id.txtCity);
        next = findViewById(R.id.go);




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = txtCity.getText().toString().trim();
                if(TextUtils.isEmpty(cityName)){
                    txtCity.setError("Enter City Name");
                    txtCity.setEnabled(true);
                }
                else {
                    Intent i = new Intent(selectCity.this,MainActivity.class);
                    i.putExtra("CityName",cityName);
                    startActivity(i);
                }

            }
        });


    }
}
