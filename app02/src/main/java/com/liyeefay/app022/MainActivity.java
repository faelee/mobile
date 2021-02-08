package com.liyeefay.app022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button clicker, nexter;
    TextView textDisplay, arrayText;
    EditText editor;
    ImageView den;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clicker = findViewById(R.id.submit);
        nexter = findViewById(R.id.next);
        arrayText = findViewById(R.id.arrayDisplay);
        textDisplay = findViewById(R.id.display);
        editor = findViewById(R.id.edit);
        den = findViewById(R.id.pic);
    }

    public void enter(View view) {
        textDisplay.setText(editor.getText() + getString(R.string.fave));
        editor.setText("");
        editor.setHint("Type here!");
    }

    public void cycle(View view) {
        Resources res = getResources();
        String[] skz = res.getStringArray(R.array.day6_array);

        if(count == 0)
            den.setImageResource(R.drawable.bang);
        else if(count == 1)
            den.setImageResource(R.drawable.jje);
        else if(count == 2)
            den.setImageResource(R.drawable.ke);
        else if(count == 3)
            den.setImageResource(R.drawable.pil);
        else
            den.setImageResource(R.drawable.don);

        arrayText.setText(skz[count++]);
        if(count >= skz.length) {
            count = 0;
        }
    }
}
