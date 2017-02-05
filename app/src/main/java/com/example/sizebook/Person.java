package com.example.sizebook;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class stores the record on an individual person. Measurements are a separate class to allow
 * additional operations to measurements, such as conversions to be added later.
 *
 * implements Serializable to allow transfer of info between activities without loading from file
 * every time
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


    /**
     * Instantiates a new Person with a name. The only constructor because everything else is added
     * once the instance is created to avoid having too many constructors.
     *
     * @param name the name
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public Measurement getNeck() {
        return neck;
    }

    /**
     * Sets neck.
     *
     * @param neck the neck
     */
    public void setNeck(Measurement neck) {
        this.neck = neck;
    }

    /**
     * Gets bust.
     *
     * @return the bust
     */
    public Measurement getBust() {
        return bust;
    }

    /**
     * Sets bust.
     *
     * @param bust the bust
     */
    public void setBust(Measurement bust) {
        this.bust = bust;
    }

    /**
     * Gets chest.
     *
     * @return the chest
     */
    public Measurement getChest() {
        return chest;
    }

    /**
     * Sets chest.
     *
     * @param chest the chest
     */
    public void setChest(Measurement chest) {
        this.chest = chest;
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public Measurement getWaist() {
        return waist;
    }

    /**
     * Sets waist.
     *
     * @param waist the waist
     */
    public void setWaist(Measurement waist) {
        this.waist = waist;
    }

    /**
     * Gets hip.
     *
     * @return the hip
     */
    public Measurement getHip() {
        return hip;
    }

    /**
     * Sets hip.
     *
     * @param hip the hip
     */
    public void setHip(Measurement hip) {
        this.hip = hip;
    }

    /**
     * Gets inseam.
     *
     * @return the inseam
     */
    public Measurement getInseam() {
        return inseam;
    }

    /**
     * Sets inseam.
     *
     * @param inseam the inseam
     */
    public void setInseam(Measurement inseam) {
        this.inseam = inseam;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        // from http://stackoverflow.com/questions/6510724/how-to-convert-java-string-to-date-object
        // taken 2017/02/04
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            this.date = sdf.parse(date);
        }
        catch(ParseException e){
            throw new RuntimeException(); // shouldn't happen due to error checking
        }
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return name on the record.
     */
    @Override
    public String toString(){
        return this.name;
    }
}
