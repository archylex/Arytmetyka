package com.alicend.arytmetyka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import java.util.Locale;
import java.util.Random;

import pl.droidsonroids.gif.GifTextView;


public class GameActivity extends ArytmetykaActivity {

    SharedPreferences mGameSettings;
    ImageView arrow;
    TextView qtxt, yourtxt, yourscoretxt, bestscoretxt, sctxt, tiltscore, nbesttxt;
    float angle;
    int set_timer;
    boolean timeout, bestsound, check_hiscore;
    int score, hiscore;
    int getDifficult;
    int getStage;
    int answer;
    int vol_mus, vol_snd;
    boolean stoptimer;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    ImageView replaybtn,exitbtn;
    MediaPlayer mp_ok, mp_lol, mp_wow, mp_mus, mp_time;
    GifTextView aniscreen;
    //String[] words = {"Молодец!", "Правильно!", "Умняшка!", "Просто прелесть!", "Отлично!"};
    String[] words = {"1", "2", "3", "4", "5"};
   //String[] words = {getString(R.string.word1), getString(R.string.word2), getString(R.string.word3), getString(R.string.word4), getString(R.string.word5)};
    private AdView mAdView;



    String sign2string(int a)
    {
        if (a == 1)
            return "+";
        if (a == 2)
            return "-";
        if (a == 3)
            return "*";

        return ":";
    }

