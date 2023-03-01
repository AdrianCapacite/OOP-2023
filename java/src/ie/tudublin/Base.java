package ie.tudublin;

import processing.core.PApplet;

 public class Base extends PApplet {
    int hue;

    public void settings() {
        size(1024, 1024, P3D);
    }

    public void setup() {
        colorMode(HSB);
        background(0);
        surface.setTitle("Base PApplet");
    }

    public void draw() {
        ortho();
        // Make trail effect
        pushMatrix();
        translate(0,0,-1000);
        fill(0,25);
        rect(0, 0, width, height);
        popMatrix();

        // Rotating box
        pushMatrix();

        stroke(frameCount % 256, 255, 255);
        noFill();
        translate(width / 2, height / 2);
        rotateX(radians(frameCount/2));
        rotateY(radians(frameCount/2));
        rotateZ(radians(frameCount/2));
        box(height/2);

        popMatrix();
    }
}
