package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class Audio3 extends PApplet {
    Minim minim;
    AudioInput ai;
    AudioBuffer abL;
    AudioBuffer abR;
    float[] lerpedAbL;
    float[] lerpedAbR;
    FFT fft;
    PitchSpeller ps;
    float lerpFreq;

    public void settings() {
        // fullScreen(P3D);
        size(1024, 1024);
    }

    public void setup() {
        colorMode(HSB, 360, 100, 100);
        background(0);

        // Set up minim
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.STEREO, width, 44100, 16);
        abL = ai.left;
        abR = ai.right;
        lerpedAbL = new float[width];
        lerpedAbR = new float[width];
        fft = new FFT(ai.bufferSize(), ai.sampleRate());
        ps = new PitchSpeller();
    }

    public void draw() {
        background(0);
        textSize(64);
        textAlign(LEFT);

        float freq = getFundamental(abL);
        // lerp freq
        lerpFreq = lerp(lerpFreq, freq, 0.05f);

        fill(255);
        text(freq + "Hz\n" + ps.spell(freq), 100, 100);

        // Lerp the audio buffer
        for (int i = 0; i < abL.size(); i++) {
            lerpedAbL[i] = lerp(lerpedAbL[i], abL.get(i), 0.05f);
            lerpedAbR[i] = lerp(lerpedAbR[i], abR.get(i), 0.05f);
        }
        float avgL = 0;
        float avgR = 0;
        for (int i = 0; i < abL.size(); i++) {
            avgL += lerpedAbL[i];
            avgR += lerpedAbR[i];
        }
        avgL /= abL.size();
        avgR /= abR.size();

        fill(121, 100, 100);
        noStroke();
        circle(width * 2 / 5 , height / 2, avgL * width * 1000);
        circle(width * 3 / 5 , height / 2, avgR * width * 1000);


    }

    float getFundamental(AudioBuffer ab) {
        fft.forward(ab);
        int highestIndex = 0;
        for (int i = 0; i < fft.specSize() / 2; i++) {
            if (fft.getBand(i) > fft.getBand(highestIndex)) {
                highestIndex = i;
            }
        }
        float freq = fft.indexToFreq(highestIndex);
        return freq;
    }

}
