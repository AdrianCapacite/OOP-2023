package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet {
	// PApplet methods =========================================================
	public void settings() {
		size(800, 800);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		smooth();
		loadStars();
		printStars();
	}

	public void draw() {
		background(0);
		strokeWeight(2);
		drawGrid();
		renderStars();
		// Display information
	}

	public void mouseClicked() {
		selectStar(mouseX, mouseY);
	}

	// StarMap methods and variables ===========================================
	ArrayList<Star> stars = new ArrayList<Star>();
	Star[] selectedStars = new Star[2];
	private int selectedStarsIndex = 0;
	float border = width * 0.1f;

	/**
	 * Selects a star if point(x, y) are within the bounds of the star
	 *
	 * @param x
	 * @param y
	 */
	public void selectStar(float x, float y) {
		for (Star star : stars) {
			// Get distance between point and star
			float starX = map(star.getXg(), -5, 5, border, width - border);
			float starY = map(star.getYg(), -5, 5, border, height - border);
			float dist = dist(x, y, starX, starY);

			// If point is within the bounds of the star
			if (dist < star.getAbsMag() / 2) {
				if (selectedStarsIndex >= 2) {
					selectedStarsIndex = 0;
					selectedStars = new Star[2];
				}
				selectedStars[selectedStarsIndex++] = star;
			}
		}
	}

	public void renderStars() {
		for (Star s : stars) {
			s.render(this);
		}
	}

	void printStars() {
		for (Star star : stars) {
			println(star);
		}
	}

	public void loadStars() {
		Table table = loadTable("HabHYG15ly.csv", "header");
		for (TableRow tr : table.rows()) {
			Star s = new Star(tr);
			stars.add(s);
		}
	}

	public void drawGrid() {
		stroke(255, 0, 255);
		strokeWeight(1);
		float border = width * 0.1f;

		for (int i = -5; i <= 5; i++) {
			float x = map(i, -5, 5, border, width - border);
			line(x, border, x, height - border);
			line(border, x, width - border, x);

			textAlign(CENTER, CENTER);
			text(i, border - 20, x);
			text(i, x, border - 20);
		}
	}
}
