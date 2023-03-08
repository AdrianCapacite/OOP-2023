package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet
{
	LifeBoard board;
    boolean running;
	public void settings()
	{
		size(1024, 1024);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		board = new LifeBoard(50, this);
		board.randomise();
        running = true;
	}

	public void draw()
	{
		background(0);
		board.render();

        if (running)
        {
            board.applyRules();
        }

	}

    public void keyPressed()
    {
        switch(key)
        {
            case ' ':
                running = !running;
                break;
            case '1':
                board.randomise();
                break;
            case '2':
                // board.clear();
                break;
            case '3':
                // Set corss
                break;
        }

    }
}
