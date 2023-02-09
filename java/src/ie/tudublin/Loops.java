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
			case 1: {
				for (int i = 0; i < 10; i++) {
					fill(360/10*i, 100, 100);
					rect(width/10*i, 0, width/10, height);
				}
				break;
			}
			case 2: {
				for (int i = 0; i < 10; i++) {
					fill(360/10*i, 100, 100);
					square(width/10*i, height/10*i, width/10);
					square(width/10*(9-i), height/10*i, width/10);
				}
				break;
			}
			case 3: {
				for (int i = 0; i < 10; i++) {
					fill(360/10*(9-i), 100, 100);
					circle(width/2, height/2, width/10*(10-i));
				}
				break;
			}
			case 4: {
				background(0,0,100);
				for (int i = 0; i < 10; i++) {
					// Circle with rect
					for (int j = 0; j < 10; j++) {
						fill(360/100*(i+1)*(j+1), 100, 100);
						circle(width/10*i + width/20, height/10*j + height/20, width/10);
					}
				}
				break;
			}
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
			case 7: {
				background(0,0,100);
				stroke(0,0,0);
				for (int i = 0; i < 5; i++) {
					float angle = TWO_PI/5*i - TWO_PI/4;
					line(width/2, height/2, width/2 + 100*cos(angle), height/2 + 100*sin(angle));
				}
				break;
			}
			case 8: {
				stroke(0,0,0);
				for (int i = 0; i < 5; i++) {
					float angle1 = TWO_PI/5*i - TWO_PI/4;
					float angle2 = TWO_PI/5*(i+1) - TWO_PI/4;
					line(width/2 + 100*cos(angle1), height/2 + 100*sin(angle1), width/2 + 100*cos(angle2), height/2 + 100*sin(angle2));
				}
				break;
			}
			case 9: {
				break;
			}
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

	class HSB {
		public int h;
		public int s;
		public int b;

		HSB(int h, int s, int b) {
			this.h = h;
			this.s = s;
			this.b = b;
		}
	}
}
