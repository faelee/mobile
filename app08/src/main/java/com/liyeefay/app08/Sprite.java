package com.liyeefay.app08;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends RectF {

    private static final int BMP_COLUMNS = 4;
    private static final int BMP_ROWS = 4;
    private static final int DOWN=0, LEFT=1, RIGHT=2, UP=3;
    private int dX, dY;
    private Bitmap bitmap;
    private int currentFrame=0, iconWidth, iconHeight, animationDelay=20;


    public Sprite(float left, float top, float right, float bottom, int dX, int dY) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
    }

    public Sprite() {
        this(50, 50, 100, 100, 5,5);
    }

    public Sprite(RectF r){
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
            currentFrame = ++currentFrame % BMP_COLUMNS;//cycles current image with boundary proteciton
            animationDelay=20;//arbitrary delay before cycling to next image
        }
    }

    public void draw(Canvas canvas){
        if(bitmap==null) {//if no bitmap exists draw a red circle
            Paint paint = new Paint();
            paint.setColor(Color.RED);//sets its color
            canvas.drawCircle(centerX(), centerY(), width() / 2, paint);//draws circle
        }else {
            iconWidth = bitmap.getWidth() / BMP_COLUMNS;//calculate width of 1 image
            iconHeight = bitmap.getHeight() / BMP_ROWS; //calculate height of 1 image
            int srcX = currentFrame * iconWidth;       //set x of source rectangle inside of bitmap based on current frame
            int srcY = getAnimationRow() * iconHeight; //set y to row of bitmap based on direction
            Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);  //defines the rectangle inside of heroBmp to displayed
            canvas.drawBitmap(bitmap,src, this,null); //draw an image
        }
    }

    private int getAnimationRow() {
        if (Math.abs(dX)>Math.abs(dY)){
            if(Math.abs(dX)==dX) return RIGHT;
            else return LEFT;
        } else if(Math.abs(dY)==dY) return DOWN;
        else return UP;

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
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap, Canvas canvas) {
        this.bitmap = bitmap;
        int w = canvas.getWidth()/2;
        int h = canvas.getHeight()/2;
        this.set(w, h, w + bitmap.getWidth()/3, h + bitmap.getHeight()/3);
    }
}
