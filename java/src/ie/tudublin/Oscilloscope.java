package ie.tudublin;

import ddf.minim.ugens.*; // UGens are classes that can be used to create audio signals
import ddf.minim.*;
import processing.core.PApplet;

public class Oscilloscope extends PApplet {
    public void settings() {
        size(1024, 1024, P3D);
    }

    public void setup() {
        colorMode(HSB);
    }

    public void draw() {
    }
}
