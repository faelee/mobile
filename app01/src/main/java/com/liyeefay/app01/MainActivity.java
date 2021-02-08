package com.liyeefay.app01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button clicky;
    int count = 0;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clicky = findViewById(R.id.cookie);
        display = findViewById(R.id.text);
        clicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("where the cookie?");
                Log.i("clickResponse", "oopsie no cookie");
                display.setText("oopsie no cookie but lookie number! "+(++count));
            }
        });
    }

    public void tap(View view) {
        display.setText("oopsie no tootsie but lookie number! "+(--count));
    }
}