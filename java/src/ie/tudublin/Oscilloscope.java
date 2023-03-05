package ie.tudublin;

import ddf.minim.ugens.*; // UGens are classes that can be used to create audio signals

import javax.xml.crypto.dsig.Transform;

import ddf.minim.*;
import processing.core.PApplet;

public class Oscilloscope extends PApplet {
    int channelOffset = 0;
    int samples = 3200;
    Minim minim;
    AudioInput ai;
    AudioPlayer ap;

    AudioBuffer abMix;
    AudioBuffer abLeft;
    AudioBuffer abRight;

    Lerped amplitudeLerped;

    public void settings() {
        size(1024, 1024, P3D);
    }

    public void setup() {
        // PApplet
        colorMode(HSB, 360, 100, 100, 100);
        background(0);

        // Minim
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.STEREO, 4410, 44100, 16); // Stereo, buffer size, sample rate, bit depth
        ap = minim.loadFile("Circles.wav", 3200 + channelOffset);

        ap.play();
        abMix = ap.mix;
        abLeft = ap.left;
        abRight = ap.right;

        amplitudeLerped = new Lerped(0.1f);

    }

    public void draw() {
        // Background
        float avgAmplitude = lerpAvgAmpl(abMix);

        // Drawing

        blendMode(BLEND);
        fill(0, 0, 0, 50);
        noStroke();
        rect(0, 0, width, height);

        rectMode(CORNER);
        fill(0, 100, 100, 100);
        // rect(0, 0, 100, height * avgAmplitude * 10);

        fill(0, 0, 0, 100);
        stroke(0, 100, 100, 100);
        // rect(200, 10, 400, 120);
        fill(0, 100, 100, 100);
        textAlign(LEFT, TOP);
        textSize(100);
        // text(frameRate, 200, 10);

        translate(width / 2, height / 2);

        renderOsci(abMix, abLeft, width / 2);

    }

    public void renderOsci(AudioBuffer x, AudioBuffer y, float scale) {
        float[] samples = x.toArray();
        float[] samples2 = y.toArray();

        shearX(-(PI * 32 / 128));
        blendMode(ADD);
        stroke(142, 86, 85/4, 100);
        strokeWeight(1);
        noFill();
        // beginShape();
        for (int i = 0; i < samples.length; i++) {
            // vertex(samples[i] * scale, samples2[i] * scale);
            // point(samples[constrain(i + channelOffset / 2, 0, samples.length - 1)] * scale, samples2[constrain(i - channelOffset /2, 0, samples.length - 1)] * scale);
            fuzzyPoint(samples[constrain(i + channelOffset / 2, 0, samples.length - 1)] * scale, samples2[constrain(i - channelOffset /2, 0, samples.length - 1)] * scale, 1, 16);
        }
        // endShape();
    }

    public void fuzzyPoint(float x, float y, float sd, int numPoints) {
        for (int i = 0; i < numPoints; i++) {
            float angle = random(TWO_PI);

            float val = randomGaussian();
            float distance = val * sd;  // Scale the gaussian random number by standard deviation and means);

            float dx = cos(angle) * distance;
            float dy = + sin(angle) * distance;
            float x1 = x * 2 + dx + dy * (PI * 32 / 128);
            float y1 = y + dy;
            point(x1 , y1);
        }
    }


    private float lerpAvgAmpl(AudioBuffer ab) {
        float[] samples = ab.toArray();
        float sum = 0;
        for (int i = 0; i < samples.length; i++) {
        // for (int i = 0; i < 1048; i++) {
            // sum += ab.get(i);
            sum += samples[i];
        }
        amplitudeLerped.setTarget(sum / samples.length);
        amplitudeLerped.update();
        return amplitudeLerped.getLerped();
    }

    private class Lerped {
        float lerped;
        float target;
        float speed;

        public Lerped(float speed) {
            this.speed = speed;
        }

        public void update() {
            lerped = lerp(lerped, target, speed);
        }

        public void setTarget(float target) {
            this.target = target;
        }

        public float getLerped() {
            return lerped;
        }
    }


}
