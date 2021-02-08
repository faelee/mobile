package com.liyeefay.app10;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

public class Screen4 extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView im;
    private Button capture;
    private TextView rgb, home;
    private String currentPhotoPath;
    private Bitmap bitmap;
    public Hashtable<String, int[]> dictColors = new Hashtable<String, int[]>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        im = findViewById(R.id.view);
        capture = findViewById(R.id.take);
        home = findViewById(R.id.back);
        rgb = findViewById(R.id.color);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_CAPTURE);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE_CAPTURE);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Screen4.this, Screen2.class);
                startActivity(intent);
            }
        });

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
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

        im.setDrawingCacheEnabled(true);
        im.buildDrawingCache(true);

        im.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = im.getDrawingCache();
                    int pixel = bitmap.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //rgb.setBackgroundColor(Color.rgb(r,g,b));
                    rgb.setText("R("+r+")"+"G("+g+")"+"B("+b+")\n" + determineColor(dictColors, r, g, b));
                }
                return true;
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d("mylog", "Exception while creating file: " + ex.toString());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Log.d("mylog", "Photofile not null");
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.liyeefay.app10.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.d("mylog", "Path: " + currentPhotoPath);
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bit = BitmapFactory.decodeFile(currentPhotoPath);
            bit = RotateBitmap(bit, 90);
            Bitmap newbit = Bitmap.createScaledBitmap(bit, bit.getWidth()/3,
                    bit.getHeight()/3, false);
            im.setImageBitmap(newbit);
        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
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
