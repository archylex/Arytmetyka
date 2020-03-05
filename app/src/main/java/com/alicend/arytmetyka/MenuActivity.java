package com.alicend.arytmetyka;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MenuActivity extends ArytmetykaActivity {
    TextView mainlogo;

    void resizeFont(){
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();  // deprecated

        float x = 0;
        if(height <= 800)
            x = 26;
        else
            x = 26 + (height - 800) / 220;

        mainlogo.setTextSize(x);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Typeface type = Typeface.createFromAsset(getAssets(),"fonts/academy-bold.ttf");
        mainlogo = (TextView) findViewById(R.id.mainlogo);

        //String language = Locale.getDefault().getDisplayLanguage();
        //String language = Locale.getDefault().getLanguage();

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
        else if (language.equals("uk"))
        {
            type = Typeface.createFromAsset(getAssets(),"fonts/academy-bold.ttf");
        }
        else {
            type = Typeface.createFromAsset(getAssets(),"fonts/NotoSans-Bold.ttf");
        }



        mainlogo.setTypeface(type);

        ImageView settings_btn = (ImageView) findViewById(R.id.imageButton3);
        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
                MenuActivity.this.finish();
            }

        });

        ImageView game_btn = (ImageView) findViewById(R.id.imageButton);
        game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, GameActivity.class));
                MenuActivity.this.finish();
            }

        });

        ImageView exit_btn = (ImageView) findViewById(R.id.imageButton2);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }

        });

        resizeFont();

    }

}
