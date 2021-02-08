package com.liyeefay.thedenimalz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class Activity_Main extends Fragment {

    SharedPreferences sP;
    SharedPreferences.Editor edit;
    Button clicker, nexter, more, back;
    TextView text;
    EditText editor;
    ImageView den;
    InputStream inputstream;
    Bitmap bitmap;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_homepage, container, false);
        sP = ((homepage)getActivity()).getSharedPreferences("Preferences", homepage.MODE_PRIVATE);
        edit = sP.edit();
        clicker = view.findViewById(R.id.submit);
        more = view.findViewById(R.id.page);
        nexter = view.findViewById(R.id.next);
        text = view.findViewById(R.id.display);
        editor = view.findViewById(R.id.edit);
        den = view.findViewById(R.id.pic);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((homepage)getActivity()).setViewPager(0);
            }
        });

        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(editor.getText());
                text.setText(s);
                if (s.equalsIgnoreCase("bang")) {
                    edit.putInt("count", 0);
                    den.setImageResource(R.drawable.bang);
                    text.setText(s);
                } else if (s.equalsIgnoreCase("jje")) {
                    edit.putInt("count", 1);
                    den.setImageResource(R.drawable.jje);
                    text.setText(s);
                } else if (s.equalsIgnoreCase("ke")) {
                    edit.putInt("count", 2);
                    den.setImageResource(R.drawable.ke);
                    text.setText(s);
                } else if (s.equalsIgnoreCase("pil")) {
                    edit.putInt("count", 3);
                    den.setImageResource(R.drawable.pil);
                    text.setText(s);
                } else if (s.equalsIgnoreCase("don")) {
                    edit.putInt("count", 4);
                    den.setImageResource(R.drawable.don);
                    text.setText(s);
                } else {
                    text.setText("That's not a Denimal!");
                    edit.putInt("count", 5);
                    den.setImageResource(R.drawable.day6);
                }
                edit.apply();
                editor.setText("");
                editor.setHint("which Denimal?");
            }
        });

        nexter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = sP.getInt("count", 5) + 1;
                Resources res = getResources();
                String[] day6 = res.getStringArray(R.array.day6_array);
                if (count >= day6.length - 1)
                    count = 0;
                edit.putInt("count", count);
                edit.apply();
                show();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((homepage)getActivity()).setViewPager(1);
            }
        });

        show();
        return view;
    }


    public void show() {
        int count = sP.getInt("count", 5);
        Resources res = getResources();
        String[] day6 = res.getStringArray(R.array.day6_array);
        text.setText(day6[count]);

        if (count == 0)
            den.setImageResource(R.drawable.bang);
        else if (count == 1)
            den.setImageResource(R.drawable.jje);
        else if (count == 2)
            den.setImageResource(R.drawable.ke);
        else if (count == 3)
            den.setImageResource(R.drawable.pil);
        else if (count == 4)
            den.setImageResource(R.drawable.don);
        else
            den.setImageResource(R.drawable.day6);
    }

}

