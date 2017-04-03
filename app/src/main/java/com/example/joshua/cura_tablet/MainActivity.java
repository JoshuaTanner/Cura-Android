package com.example.joshua.cura_tablet;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import android.graphics.Typeface;

public class MainActivity extends AppCompatActivity {

    ImageButton scheduleButton;
    ImageButton familyButton;
    ImageButton nextTaskButton;
    ImageButton identifyButton;
    ImageButton notifyButton;

    TextView helloText;

    ImageButton speechButton;

    TextToSpeech TTS;
    Typeface typeFace;

    TextClock txtClock;
    TextClock txtDate;


    private final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout();

        setContentView(R.layout.activity_main);
        Init();

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






    public void Init() {

        //Set fonts
        typeFace = Typeface.createFromAsset(getAssets(),"fonts/KozGoPr6N-Heavy.otf");

        //Clock Set fonts for clock
        txtClock = (TextClock) findViewById(R.id.textClock);
        txtClock.setTypeface(typeFace);
        //Date Set fonts for clock
        txtDate = (TextClock) findViewById(R.id.textDate);
        txtDate.setTypeface(typeFace);
        //Setting up schedule on click
        nextTaskButton = (ImageButton) findViewById(R.id.btn_invisible);
        nextTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextTask();
            }
        });

        scheduleButton = (ImageButton) findViewById(R.id.ScheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule();
            }
        });
        familyButton = (ImageButton) findViewById(R.id.FamilyButton);
        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FamilyTree();
            }
        });
        //Setting up identify on click
        identifyButton = (ImageButton) findViewById(R.id.btn_invisible2);
        identifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Identify();
            }
        });

        notifyButton = (ImageButton) findViewById(R.id.btn_Invisible3);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notify();
            }
        });


        helloText = (TextView) findViewById(R.id.Hello);


        speechButton = (ImageButton) findViewById(R.id.MicButton);

        speechButton.setOnClickListener(new View.OnClickListener(){
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

    public void TextToSpeech(String toSpeak){
        Toast.makeText(getApplicationContext(),toSpeak,Toast.LENGTH_SHORT).show();
        TTS.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null);
    }


    public void NextTask() {
        Log.i("LOG", "Accessing schedule");
        TextToSpeech("Head to the dining room in 15 minutes for lunch");
    }

    public void Identify() {
        Log.i("LOG", "Accessing identify");

        TextToSpeech("James Tanner is your 36 year old son. Here are your memories");

        Intent work = new Intent(MainActivity.this, Memories.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }

    public void Notify() {
        Log.i("LOG", "Accessing notify");

        TextToSpeech("Lunch is starting at 11:45 am");
        Intent work = new Intent(MainActivity.this, Notification.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();


    }

    public void Schedule() {
        Log.i("LOG", "Accessing schedule window");

        TextToSpeech("You are currently viewing Schedule");

        Intent work = new Intent(MainActivity.this, MainActivity.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }
    public void FamilyTree() {
        Log.i("LOG", "Accessing family tree");

        Intent work = new Intent(MainActivity.this, Family.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }

}
