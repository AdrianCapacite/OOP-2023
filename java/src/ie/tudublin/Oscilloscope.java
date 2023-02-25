package ie.tudublin;

import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.AudioOutput; // AudioOutput is a class in Minim that writes to the speakers
import ddf.minim.ugens.*; // UGens are classes that can be used to create audio signals
import processing.core.PApplet;

public class Oscilloscope extends PApplet {
    Minim minim; // Minim is a library for audio processing
    AudioPlayer ap; // AudioPlayer is a class in Minim that reads from a file
    AudioBuffer ab; // AudioBuffer is a class in Minim that stores the audio samples
    // Time buffer, represents the time domain relative to audio buffer
    float[] tb;
    AudioOutput out; // AudioOutput is a class in Minim that writes to the speakers
    Oscil waveL; // Oscil is a class in Minim that can be used to create a sine wave
    Oscil waveR; // Oscil is a class in Minim that can be used to create a sine wave
    Balance bal; // Balance is a class in Minim that can be used to mix two audio signals

    public void settings() {
        size(1024, 1024, P3D);
    }

    public void setup() {
        minim = new Minim(this);

        out = minim.getLineOut(Minim.MONO, width, 44100, 16);

        // ap = minim.loadFile("345Hz.wav");
        // ap.play();
        // ab = ap.mix; // mix is a method in AudioPlayer that returns an AudioBuffer

        // wave = new Oscil(345, 0.5f, Waves.SINE);
        // Wave with sine on one channel and saw on the other

        waveL = new Oscil(345, 0.5f, Waves.SINE);
        waveR = new Oscil(345, 0.5f, Waves.SAW);

        // Mix the two waves together to stereo
        





        ab = out.mix;

        colorMode(HSB);
    }

    public void draw() {
        waveL.setFrequency(map(mouseX, 0, width, 20, 500));
        background(0);
        stroke(255);
        noFill();
        drawWave();
    }

    public void keyPressed()
    {
    switch( key )
    {
        case '1':
        waveL.setWaveform( Waves.SINE );
        break;

        case '2':
        waveL.setWaveform( Waves.TRIANGLE );
        break;

        case '3':
        waveL.setWaveform( Waves.SAW );
        break;

        case '4':
        waveL.setWaveform( Waves.SQUARE );
        break;

        case '5':
        waveL.setWaveform( Waves.QUARTERPULSE );
        break;

        default: break;
    }
    }

    void drawWave() {
        // Add bloom effect


        beginShape();
        for (int i = 0; i < ab.size(); i++) {
            float x = map(i, 0, ab.size(), 0, width);
            float y = map(ab.get(i), -1, 1, height, 0);
            vertex(x, y);
        }
        endShape();
    }

    void drawWave2() {
        // Add bloom effect
        OscilXY oscilXY = new OscilXY(this, ab);
        oscilXY.render();
    }
}

/**
 * Simulates an oscilloscope with two channels, one for the X axis and one for the Y axis.
 * Takes in an AudioBuffer and draws the waveform into PApplet.
 */
class OscilXY {
    AudioBuffer x;
    AudioBuffer y;
    PApplet p;

    OscilXY(PApplet p, AudioBuffer ab) {
        this.p = p;
        this.ab = ab;
    }

    void render() {
        p.beginShape();
        p.endShape();
    }


}