package com.liyeefay.app08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button u, d, l, r;
    TextView text;
    DrawView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        text = findViewById(R.id.display);
        view = findViewById(R.id.drawView);
        u = findViewById(R.id.up);
        d = findViewById(R.id.down);
        l = findViewById(R.id.left);
        r = findViewById(R.id.right);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.misaki.getdX() > 0)
                    view.misaki.setdX(view.misaki.getdX() * -1);
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.misaki.getdX() < 0)
                    view.misaki.setdX(view.misaki.getdX() * -1);
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.misaki.getdY() < 0)
                    view.misaki.setdY(view.misaki.getdY() * -1);
            }
        });

        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.misaki.getdY() > 0)
                    view.misaki.setdY(view.misaki.getdY() * -1);
            }
        });
    }
}
