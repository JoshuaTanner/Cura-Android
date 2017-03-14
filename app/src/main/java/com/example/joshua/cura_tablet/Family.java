package com.example.joshua.cura_tablet;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class Family extends AppCompatActivity {

    ImageButton scheduleButton;
    ImageButton curaButton;
    ImageButton homeButton;

    TextClock txtClock_menu;
    TextClock textDate_menu;

    TextView helloText;


    TextToSpeech TTS;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        layout();

        Init();
    }
    public void Init() {
        //Set fonts
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/KozGoPr6N-Heavy.otf");
        //Clock Set fonts for clock
        txtClock_menu = (TextClock) findViewById(R.id.txtClock_menuTime1);
        txtClock_menu.setTypeface(typeFace);
        //Date Set fonts for clock
        textDate_menu = (TextClock) findViewById(R.id.txtClock_menuDate1);
        textDate_menu.setTypeface(typeFace);
        //setup button

        scheduleButton = (ImageButton) findViewById(R.id.menubtn_schedule);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule();
            }
        });

        homeButton = (ImageButton) findViewById(R.id.menubtn_home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home();
            }
        });

        //For speech functionallity
        helloText = (TextView) findViewById(R.id.HelloText);

        curaButton = (ImageButton) findViewById(R.id.menubtn_cura);

        curaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        TTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    TTS.setLanguage(Locale.UK);
                }
            }
        });

    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));

        //TextToSpeech("How can I help you?");


        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    helloText.setText(result.get(0));

                    Log.i("LOG", helloText.toString());

                    if(helloText.getText().equals("who is my son"))
                    {
                        Identify();
                    }
                    else if(helloText.getText().equals("what is next"))
                    {
                        NextTask();
                    }
                    else
                    {
                        TextToSpeech("I don't understand");
                    }
                }
                break;
            }

        }
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

        Intent work = new Intent(Family.this, Memories.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }

    public void Schedule() {
        Log.i("LOG", "Accessing schedulewindow");

        Intent work = new Intent(Family.this, Schedule.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }

    public void Home() {
        Log.i("LOG", "Accessing family tree");

        Intent work = new Intent(Family.this, MainActivity.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }
}



