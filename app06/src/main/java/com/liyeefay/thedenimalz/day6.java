package com.liyeefay.thedenimalz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static android.content.Context.MODE_PRIVATE;

public class day6 extends Fragment {

    Button back;
    TextView bob, chicken, brian, gumaknae, drum, blank, day6, myday, date;
    Member sj, jh, yk, wp, dw;
    ImageView s, j, y, w, d;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.day6, container, false);

        day6 = view.findViewById(R.id.title);
        myday = view.findViewById(R.id.subtitle);
        date = view.findViewById(R.id.info);
        back = view.findViewById(R.id.home);
        bob = view.findViewById(R.id.sungjin);
        chicken = view.findViewById(R.id.jae);
        brian = view.findViewById(R.id.youngk);
        gumaknae = view.findViewById(R.id.wonpil);
        drum = view.findViewById(R.id.dowoon);
        blank = view.findViewById(R.id.space);
        s = view.findViewById(R.id.psj);
        j = view.findViewById(R.id.pjh);
        y = view.findViewById(R.id.kyh);
        w = view.findViewById(R.id.kwp);
        d = view.findViewById(R.id.ydw);

        Gson gson = new GsonBuilder().create();
        sj = gson.fromJson(getString(R.string.sungjin), Member.class);
        jh = gson.fromJson(getString(R.string.jae), Member.class);
        yk = gson.fromJson(getString(R.string.youngk), Member.class);
        wp = gson.fromJson(getString(R.string.wonpil), Member.class);
        dw = gson.fromJson(getString(R.string.dowoon), Member.class);

        bob.setText(sj.toString() + "\n");
        chicken.setText(jh.toString() + "\n");
        brian.setText(yk.toString() + "\n");
        gumaknae.setText(wp.toString() + "\n");
        drum.setText(dw.toString() + "\n");

        bob.setGravity(Gravity.CENTER);
        chicken.setGravity(Gravity.CENTER);
        brian.setGravity(Gravity.CENTER);
        gumaknae.setGravity(Gravity.CENTER);
        drum.setGravity(Gravity.CENTER);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((homepage)getActivity()).setViewPager(0);
            }
        });

        return view;
    }
}
