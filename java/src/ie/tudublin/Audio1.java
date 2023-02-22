package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {
    Minim minim; // Minim is a library for audio processing
    AudioPlayer ap; // AudioPlayer is a class in Minim that reads from a file
    AudioInput ai; // AudioInput is a class in Minim that reads from the microphone
    AudioBuffer ab; // AudioBuffer is a class in Minim that stores the audio samples

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        size(1024, 1000, P3D);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;

        // And comment the next two lines out
        ap = minim.loadFile("heroplanet.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

    }

    float off = 0;

    public void draw() {
        // background(0);
        float halfH = height / 2; // half height of screen
        float halfW = width / 2; // half width of screen
        float average = 0; // average of the samples within the buffer
        float sum = 0; // sum of the samples within the buffer
        off += 1; // increment the offset

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        // Lerp samples
        float smoothedab[] = new float[ab.size()];
        for (int i = 0; i < ab.size(); i++) {
            smoothedab[i] = lerp(smoothedab[i], ab.get(i), 0.1f);
        }

        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
            case 0:
                background(0);
                for (int i = 0; i < ab.size(); i++) {
                    // float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = smoothedab[i] * halfH;
                    // line(i, halfH + f, i, halfH - f);
                    line(i, halfH + f, halfH - f, i);
                }
                break;
            case 1:
                background(0);
                for (int i = 0; i < ab.size(); i++) {
                    // float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = smoothedab[i] * halfH;
                    // float f = ab.get(i) * halfH;
                    line(i, halfH + f, i, halfH - f);
                }
                break;
            case 2:
                background(0);
                for (int i = 0; i < ab.size(); i++) {
                    // float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = smoothedab[i] * height;

                    // line(i, halfH + f, i, halfH - f); // Middle
                    line(i, 0 + f, i, 0 ); // Top
                    line(i, height - f, i, height ); // Bottom
                    line(0 + f, i, 0, i); // Left
                    line(width - f, i, width, i); // Left
                }
                break;
            case 3:
                background(0);
                noFill();
                stroke(smoothedAmplitude * 255, 255, 255);
                circle(halfW, halfH, smoothedAmplitude * height);
                break;
            case 4:
                background(0);
                noFill();
                stroke(smoothedAmplitude * 255, 255, 255);
                rectMode(CENTER);
                square(halfW, halfH, smoothedAmplitude * height);
                break;
            case 5:
                background(0);
                noFill();
                float pi4_10 = 2 * PI / 10;
                float mouseMap = map(mouseX, 0, width, pi4_10 - 0.010f, pi4_10 + 0.010f);
                text(mouseMap, 100, 100);
                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = smoothedab[i] * TWO_PI;
                    f += 0.1f;
                    // arc(a, b, c, d, start, stop)
                    // a, b = center of arc
                    // c, d = width and height of arc
                    // start = start angle
                    // stop = stop angle
                    // float r = ((i * off / (60 * 60) ) % 360);
                    // float r = sin(i * (off/(60*60)))/1 * PI / 2;
                    // float r = sin(i * 54.980f) / 1 * PI / 2;
                    float r = sin(i * mouseMap) / 1 * PI / 2;
                    // r += sin(off / (60*60))/1;
                    arc(halfW, halfH, i, i, r - f, r + f);
                    r += PI;
                    arc(halfW, halfH, i, i, r - f, r + f);

                }
                break;
        }

        // Other examples we made in the class
        /*
         * stroke(255);
         * fill(100, 255, 255);
         *
         * circle(width / 2, halfH, lerpedA * 100);
         *
         * circle(100, y, 50);
         * y += random(-10, 10);
         * smoothedY = lerp(smoothedY, y, 0.1f);
         * circle(200, smoothedY, 50);
         */

    }
}
