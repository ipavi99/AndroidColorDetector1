package com.example.pavithran.colourdetector;

import java.io.Serializable;

public class sample implements Serializable {

    private String samplename;
    private int c1;
    private int c2;
    private String colour1;
    private String colour2;

    public sample() {
        samplename = "";
        colour1 = "";
        colour2 = "";
        c1=0;
        c2=0;
    }

    public sample(String samplename, String colour1,int c1, String colour2, int c2) {
        this.samplename = samplename;
        this.c1 = c1;
        this.c2 = c2;
        this.colour1 = colour1;
        this.colour2 = colour2;
    }

    public int getC1() { return c1; }

    public void setC1(int c1) { this.c1 = c1; }

    public int getC2() { return c2; }

    public void setC2(int c2) { this.c2 = c2; }

    public String getSamplename() {
        return samplename;
    }

    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    public String getColour1() {
        return colour1;
    }

    public void setColour1(String colour1) {
        this.colour1 = colour1;
    }

    public String getColour2() {
        return colour2;
    }

    public void setColour2(String colour2) {
        this.colour2 = colour2;
    }
}
