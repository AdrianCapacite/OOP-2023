package ie.tudublin;

import processing.core.PApplet;
import processing.core.PShape;

public class Horse extends PApplet {
    PShape horse;

    public void settings() {
        size(500, 500, P3D);
    }

    public void setup() {
        colorMode(HSB);
        background(0);

        horse = loadShape("frog.obj");
    }

    public void draw() {
        float scale = 20;
        scale(scale);
        background(0);
        lights();
        translate(width / (2 * scale), height / (2 * scale));
        rotateX(radians(frameCount));
        rotateY(radians(frameCount));
        rotateZ(radians(frameCount));
        shapeMode(CENTER);
        shape(horse, 0, 0);
    }
}
