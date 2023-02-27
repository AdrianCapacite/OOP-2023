package ie.tudublin;

import ddf.minim.ugens.*; // UGens are classes that can be used to create audio signals
import ddf.minim.*;
import processing.core.PApplet;

public class Oscilloscope extends PApplet {
    Minim minim; // Minim is a library for audio processing
    AudioPlayer ap; // AudioPlayer is a class in Minim that reads from a file
    Oscil osc; // Oscil is a class in Minim that generates a sine wave
    Oscil osc2; // Oscil is a class in Minim that generates a sine wave

    AudioBuffer abL; // AudioBuffer is a class in Minim that stores the audio samples
    AudioBuffer abR; // AudioBuffer is a class in Minim that stores the audio samples

    AudioOutput out; // AudioOutput is a class in Minim that writes to the speakers
    AudioInput in; // AudioInput is a class in Minim that reads from the microphone

    OscilloscopeXY oscilloscopeXY;

    public void settings() {
        size(1024, 1024, P3D);
    }

    public void setup() {
        minim = new Minim(this);

        out = minim.getLineOut(Minim.STEREO, width, 44100, 16);
        in = minim.getLineIn(Minim.STEREO, width, 44100, 16);

        // ap = minim.loadFile("segmentationfault.wav");
        // ap.play();

        // osc = new Oscil(440, 0.5f, Waves.SINE);
        // osc2 = new Oscil(880, 0.5f, Waves.SINE);


        // osc.patch(out);

        // abL = ap.left; abR = ap.right;
        abL = in.left;
        abR = in.right;
        oscilloscopeXY = new OscilloscopeXY(this, abL, abR);

        colorMode(HSB);
    }

    public void draw() {
        background(0);
        stroke(255);
        noFill();
        oscilloscopeXY.render();
        // Average the amplitude of the left and right channels
        float avg = 0;
        for (int i = 0; i < abL.size(); i++) {
            float l = abL.get(i);
            float r = abR.get(i);
            avg = (l + r) / 2;
        }

        circle(width/2, height/2, avg * 100);
    }

}

/**
 * Simulates an oscilloscope with two channels, one for the X axis and one for the Y axis.
 * Takes in an AudioBuffer and draws the waveform into PApplet.
 */
class OscilloscopeXY {
    AudioBuffer abL;
    AudioBuffer abR;
    PApplet p;

    OscilloscopeXY(PApplet p, AudioBuffer abL, AudioBuffer abR) {
        this.p = p;
        this.abL = abL;
        this.abR = abR;
    }

    void render() {
        p.stroke(255);
        p.noFill();
        p.beginShape();
        for (int i = 0; i < 10; i++) {
            float x = p.width/2 + abL.get(i)*p.width/2;
            float y = p.height/2 + abR.get(i)*p.height/2;
            p.vertex(x, y);
            // p.circle(x, y, 100);
        }
        p.endShape();
    }


}
