package com.example.joshua.cura_tablet;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;
import android.widget.Toast;

import com.example.joshua.cura_tablet.MainActivity;



public class Schedule extends AppCompatActivity {

    TextClock txtClock_menu;
    TextClock textDate_menu;
    TextToSpeech TTS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        layout();

        Init();
    }
    public void Init() {
        //Set fonts
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/KozGoPr6N-Heavy.otf");
        //Clock Set fonts for clock
        txtClock_menu = (TextClock) findViewById(R.id.txtClock_menuTime);
        txtClock_menu.setTypeface(typeFace);
        //Date Set fonts for clock
        textDate_menu = (TextClock) findViewById(R.id.txtClock_menuDate);
        textDate_menu.setTypeface(typeFace);

    }


    public void layout()
    {
        int currentApiVersion;

        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

    }

    public void TextToSpeech(String toSpeak){
        Toast.makeText(getApplicationContext(),toSpeak,Toast.LENGTH_SHORT).show();
        TTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }


    public void NextTask() {
        Log.i("LOG", "Accessing schedule");
        TextToSpeech("Head to the dining room in 15 minutes for lunch");
    }




    public void Identify() {
        Log.i("LOG", "Accessing identify");

        Intent work = new Intent(Schedule.this, Memories.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }

    public void FamilyTree() {
        Log.i("LOG", "Accessing family tree");

        Intent work = new Intent(Schedule.this, Family.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }

    public void Home() {
        Log.i("LOG", "Accessing family tree");

        Intent work = new Intent(Schedule.this, MainActivity.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }

}
