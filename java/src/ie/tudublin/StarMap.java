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
		// printStars();
		border = width * 0.1f;
	}

	public void draw() {
		background(0);
		strokeWeight(2);
		drawGrid();
		renderStars();
		displayInfo();
		renderLine();
		// Display information
	}

	public void mouseClicked() {
		selectStar(mouseX, mouseY);
	}

	// StarMap methods and variables ===========================================
	ArrayList<Star> stars = new ArrayList<Star>();
	public Star[] selectedStars = new Star[2];
	private int selectedStarsIndex = 0;
	float border;

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
				return;
			}
		}
		selectedStarsIndex = 0;
		selectedStars = new Star[2];
	}

	void displayInfo() {
		textAlign(LEFT, TOP);
		String info = "X: " + mouseX + " Y: " + mouseY;

		// No stars selected
		if (selectedStars[0] == null
			&& selectedStars[1] == null) {
			info += "\n Select a star";
		}

		// Two stars selected else one star selected
		if (selectedStars[0] != null
			&& selectedStars[1] != null) {
			String star1Name = selectedStars[0].getDisplayName();
			String star2Name = selectedStars[1].getDisplayName();
			float distance = selectedStars[0].distanceTo(selectedStars[1]);
			info += "\nDistance from" + star1Name + " to " + star2Name +
					" is " + distance + " parsecs";
		} else if (selectedStars[0] != null) {
			info += "\n" + selectedStars[0].toString();
		}

		circle(border, height - border, 10);
		text(info, border, height - border);
	}

	/**
	 * Render a line between star and mouse or other star
	 */
	void renderLine() {
		float x;
		float y;
		if (selectedStars[1] != null) {
			x = map(selectedStars[1].getXg(), -5, 5, border, width - border);
			y = map(selectedStars[1].getYg(), -5, 5, border, height - border);
		} else {
			x = mouseX;
			y = mouseY;
		}
		if (selectedStars[0] != null) {
			float starX = map(selectedStars[0].getXg(), -5, 5, border, width - border);
			float starY = map(selectedStars[0].getYg(), -5, 5, border, height - border);
			line(starX, starY, x, y);
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
