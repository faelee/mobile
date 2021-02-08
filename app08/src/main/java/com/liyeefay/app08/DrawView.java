package com.liyeefay.app08;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawView extends View {
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int x = 0;
    private Paint p = new Paint();
    private Paint txt = new Paint();
    private Typeface tf;
    int w = 50, h = 50;
    Sprite misaki = new Sprite();
    ArrayList<Icon> stalkers = new ArrayList<Icon>();
    Icon taku = new Icon(stalkers);
    Bitmap misakisprite = BitmapFactory.decodeResource(getResources(), R.drawable.misakipurple);


    Bitmap t = BitmapFactory.decodeResource(getResources(), R.drawable.usui);
    Bitmap usuii = Bitmap.createScaledBitmap(t, t.getWidth()/2, t.getHeight()/2, false);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tf = Typeface.createFromAsset(this.getResources().getAssets(), "smiley_cat.otf");
        txt.setColor(Color.BLACK);
        txt.setTypeface(tf);
        txt.setTextSize(72);

        w = canvas.getWidth()/2;
        h = canvas.getHeight()/2;

        p.setColor(Color.rgb(249,233, 88));
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);


        misaki.update(canvas);
        if(misaki.getBitmap() == null){
            misaki.setBitmap(misakisprite, canvas);
        }
        misaki.draw(canvas);

        for(int i = 0; i < stalkers.size(); i++){
            Icon takumi = stalkers.get(i);
            takumi.update(canvas);
            if(takumi.getBitmap() == null){
                takumi.setBitmap(usuii, canvas.getWidth() /2, canvas.getHeight() /2);
            }
            takumi.draw(canvas);
            if(RectF.intersects(takumi, misaki)){
                for(int k = 0; k < stalkers.size(); k++) {
                    Icon tu = stalkers.get(k);
                    tu.setdX(0);
                    tu.setdY(0);
                }
                misaki.setdX(0);
                misaki.setdY(0);
                x = 0;
                canvas.drawText("Game Over! :( Tap to Play Again!", 50, canvas.getHeight()/2, txt);
            }
        }

        x++;
        if(x % 500 == 0){
            Icon temp = new Icon(stalkers);
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_UP){
            misaki.setdX(5);
            misaki.setdY(5);
            Bitmap bitmap = misaki.getBitmap();
            misaki.set(w, h, w + bitmap.getWidth()/3, h + bitmap.getHeight()/3);

            stalkers = new ArrayList<Icon>();
            stalkers.add(taku);
            taku.setdX(5);
            taku.setdY(5);
            Bitmap bitt = taku.getBitmap();
            int width = (int)(Math.random() * 200 + 20);
            int height = (int)(Math.random() * 200 + 20);
            taku.set(width, height, width + bitt.getWidth(), height + bitt.getWidth());

            x = 0;
        }

        return true;
    }
}
