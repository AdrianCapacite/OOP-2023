package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet
{

	LifeBoard board;
	public void settings()
	{
		size(1024, 1024);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		board = new LifeBoard(50, this);
		board.randomise();
	}

	public void draw()
	{
		background(0);
		board.render();
		board.applyRules();

	}
}
