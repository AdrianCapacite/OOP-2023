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
		background(0);
	}

	public void draw()
	{
		noFill();
		noStroke();
		strokeWeight(10);
		stroke(255);
		fill(255,0,255);
		line(10, 10, 100, 100); // x1, y1, x2, y2
		circle(300, 300, 50); // x, y, radius
		rect(400, 200, 400, 200); // x, y, width, height
		triangle(100, 100, 200, 100, 150, 200); // x1, y1, x2, y2, x3, y3
	}
}
