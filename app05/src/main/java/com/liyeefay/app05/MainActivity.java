package com.liyeefay.app05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sP;
    SharedPreferences.Editor edit;
    Button topL, topR, bottomL, bottomR;
    SeekBar seek;
    TextView createC, startC, resumeC, pauseC, stopC, restartC, destroyC;
    TextView createT, startT, resumeT, pauseT, stopT, restartT, destroyT;
    int cCreate=0;
    int cStart=0;
    int cResume=0;
    int cPause=0;
    int cStop=0;
    int cRestart=0;
    int cDestroy=0;
    int tCreate=0;
    int tStart=0;
    int tResume=0;
    int tPause=0;
    int tStop=0;
    int tRestart=0;
    int tDestroy=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sP = getSharedPreferences("Preferences", MODE_PRIVATE);
        edit = sP.edit();

        createC = findViewById(R.id.create);
        startC = findViewById(R.id.start);
        resumeC = findViewById(R.id.resume);
        pauseC = findViewById(R.id.pause);
        stopC = findViewById(R.id.stop);
        restartC = findViewById(R.id.restart);
        destroyC = findViewById(R.id.destroy);
        createT = findViewById(R.id.createTotal);
        startT = findViewById(R.id.startTotal);
        resumeT = findViewById(R.id.resumeTotal);
        pauseT = findViewById(R.id.pauseTotal);
        stopT = findViewById(R.id.stopTotal);
        restartT = findViewById(R.id.restartTotal);
        destroyT = findViewById(R.id.destroyTotal);
        topL = findViewById(R.id.topLeft);
        topL.setOnClickListener(this);
        bottomL = findViewById(R.id.bottomLeft);
        bottomL.setOnClickListener(this);
        topR = findViewById(R.id.topRight);
        topR.setOnClickListener(this);
        bottomR = findViewById(R.id.bottomRight);
        bottomR.setOnClickListener(this);
        seek = findViewById(R.id.seekBar);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                topL.setTextSize(progress);
                topR.setTextSize(progress);
                bottomL.setTextSize(progress);
                bottomR.setTextSize(progress);
                //Toast.makeText(getApplicationContext(), "font size: " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });

       setInitial();

        cCreate++;
        tCreate++;
        put();
        show();
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        int next = 1 + Integer.parseInt("" + b.getText());
        b.setText("" + next);

        String display = sP.getString("" + b.getId(), "oops error") + next + " clicks";
        Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();

        put();
        show();
    }

    public void setInitial(){
        edit.putString("" + topL.getId(), "top left button was clicked: ");
        edit.putString("" + topR.getId(), "top right button was clicked: ");
        edit.putString("" + bottomR.getId(), "bottom right button was clicked: ");
        edit.putString("" + bottomL.getId(), "bottom left button was clicked: ");

        topL.setText("" + sP.getInt("tL", 0));
        topR.setText("" + sP.getInt("tR", 0));
        bottomL.setText("" + sP.getInt("bL", 0));
        bottomR.setText("" + sP.getInt("bR", 0));
        cCreate=sP.getInt("onCreate", 0);
        cStart=sP.getInt("onStart", 0);
        cResume=sP.getInt("onResume", 0);
        cPause=sP.getInt("onPause", 0);
        cStop=sP.getInt("onStop", 0);
        cRestart=sP.getInt("onRestart", 0);
        cDestroy=sP.getInt("onDestroy", 0);

        put();
    }

    public void put() {
        edit.putInt("tL", Integer.parseInt("" + topL.getText()));
        edit.putInt("tR", Integer.parseInt("" + topR.getText()));
        edit.putInt("bL", Integer.parseInt("" + bottomL.getText()));
        edit.putInt("bR", Integer.parseInt("" + bottomR.getText()));
        edit.putInt("onCreate", cCreate);
        edit.putInt("onStart", cStart);
        edit.putInt("onResume", cResume);
        edit.putInt("onPause", cPause);
        edit.putInt("onStop", cStop);
        edit.putInt("onRestart", cRestart);
        edit.putInt("onDestroy", cDestroy);
        edit.apply();
    }

    public void show(){
        createC.setText("Current values onCreate: " + tCreate);
        startC.setText("Current values onStart: " + tStart);
        resumeC.setText("Current values onResume: " + tResume);
        pauseC.setText("Current values onPause: " + tPause);
        stopC.setText("Current values onStop: " + tStop);
        restartC.setText("Current values onRestart: " + tRestart);
        destroyC.setText("Current values onDestroy: " + tDestroy);
        createT.setText("Lifetime values onCreate: " + "" + sP.getInt("onCreate", 0));
        startT.setText("Lifetime values onStart: " + sP.getInt("onStart", 0));
        resumeT.setText("Lifetime values onResume: " + sP.getInt("onResume", 0));
        pauseT.setText("Lifetime values onPause: " + sP.getInt("onPause", 0));
        stopT.setText("Lifetime values onStop: " + sP.getInt("onStop", 0));
        restartT.setText("Lifetime values onRestart: " + sP.getInt("onRestart", 0));
        destroyT.setText("Lifetime values onDestroy: " + sP.getInt("onDestroy", 0));
    }

    @Override
    protected void onPause() {
        super.onPause();
        cPause++;
        tPause++;
        put();
        show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cStart++;
        tStart++;
        put();
        show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cResume++;
        tResume++;
        put();
        show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cStop++;
        tStop++;
        put();
        show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cRestart++;
        tRestart++;
        put();
        show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cDestroy++;
        tDestroy++;
        put();
        show();
    }
}
