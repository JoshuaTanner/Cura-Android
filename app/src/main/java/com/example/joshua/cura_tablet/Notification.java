package com.example.joshua.cura_tablet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Locale;

public class Notification extends AppCompatActivity {

    TextView taskTitle;
    TextView taskContent;
    TextView taskWhere;
    TextView taskWhen;

    ImageButton confirmButton;

    TextToSpeech TTS;
    private final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        layout();

        Init();
    }
    public void Init() {
        //Set fonts
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/KozGoPr6N-Heavy.otf");

        confirmButton = (ImageButton) findViewById(R.id.btn_Confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home();
            }
        });

        taskTitle = (TextView) findViewById(R.id.taskName);
        taskContent = (TextView) findViewById(R.id.taskDes);
        taskWhere = (TextView) findViewById(R.id.taskWhere);
        taskWhen = (TextView) findViewById(R.id.taskWhen);

        taskTitle.setTypeface(typeFace);
        taskContent.setTypeface(typeFace);
        taskWhere.setTypeface(typeFace);
        taskWhen.setTypeface(typeFace);

        taskTitle.setText("Lunch");
        taskContent.setText("Go to dining room");
        taskWhere.setText("Where : Diningroom, Nikau, LadyAllum");
        taskWhen.setText(" When : 11:45 am");


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
     * Receiving speech input
     * */
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

    public void Home() {
        Log.i("LOG", "Accessing home");

        Intent work = new Intent(Notification.this, MainActivity.class);
        //work.putExtra("stopLat", response.getLatitude());

        startActivity(work);
        finish();
    }
}
