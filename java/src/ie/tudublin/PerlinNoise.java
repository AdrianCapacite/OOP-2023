package ie.tudublin;

import processing.core.PApplet;

public class PerlinNoise extends PApplet {
    public void settings() {
        size(500, 500, P2D);
    }

    public void setup() {
        colorMode(HSB);
        background(0);
    }

    public void draw() {
        background(0);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // noiseDetail(round(map(mouseX, 0, width, 0, 10)), map(mouseY, 0, height, 0, 1));
                float val = noise(x / 100.0f, y / 100.0f, frameCount / 100.0f);
                stroke(val * 255);
                if (x % 2 == 0 && y % 2 == 0)
                    point(x, y);
            }
        }
    }

    public static void main(String[] args) {
        processing.core.PApplet.runSketch(new String[] { "PerlinNoise" }, new PerlinNoise());
    }

}
