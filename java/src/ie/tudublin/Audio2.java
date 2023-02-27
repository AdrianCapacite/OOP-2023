package ie.tudublin;

import processing.core.PApplet;
// import ddf.minim.*;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

/**
 * Class notes:
 *  - Abstract class = class that cannot be instantiated but can be extended
 *      - E.g PApplet
 */
public class Audio2 extends PApplet{
    Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;

    public void settings()
    {
        size(1024, 1024, P3D);
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.STEREO, width, 44100, 16);
        ab = ai.mix; // get the mixed audio buffer

    }

    public void setup()
    {
         colorMode(HSB);
         background(0);
    }

    public void draw()
    {
        background(0);
        stroke(255);
        float half = height / 2;
        for (int i = 0; i < ab.size(); i++) {
            line(i,half, i, half + ab.get(i) * half);
        }
    }

    /**
     * Map a value from one range to another range
     * Example:
     *  map1(5, 0, 10, 0, 100) = 50
     *  0 -> 0
     *  1 -> 10
     *  2 -> 20
     *  3 -> 30
     *  4 -> 40
     *  5 -> 50
     *  6 -> 60
     *  7 -> 70
     *  8 -> 80
     *  9 -> 90
     * 10 -> 100
     * @param value
     * @param start1
     * @param stop1
     * @param start2
     * @param stop2
     * @return
     */
    float map1(float value, float start1, float stop1, float start2, float stop2) {
        // Get the percent of the value between start1 and stop1
        // Example:
        //   map1(5, 0, 10, 0, 100) = 50
        //   percent = 0.5 = (5 - 0) / (10 - 0)
        //   result = 50 = 0 + (100 - 0) * 0.5
        float percent = (value - start1) / (stop1 - start1);
        float result = start2 + (stop2 - start2) * percent;

        return result;
    }
}
