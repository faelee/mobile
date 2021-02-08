package com.liyeefay.app08;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

public class Icon extends RectF {

    private static final int BMP_COLUMNS = 4;
    private static final int BMP_ROWS = 4;
    private static final int DOWN=0, LEFT=1, RIGHT=2, UP=3;
    private int dX, dY;
    private Bitmap takumi;
    private int currentFrame=0, iconWidth, iconHeight, animationDelay=20;

    public Icon(float left, float top, float right, float bottom, int dX, int dY) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
    }

    public Icon(ArrayList<Icon> stalkers) {
        this((int)(Math.random() * 100 + 1), (int)(Math.random() * 100 + 1),
                (int)(Math.random() * 100 + 11), (int)(Math.random() * 100 + 11), 5,5);
        stalkers.add(this);
    }

    public Icon(RectF r){
        super(r);
    }

    public void update(Canvas canvas) {
        if (left + dX < 0 || right + dX > canvas.getWidth()){
            dX *= -1;
        }
        if(bottom+dY>canvas.getHeight() || top+dY<0) {
            dY *= -1;
        }
        offset(dX,dY);//moves dX to the right and dY downwards
        if(animationDelay--<0) {//increment to next sprite image after delay
            animationDelay=20;//arbitrary delay before cycling to next image
        }
    }

    public void draw(Canvas canvas){
        if(takumi==null) {//if no bitmap exists draw a red circle
            Paint paint = new Paint();
            paint.setColor(Color.RED);//sets its color
            canvas.drawCircle(centerX(), centerY(), width() / 2, paint);//draws circle
        }else {
            canvas.drawBitmap(takumi, null, this, null);
        }
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public Bitmap getBitmap() {
        return takumi;
    }

    public void setBitmap(Bitmap bitmap, int width, int height) {
        this.takumi = bitmap;
        int w = (int)(Math.random() * width + 20);
        int h = (int)(Math.random() * height + 20);
        this.set(w, h, w + bitmap.getWidth(), h + bitmap.getWidth());
    }
}

