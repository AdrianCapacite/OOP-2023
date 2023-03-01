package ie.tudublin;

public class PerlinLines extends Base {
    int lines;
    public void settings() {
        int length = 1024;
        size(length, length, P3D);
    }

    public void setup() {
        colorMode(HSB);
        background(0);
        frameRate(60);
        surface.setTitle("Perlin Lines");

        lines = 50;
    }

    public void draw() {
        background(0);
        text(frameCount * 0.01f, 10, 10);
        stroke(255);
        strokeWeight(2);
        noFill();
        for (int i = 0; i < lines; i++) {
            float x1 = constrain(map(i,0,lines/2,0,width), 0, width);
            float y1 = constrain(map(i,lines/2,lines,0,height), 0, height);
            float x2 = constrain(map(i,lines/2,lines,0,width), 0, width);
            float y2 = constrain(map(i,0,lines/2,0,height), 0, height);
            beginShape();
            int sections = (int) dist(x1, y1, x2, y2)/10;
            float scale = 0.0005f; // Smaller number = more detail
            float timeMod = 0.005f;
            for (int j = 0; j < sections; j++) {
                float x = map(j, -1, sections, x1, x2);
                float y = map(j, -1, sections, y1, y2);
                float displacement = map(noise(x * scale, y * scale, frameCount * timeMod), 0, 1, -500, 500);
                float mouseDist = dist(mouseX, mouseY, x, y);
                y += displacement;
                // y += displacement * constrain(1 - mouseDist/200,0,1);
                curveVertex(x, y);
            }
            endShape();
        }
    }
}
