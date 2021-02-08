package com.liyeefay.app10;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Hashtable;
import java.util.Set;

public class Screen3 extends AppCompatActivity {
    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 0;

    private Button btnUpload;
    private TextView back;
    private TextView color;
    private ImageView imageView;
    private Bitmap bitmap;
    public Hashtable<String, int[]> dictColors = new Hashtable<String, int[]>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }

        btnUpload = findViewById(R.id.btnUpload);
        back = findViewById(R.id.back);
        imageView = findViewById(R.id.imageView);
        color = findViewById(R.id.color);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Screen3.this, Screen2.class);
                startActivity(intent);
            }
        });

        dictColors.put("dark red", new int[]{102, 0, 0});
        dictColors.put("red", new int[]{255, 0, 0});
        dictColors.put("light red", new int[]{255, 153, 153});
        dictColors.put("dark orange", new int[]{153, 76, 0});
        dictColors.put("orange", new int[]{255, 128, 0});
        dictColors.put("light orange", new int[]{255, 178, 102});
        dictColors.put("dark yellow", new int[]{102, 102, 0});
        dictColors.put("yellow", new int[]{255, 255, 0});
        dictColors.put("light yellow", new int[]{255, 255, 102});
        dictColors.put("lime green", new int[]{128, 255, 0});
        dictColors.put("dark green", new int[]{0, 102, 0});
        dictColors.put("green", new int[]{0, 255, 0});
        dictColors.put("light green", new int[]{153, 255, 153});
        dictColors.put("mint green", new int[]{0, 255, 128});
        dictColors.put("turquoise", new int[]{0, 255, 255});
        dictColors.put("teal", new int[]{0, 102, 102});
        dictColors.put("cerulean", new int[]{0, 128, 255});
        dictColors.put("dark blue", new int[]{0, 0, 102});
        dictColors.put("blue", new int[]{0, 0, 255});
        dictColors.put("light blue", new int[]{153, 204, 255});
        dictColors.put("indigo", new int[]{58, 18, 173});
        dictColors.put("dark purple", new int[]{51, 0, 102});
        dictColors.put("purple", new int[]{127, 0, 255});
        dictColors.put("light purple", new int[]{204, 153, 255});
        dictColors.put("magenta", new int[]{255, 0, 255});
        dictColors.put("dark pink", new int[]{102, 0, 51});
        dictColors.put("pink", new int[]{255, 0, 127});
        dictColors.put("light pink", new int[]{255, 153, 204});
        //dictColors.put("black", new int[]{0, 0, 0});
        //dictColors.put("white", new int[]{255, 255, 255});
        dictColors.put("brown", new int[]{102, 51, 0});
        dictColors.put("beige", new int[]{245, 245, 220});
        dictColors.put("tan", new int[]{210, 180, 140});
        dictColors.put("dark gray", new int[]{64, 64, 64});
        dictColors.put("gray", new int[]{128, 128, 128});
        dictColors.put("light gray", new int[]{192, 192, 192});

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = imageView.getDrawingCache();
                    int pixel = bitmap.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //color.setBackgroundColor(Color.rgb(r,g,b));
                    color.setText("R("+r+")"+"G("+g+")"+"B("+b+")\n" + determineColor(dictColors, r, g, b));
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap bit = BitmapFactory.decodeFile(picturePath);
                    //Bitmap newbit = Bitmap.createScaledBitmap(bit, bit.getWidth()/3,
                           // bit.getHeight()/3, false);
                    imageView.setImageBitmap(bit);
                }
        }
    }

    public String determineColor(Hashtable<String, int[]> dict, int r, int g, int b){
        Set<String> keys = dict.keySet();
        double dist = Math.sqrt(3*Math.pow(255, 2));
        String closestColor = "";
        if(r==0 && g==0 && b==0)
            return "black";
        if(r==255 && g==255 && b==255)
            return "white";
        for(String color: keys){
            int[] rgb = dict.get(color);
            if(rgb == null)
                return "";
            double temp = Math.sqrt(Math.pow(r-rgb[0], 2) + Math.pow(g-rgb[1], 2) + Math.pow(b-rgb[2], 2));
            if(temp < dist){
                dist = temp;
                closestColor = color;
            }
            if(temp == 0)
                break;
        }
        return closestColor;
    }
}
