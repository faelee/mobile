package com.liyeefay.thedenimalz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;

public class homepage extends AppCompatActivity {

    private SectionStatePagerAdapter pager;
    private ViewPager vp;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        pager = new SectionStatePagerAdapter(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.container);
        setupViewPager(vp);
        put();

        vp.setCurrentItem(0);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Activity_Main(), "Homepage");
        adapter.addFragment(new day6(), "DAY6");
        viewPager.setAdapter(adapter);

    }

    public void setViewPager(int num){
        vp.setCurrentItem(num);
    }


    @Override
    public void onPause() {
        super.onPause();
        put();
    }

    @Override
    public void onStart() {
        super.onStart();
        put();
    }

    @Override
    public void onResume() {
        super.onResume();
        put();
    }

    @Override
    public void onStop() {
        super.onStop();
        put();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        put();
    }

    public void put() {
        int count = sharedPreferences.getInt("count", 5);
        System.out.println(count);
        editor.putInt("count", count);
        editor.apply();
    }
}