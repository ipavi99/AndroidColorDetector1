package com.example.pavithran.colourdetector;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuitem = menu.getItem(0);
        menuitem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.ic_home:
                        break;

                    case R.id.ic_camera:
                        Intent intent1 = new Intent(MainActivity.this,ActivityCamera.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_storage:
                        Intent intent2 = new Intent(MainActivity.this,ActivityStorage.class);
                        startActivity(intent2);

                        break;

                }

                return false;
            }
        });

    }
}
