package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
	// PApplet methods
	ArrayList<Star> stars = new ArrayList<Star>();
	Star[] selectedStars = new Star[2];

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

	private int selectedStarsIndex = 0;
	public void mouseClicked()
	{
		circle(mouseX, mouseY, 10);
		// For each star, check if mouse is hovering over it
		for (Star star : stars) {
			float border = width * 0.1f;

			// Get the x and y of the star and distance from mouse
			float x = map(star.getXg(), -5, 5, border, width - border);
			float y = map(star.getYg(), -5, 5, border, height - border);
			float dist = dist(mouseX, mouseY, x, y);

			// If mouse is hovering over star
			if (dist < star.getAbsMag()/2) {
				// Reset if at the end
				if (selectedStarsIndex >= 2) {
					selectedStarsIndex = 0;
					selectedStars = new Star[2];
				}

				// Add star to selected
				selectedStars[selectedStarsIndex++] = star;
			}
		}
	}

	//

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


	public void draw()
	{
		background(0);
		strokeWeight(2);

		drawGrid();

		renderStars();

	}

	// StarMap methods

}
