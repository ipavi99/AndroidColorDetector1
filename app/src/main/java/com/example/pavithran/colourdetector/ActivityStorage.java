package com.example.pavithran.colourdetector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityStorage extends AppCompatActivity {

    ImageView image;
    public static final String mysample = "MySample";
    private List<sample>sList = new ArrayList<>();
    RecyclerView recyclerView;
    sampleadapter mAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stor);

        recyclerView=findViewById(R.id.recycler_view);
        mAdapter = new sampleadapter(sList);

        //recyclerView.LayoutManager layoutManager =


        final sample s = (sample) getIntent().getSerializableExtra("SampleObject");
        sList.add(s);
        mAdapter.notifyDataSetChanged();
//        Toast.makeText(this, s.getSamplename(), Toast.LENGTH_SHORT).show();

        recyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        samplecreate();









        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navViewBar2);
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

    private void samplecreate(){
        sample s1 = new sample("S01","nil",-255,"nil",-65280);
        sList.add(s1);

        s1 = s1 = new sample("S01","nil",-65280,"nil",-255);
        sList.add(s1);

        mAdapter.notifyDataSetChanged();
    }

}
