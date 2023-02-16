package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
	ArrayList<Star> stars = new ArrayList<Star>();
	public void settings()
	{
		size(800, 800);
	}

	public void setup() {
		colorMode(RGB);
		background(0);

		smooth();

		loadStars();
		printStars();
	}

	public void renderStars()
	{
		for(Star s:stars)
		{
			s.render(this);
		}
	}

	void printStars()
	{
		for (Star star : stars) {
			println(star);
		}
	}

	public void loadStars()
	{
		Table table = loadTable("HabHYG15ly.csv", "header");
		for(TableRow tr:table.rows())
		{
			Star s = new Star(tr);
			stars.add(s);
		}
	}


	public void drawGrid()
	{
		stroke(255,0,255);
		strokeWeight(1);
		float border = width * 0.1f;

		for(int i = -5 ; i <= 5 ; i ++)
		{
			float x = map(i, -5, 5, border, width - border);
			line(x, border, x, height - border);
			line(border, x, width - border, x);

			textAlign(CENTER, CENTER);
			text(i, border - 20, x);
			text(i, x, border - 20);
		}

	}

	float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c-b; // percentage of
		float r2 = e-d;
		float dist = a-b;

		return (dist/r1)*r2 + d;
	}

	public void draw()
	{
		strokeWeight(2);

		drawGrid();

		renderStars();
	}
}
