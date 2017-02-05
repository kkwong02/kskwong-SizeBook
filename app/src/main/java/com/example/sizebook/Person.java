package com.example.sizebook;

import java.util.Date;

/**
 * Created by kirsten on 1/25/17.
 */

public class Person {
    private String name;
    private Date date;
    private String comment;
    private Measurement neck;
    private Measurement bust;
    private Measurement chest;
    private Measurement waist;
    private Measurement hip;
    private Measurement inseam;


    @Override
    public String toString(){
        return this.name;

    }
}
