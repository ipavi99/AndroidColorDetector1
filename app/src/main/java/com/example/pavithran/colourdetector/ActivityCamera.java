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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.Log.d;

public class ActivityCamera extends AppCompatActivity {
    private Button btnCapture;
    private ImageView imgCapture;
    Button nextbutton;
    Bitmap bp;
    String hexPixelColor;
    int pixelColor;
    String sname;
    int j=0;
    private static final int Image_Capture_Code = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        nextbutton = findViewById(R.id.btnNext);
        nextbutton.setVisibility(View.INVISIBLE);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar1);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuitem = menu.getItem(1);
        menuitem.setChecked(true);


        btnCapture =(Button)findViewById(R.id.btnTakePicture);
        imgCapture = (ImageView) findViewById(R.id.capturedImage);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ActivityCamera.this);
                View mView = getLayoutInflater().inflate(R.layout.sample_name, null);
                final EditText sampleName = (EditText)mView.findViewById(R.id.etS_name);
                Button button_done = (Button)mView.findViewById(R.id.butt_done);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                button_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ActivityCamera.this, "Hi hello welcome", Toast.LENGTH_SHORT).show();

                        if(!sampleName.getText().toString().isEmpty())
                        {
                            sname = sampleName.getText().toString();
                            dialog.hide();
                            Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cInt,Image_Capture_Code);
                        }
                        else
                        {
                            Toast.makeText(ActivityCamera.this, "Please enter the name of your sample", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Toast.makeText(ActivityCamera.this, "Inside the ItemSelectedListener", Toast.LENGTH_SHORT).show();


                switch(item.getItemId()){

                    case R.id.ic_home:
                        Intent intent0 = new Intent(ActivityCamera.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_camera:
                        break;

                    case R.id.ic_storage:

                        Toast.makeText(ActivityCamera.this, "Selected 2nd one", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(ActivityCamera.this,ActivityStorage.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                        finish();

                        break;

                }

                return false;
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

        d("previewCapturedImage", "before colorutils ");
        Toast.makeText(ActivityCamera.this,"hooo", Toast.LENGTH_SHORT).show();
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
                int pixel = bp.getPixel(x,y);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                pixelColor = pixel;
                hexPixelColor = "Does it work?";
                hexPixelColor = String.format("#%06X", (0xFFFFFF & pixel));
                nextbutton.setVisibility(View.VISIBLE);
                nextbutton.setBackgroundColor(pixelColor);
                j=1;
                return true;
            }

        });



        nextbutton = findViewById(R.id.btnNext);


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sample sampleObject = new sample();
                sampleObject.setSamplename(sname);
                sampleObject.setColour1(hexPixelColor);
                sampleObject.setC1(pixelColor);
                Intent i = new Intent(ActivityCamera.this,SelectActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putSerializable("SampleObject",sampleObject);
                i.putExtras(mBundle);
                startActivity(i);

            }
        });




//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar1);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                Toast.makeText(ActivityCamera.this, "Inside the ItemSelectedListener", Toast.LENGTH_SHORT).show();
//
//
//                switch(item.getItemId()){
//
//                    case R.id.ic_home:
//                        Intent intent0 = new Intent(ActivityCamera.this,MainActivity.class);
//                        startActivity(intent0);
//                        break;
//
//                    case R.id.ic_camera:
//                        break;
//
//                    case R.id.ic_storage:
//
//                        Toast.makeText(ActivityCamera.this, "Selected 2nd one", Toast.LENGTH_SHORT).show();
//                        Intent intent2 = new Intent(ActivityCamera.this,ActivityStorage.class);
//                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent2);
//                        finish();
//
//                        break;
//
//                }
//
//                return false;
//            }
//        });
    }


}
