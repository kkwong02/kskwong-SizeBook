package com.example.sizebook;

import java.io.Serializable;

/**
 * Created by kirsten on 1/25/17.
 */

public class Measurement implements Serializable {
    float size;

    public Measurement(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
