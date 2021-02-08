package com.liyeefay.app07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class DrawView extends View{

    private Paint p = new Paint();
    private Paint w = new Paint();
    private Paint b = new Paint();
    private Paint t = new Paint();
    private int x = 0, dx = 5, dr = 1, r = 0;
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float midx = getWidth()/2.0f;
        float cx = getWidth()/3.0f;
        float cy = getHeight()/3.0f;
        float midy = getHeight()/2.0f;
        p.setColor(Color.rgb(142, 176, 250));
        b.setColor(Color.BLACK);
        w.setColor(Color.WHITE);

        int c1 = 0;
        canvas.drawRect(0,0, getWidth(), getHeight(), p);
        for(int width = (int)(Math.random() * 10 + 20); width <= getWidth(); width += (int)(Math.random() * 50 + 50)){
            for(int height = (int)(Math.random() * 10 + 20); height <= midy; height += (int)(Math.random() * 50 + 50)){
                c1++;
                if(c1 % 4 == 3){
                    canvas.drawCircle(width, height, 10, w);
                }
                else if(c1 % 4 == 2){
                    canvas.drawCircle(width + 25, height, 10, w);
                }
                else if(c1 % 4 == 1){
                    canvas.drawCircle(width, height + 20, 10, w);
                }
                else{
                    canvas.drawCircle(width + 15, height + 15, 10, w);
                }
            }
        }


        p.setColor(Color.rgb(230, 242, 255));
        canvas.drawRect(0, midy - 100, getWidth(), getHeight(), p);

        canvas.drawOval(-105, midy - 205, cx + 5, midy + 205, p);
        canvas.drawOval(-100, midy - 200, cx, midy + 200, p);
        canvas.drawOval(cx + 195, midy - 305, getWidth() + 105, midy + 305, p);
        canvas.drawOval(cx + 200, midy - 300, getWidth() + 100, midy + 300, p);

        canvas.drawOval(cx - 60, midy - 45, cx + 160, midy + 335, b);
        canvas.drawOval(cx - 50, midy - 35, cx + 150, midy + 325, w);
        canvas.drawOval(2 * cx - 160, midy - 45, 2 * cx + 60, midy + 335, b);
        canvas.drawOval(2 * cx - 150, midy - 35, 2 * cx + 50, midy + 325, w);

        canvas.drawOval(midx - 385, midy - 435, midx + 385, midy + 235, b);
        canvas.drawOval(midx - 375, midy - 425, midx + 375, midy + 225, w);
        canvas.drawOval(midx - 225, midy - 175, midx + 225, midy + 230, b);
        canvas.drawOval(midx - 220, midy - 170, midx + 220, midy + 225, w);

        canvas.drawCircle(midx - 130, cy - 130, 85f, b);
        canvas.drawCircle(midx + 130, cy - 130, 85f, b);
        canvas.drawCircle(midx - 130, cy - 130, 75f, w);
        canvas.drawCircle(midx + 130, cy - 130, 75f, w);

        canvas.drawCircle(midx, cy,210f,b);
        canvas.drawCircle(midx, cy,200f,w);
        canvas.drawOval(midx - 90, cy + 35, midx + 90, cy + 165, b);
        canvas.drawOval(midx - 85, cy + 40, midx + 85, cy + 160, w);

        canvas.drawCircle(midx - 100, cy, 15, b);
        canvas.drawCircle(midx + 100, cy, 15, b);
        canvas.drawOval(midx - 40, cy + 65, midx + 40, cy + 105, b);

        p.setColor(Color.rgb(207,225,255));
        canvas.drawRect(0, getHeight() - 150, x, getHeight() - 100, p);
        canvas.drawCircle(x, getHeight() - 100 - r, r, w);
        x += dx;
        r += dr;
        if( x - r  >= getWidth()) {
            x = 0;
            r = 0;
        }
        invalidate();
    }
}
