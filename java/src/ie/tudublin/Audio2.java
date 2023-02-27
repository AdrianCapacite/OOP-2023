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
        size(1024, 1024);
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
    }
}
