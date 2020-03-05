package com.alicend.arytmetyka;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class SettingsActivity extends ArytmetykaActivity {

    SharedPreferences mGameSettings;
    String[] data = {"1", "2", "3"};
    MediaPlayer mp_ok, mp_mus;
    TextView setlogo, dfq, snd, msc,tmt;

    void resizeFont(){
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();  // deprecated

        float x = 0;
        if(height <= 800)
            x = 15;
        else
            x = 15 + (height - 800) / 220;

            setlogo.setTextSize(x+5);
            snd.setTextSize(x);
            msc.setTextSize(x);
            tmt.setTextSize(x);
            dfq.setTextSize(x);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        data[0] = getString(R.string.easy);
        data[1] = getString(R.string.normal);
        data[2] = getString(R.string.hard);

        mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Bold.ttf");

        String language = Locale.getDefault().getLanguage();

        if (language.equals("en"))
        {
            type = Typeface.createFromAsset(getAssets(),"fonts/academy-bold.ttf");
        }
        else if (language.equals("ru"))
        {
            type = Typeface.createFromAsset(getAssets(),"fonts/academy-bold.ttf");
        }
        else {
            type = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Bold.ttf");
        }

        mp_ok = MediaPlayer.create(this, R.raw.ok);
        mp_mus = MediaPlayer.create(this, R.raw.mus);


        ImageView back_btn = (ImageView) findViewById(R.id.bbtn);

        //back_btn.setTypeface(type);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp_mus.stop();
                mp_ok.stop();
                startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
                SettingsActivity.this.finish();
            }

        });


        setlogo = (TextView) findViewById(R.id.setlogo);
        setlogo.setTypeface(type);

        // адаптер
        dfq = (TextView) findViewById(R.id.dfqt);
        dfq.setTypeface(type);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);


        // заголовок
        spinner.setPrompt(getString(R.string.difficult));
        // выделяем элемент
        spinner.setSelection(1);

        if (mGameSettings.contains(GAME_PREFERENCES_DIFFICULT)) {
            spinner.setSelection(mGameSettings.getInt(GAME_PREFERENCES_DIFFICULT, 1));
        }
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Editor editor = mGameSettings.edit();
                editor.putInt(GAME_PREFERENCES_DIFFICULT, position);
                editor.commit();
               // Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // sound volume
        snd = (TextView) findViewById(R.id.sndt);
        snd.setTypeface(type);

        // sound volume slider
        SeekBar sound = (SeekBar) findViewById(R.id.soundSlider);
        if (mGameSettings.contains(GAME_PREFERENCES_SOUND_VOLUME)) {
            sound.setProgress(mGameSettings.getInt(GAME_PREFERENCES_SOUND_VOLUME, 1));
        }



        sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                Editor editor = mGameSettings.edit();
                editor.putInt(GAME_PREFERENCES_SOUND_VOLUME, progress);
                editor.commit();

                mp_ok.setLooping(true);
                float vol = (float) progress / 100;
                mp_ok.setVolume(vol, vol);
                mp_ok.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                mp_ok.pause();
            }
        });


        // music volume
        msc = (TextView) findViewById(R.id.msct);
        msc.setTypeface(type);

        // music volume slider
        SeekBar music = (SeekBar) findViewById(R.id.musicSlider);
        if (mGameSettings.contains(GAME_PREFERENCES_MUSIC_VOLUME)) {
            music.setProgress(mGameSettings.getInt(GAME_PREFERENCES_MUSIC_VOLUME, 1));
        }

        music.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                Editor editor = mGameSettings.edit();
                editor.putInt(GAME_PREFERENCES_MUSIC_VOLUME, progress);
                editor.commit();

                mp_mus.setLooping(true);
                float vol = (float) progress / 100;
                mp_mus.setVolume(vol, vol);
                mp_mus.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                mp_mus.pause();
            }
        });


        // time volume
        tmt = (TextView) findViewById(R.id.tmrt);
        tmt.setTypeface(type);

        // time slider
        SeekBar time = (SeekBar) findViewById(R.id.timeSlider);
        if (mGameSettings.contains(GAME_PREFERENCES_TIME_VOLUME)) {
            time.setProgress(mGameSettings.getInt(GAME_PREFERENCES_TIME_VOLUME, 0));
        }
        time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                Editor editor = mGameSettings.edit();
                editor.putInt(GAME_PREFERENCES_TIME_VOLUME, progress);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        resizeFont();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        mp_mus.stop();
        mp_ok.stop();
        startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
        SettingsActivity.this.finish();
    }
}
