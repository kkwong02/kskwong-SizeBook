package com.example.sizebook;

import java.io.Serializable;

/**
 * This class stores information and allows operations on measurements. Each measurement has
 * the same (or very similar) attributes, so they are generalized into one class.
 * Additional operations to measurements, such as unit conversions, are also all very the same
 * for every measurement.
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