    int random_range(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    boolean c_random()
    {
        boolean res = false;
        int b_od = random_range(1, 10);
        if (b_od % 2 == 0)
            res = true;
        else
            res = false;
        return res;
    }

    int divide(int rand_answ, int range, int sign)
    {
        int count_d = 0;
        int[] array_numb = new int[1000];

        if(sign == 3)
            for (int z = 1; z <= rand_answ; z++)
                if (rand_answ % z == 0)
                {
                    array_numb[count_d] = z;
                    count_d++;
                }
        if(sign == 4)
            for (int w = rand_answ; w <= range; w++)
                if (w % rand_answ == 0)
                {
                    array_numb[count_d] = w;
                    count_d++;
                }
        int num = random_range(0, count_d - 1);
        return array_numb[num];
    }

    String SetQuest(int difficult, int stage, int rand_answer){
        int[] array_digs = new int[5];
        int[] array_signs = new int[5];
        String question = "";
        boolean oper_del = false;

        switch (difficult)
        {
            // difficult: 0-3, stage: 2-4
            case 0: // Easy
                // stage 2
                array_digs[1] = random_range(1, 20);
                if (rand_answer < array_digs[1])
                {
                    array_signs[1] = 2;
                    array_digs[2] = array_digs[1] - rand_answer;
                }
                else
                {
                    array_signs[1] = 1;
                    array_digs[2] = rand_answer - array_digs[1];
                }
                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]);

                if (stage == 2) break;

                // stage 3
                int sum_b = array_digs[2];
                array_digs[2] = random_range(1, 20);
                if (sum_b < array_digs[2])
                {
                    array_signs[2] = 2;
                    array_digs[3] = array_digs[2] - sum_b;
                }
                else
                {
                    array_signs[2] = 1;
                    array_digs[3] = sum_b - array_digs[2];
                }
                if (array_signs[1] == 2)
                {
                    if (array_signs[2] == 2)
                        array_signs[2] = 1;
                    else if (array_signs[2] == 1)
                        array_signs[2] = 2;
                }
                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]);

                if (stage == 3) break;

                // stage 4
                int sum_c = array_digs[3];
                array_digs[3] = random_range(1, 20);

                if (sum_c < array_digs[3])
                {
                    array_signs[3] = 2;
                    array_digs[4] = array_digs[3] - sum_c;
                }
                else
                {
                    array_signs[3] = 1;
                    array_digs[4] = sum_c - array_digs[3];
                }
                if (array_signs[2] == 2)
                {
                    if (array_signs[3] == 2)
                        array_signs[3] = 1;
                    else if (array_signs[3] == 1)
                        array_signs[3] = 2;
                }
                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]);
                break;

            case 1: // Normal
                // stage 2
                oper_del = c_random();

                if (oper_del)
                {
                    array_digs[1] = random_range(1, 20);
                    if (rand_answer < array_digs[1])
                    {
                        array_signs[1] = 2;
                        array_digs[2] = array_digs[1] - rand_answer;
                    }
                    else
                    {
                        array_signs[1] = 1;
                        array_digs[2] = rand_answer - array_digs[1];
                    }
                }
                else
                {
                    if (c_random())
                        array_signs[1] = 3;
                    else
                        array_signs[1] = 4;

                    array_digs[1] = divide(rand_answer, 81, array_signs[1]);
                    if (array_signs[1] == 4)
                        array_digs[2] = array_digs[1] / rand_answer;

                    if (array_signs[1] == 3)
                        array_digs[2] = rand_answer / array_digs[1];
                }
                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]);

                if (stage == 2) break;

                // stage 3
                sum_b = array_digs[2];

                oper_del = c_random();

                if (oper_del)
                {
                    array_digs[2] = random_range(1, 20);
                    if (sum_b < array_digs[2])
                    {
                        array_signs[2] = 2;
                        array_digs[3] = array_digs[2] - sum_b;
                    }
                    else
                    {
                        array_signs[2] = 1;
                        array_digs[3] = sum_b - array_digs[2];
                    }
                    if (array_signs[1] == 2)
                    {
                        if (array_signs[2] == 2)
                            array_signs[2] = 1;
                        else if (array_signs[2] == 1)
                            array_signs[2] = 2;
                    }

                }
                else
                {
                    if (c_random())
                        array_signs[2] = 3;
                    else
                        array_signs[2] = 4;

                    array_digs[2] = divide(sum_b, 81, array_signs[2]);
                    if (array_signs[2] == 4)
                        array_digs[3] = array_digs[2] / sum_b;

                    if (array_signs[2] == 3)
                        array_digs[3] = sum_b / array_digs[2];

                }
                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]);
                if (array_signs[1] == 3 | array_signs[1] == 4)
                    question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " ( " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]) + " )";

                if (stage == 3) break;


                // stage 4
                sum_c = array_digs[3];

                oper_del = c_random();

                if (oper_del)
                {
                    array_digs[3] = random_range(1, 20);

                    if (sum_c < array_digs[3])
                    {
                        array_signs[3] = 2;
                        array_digs[4] = array_digs[3] - sum_c;
                    }
                    else
                    {
                        array_signs[3] = 1;
                        array_digs[4] = sum_c - array_digs[3];
                    }
                    if (array_signs[2] == 2)
                    {
                        if (array_signs[3] == 2)
                            array_signs[3] = 1;
                        else if (array_signs[3] == 1)
                            array_signs[3] = 2;
                    }
                }
                else
                {
                    if (c_random())
                        array_signs[3] = 3;
                    else
                        array_signs[3] = 4;

                    array_digs[3] = divide(sum_c, 81, array_signs[3]);
                    if (array_signs[3] == 4)
                        array_digs[4] = array_digs[3] / sum_c;

                    if (array_signs[3] == 3)
                        array_digs[4] = sum_c / array_digs[3];
                }

                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]);

                if (array_signs[2] == 3 | array_signs[2] == 4)
                    question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " ( " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]) + " )";


                if (array_signs[1] == 3 | array_signs[1] == 4)
                {
                    question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " ( " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]) + " )";
                    if (array_signs[2] == 3 | array_signs[2] == 4)
                        question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " ( " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " ( " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]) + " ) )";
                }

                break;

            case 2: // Hard
                // stage 2
                oper_del = c_random();

                if (oper_del)
                {
                    array_digs[1] = random_range(1, 100);
                    if (rand_answer < array_digs[1])
                    {
                        array_signs[1] = 2;
                        array_digs[2] = array_digs[1] - rand_answer;
                    }
                    else
                    {
                        array_signs[1] = 1;
                        array_digs[2] = rand_answer - array_digs[1];
                    }
                }
                else
                {
                    if (c_random())
                        array_signs[1] = 3;
                    else
                        array_signs[1] = 4;

                    array_digs[1] = divide(rand_answer, 900, array_signs[1]);
                    if (array_signs[1] == 4)
                        array_digs[2] = array_digs[1] / rand_answer;

                    if (array_signs[1] == 3)
                        array_digs[2] = rand_answer / array_digs[1];
                }
                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]);

                if (stage == 2) break;

                // stage 3
                sum_b = array_digs[2];

                oper_del = c_random();

                if (oper_del)
                {
                    array_digs[2] = random_range(1, 100);
                    if (sum_b < array_digs[2])
                    {
                        array_signs[2] = 2;
                        array_digs[3] = array_digs[2] - sum_b;
                    }
                    else
                    {
                        array_signs[2] = 1;
                        array_digs[3] = sum_b - array_digs[2];
                    }
                    if (array_signs[1] == 2)
                    {
                        if (array_signs[2] == 2)
                            array_signs[2] = 1;
                        else if (array_signs[2] == 1)
                            array_signs[2] = 2;
                    }

                }
                else
                {
                    if (c_random())
                        array_signs[2] = 3;
                    else
                        array_signs[2] = 4;

                    array_digs[2] = divide(sum_b, 900, array_signs[2]);
                    if (array_signs[2] == 4)
                        array_digs[3] = array_digs[2] / sum_b;

                    if (array_signs[2] == 3)
                        array_digs[3] = sum_b / array_digs[2];

                }
                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]);
                if (array_signs[1] == 3 | array_signs[1] == 4)
                    question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " ( " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]) + " )";

                if (stage == 3) break;


                // stage 4
                sum_c = array_digs[3];

                oper_del = c_random();

                if (oper_del)
                {
                    array_digs[3] = random_range(1, 100);

                    if (sum_c < array_digs[3])
                    {
                        array_signs[3] = 2;
                        array_digs[4] = array_digs[3] - sum_c;
                    }
                    else
                    {
                        array_signs[3] = 1;
                        array_digs[4] = sum_c - array_digs[3];
                    }
                    if (array_signs[2] == 2)
                    {
                        if (array_signs[3] == 2)
                            array_signs[3] = 1;
                        else if (array_signs[3] == 1)
                            array_signs[3] = 2;
                    }
                }
                else
                {
                    if (c_random())
                        array_signs[3] = 3;
                    else
                        array_signs[3] = 4;

                    array_digs[3] = divide(sum_c, 900, array_signs[3]);
                    if (array_signs[3] == 4)
                        array_digs[4] = array_digs[3] / sum_c;

                    if (array_signs[3] == 3)
                        array_digs[4] = sum_c / array_digs[3];
                }

                question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]);

                if (array_signs[2] == 3 | array_signs[2] == 4)
                    question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " ( " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]) + " )";


                if (array_signs[1] == 3 | array_signs[1] == 4)
                {
                    question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " ( " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]) + " )";
                    if (array_signs[2] == 3 | array_signs[2] == 4)
                        question = Integer.toString(array_digs[1]) + " " + sign2string(array_signs[1]) + " ( " + Integer.toString(array_digs[2]) + " " + sign2string(array_signs[2]) + " ( " + Integer.toString(array_digs[3]) + " " + sign2string(array_signs[3]) + " " + Integer.toString(array_digs[4]) + " ) )";
                }

                break;

        }
        return question;
    }



    void resizeFont(){
        Display display = getWindowManager().getDefaultDisplay();
        //int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated

        float x = 0;
        if(height <= 800)
            x = 22;
        else
            x = 22 + (height - 800) / 220;

        qtxt.setTextSize(x);
        btn_1.setTextSize(x);
        btn_2.setTextSize(x);
        btn_3.setTextSize(x);
        btn_4.setTextSize(x);
        btn_5.setTextSize(x);
        btn_6.setTextSize(x);
        btn_7.setTextSize(x);
        btn_8.setTextSize(x);
        btn_9.setTextSize(x);
        sctxt.setTextSize(x);
        nbesttxt.setTextSize(x-10);

        yourtxt.setTextSize(x);
        yourscoretxt.setTextSize(x+10);
        bestscoretxt.setTextSize(x-4);
        tiltscore.setTextSize(2*x);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        words[0] = getString(R.string.word1);
        words[1] = getString(R.string.word2);
        words[2] = getString(R.string.word3);
        words[3] = getString(R.string.word4);
        words[4] = getString(R.string.word5);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        /*
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();*/

        mAdView.loadAd(adRequest);


        bestsound = false;
        check_hiscore = false;

        mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
        set_timer = mGameSettings.getInt(GAME_PREFERENCES_TIME_VOLUME, 0);
        if(set_timer == 0)
            set_timer = 6;
        else
            set_timer = (int)Math.round(set_timer * 0.6f);
        timeout = false;
        stoptimer = false;

        mp_ok = MediaPlayer.create(this, R.raw.ok);
        mp_lol = MediaPlayer.create(this, R.raw.smile);
        mp_mus = MediaPlayer.create(this, R.raw.mus);
        mp_wow = MediaPlayer.create(this, R.raw.wow);
        mp_time = MediaPlayer.create(this, R.raw.timeout);
        vol_mus =  mGameSettings.getInt(GAME_PREFERENCES_MUSIC_VOLUME, 0);
        vol_snd =  mGameSettings.getInt(GAME_PREFERENCES_SOUND_VOLUME, 0);

        hiscore =  mGameSettings.getInt(GAME_PREFERENCES_SCORE, 0);

        mp_mus.setLooping(true);
        float vol = (float) vol_mus / 100;
        mp_mus.setVolume(vol, vol);
        mp_mus.start();

        score = 0;
        getStage = 2;
        getDifficult = mGameSettings.getInt(GAME_PREFERENCES_DIFFICULT, 0);


        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Bold.ttf");
        type.isBold();
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


        sctxt = (TextView) findViewById(R.id.scoreText);
        sctxt.setTypeface(type);
        sctxt.setText(Integer.toString(score));

        tiltscore = (TextView) findViewById(R.id.scoreTextT);
        tiltscore.setTypeface(type);
        tiltscore.setText(Integer.toString(score));
        tiltscore.setVisibility(View.INVISIBLE);
        //str to int - Integer.parseInt(score.getText().toString()

        nbesttxt = (TextView) findViewById(R.id.newbest);
        nbesttxt.setTypeface(type);
        nbesttxt.setVisibility(View.INVISIBLE);

        qtxt = (TextView) findViewById(R.id.questtxt);
        qtxt.setTypeface(type);
        answer = random_range(1, 9);
        qtxt.setText(SetQuest(getDifficult, getStage, answer));

        aniscreen = (GifTextView) findViewById(R.id.gifTextView);

        arrow = (ImageView) findViewById(R.id.arrow);
        angle = 0;
        arrow.setRotation(angle);

        buttons();
        // game over button
        yourtxt = (TextView) findViewById(R.id.your);
        yourtxt.setTypeface(type);
        yourscoretxt = (TextView) findViewById(R.id.yourscores);
        yourscoretxt.setTypeface(type);
        bestscoretxt = (TextView) findViewById(R.id.bestscore);
        bestscoretxt.setTypeface(type);
        replaybtn = (ImageView) findViewById(R.id.replay);
        replaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }

        });

        exitbtn = (ImageView) findViewById(R.id.exit);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, MenuActivity.class));
                GameActivity.this.finish();
            }

        });

        hide_gameover();
        resizeFont();

        // process
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                if (!stoptimer) {
                                    angle = angle + 0.1f * 360 / set_timer;
                                    arrow.setRotation(angle);
                                    if(angle >= 360) {
                                        stoptimer = true;
                                        GameOver(0);
                                    }

                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    @Override
    public void onBackPressed() {
        mp_mus.stop();
        mp_lol.stop();
        mp_ok.stop();
        mp_time.stop();
        mp_wow.stop();
        stoptimer = true;
        startActivity(new Intent(GameActivity.this, MenuActivity.class));
        GameActivity.this.finish();
    }

    public void buttons(){
        btn_1 = (Button) findViewById(R.id.Button1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 1)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_2 = (Button) findViewById(R.id.Button2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 2)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_3 = (Button) findViewById(R.id.Button3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 3)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_4 = (Button) findViewById(R.id.Button4);
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 4)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_5 = (Button) findViewById(R.id.Button5);
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 5)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_6 = (Button) findViewById(R.id.Button6);
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 6)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_7 = (Button) findViewById(R.id.Button7);
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 7)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_8 = (Button) findViewById(R.id.Button8);
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 8)
                    Win();
                else
                    GameOver(1);
            }

        });
        btn_9 = (Button) findViewById(R.id.Button9);
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 9)
                    Win();
                else
                    GameOver(1);
            }

        });
    }

    public void block_buttons(){
        btn_1.setEnabled(false);
        btn_2.setEnabled(false);
        btn_3.setEnabled(false);
        btn_4.setEnabled(false);
        btn_5.setEnabled(false);
        btn_6.setEnabled(false);
        btn_7.setEnabled(false);
        btn_8.setEnabled(false);
        btn_9.setEnabled(false);
    }

    public void unblock_buttons(){
        btn_1.setEnabled(true);
        btn_2.setEnabled(true);
        btn_3.setEnabled(true);
        btn_4.setEnabled(true);
        btn_5.setEnabled(true);
        btn_6.setEnabled(true);
        btn_7.setEnabled(true);
        btn_8.setEnabled(true);
        btn_9.setEnabled(true);
    }

    public void hide_buttons(){
        btn_1.setVisibility(View.INVISIBLE);
        btn_2.setVisibility(View.INVISIBLE);
        btn_3.setVisibility(View.INVISIBLE);
        btn_4.setVisibility(View.INVISIBLE);
        btn_5.setVisibility(View.INVISIBLE);
        btn_6.setVisibility(View.INVISIBLE);
        btn_7.setVisibility(View.INVISIBLE);
        btn_8.setVisibility(View.INVISIBLE);
        btn_9.setVisibility(View.INVISIBLE);
    }

    public void unhide_buttons(){
        btn_1.setVisibility(View.VISIBLE);
        btn_2.setVisibility(View.VISIBLE);
        btn_3.setVisibility(View.VISIBLE);
        btn_4.setVisibility(View.VISIBLE);
        btn_5.setVisibility(View.VISIBLE);
        btn_6.setVisibility(View.VISIBLE);
        btn_7.setVisibility(View.VISIBLE);
        btn_8.setVisibility(View.VISIBLE);
        btn_9.setVisibility(View.VISIBLE);
    }

    public void hide_gameover(){
        yourtxt.setVisibility(View.GONE);
        yourscoretxt.setVisibility(View.GONE);
        bestscoretxt.setVisibility(View.GONE);
        replaybtn.setVisibility(View.GONE);
        exitbtn.setVisibility(View.GONE);
    }

    public void unhide_gameover(){
        yourscoretxt.setText(Integer.toString(score));
        bestscoretxt.setText(getString(R.string.best_record) + " " + Integer.toString(hiscore));
        yourtxt.setVisibility(View.VISIBLE);
        yourscoretxt.setVisibility(View.VISIBLE);
        bestscoretxt.setVisibility(View.VISIBLE);
        replaybtn.setVisibility(View.VISIBLE);
        exitbtn.setVisibility(View.VISIBLE);
    }

    public void Win(){
        stoptimer = true;
        score++;
        sctxt.setText(Integer.toString(score));
        tiltscore.setText(Integer.toString(score));
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.tilttext);
        tiltscore.setVisibility(View.VISIBLE);
        tiltscore.startAnimation(anim);
        block_buttons();
        aniscreen.setBackgroundResource(R.drawable.okanim);

        if(score > hiscore && !check_hiscore) {
            nbesttxt.setVisibility(View.VISIBLE);
            bestsound = true;
            check_hiscore = true;
        }


        int vc = random_range(0, 4);
        qtxt.setText(words[vc]);

        if(!bestsound) {
            mp_ok.setLooping(false);
            float vol = (float) vol_snd / 100;
            mp_ok.setVolume(vol, vol);
            mp_ok.start();
        } else {
            mp_wow.setLooping(false);
            float vol = (float) vol_snd / 100;
            mp_wow.setVolume(vol, vol);
            mp_wow.start();
            bestsound = false;
        }

        //block buttons

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                aniscreen.setBackgroundResource(R.drawable.stay);
                answer = random_range(1, 9);
                if(score >= 0 & score <20)
                    getStage = 2;
                else if(score >= 20 & score < 60)
                    getStage = 3;
                else if(score >= 60)
                    getStage = 4;
                qtxt.setText(SetQuest(getDifficult, getStage, answer));
                unblock_buttons();
                angle = 0;
                tiltscore.setVisibility(View.INVISIBLE);
                stoptimer = false;
            }
        }, 800);
     }

    public void GameOver(int e){

        stoptimer = true;

        if(score > hiscore) {
            SharedPreferences.Editor editor = mGameSettings.edit();
            editor.putInt(GAME_PREFERENCES_SCORE, score);
            editor.commit();
            hiscore = score;
        }

        hide_buttons();

        if(e == 1) {
            aniscreen.setBackgroundResource(R.drawable.lolanim);
            mp_lol.setLooping(false);
            float vol = (float) vol_snd / 100;
            mp_lol.setVolume(vol, vol);
            mp_lol.start();
        } else {
            aniscreen.setBackgroundResource(R.drawable.timeout);
            qtxt.setText("...");
            mp_time.setLooping(false);
            float vol = (float) vol_mus / 100;
            mp_time.setVolume(vol, vol);
            mp_time.start();
        }

        unhide_gameover();

    }

    public void restartGame(){
        aniscreen.setBackgroundResource(R.drawable.stay);
        hide_gameover();
        unhide_buttons();
        angle = 0;
        timeout = false;
        stoptimer = false;
        score = 0;
        sctxt.setText(Integer.toString(score));
        tiltscore.setText(Integer.toString(score));
        nbesttxt.setVisibility(View.INVISIBLE);
        getStage = 2;
        answer = random_range(1, 9);
        qtxt.setText(SetQuest(getDifficult, getStage, answer));
    }



    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}
