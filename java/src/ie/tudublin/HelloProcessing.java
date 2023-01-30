package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup()
	{
		// colorMode(HSB);
		color(RGB);
		// background(0);
		background(255,0,0);
	}

	public void draw()
	{
		// int eyeSize = (mouseY + 250) / 50;
		int eyeSize = 1;
		noStroke();
		fill(255,255,0);
		circle(250,300,400);
		fill(0,255,255);
		triangle(250, 0, 50, 450, 450, 450); // x1, y1, x2, y2, x3, y3
		fill(255/2);
		ellipse(250, 250, 200, 100/eyeSize); // x, y, width, height
		fill(0);
		circle(250,250,80/eyeSize);
	}
}
