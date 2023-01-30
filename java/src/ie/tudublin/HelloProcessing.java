package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
	}

	public void setup()
	{
		colorMode(HSB);
		background(0);
	}

	public void draw()
	{
		stroke(255);
		line(10, 10, 100, 100);
	}
}
