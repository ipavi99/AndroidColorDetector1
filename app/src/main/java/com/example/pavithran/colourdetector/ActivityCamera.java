package com.example.pavithran.colourdetector;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.Log.d;

public class ActivityCamera extends AppCompatActivity {
    private Button btnCapture;
    private ImageView imgCapture;
    private boolean enter=false;
    private static final int Image_Capture_Code = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
//        TextView tw = (TextView) findViewById(R.id.camtext);
//        tw.setText("Open camera");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuitem = menu.getItem(1);
        menuitem.setChecked(true);


        btnCapture =(Button)findViewById(R.id.btnTakePicture);
        imgCapture = (ImageView) findViewById(R.id.capturedImage);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Capture_Code)
        {
            if (resultCode == RESULT_OK)
            {
                final Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);

                imgCapture.setVisibility(View.VISIBLE);
                d("previewCapturedImage", "before colorutils ");
                imgCapture.setImageBitmap(bp);
                imgCapture.setOnTouchListener(new View.OnTouchListener() {

                    Toast mytoast = Toast.makeText(getApplicationContext(),"On touchlistener",Toast.LENGTH_SHORT);
                    




                    @Override
                    public boolean onTouch(View view, MotionEvent event) {

                        float eventX = event.getX();
                        float eventY = event.getY();
                        float[] eventXY = new float[]{eventX, eventY};

                        Matrix invertMatrix = new Matrix();
                        ((ImageView) view).getImageMatrix().invert(invertMatrix);

                        invertMatrix.mapPoints(eventXY);
                        int x = Integer.valueOf((int) eventXY[0]);
                        int y = Integer.valueOf((int) eventXY[1]);
                        int pixel = bp.getPixel(x,y);
                        int red = Color.red(pixel);
                        int green = Color.green(pixel);
                        int blue = Color.blue(pixel);
                        int pixelColor = pixel;
                        String hexPixelColor = String.format("#%06X", (0xFFFFFF & pixel));
                        enter=true;
                        return true;
                    }



                });


            }


            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }

            if(enter)
            {
                Intent intentA1 = new Intent(ActivityCamera.this,SelectActivity.class) ;
                startActivity(intentA1);
            }
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.ic_home:
                        Intent intent0 = new Intent(ActivityCamera.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_camera:
                        break;

                    case R.id.ic_storage:
                        Intent intent2 = new Intent(ActivityCamera.this,ActivityStorage.class);
                        startActivity(intent2);

                        break;

                }

                return false;
            }
        });
    }
}
