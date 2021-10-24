package com.example.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button bLang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLang = findViewById(R.id.bRun);
        bLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.toast_string, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, getScreenOrientation(newConfig), Toast.LENGTH_SHORT).show();
    }
    private String getScreenOrientation(Configuration сonfig){
        if(сonfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            return "Портретная ориентация";
        else if (сonfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            return "Альбомная ориентация";
        return "";
    }
}