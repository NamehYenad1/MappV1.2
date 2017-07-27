package com.mappassignment.android.mapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;



/**
 * Created by Strife on 5/16/2017.
 */

public class Splash extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private int progress = 0;
    private final int mProgMax = 3;
    final Handler h = new Handler();
    @Override
    protected void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.splash);
        final ProgressBar mProg =(ProgressBar) findViewById(R.id.progressBar2);
        mProg.setMax(mProgMax);
        final Thread pBarThread = new Thread() {
            @Override
            public void run() {
                try {
                    while(progress<=mProgMax) {
                        mProg.setProgress(progress);
                        sleep(1000);
                        ++progress;
                        if (progress == mProgMax){
                            Intent i = new Intent(Splash.this,LogIn.class  );
                            startActivity(i);
                        }
                    }
                }
                catch(InterruptedException e) {
                }
            }
        };

        pBarThread.start();

    }

    @Override
    protected void onStop(){
        super.onStop();

            finish();

    }



}
