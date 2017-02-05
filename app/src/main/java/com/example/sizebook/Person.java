package com.example.sizebook;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kirsten on 1/25/17.
 */

public class Person implements Serializable {
    private String name;
    private Date date;
    private String comment;
    private Measurement neck;
    private Measurement bust;
    private Measurement chest;
    private Measurement waist;
    private Measurement hip;
    private Measurement inseam;


    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
