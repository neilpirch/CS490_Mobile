package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private static final String PREFS = "prefFile";
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    TextToSpeech tts;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static String name = "name";

    private final int CHECK_CODE = 0x1;
    private final int LONG_DURATION = 5000;
    private final int SHORT_DURATION = 1200;

    private Speaker speaker;


    private void checkTTS() {
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, CHECK_CODE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);

        preferences = getSharedPreferences(PREFS, 0);
        editor = preferences.edit();

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                    tts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
                    mVoiceInputTv.setText("Hello");
                }
            }
        });
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            a.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result != null && result.size() > 0) {
                        mVoiceInputTv.setText(result.get(0));
                        if (result.get(0).equalsIgnoreCase("hello")) {
                            tts.speak("What is your name?", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.setText("What is your name?");
                        } else if (result.get(0).contains("name")) {
                            name = result.get(0).substring(result.get(0).lastIndexOf(' ') + 1);
                            editor.putString("name", name).apply();
                            tts.speak("Hello, " + name, TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.setText("Hello, " + name);
                        } else if (result.get(0).contains("not feeling good")) {
                            tts.speak("I'm sorry you don't feel well. What are your symptoms?", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.setText("I'm sorry you don't feel well. What are your symptoms?");
                        } else if (result.get(0).contains("what") && result.get(0).contains("time")) {
                            // TODO: 12/1/2018 get time code
                            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm", Locale.US);//dd/MM/yyyy
                            Date now = new Date();
                            String[] strDate = sdfDate.format(now).split(":");
                            if (strDate[1].contains("00")) strDate[1] = "o'clock";
                            tts.speak("The time is : " + sdfDate.format(now), TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.setText("The time is : " + sdfDate.format(now));
                        } else if (result.get(0).contains("what") && result.get(0).contains("medicine")) {
                            tts.speak("I think you have a fever, and the only cure is more cowbell.", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.setText("I think you have a fever, and the only cure is more cowbell.");
                        } else if (result.get(0).contains("thank you")) {
                            tts.speak("Thank you, too, " + name + ". Take care", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.setText("Thank you, too, " + name + ". Take care");
                        } else {
                            tts.speak("Sorry, " + name + ", I don't understand. Please try again.", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.setText("Sorry, " + name + ", I don't understand. Please try again.");
                        }
                        break;
                    }
                }
            }
        }
    }
}