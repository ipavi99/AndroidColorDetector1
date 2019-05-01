package com.example.pavithran.colourdetector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityStorage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stor);
        TextView tw = (TextView) findViewById(R.id.storagetext);
        tw.setText("Storage window");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuitem = menu.getItem(2);
        menuitem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.ic_home:
                        Intent intent0 = new Intent(ActivityStorage.this,MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_camera:
                        Intent intent1 = new Intent(ActivityStorage.this,ActivityCamera.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_storage:
                        break;

                }

                return false;
            }
        });
    }
}
