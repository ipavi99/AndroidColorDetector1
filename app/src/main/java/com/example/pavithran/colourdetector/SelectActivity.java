package com.example.pavithran.colourdetector;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SelectActivity extends AppCompatActivity {
    TextView mTextField;
    Button timer;
    long timeLeftInMilliS = 600000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        final sample s = (sample) getIntent().getSerializableExtra("SampleObject");



        timer = (Button)findViewById(R.id.timerStart);
        mTextField = (TextView)findViewById(R.id.mText);

        //final String message = bundle.getString("message");
        //sample s = (sample)bundle.getSerializable("sampleObject");




        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(SelectActivity.this, s.getSamplename(), Toast.LENGTH_SHORT).show();
                CountDownTimer countDownTimer = new CountDownTimer(10000,1000){


                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTextField.setText(millisUntilFinished / 60000 +": " + (millisUntilFinished / 1000)%60);
                    }

                    public void onFinish() {
                        mTextField.setText("done!");


                        Intent i = new Intent(SelectActivity.this,Activity2Camera.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("SampleObject",s);
                        i.putExtras(mBundle);
                        startActivity(i);

                    }

                }.start();

            }
        });

    }

}
