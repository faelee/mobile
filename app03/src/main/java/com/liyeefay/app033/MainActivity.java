package com.liyeefay.app033;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sP;
    SharedPreferences.Editor edit;
    Button topL, topR, bottomL, bottomR, reset, enter;
    TextView display;
    EditText editor;
    SeekBar seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sP = getSharedPreferences("Preferences", MODE_PRIVATE);
        edit = sP.edit();
        reset = findViewById(R.id.zero);
        display = findViewById(R.id.text);
        editor = findViewById(R.id.edit);
        enter = findViewById(R.id.set);

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
                display.setText("font size: " + progress);

                String out;
                if(progress < 15) {
                    out = "your font size is too small!";
                    Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
                }
                else if(progress > 60) {
                    out = "your font size is too big!";
                    Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
                }
                else {
                    out = "your font size is just right!";
                    Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
                }
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

        edit.putString("" + topL.getId(), "top left button was clicked: ");
        edit.putString("" + topR.getId(), "top right button was clicked: ");
        edit.putString("" + bottomR.getId(), "bottom right button was clicked: ");
        edit.putString("" + bottomL.getId(), "bottom left button was clicked: ");
        edit.apply();

        topL.setText("" + sP.getInt("tL", 0));
        topR.setText("" + sP.getInt("tR", 0));
        bottomL.setText("" + sP.getInt("bL", 0));
        bottomR.setText("" + sP.getInt("bR", 0));
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        int next = 1 + Integer.parseInt("" + b.getText());
        b.setText("" + next);

        String display = sP.getString("" + b.getId(), "oops error") + next + " clicks";
        Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();

        edit.putInt("tL", Integer.parseInt("" + topL.getText()));
        edit.putInt("tR", Integer.parseInt("" + topR.getText()));
        edit.putInt("bL", Integer.parseInt("" + bottomL.getText()));
        edit.putInt("bR", Integer.parseInt("" + bottomR.getText()));
        edit.apply();
    }

    public void clear(View v){
        edit.putInt("tL", 0);
        edit.putInt("tR", 0);
        edit.putInt("bL", 0);
        edit.putInt("bR", 0);
        edit.apply();
        topL.setText("" + sP.getInt("tL", 0));
        topR.setText("" + sP.getInt("tR", 0));
        bottomL.setText("" + sP.getInt("bL", 0));
        bottomR.setText("" + sP.getInt("bR", 0));
        Toast.makeText(getApplicationContext(), "All back to 0 clicks!", Toast.LENGTH_SHORT).show();
    }

    public void setter(View v){
        int a = Integer.parseInt("" + editor.getText());
        display.setText("font size: " + a);
        topL.setTextSize(a);
        topR.setTextSize(a);
        bottomL.setTextSize(a);
        bottomR.setTextSize(a);
        editor.setText("");
        editor.setHint("specify your font size here");

        String out;
        if(a < 15) {
            out = "your font size is too small!";
            Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
        }
        else if(a > 60) {
            out = "your font size is too big!";
            Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
        }
        else {
            out = "your font size is just right!";
            Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
        }
    }
}
