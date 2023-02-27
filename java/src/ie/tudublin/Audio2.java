package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Audio2 extends PApplet{
    Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;

    public void settings()
    {
       size(1024, 1024);
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
