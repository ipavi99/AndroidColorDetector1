package com.example.pavithran.colourdetector;

import android.content.Context;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class sampleadapter extends RecyclerView.Adapter<sampleadapter.MyViewHolder>{

    private List<sample> sampleList;

    public sampleadapter(List<sample> sampleList) {
        this.sampleList = sampleList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_list_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        sample sample = sampleList.get(position);
        myViewHolder.samplename.setText(sample.getSamplename());
        myViewHolder.dispc1.setBackgroundColor(sample.getC2());
        myViewHolder.dispc1.setEnabled(false);
        myViewHolder.dispc2.setBackgroundColor(sample.getC1());
        myViewHolder.dispc2.setEnabled(false);
        if(sample.getStat()==true)
        {
            myViewHolder.samplestatus.setImageResource(R.drawable.ic_thumb_up);
        }
        else
        {
            myViewHolder.samplestatus.setImageResource(R.drawable.ic_thumb_down_black_24dp);
        }

    }

    @Override
    public int getItemCount() {
        return sampleList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView samplename;
        public ImageView samplestatus;
        public Button dispc1, dispc2;

        public MyViewHolder(View view){

            super(view);
            samplename = view.findViewById(R.id.sname);
            samplestatus = view.findViewById(R.id.imgThumb);
            dispc1 = view.findViewById(R.id.c1);
            dispc2 = view.findViewById(R.id.c2);
        }
    }



}
