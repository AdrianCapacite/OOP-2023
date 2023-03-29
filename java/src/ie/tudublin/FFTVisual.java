package ie.tudublin;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.core.PFont;

public class FFTVisual extends PApplet {
    // Minim stuff
    Minim minim;
    AudioPlayer ap;
    FFT fftLin;
    FFT fftLog;
    PFont font;

    float height3;
    float height23;
    float spectrumScale = 4;

    public void settings() {
        size(1024, 1024);
    }

    public void setup() {
        // Minim stuff
        minim = new Minim(this);
        ap = minim.loadFile("Toto - Hold The Line.wav", 1024);
        ap.play();

        fftLin = new FFT(ap.bufferSize(), ap.sampleRate());
        fftLin.linAverages(30); // 30 bands

        fftLog = new FFT(ap.bufferSize(), ap.sampleRate());
        // logAverages(int minBandwidth, int bandsPerOctave)
        // fftLog.logAverages(22, 3); // 22 Hz, 3 bands per octave, 30 bands
        fftLog.logAverages(60, 3);

        // Processing stuff
        height3 = height / 3;
        height23 = height3 * 2;
        colorMode(RGB);
        background(0);
        font = createFont("Arial", 16);
        rectMode(CORNERS);
    }

    // TODO - Fix width of lin and log averages
    public void draw() {
        background(0);

        textFont(font);
        textSize(18);

        float centerFrequency = 0;

        // perform a forward FFT on the samples in jingle's mix buffer
        // note that if jingle were a MONO file, this would be the same as using
        // jingle.left or jingle.right
        fftLin.forward(ap.mix);
        fftLog.forward(ap.mix);

        // draw the full spectrum
        {
            // noFill();
            int w = (int) (width * 2 / fftLin.specSize());
            for (int i = 0; i < fftLin.specSize(); i++) {
                // if the mouse is over the spectrum value we're about to draw
                // set the stroke color to red
                if (i == mouseX) {
                    centerFrequency = fftLin.indexToFreq(i);
                    stroke(255, 0, 0);
                } else {
                    stroke(255);
                }

                rect(i * w, height3, i * w + w, height3 - fftLin.getBand(i) * spectrumScale);
            }

            fill(255, 128);
            text("Spectrum Center Frequency: " + centerFrequency, 5, height3 - 25);
        }

        // no more outline, we'll be doing filled rectangles from now
        noStroke();

        // draw the linear averages
        {
            // since linear averages group equal numbers of adjacent frequency bands
            // we can simply precalculate how many pixel wide each average's
            // rectangle should be.
            int w = (int) (width / fftLin.avgSize());
            for (int i = 0; i < fftLin.avgSize(); i++) {
                // if the mouse is inside the bounds of this average,
                // print the center frequency and fill in the rectangle with red
                if (mouseX >= i * w && mouseX < i * w + w) {
                    centerFrequency = fftLin.getAverageCenterFrequency(i);

                    fill(255, 128);
                    text("Linear Average Center Frequency: " + centerFrequency, 5, height23 - 25);
                    // Index
                    text("Band index: " + i, 5, height23 - 50);

                    fill(255, 0, 0);
                } else {
                    fill(255);
                }
                // draw a rectangle for each average, multiply the value by spectrumScale so we
                // can see it better
                rect(i * w, height23, i * w + w, height23 - fftLin.getAvg(i) * spectrumScale);
            }
        }

        // draw the logarithmic averages
        {
            // since logarithmically spaced averages are not equally spaced
            // we can't precompute the width for all averages
            for (int i = 0; i < fftLog.avgSize(); i++) {
                centerFrequency = fftLog.getAverageCenterFrequency(i);
                // how wide is this average in Hz?
                float averageWidth = fftLog.getAverageBandWidth(i);

                // we calculate the lowest and highest frequencies
                // contained in this average using the center frequency
                // and bandwidth of this average.
                float lowFreq = centerFrequency - averageWidth / 2;
                float highFreq = centerFrequency + averageWidth / 2;

                // freqToIndex converts a frequency in Hz to a spectrum band index
                // that can be passed to getBand. in this case, we simply use the
                // index as coordinates for the rectangle we draw to represent
                // the average.
                int xl = (int) fftLog.freqToIndex(lowFreq) * 2;
                int xr = (int) fftLog.freqToIndex(highFreq) * 2;

                // if the mouse is inside of this average's rectangle
                // print the center frequency and set the fill color to red
                if (mouseX >= xl && mouseX < xr) {
                    fill(255, 128);
                    text("Logarithmic Average Center Frequency: " + centerFrequency, 5, height - 25);
                    text("Band index: " + i, 5, height - 50);
                    text("Band value: " + fftLog.getAvg(i), 5, height - 75);
                    fill(255, 0, 0);
                } else {
                    fill(255);
                }
                // draw a rectangle for each average, multiply the value by spectrumScale so we
                // can see it better
                rect(xl, height, xr, height - fftLog.getAvg(i) * spectrumScale);



            }
        }
    }
}
