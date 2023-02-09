package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {
	// Constants
	// final HSB RED = new HSB(0, 255, 255);
	// final HSB CYAN = new HSB(255*180/360, 255, 255);
	// final HSB BLACK = new HSB(0, 0, 0);
	int red;
	int cyan;
	int black;
	int stars[][] = new int[50][5];


	// HoverBoxes
	HoverBox[] hoverBoxes = new HoverBox[7];

	int mode = 0;
	int submode = 0;

	public void settings() {
		// fullScreen();
		size(1000, 1000);
	}

	public void setup() {
		colorMode(HSB,360,100,100);
		this.red = color(0, 100, 100);
		this.cyan = color(180, 100, 100);
		this.black = color(0, 0, 0);

		// this.width = 1000;
		// this.height = 1000;
		// Mode 1.1
		this.hoverBoxes[0] = new HoverBox(0, 0, width/2, height, black, cyan);
		this.hoverBoxes[1] = new HoverBox(width/2, 0, width/2, height, black, cyan);

		// Mode 1.2
		this.hoverBoxes[2] = new HoverBox(0, 0, width/2, height/2, black, cyan);
		this.hoverBoxes[3] = new HoverBox(0, height/2, width/2, height/2, black, cyan);
		this.hoverBoxes[4] = new HoverBox(width/2, 0, width/2, height/2, black, cyan);
		this.hoverBoxes[5] = new HoverBox(width/2, height/2, width/2, height/2, black, cyan);

		// Mode 1.3
		this.hoverBoxes[6] = new HoverBox(width/3, 2*height/5, width/3, height/5, cyan, red);

		// Generate random points, sizes, and colors for each star
		for (int i = 0; i < stars.length; i++) {
			stars[i] = new int[] {
				(int) random(0, width),
				(int) random(0, height),
				(int) random(20, 100),
				(int) random(5, 8),
				color(random(0, 360), 100, 100)
			};
		}

	}


	public void keyPressed() {
		mode = key - '0';
		println(mode);
	}

	public void mouseClicked() {
		this.submode = (this.submode + 1) % 3;
		println(submode);
	}


	public void draw() {
		background(0);
		fill(255);
		noStroke();


		switch (mode) {
			// Show if mouse is hovering over boxes
			case 0: {
				switch (submode) {
					case 0: {
						this.hoverBoxes[0].render();
						this.hoverBoxes[1].render();
						break;
					}
					case 1: {
						this.hoverBoxes[2].render();
						this.hoverBoxes[3].render();
						this.hoverBoxes[4].render();
						this.hoverBoxes[5].render();
						break;
					}
					case 2: {
						this.hoverBoxes[6].render();
						break;
					}
				}
				break;
			}
			// Rainbow
			case 1: {
				for (int i = 0; i < 10; i++) {
					fill(360/10*i, 100, 100);
					rect(width/10*i, 0, width/10, height);
				}
				break;
			}
			// Rainbow cross
			case 2: {
				for (int i = 0; i < 10; i++) {
					fill(360/10*i, 100, 100);
					square(width/10*i, height/10*i, width/10);
					square(width/10*(9-i), height/10*i, width/10);
				}
				break;
			}
			// Rainobow circle
			case 3: {
				for (int i = 0; i < 10; i++) {
					fill(360/10*(9-i), 100, 100);
					circle(width/2, height/2, width/10*(10-i));
				}
				break;
			}
			// Rainbow circle grid
			case 4: {
				background(0,0,100);
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						fill(18 * i + 18 * j, 100, 100);
						circle(width/10*i + width/20, height/10*j + height/20, width/10);
					}
				}
				break;
			}
			// Green Grid
			case 5: {
				for (int i = 0; i < 11; i++) {
					fill(0,0,100);
					stroke(120, 100, 100);
					textAlign(CENTER,CENTER);
					textSize(50);

					// Rows
					text(i-5, width/12*i + width/12, height/12/2);
					line(width/12, height/12*i + height/12, width/12*11, height/12*i + height/12);

					// Columns
					text(i-5, width/12/2, height/12*i + height/12);
					line(width/12*i + width/12, height/12, width/12*i + width/12, height/12*11);
				}
				break;
			}
			// Checkerboard
			case 6: {
				int brightness = 1;
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						fill(240, 100, 50 + 50*brightness);
						brightness = (brightness + 1) % 2;
						square(width/20*j, height/20*i, width/20);
					}
					brightness = (brightness + 1) % 2;
				}
				break;
			}
			// Five lines
			case 7: {
				background(0,0,100);
				stroke(0,0,0);
				for (int i = 0; i < 5; i++) {
					float angle = TWO_PI/5*i - TWO_PI/4;
					line(width/2, height/2, width/2 + 100*cos(angle), height/2 + 100*sin(angle));
				}
				break;
			}
			// Polygons
			case 8: {
				stroke(0,0,100);
				drawPolygon(width/2, height/2, 100, round(20*mouseX/width) + 3);
				// for (int i = 0; i < 5; i++) {
				// 	float angle1 = TWO_PI/5*i - TWO_PI/4;
				// 	float angle2 = TWO_PI/5*(i+1) - TWO_PI/4;
				// 	line(width/2 + 100*cos(angle1), height/2 + 100*sin(angle1), width/2 + 100*cos(angle2), height/2 + 100*sin(angle2));
				// }
				break;
			}
			case 9: {
				// drawStar(width/2, height/2, 100, 50, 5);
				for (int i = 0; i < stars.length; i++) {
					stroke(stars[i][4]);
					drawStar(stars[i][0], stars[i][1], stars[i][2], stars[i][2]/2, stars[i][3]);
				}
				break;
			}
		}


	}

	/**
	 * Draws a polygon with the given number of sides and radius
	 * @param x center x
	 * @param y center y
	 * @param r radius
	 * @param s number of sides
	 */
	public void drawPolygon(int x, int y, int r, int s) {
		for (int i = 0; i < s; i++) { //draws a line between each pair of points
			float angle1 = TWO_PI/s*i - TWO_PI/4;
			float angle2 = TWO_PI/s*(i+1) - TWO_PI/4;
			line(x + r*cos(angle1), y + r*sin(angle1), x + r*cos(angle2), y + r*sin(angle2));
		}
	}

	/**
	 * Draws a star with the given number of points and radius
	 * @param x center x
	 * @param y center y
	 * @param ri radius inner
	 * @param ro radius outer;
	 * @param s number of points
	 */
	public void drawStar(int x, int y, int ro, int ri, int s) {
		for (int i = 0; i < s; i++) {
			float angle1 = TWO_PI/s*i - TWO_PI/4;
			float angle2 = TWO_PI/s*(i+0.5f) - TWO_PI/4;
			float angle3 = TWO_PI/s*(i+1) - TWO_PI/4;
			line(x + ro*cos(angle1), y + ro*sin(angle1), x + ri*cos(angle2), y + ri*sin(angle2));
			line(x + ri*cos(angle2), y + ri*sin(angle2), x + ro*cos(angle3), y + ro*sin(angle3));
		}
	}

	class HoverBox {
		float x;
		float y;
		float w;
		float h;
		int color;
		int hoverColor;

		HoverBox(float x, float y, float w, float h, int color, int hoverColor) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.color = color;
			this.hoverColor = hoverColor;
		}

		void render() {
			fill(isHovering() ? this.hoverColor : this.color);
			rect(x, y, w, h);
		}

		boolean isHovering() {
			return mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h;
		}
	}
}
