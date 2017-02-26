package com.example.joshua.cura_tablet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button scheduleButton;
    Button identifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    public void Init() {

        //Setting up schedule on click
        scheduleButton = (Button) findViewById(R.id.Schedule);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule();
            }
        });

        //Setting up identify on click
        identifyButton = (Button) findViewById(R.id.Identify);
        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Identify();
            }
        });
    }

    public void Schedule() {
        Log.i("LOG", "Accessing schedule");
    }

    public void Identify() {
        Log.i("LOG", "Accessing identify");
    }

}
