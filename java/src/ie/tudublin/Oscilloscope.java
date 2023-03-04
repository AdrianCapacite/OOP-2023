package ie.tudublin;

import ddf.minim.ugens.*; // UGens are classes that can be used to create audio signals

import javax.xml.crypto.dsig.Transform;

import ddf.minim.*;
import processing.core.PApplet;

public class Oscilloscope extends PApplet {
    Minim minim;
    AudioInput ai;
    AudioPlayer ap;

    AudioBuffer abMix;

    public void settings() {
        size(1024, 1024, P3D);
    }

    public void setup() {
        // PApplet
        colorMode(HSB);

        // Minim
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.STEREO, 44100, 44100, 16); // Stereo, buffer size, sample rate, bit depth
        ap = minim.loadFile("Circles.wav", 44100);

        ap.play();
        abMix = ap.mix;


    }

    public void draw() {
        // Background
        float avgAmplitude = getAvgAmplitude(abMix);

        // Drawing
        fill(0, 0, 0, 0.1f);
        rect(0, 0, width, height);

        rectMode(CORNER);
        rect(0, 0, 100, height * avgAmplitude/1);

        translate(width/2, height/2);


    }

    public void renderOsci(AudioBuffer x, AudioBuffer y) {

    }

    private float getAvgAmplitude(AudioBuffer ab) {
        float[] samples = ab.toArray();
        float sum = 0;
        for (int i = 0; i < samples.length; i++) {
            sum += samples[i];
        }
        return sum / samples.length;
    }
}
