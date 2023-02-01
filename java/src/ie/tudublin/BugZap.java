package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet
{
	// Attributes
	private int playerX;
	private int playerY;
	private int playerWidth;

	public void settings()
	{
		// size(500, 500);
		fullScreen();
	}

	public void setup()
	{
		colorMode(HSB);
		background(0);

		this.playerX = width / 2;
		this.playerY = height - 100;

		smooth();
	}

	public void draw()
	{
		background(0);

		// drawplayer(playerX, playerY, playerWidth);
		drawPlayer(this.playerX, this.playerY, 100);


	}

	public void drawPlayer(float x, float y, float w)
	{
		float h = w/2;
		fill(255);
		stroke(255);
		strokeWeight(2);

		line(x, y, x, y - h);
		// rect(x, y, w, h);
	}

	public void keyPressed()
	{
		if (keyCode == LEFT)
		{
			System.out.println("Left");
			this.playerX -= 10;
		}
		if (keyCode == RIGHT)
		{
			System.out.println("Right");
			this.playerX += 10;
		}
		if (key == ' ')
		{
			System.out.println("Space");
		}
	}
}
