package com.example.pavithran.colourdetector;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import static android.util.Log.d;

public class Activity2Camera extends AppCompatActivity {

    private Button btnCapture;
    private ImageView imgCapture;
    Button nextbutton;
    Bitmap bp;
    String hexPixelColor;
    int pixelColor;
    int j=0;
    private static final int Image_Capture_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_camera);
        nextbutton = findViewById(R.id.btnNext);
        nextbutton.setVisibility(View.INVISIBLE);




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
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }

        imgCapture.setVisibility(View.VISIBLE);
        final sample s = (sample) getIntent().getSerializableExtra("SampleObject");


        d("previewCapturedImage", "before colorutils ");
        imgCapture.setOnTouchListener(new View.OnTouchListener() {
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
                int pixel = bp.getPixel(x, y);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                pixelColor = pixel;
                hexPixelColor = "Does it work?";
                hexPixelColor = String.format("#%06X", (0xFFFFFF & pixel));
                nextbutton.setVisibility(View.VISIBLE);
                nextbutton.setBackgroundColor(pixelColor);
                return true;
            }

        });

        nextbutton = findViewById(R.id.btnNext);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.setColour2(hexPixelColor);
                s.setC2(pixelColor);
                //Toast.makeText(Activity2Camera.this, "Sample obj contents"+s.getSamplename()+" "+s.getColour1()+" "+s.getC1()+" "+s.getColour2()+" "+s.getC2(), Toast.LENGTH_LONG).show();
                final Intent i =new Intent(Activity2Camera.this,ActivityStorage.class);
                Bundle mBundle = new Bundle();
                String val;
                if(s.getStat()){val="CONSUMABLE";}
                else{val="NOT CONSUMABLE";}
                mBundle.putSerializable("SampleObject",s);
                i.putExtras(mBundle);
                final AlertDialog.Builder builder = new AlertDialog.Builder(Activity2Camera.this);
                builder.setMessage("The Sample "+s.getSamplename()+" is "+val).setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {

                                //finish();
                                startActivity(i);

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("DONE");
                alert.show();

//                startActivity(i);

            }
        });


    }
}
